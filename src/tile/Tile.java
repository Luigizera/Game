package tile;

import java.awt.image.BufferedImage;

public class Tile {
	private BufferedImage image;
	private boolean collision = false;
	
	Tile(BufferedImage image, boolean collision) {
		this.image = image;
		this.collision = collision;
	}
	
	Tile(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public boolean getCollision() {
		return collision;
	}
	
}
