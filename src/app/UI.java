package app;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.Format;

import object.Object_Key;

public class UI {
	GamePanel gamePanel;
	Font arial_40, arial_80B;
	BufferedImage keyImage;
	private boolean message_on = false;
	private String message = "";
	private int message_counter = 0;
	private boolean game_finished = false;
	private double playTime = 0;
	private DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 80);
		Object_Key key = new Object_Key();
		keyImage = key.getSprite();
	}
	
	public void showMessage(String text) {
		message = text;
		message_on = true;
	}
	
	public void setGame_finished(boolean game_finished) {
		this.game_finished = game_finished;
	}
	
	public void draw(Graphics2D graphics2d) {
		if(game_finished) {
			graphics2d.setFont(arial_40);
			graphics2d.setColor(Color.black);
			String text = "You found the treasure!";
			int textLength = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
			int x = GamePanel.SCREEN_WIDTH/2 - textLength/2;
			int y = GamePanel.SCREEN_HEIGHT/2 - (GamePanel.TILESIZE*3);
			graphics2d.drawString(text, x, y);
			
			text = "Your time is: " + dFormat.format(playTime) + "!";
			textLength = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
			x = GamePanel.SCREEN_WIDTH/2 - textLength/2;
			y = GamePanel.SCREEN_HEIGHT/2 + (GamePanel.TILESIZE*4);
			graphics2d.drawString(text, x, y);
			
			
			graphics2d.setFont(arial_80B);
			graphics2d.setColor(Color.yellow);
			text = "Congratulations!";
			textLength = (int) graphics2d.getFontMetrics().getStringBounds(text, graphics2d).getWidth();
			x = GamePanel.SCREEN_WIDTH/2 - textLength/2;
			y = GamePanel.SCREEN_HEIGHT/2 + (GamePanel.TILESIZE*2);
			graphics2d.drawString(text, x, y);
			
			gamePanel.stopGameThread();
		}
		else {
			graphics2d.setFont(arial_40);
			graphics2d.setColor(Color.black);
			graphics2d.drawImage(keyImage, GamePanel.TILESIZE / 2, GamePanel.TILESIZE / 2, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
			graphics2d.drawString("x " + gamePanel.getPlayer().getKey_count(), 74, 65);
			
			playTime += (double)1/GamePanel.FPS;
			graphics2d.drawString("Time:"+dFormat.format(playTime), GamePanel.TILESIZE * 11, 65);
			
			if(message_on == true) {
				graphics2d.setFont(graphics2d.getFont().deriveFont(30F));
				int textLength = (int) graphics2d.getFontMetrics().getStringBounds(message, graphics2d).getWidth();
				graphics2d.drawString(message, GamePanel.SCREEN_WIDTH/2 - textLength/2, GamePanel.TILESIZE*5 - message_counter);
				message_counter++;
				if(message_counter > GamePanel.FPS) {
					message_counter = 0;
					message_on = false;
				}
			}
		}
		
	}
}
