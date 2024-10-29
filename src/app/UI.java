package app;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.Format;

import app.GamePanel.GameState;
import object.Object_Key;

public class UI {
	
	private GamePanel gamePanel;
	private Graphics2D graphics2d;
	private Font font_default;
	private String currentDialogue = "";
	private int command_number = 0;
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/MP16OSF.ttf");
			font_default = Font.createFont(Font.TRUETYPE_FONT, is);
		} 
		catch (FontFormatException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void decreaseCommand_number() {
		this.command_number--;
		if(command_number < 0) {
			this.command_number = 2;
		}
	}
	
	public void addCommand_number() {
		this.command_number++;
		if(command_number > 2) {
			this.command_number = 0;
		}
	}
	
	public int getCommand_number() {
		return command_number;
	}
	
	public void setCurrentDialogue(String currentDialogue) {
		this.currentDialogue = currentDialogue;
	}
	
	public void draw(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
			graphics2d.setFont(font_default);
			graphics2d.setColor(Color.black);
			switch (gamePanel.getGame_state()) {
			case TITLE: {
				drawTitleScreen();
			}
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
	private void drawTitleScreen() {
		//BACKGROUND COLOR
		graphics2d.setColor(new Color(127,127,127));
		graphics2d.fillRect(0, 0, GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT);
		
		//TITLE NAME
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD, 96F));
		String text = "Ludas Game";
		int x = getCenterText_X(text);
		int y = GamePanel.TILESIZE * 3;
		
		graphics2d.setColor(Color.black);
		graphics2d.drawString(text, x+5, y+5);
		graphics2d.setColor(Color.white);
		graphics2d.drawString(text, x, y);
		
		//PLAYER IMG
		x = GamePanel.SCREEN_WIDTH/2 - GamePanel.TILESIZE;
		y += GamePanel.TILESIZE * 2;
		graphics2d.drawImage(gamePanel.getPlayer().getAllSprites().get(0), x, y, GamePanel.TILESIZE*2, GamePanel.TILESIZE*2, null);
		
		
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.BOLD, 48F));
		text = "New Game";
		x = getCenterText_X(text);
		y += GamePanel.TILESIZE*4;
		graphics2d.drawString(text, x, y);
		if(command_number == 0) {
			graphics2d.drawString(">", x - GamePanel.TILESIZE, y);
		}
		
		text = "Load Game";
		x = getCenterText_X(text);
		y += GamePanel.TILESIZE;
		graphics2d.drawString(text, x, y);
		if(command_number == 1) {
			graphics2d.drawString(">", x - GamePanel.TILESIZE, y);
		}
		
		text = "Quit";
		x = getCenterText_X(text);
		y += GamePanel.TILESIZE;
		graphics2d.drawString(text, x, y);
		if(command_number == 2) {
			graphics2d.drawString(">", x - GamePanel.TILESIZE, y);
		}
	}
	
	
	
	private void drawPauseScreen() {
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.PLAIN, 80F));
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
		
		graphics2d.setFont(graphics2d.getFont().deriveFont(Font.PLAIN, 32F));
		Color color = new Color(255, 255, 255);
		x += GamePanel.TILESIZE;
		y += GamePanel.TILESIZE;
		graphics2d.setColor(color);
		for(String line : currentDialogue.split("\n")) {
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
