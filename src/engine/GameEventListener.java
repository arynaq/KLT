package engine;

import gfx.ScreenManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class GameEventListener implements KeyListener, MouseListener,
WindowListener {
	private Timer releaseTimer;
	private MovementManager movementManager;
	private ScreenManager screenManager;
	private List<KeyEvent> moves;
	private KeyEvent sisteKey;
	private long lastTimeMove = System.currentTimeMillis();
	private long lastTimeAttack = System.currentTimeMillis();
	private int attackTime = 5000;
	private int moveTime = 5;
	private long timeElapsedMove = moveTime;
	private long timeElapsedAttack = attackTime;

	public GameEventListener() {
		moves = new ArrayList<KeyEvent>();
	}

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

	public void setKey(KeyEvent e) {
		sisteKey = e;
	}

	public KeyEvent getKey() {
		return sisteKey;
	}

	@Override

	public void keyPressed(KeyEvent e) {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {
				if (e.getKeyCode() == KeyEvent.VK_W) {
				// System.out.println("Game is running, moving player up");
					movementManager.movePlayer(GameInput.Movement.UP);
				} else if (e.getKeyCode() == KeyEvent.VK_S) {

				// System.out.println("Game is running, moving player down ");
					movementManager.movePlayer(GameInput.Movement.DOWN);
				}

				else if (e.getKeyCode() == KeyEvent.VK_A) {

				// System.out.println("Game is running, moving player left");
					movementManager.movePlayer(GameInput.Movement.LEFT);
				}

				else if (e.getKeyCode() == KeyEvent.VK_D) {

					movementManager.movePlayer(GameInput.Movement.RIGHT);
				// System.out.println("Game is running, move player right");
				}

				else if (e.getKeyCode() == KeyEvent.VK_E) {
				movementManager.testPauseMovement();
				// System.out.println("Spawning a new player");
				}

			if (timeElapsedAttack >= attackTime) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					lastTimeAttack = System.currentTimeMillis();
					// System.out.println("ANGRIPER");
					timeElapsedAttack = 0;
				}
			} else {
				timeElapsedAttack = System.currentTimeMillis() - lastTimeAttack;
			}


			// Process player inputs and inputs corresponding to the gameframe
		}

		else if (GameState.getInstance().getState() == GameCondition.PAUSED) {
			// Do stuff that correspond to the pause screen
		}

		else if (GameState.getInstance().getState() == GameCondition.MENU) {
			// Input does stuff on the menu
		}

		else if (GameState.getInstance().getState() == GameCondition.SPLASH) {
			// What do keys do on the splashscreen?
		}

		else if (GameState.getInstance().getState() == GameCondition.GAMEOVER) {
			// What do keys do when game is over?

			// test
			if (e.getKeyCode() == KeyEvent.VK_R) {
				GameState.getInstance().setState(GameCondition.RUNNING);
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {

			// if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// if (timeElapsedAttack > attackTime) {
			// lastTimeAttack = System.currentTimeMillis();
			// System.out.println("ANGRIPER");
			// timeElapsedAttack = 0;
			// } else {
			// timeElapsedAttack = System.currentTimeMillis()
			// - lastTimeAttack;
			// }
			// // timeElapsed = System.currentTimeMillis()-lastTime;
			// }

		}
		// Process player inputs and inputs corresponding to the gameframe

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

	public void setMovementManager(MovementManager movementManager) {
		this.movementManager = movementManager;
	}

	public void setScreenManager(ScreenManager screenManager) {
		this.screenManager = screenManager;
	}

	class attack extends TimerTask {
		GameEventListener dritt = new GameEventListener();

		public void run() {
			releaseTimer.cancel(); // Terminate the timer thread

		}
	}

}