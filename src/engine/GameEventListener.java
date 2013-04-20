package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameEventListener implements KeyListener, MouseListener,
WindowListener {
    private InputManager inputManager;
	private boolean up, down, left, right, p, o, i, x;

	public GameEventListener() {

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

    public void setMovementManager(InputManager inputManager) {
        this.inputManager = inputManager;
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
		case KeyEvent.VK_I:
			i = true;
			break;
		case KeyEvent.VK_P:
			p = true;
			break;
		case KeyEvent.VK_O:
			o = true;
			break;
		case KeyEvent.VK_X:
			x = true;
			break;
		}

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            inputManager.attack();
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
		case KeyEvent.VK_I:
			i = false;
			break;
		case KeyEvent.VK_P:
			p = false;
			break;
		case KeyEvent.VK_O:
			o = false;
			break;
		case KeyEvent.VK_X:
			x = false;
			break;
		}
	}

	public void update() {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {
			if (up == true) {
                inputManager.movePlayer(GameInput.Movement.UP);
			}
			if (down == true) {
                inputManager.movePlayer(GameInput.Movement.DOWN);
			}
			if (left == true) {
                inputManager.movePlayer(GameInput.Movement.LEFT);
			}
			if (right == true) {
                inputManager.movePlayer(GameInput.Movement.RIGHT);
			}
			if (p == true) {
                inputManager.pauseGame();
			}
			if (o == true) {
                // inputManager.testPlayerDamage();
			}
			if (i == true) {
                inputManager.usePotion();
			}
			if (x == true) {
                inputManager.giveXp();
			}
		}
		if (GameState.getInstance().getState() == GameCondition.PAUSED) {
			System.out.println("Inni dritern");
            if (p == true) {
                inputManager.resumeGame();
			}
		}
	}
}
