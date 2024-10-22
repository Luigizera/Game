package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import app.GamePanel;
import app.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;
	GamePanel gamePanel;
	private final int screen_x, screen_y;
	
	public Player(KeyHandler keyH, GamePanel gamePanel) {
		this.keyH = keyH;
		this.gamePanel = gamePanel;
		screen_x = GamePanel.SCREEN_WIDTH/2 - GamePanel.TILESIZE/2;
		screen_y = GamePanel.SCREEN_HEIGHT/2 - GamePanel.TILESIZE/2;
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		collision_area = new Rectangle(8, 16, 32, 32);
		world_x = GamePanel.TILESIZE * 23;
		world_y = GamePanel.TILESIZE * 21;
		setSprite("player");
	}
	
	public int getScreen_x() {
		return screen_x;
	}
	public int getScreen_y() {
		return screen_y;
	}
	
	private boolean anyKeysPressed() {
		return this.keyH.key_upPressed ||
				this.keyH.key_downPressed ||
				this.keyH.key_leftPressed ||
				this.keyH.key_rightPressed;
	}
	
	public void updateCollision() {
		collision_on = false;
		gamePanel.getCollision_checker().check(this);
		
		if(collision_on == false) {
			switch (direction) {
				case 0: {
					world_y += speed;
					speed_current = speed;
					break;
				}
				case 1: {
					world_x -= speed;
					speed_current = speed;
					break;
				}
				case 2: {
					world_x += speed;
					speed_current = speed;
					break;
				}
				case 3: {
					world_y -= speed;
					speed_current = speed;
					break;
				}
			}
		}
	}
	
	public void update() {
		if(anyKeysPressed())
		{
			if(this.keyH.key_upPressed){
				direction = Direction.UP.getByte();
				
			}
			if(this.keyH.key_downPressed){
				direction = Direction.DOWN.getByte();
			}
			if(this.keyH.key_leftPressed){
				direction = Direction.LEFT.getByte();
				
			}
			if(this.keyH.key_rightPressed){
				direction = Direction.RIGHT.getByte();
				
			}
			
			updateCollision();
			
			updateSprite();
		}
		updateSpeed();
		
	}
	public void draw(Graphics2D graphics2d) {
		
		BufferedImage sprite_current = sprite_all.get(sprite_num);
		
		graphics2d.drawImage(sprite_current, screen_x, screen_y, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
	}
}
