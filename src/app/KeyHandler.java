package app;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import app.GamePanel.GameState;

public class KeyHandler implements KeyListener{
	GamePanel gamePanel;
	public boolean key_upPressed = false, 
			key_downPressed = false, 
			key_leftPressed = false, 
			key_rightPressed = false,
			key_enterPressed = false;
	private boolean checkDrawTime = false;

	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public boolean getCheckDrawTime() {
		return checkDrawTime;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (gamePanel.getGame_state()) 
		{
			case TITLE: 
			{
				if(key == KeyEvent.VK_W) {
					gamePanel.getGameUI().decreaseCommand_number();
				}
				if(key == KeyEvent.VK_S) {
					gamePanel.getGameUI().addCommand_number();
				}
				if(key == KeyEvent.VK_ENTER) {
					switch (gamePanel.getGameUI().getCommand_number()) 
					{
						case 0: {
							gamePanel.setGame_state(GameState.PLAY);
							gamePanel.playMusic(0);
							break;
						}
						case 1: {
							//TODO
							break;
						}
						case 2: {
							System.exit(0);
							break;
						}
					}
				}
			}
			case PLAY: 
			{
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
				if(key == KeyEvent.VK_ENTER) {
					key_enterPressed = true;
				}
				
				if(key == KeyEvent.VK_ESCAPE) 
				{
					gamePanel.setGame_state(GameState.PAUSE);
				}
				
				//DEBUG
				if(key == KeyEvent.VK_T) {
					if(checkDrawTime) {
						checkDrawTime = false;
					}
					else {
						checkDrawTime = true;
					}
					
				}
				break;
			}
			case PAUSE: 
			{
				if(key == KeyEvent.VK_ESCAPE) 
				{
					switch (gamePanel.getGame_state()) 
					{
						case PLAY: {
							gamePanel.setGame_state(GameState.PAUSE);
							break;
						}
						case PAUSE: {
							gamePanel.setGame_state(GameState.PLAY);
							break;
						}
						default: {
							gamePanel.setGame_state(GameState.PLAY);
							break;
						}
					}
				}
			}
			case DIALOGUE: 
			{
				if(key == KeyEvent.VK_ENTER) {
					gamePanel.setGame_state(GameState.PLAY);
				}
			}
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
