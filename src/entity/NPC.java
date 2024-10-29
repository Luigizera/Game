package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import app.GamePanel;

public abstract class NPC extends Entity{
	protected int action_lock_counter = 0;
	protected String dialogues[] = new String[20];
	protected int dialogue_index = 0;

	public NPC(GamePanel gamePanel) {
		super(gamePanel);
	}

	public abstract void setAction();
	
	public void speak() {
		if(dialogues[dialogue_index] == null) {
			dialogue_index = 0;
		}
		gamePanel.getGameUI().setCurrentDialogue(dialogues[dialogue_index]);
		dialogue_index++;
		
		switch (gamePanel.getPlayer().getDirection()) 
		{
			case 0: {
				direction = Direction.UP.getByte();
				break;
			}
			case 1: {
				direction = Direction.RIGHT.getByte();			
				break;
			}
			case 2: {
				direction = Direction.LEFT.getByte();
				break;
			}
			case 3: {
				direction = Direction.DOWN.getByte();
				break;
			}
		}
	}
	
	public void update() {
		setAction();
		collision_on = false;
		gamePanel.getCollision_checker().checkEntityCollision(this);
		gamePanel.getCollision_checker().checkObject(this);
		gamePanel.getCollision_checker().checkPlayer(this);
			
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
			updateSprite();
		}
		updateSpeed();
	}
	
	private boolean tileDrawScreenLimit(int world_x, int world_y, GamePanel gamePanel) {
		return world_x + GamePanel.TILESIZE > gamePanel.getPlayer().getWorld_x() - gamePanel.getPlayer().getScreen_x() &&
			   world_x - GamePanel.TILESIZE < gamePanel.getPlayer().getWorld_x() + gamePanel.getPlayer().getScreen_x() &&
			   world_y + GamePanel.TILESIZE > gamePanel.getPlayer().getWorld_y() - gamePanel.getPlayer().getScreen_y() &&
			   world_y - GamePanel.TILESIZE < gamePanel.getPlayer().getWorld_y() + gamePanel.getPlayer().getScreen_y();
	}
	
	public void draw(Graphics2D graphics2d) 
	{
		BufferedImage sprite_current = sprite_all.get(sprite_num);
		int screen_x = world_x - gamePanel.getPlayer().getWorld_x() + gamePanel.getPlayer().getScreen_x();
		int screen_y = world_y - gamePanel.getPlayer().getWorld_y() + gamePanel.getPlayer().getScreen_y();
		
		if(tileDrawScreenLimit(world_x, world_y, gamePanel)) 
		{
			graphics2d.drawImage(sprite_current, screen_x, screen_y, null);
		}
	}
}
