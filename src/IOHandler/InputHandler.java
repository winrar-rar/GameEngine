package IOHandler;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Christian Andersson.
 *
 */
public class InputHandler implements KeyListener{

	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right, esc; //public so player works.
	
	
	/**
	 * Updates the inputhandler class. Makes so that all the keys works.
	 */
	public void update(){
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		esc = keys[KeyEvent.VK_ESCAPE];
	}
	

	/**
	 * Method that checks if a key is pressed down.
	 */
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	
	}

	/**
	 * Method that checks if a key is released.
	 */
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
	
	/**
	 * Method that checks if a key has been typed. 
	 */
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
