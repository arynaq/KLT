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
	private boolean up, down, left, right;
	long lastTime;

	public GameEventListener() {
        System.out.println("GameEventListener loaded.");
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

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_P:
			if (GameState.getInstance().getState() == GameCondition.RUNNING) {
				GameState.getInstance().setState(GameCondition.PAUSED);
			} else if (GameState.getInstance().getState() == GameCondition.PAUSED){
				GameState.getInstance().setState(GameCondition.RUNNING);
			}

			break;
		case KeyEvent.VK_X:
			if (GameState.getInstance().getState() == GameCondition.RUNNING) {
				movementManager.giveXp();
				// movementManager.tellEngineToPlayXpSound();
			}
			break;
		case KeyEvent.VK_O:
			if (GameState.getInstance().getState() == GameCondition.RUNNING) {
                // movementManager.testPlayerDamage();
			}
			break;
		case KeyEvent.VK_U:
			if (GameState.getInstance().getState() == GameCondition.RUNNING) {
                // movementManager.givePotion();
			}
			break;
        case KeyEvent.VK_E:
			if (GameState.getInstance().getState() == GameCondition.RUNNING) {
				movementManager.usePotion();
			}
			break;
		case KeyEvent.VK_SPACE:
			if (GameState.getInstance().getState() == GameCondition.RUNNING) {
                movementManager.attack();
			}
			break;
		case KeyEvent.VK_ENTER:
			if (GameState.getInstance().getState() == GameCondition.SPLASH) {
				GameState.getInstance().setState(GameCondition.RUNNING);
			}
		}
	}

	public void checkIfmoveAllowed(String type) {
		long thisTime = System.currentTimeMillis();
		if (type == "attack") {
			if ((lastTime + 600) < thisTime) {
				System.out.println("Attacking!");
				lastTime = thisTime;
			}
		} else if (type == "interact") {
			if ((lastTime + 1000) < thisTime) {
				System.out.println("Interacting!");
				lastTime = thisTime;
			}

		}
	}
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		}
	}

	public void update() {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {
			if (up == true) {
				movementManager.movePlayer(GameInput.Movement.UP);
			}
			if (down == true) {
				movementManager.movePlayer(GameInput.Movement.DOWN);
			}
			if (left == true) {
				movementManager.movePlayer(GameInput.Movement.LEFT);
			}
			if (right == true) {
				movementManager.movePlayer(GameInput.Movement.RIGHT);
			}
		}

	}
}