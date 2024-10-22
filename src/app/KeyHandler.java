package app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	public boolean key_upPressed, key_downPressed, key_leftPressed, key_rightPressed;

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) {
			key_upPressed = true;
		}
		if(key == KeyEvent.VK_S) {
			key_downPressed = true;		
		}
		if(key == KeyEvent.VK_A) {
			key_leftPressed = true;
		}
		if(key == KeyEvent.VK_D) {
			key_rightPressed = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			key_upPressed = false;
		}
		if(key == KeyEvent.VK_S) {
			key_downPressed = false;		
		}
		if(key == KeyEvent.VK_A) {
			key_leftPressed = false;
		}
		if(key == KeyEvent.VK_D) {
			key_rightPressed = false;
		}
	}

}
