package app;

import object.Object_Chest;
import object.Object_Door;
import object.Object_Key;

public class AssetSetter {
	GamePanel gamePanel;
	
	public AssetSetter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void setObject() {
		gamePanel.object_game[0] = new Object_Key();
		gamePanel.object_game[0].setWorld_x(23 * GamePanel.TILESIZE);
		gamePanel.object_game[0].setWorld_y(7 * GamePanel.TILESIZE);
		
		gamePanel.object_game[1] = new Object_Key();
		gamePanel.object_game[1].setWorld_x(23 * GamePanel.TILESIZE);
		gamePanel.object_game[1].setWorld_y(40 * GamePanel.TILESIZE);
		
		gamePanel.object_game[2] = new Object_Key();
		gamePanel.object_game[2].setWorld_x(44 * GamePanel.TILESIZE);
		gamePanel.object_game[2].setWorld_y(6 * GamePanel.TILESIZE);
		
		gamePanel.object_game[3] = new Object_Door();
		gamePanel.object_game[3].setWorld_x(2 * GamePanel.TILESIZE);
		gamePanel.object_game[3].setWorld_y(12 * GamePanel.TILESIZE);
		
		gamePanel.object_game[4] = new Object_Door();
		gamePanel.object_game[4].setWorld_x(2 * GamePanel.TILESIZE);
		gamePanel.object_game[4].setWorld_y(24 * GamePanel.TILESIZE);
		
		gamePanel.object_game[5] = new Object_Door();
		gamePanel.object_game[5].setWorld_x(2 * GamePanel.TILESIZE);
		gamePanel.object_game[5].setWorld_y(36 * GamePanel.TILESIZE);
		
		gamePanel.object_game[6] = new Object_Chest();
		gamePanel.object_game[6].setWorld_x(8 * GamePanel.TILESIZE);
		gamePanel.object_game[6].setWorld_y(43 * GamePanel.TILESIZE);
	}
}
