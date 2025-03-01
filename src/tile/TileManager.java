package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import app.GamePanel;

public class TileManager {
	private GamePanel gamePanel;
	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private int mapTileNum[][];
	
	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		mapTileNum = new int[GamePanel.MAXWORLD_COL][GamePanel.MAXWORLD_ROW];
		getTileImages();
		loadMap("fields0");
	}
	
	public int getSpecificMapTileNum(int col, int row) {
		if(row < 0 || col < 0) {
			return 1;
		}
		return mapTileNum[col][row];
	}
	
	public Tile getSpecificTile(int num) {
		if(num > tiles.size()) {
			return tiles.get(0);
		}
		return tiles.get(num);
	}
	
	public void getTileImages() {
		
		InputStream is = getClass().getResourceAsStream("/tiles/tiles.txt");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			String line = br.readLine();
			while (line != null)
            {
				String[] lineArray = line.split(" ");
				BufferedImage scaledSprite = new BufferedImage(GamePanel.TILESIZE, GamePanel.TILESIZE, ImageIO.read(getClass().getResourceAsStream("/tiles/"+ lineArray[0] +".png")).getType());
				Graphics2D graphics2d = scaledSprite.createGraphics();
				graphics2d.drawImage(ImageIO.read(getClass().getResourceAsStream("/tiles/"+ lineArray[0] +".png")), 0, 0, GamePanel.TILESIZE, GamePanel.TILESIZE, null);
				graphics2d.dispose();
            	tiles.add(new Tile(scaledSprite, Boolean.valueOf(lineArray[1])));
                line = br.readLine();
            }
            br.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String name_map) {
		try {
			InputStream is = getClass().getResourceAsStream("/maps/"+ name_map +".txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;
			
			while(col < GamePanel.MAXWORLD_COL && row < GamePanel.MAXWORLD_ROW) {
				String line = br.readLine();
				while(col < GamePanel.MAXWORLD_COL) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == GamePanel.MAXWORLD_COL) {
					col = 0;
					row++;
				}
			}
			br.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean tileDrawScreenLimit(int world_x, int world_y) {
		return world_x + GamePanel.TILESIZE > gamePanel.getPlayer().getWorld_x() - gamePanel.getPlayer().getScreen_x() &&
			   world_x - GamePanel.TILESIZE < gamePanel.getPlayer().getWorld_x() + gamePanel.getPlayer().getScreen_x() &&
			   world_y + GamePanel.TILESIZE > gamePanel.getPlayer().getWorld_y() - gamePanel.getPlayer().getScreen_y() &&
			   world_y - GamePanel.TILESIZE < gamePanel.getPlayer().getWorld_y() + gamePanel.getPlayer().getScreen_y();
	}
	
	public void draw(Graphics2D graphics2d) {
		int world_col = 0;
		int world_row = 0;
		
		while (world_col < GamePanel.MAXWORLD_COL && world_row < GamePanel.MAXWORLD_ROW) {
			int tileNum = mapTileNum[world_col][world_row];
			
			int world_x = world_col * GamePanel.TILESIZE;
			int world_y = world_row * GamePanel.TILESIZE;
			int screen_x = world_x - gamePanel.getPlayer().getWorld_x() + gamePanel.getPlayer().getScreen_x();
			int screen_y = world_y - gamePanel.getPlayer().getWorld_y() + gamePanel.getPlayer().getScreen_y();
			
			if(tileDrawScreenLimit(world_x, world_y)) {
				graphics2d.drawImage(tiles.get(tileNum).getImage(), screen_x, screen_y, null);
			}
			
			world_col++;
			if(world_col == GamePanel.MAXWORLD_COL) {
				world_col = 0;
				world_row++;
			}
		}
	}
	
	
}
