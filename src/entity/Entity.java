package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.GamePanel;

public abstract class Entity {
	public enum Direction {
		DOWN(0),
		LEFT(1),
		RIGHT(2),
		UP(3);
		
		
		private int num;
		private Direction(int num) {
			this.num = num;
		}
		
		protected byte getByte() {
			return (byte)num;
		}
	}
	protected GamePanel gamePanel;
	protected int world_x = 0, world_y = 0;
	protected byte speed = 4;
	protected byte speed_current = 0;
	protected BufferedImage sprite; 
	protected ArrayList<BufferedImage> sprite_all = new ArrayList<BufferedImage>();
	protected byte direction = Direction.DOWN.getByte();
	protected byte sprite_counter = 0;
	protected byte sprite_num = 0;
	protected byte sprite_width = 0;
	protected byte sprite_height = 0;
	private byte sprite_width_count = 0;
	protected Rectangle collision_area = new Rectangle(0, 0, GamePanel.TILESIZE, GamePanel.TILESIZE); //solidArea
	protected int collision_default_x = 0, collision_default_y = 0;
	protected boolean collision_on = false;
	
	public Entity(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public Rectangle getCollision_area() {
		return collision_area;
	}
	public int getCollision_default_x() {
		return collision_default_x;
	}
	public int getCollision_default_y() {
		return collision_default_y;
	}
	public void setCollision(int x, int y, int width, int height) {
		if(width > GamePanel.TILESIZE || width <= -1) {
			width = GamePanel.TILESIZE;
		}
		if(height > GamePanel.TILESIZE || height <= -1) {
			height = GamePanel.TILESIZE;
		}
		collision_area = new Rectangle(x, y, width, height);
		collision_default_x = x;
		collision_default_y = y;
	}
	public void setCollision_on(boolean collision_on) {
		this.collision_on = collision_on;
	}
	
	public void setWorldLocation(int world_x, int world_y) {
		this.world_x = world_x * GamePanel.TILESIZE;
		this.world_y = world_y * GamePanel.TILESIZE;
	}
	
	public byte getSpeed() {
		return speed;
	}
	public byte getDirection() {
		return direction;
	}
	
	public int getWorld_x() {
		return world_x;
	}
	
	public int getWorld_y() {
		return world_y;
	}

	
	protected void setDefaultSprite(String name) {
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/entity/" + name + ".png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		sprite_all = getAllSprites();
	}
	
	protected boolean verifySprite() {
		return sprite != null &&
			   sprite.getWidth() % 16 == 0 &&
			   sprite.getHeight() % 16 == 0;
	}
	
	public ArrayList<BufferedImage> getAllSprites() {
		if(verifySprite()){
			sprite_width = (byte)(sprite.getWidth() / GamePanel.TILESIZE_ORIGINAL);
			sprite_height = (byte)(sprite.getHeight() / GamePanel.TILESIZE_ORIGINAL);
			ArrayList<BufferedImage> all = new ArrayList<BufferedImage>();
			for(int loop_y = 0; loop_y < sprite.getHeight(); loop_y += GamePanel.TILESIZE_ORIGINAL) {
				for(int loop_x = 0; loop_x < sprite.getWidth(); loop_x += GamePanel.TILESIZE_ORIGINAL) {
					BufferedImage scaledSprite = new BufferedImage(GamePanel.TILESIZE, GamePanel.TILESIZE, BufferedImage.TYPE_INT_ARGB);
					Graphics2D graphics2d = scaledSprite.createGraphics();
					graphics2d.drawImage(sprite.getSubimage(loop_x, loop_y, GamePanel.TILESIZE_ORIGINAL, GamePanel.TILESIZE_ORIGINAL), 0, 0, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
					graphics2d.dispose();
					all.add(scaledSprite);
				}
			}
			return all;
		}
		return new ArrayList<BufferedImage>();
	}
	
	protected void updateSprite()
	{
		sprite_counter++;
		if(sprite_counter > 10) 	
		{
			if(sprite_width == 2)
			{
				if(sprite_num != (byte) (direction * sprite_width)) {
					sprite_num = (byte) (direction * sprite_width - 1);
				}
				sprite_num++;
			}
			if(sprite_width > 2)
			{
				sprite_width_count++;
				if(sprite_num != (byte) (direction * sprite_width) && sprite_width_count >= sprite_width) {
					sprite_num = (byte) (direction * sprite_width - 1);
					sprite_width_count = 0;
				}
				sprite_num++;
			}
			
			sprite_counter = 0;
		}
	}
	
	protected void updateSpeed() {
		if(speed_current > 0) {
			speed_current--;
		}
		else {
			sprite_num = (byte) (direction * sprite_width);
			speed_current = 0;
		}
	}
	
	
}
