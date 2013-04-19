package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameEventListener implements KeyListener, MouseListener,
WindowListener {
	private InputManager movementManager;
	private long lastTimeAttack = System.currentTimeMillis();
	private int attackTime = 500;
	private long timeElapsedAttack = attackTime;


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override

	public void keyPressed(KeyEvent e) {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {
			/*
			 * Player movement WASD
			 */
			if (e.getKeyCode() == KeyEvent.VK_W) {
				movementManager.movePlayer(GameInput.Movement.UP);
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				movementManager.movePlayer(GameInput.Movement.DOWN);
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				movementManager.movePlayer(GameInput.Movement.LEFT);
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				movementManager.movePlayer(GameInput.Movement.RIGHT);
			}
			/*
			 * Player debug commands
			 */
			else if (e.getKeyCode() == KeyEvent.VK_O) {
				// movementManager.testPlayerDamage(3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_X) {
				movementManager.giveXp();
			} else if (e.getKeyCode() == KeyEvent.VK_E) {
				movementManager.testGameOver();
			}

			/*
			 * Potions, pause, attack and interact.
			 */

			else if (e.getKeyCode() == KeyEvent.VK_I) {
				movementManager.usePotion();
			} else if (e.getKeyCode() == KeyEvent.VK_P) {
				movementManager.pauseGame();
			}
			if (timeElapsedAttack >= attackTime) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					lastTimeAttack = System.currentTimeMillis();
					timeElapsedAttack = 0;
					movementManager.attack();
				}
			} else {
				timeElapsedAttack = System.currentTimeMillis() - lastTimeAttack;
			}
		} else if (GameState.getInstance().getState() == GameCondition.PAUSED) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				movementManager.resumeGame();
			}
		} else if (GameState.getInstance().getState() == GameCondition.MENU) {
			// Input does stuff on the menu
		} else if (GameState.getInstance().getState() == GameCondition.SPLASH) {
			// What do keys do on the splashscreen?
		} else if (GameState.getInstance().getState() == GameCondition.GAMEOVER) {
			// What do keys do when game is over?
			if (e.getKeyCode() == KeyEvent.VK_R) {
				GameState.getInstance().setState(GameCondition.RUNNING);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// Do cleanup

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// Unpause

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// Pause

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// ?
	}

	public void setMovementManager(InputManager movementManager) {
		this.movementManager = movementManager;
	}
}