package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.Format;

import app.GamePanel.GameState;
import object.Object_Key;

public class UI {
	
	private GamePanel gamePanel;
	private Graphics2D graphics2d;
	private static final Font ARIAL_40 = new Font("Arial", Font.PLAIN, 40), 
							  ARIAL_80B = new Font("Arial", Font.BOLD, 80), 
							  ARIAL_32 = new Font("Arial", Font.PLAIN, 32);
	private String currentDialogue = "";
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void setCurrentDialogue(String currentDialogue) {
		this.currentDialogue = currentDialogue;
	}
	
	public void draw(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
			graphics2d.setFont(ARIAL_40);
			graphics2d.setColor(Color.black);
			switch (gamePanel.getGame_state()) {
			case PLAY: {
				
				break;
			}
			case PAUSE: {
				drawPauseScreen();
				break;
			}
			case DIALOGUE: {
				drawDialogueScreen();
				break;
			}
			default: {
				throw new IllegalArgumentException("Unexpected value: " + gamePanel.getGame_state());
			}
		}
	}
	
	private void drawPauseScreen() {
		graphics2d.setFont(ARIAL_80B);
		String text = "PAUSED";
		int x = getCenterText_X(text);
		int y = GamePanel.SCREEN_HEIGHT/2;
		
		graphics2d.drawString(text, x, y);
	}
	
	public int getCenterText_X(String text) {
		int length = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
		return GamePanel.SCREEN_WIDTH/2 - length/2;
	}
	
	public void drawDialogueScreen() {
		//Window
		int x = GamePanel.TILESIZE * 2;
		int y = GamePanel.SCREEN_HEIGHT - (GamePanel.TILESIZE*4);
		int width = GamePanel.SCREEN_WIDTH - (GamePanel.TILESIZE*4);
		int height = GamePanel.TILESIZE*4;
		drawSubWindow(x, y, width, height);
		
		graphics2d.setFont(ARIAL_32);
		Color color = new Color(255, 255, 255);
		x += GamePanel.TILESIZE;
		y += GamePanel.TILESIZE;
		graphics2d.setColor(color);
		for(String line : currentDialogue.split("\\n")) {
			graphics2d.drawString(line, x, y);
			y += GamePanel.TILESIZE;
		}
	}
	
	private void drawSubWindow(int x, int y, int width, int height) {
		Color color = new Color(0, 0, 0, 220);
		graphics2d.setColor(color);
		graphics2d.fillRoundRect(x, y, width, height, 35, 35);
		
		color = new Color(255, 255, 255);
		graphics2d.setColor(color);
		graphics2d.setStroke(new BasicStroke(5));
		graphics2d.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
}
