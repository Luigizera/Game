package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import app.GamePanel;

public class ObjectGame {
	protected BufferedImage sprite;
	protected String name;
	protected boolean collision = false;
	protected int world_x, world_y;
	protected Rectangle collision_area = new Rectangle(0, 0, GamePanel.TILESIZE, GamePanel.TILESIZE);
	protected int collision_default_x = 0;
	protected int collision_default_y = 0;
	
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
	
	public int getCollision_default_x() {
		return collision_default_x;
	}
	
	public int getCollision_default_y() {
		return collision_default_y;
	}
	
	public Rectangle getCollision_area() {
		return collision_area;
	}
	
	public boolean getCollision() {
		return collision;
	}
	
	public int getWorld_x() {
		return world_x;
	}
	public void setWorld_x(int world_x) {
		this.world_x = world_x * GamePanel.TILESIZE;
	}
	
	public int getWorld_y() {
		return world_y;
	}
	public void setWorld_y(int world_y) {
		this.world_y = world_y * GamePanel.TILESIZE;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public BufferedImage getSprite() {
		return sprite;
	}
	
	protected void setSprite(String name) {
		try {
			sprite = ImageIO.read(getClass().getResourceAsStream("/objects/" + name + ".png"));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean tileDrawScreenLimit(int world_x, int world_y, GamePanel gamePanel) {
		return world_x + GamePanel.TILESIZE > gamePanel.getPlayer().getWorld_x() - gamePanel.getPlayer().getScreen_x() &&
			   world_x - GamePanel.TILESIZE < gamePanel.getPlayer().getWorld_x() + gamePanel.getPlayer().getScreen_x() &&
			   world_y + GamePanel.TILESIZE > gamePanel.getPlayer().getWorld_y() - gamePanel.getPlayer().getScreen_y() &&
			   world_y - GamePanel.TILESIZE < gamePanel.getPlayer().getWorld_y() + gamePanel.getPlayer().getScreen_y();
	}
	
	public void draw(Graphics2D graphics2d, GamePanel gamePanel) {
		int screen_x = world_x - gamePanel.getPlayer().getWorld_x() + gamePanel.getPlayer().getScreen_x();
		int screen_y = world_y - gamePanel.getPlayer().getWorld_y() + gamePanel.getPlayer().getScreen_y();
		
		if(tileDrawScreenLimit(world_x, world_y, gamePanel)) {
			graphics2d.drawImage(sprite, screen_x, screen_y, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
		}
	}
}
