package entity;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import app.GamePanel;
import app.GamePanel.GameState;
import app.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;
	private final int screen_x, screen_y;
	int key_count = 0;
	
	public Player(KeyHandler keyH, GamePanel gamePanel) {
		super(gamePanel);
		this.keyH = keyH;
		screen_x = GamePanel.SCREEN_WIDTH/2 - GamePanel.TILESIZE/2;
		screen_y = GamePanel.SCREEN_HEIGHT/2 - GamePanel.TILESIZE/2;
		setDefaultValues();
	}
	
	public void setDefaultValues() {
		setCollision(12, 32, 26, 14);
		setWorldLocation(23, 21);
		setDefaultSprite("player");
	}
	
	public int getKey_count() {
		return key_count;
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
			
			collision_on = false;
			gamePanel.getCollision_checker().checkEntityCollision(this);
			int obj_index = gamePanel.getCollision_checker().checkObject(this);
			pickUpObject(obj_index);
			
			int npc_index = gamePanel.getCollision_checker().checkEntity(this, gamePanel.getNPC());
			interactNPC(npc_index);
			
			if(collision_on == false) 
			{
				switch (direction) 
				{
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
			
			updateSprite();
		}
		updateSpeed();
		
	}
	
	public void pickUpObject(int index) {
		if(index != -1) {
			
		}
	}
	
	public void interactNPC(int index) {
		if(index != -1) {
			if(this.keyH.key_enterPressed == true) {
				gamePanel.setGame_state(GameState.DIALOGUE);
				gamePanel.getNPC()[index].speak();
			}
		}
		this.keyH.key_enterPressed = false;
	}
	
	public void draw(Graphics2D graphics2d) {
		
		BufferedImage sprite_current = sprite_all.get(sprite_num);
		graphics2d.drawImage(sprite_current, screen_x, screen_y, null);
	}
}
