package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameEventListener implements KeyListener, MouseListener,
WindowListener {

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
	public void keyPressed(KeyEvent e) {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				System.out.println("Game is running, moving player up");
			}
			else if (e.getKeyCode() == KeyEvent.VK_S) {
				System.out.println("Game is running, moving player down ");
			}

			else if (e.getKeyCode() == KeyEvent.VK_A) {
				System.out.println("Game is running, moving player left");
			}

			else if (e.getKeyCode() == KeyEvent.VK_D) {
				System.out.println("Game is running, move player right");
			}

			else if (e.getKeyCode() == KeyEvent.VK_E) {
				System.out.println("Interacting with the bastard facing you");
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

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (GameState.getInstance().getState() == GameCondition.RUNNING) {
			if (e.getKeyCode() == KeyEvent.VK_F) {
				System.out
						.println("Aha! You pressed F, attacking the bastard facing you!");
				// Just pressed and released F, attack?
				e.consume();
			}
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

}
