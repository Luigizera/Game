package app;

import object.Object_Boots;
import object.Object_Chest;
import object.Object_Door;
import object.Object_Key;

public class AssetSetter {
	GamePanel gamePanel;
	
	public AssetSetter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void setObject() {
		gamePanel.getObject_game()[0] = new Object_Key();
		gamePanel.getObject_game()[0].setWorld_x(23);
		gamePanel.getObject_game()[0].setWorld_y(7);
		
		gamePanel.getObject_game()[1] = new Object_Key();
		gamePanel.getObject_game()[1].setWorld_x(23);
		gamePanel.getObject_game()[1].setWorld_y(40);
		
		gamePanel.getObject_game()[2] = new Object_Key();
		gamePanel.getObject_game()[2].setWorld_x(44);
		gamePanel.getObject_game()[2].setWorld_y(6);
		
		gamePanel.getObject_game()[3] = new Object_Door();
		gamePanel.getObject_game()[3].setWorld_x(2);
		gamePanel.getObject_game()[3].setWorld_y(12);
		
		gamePanel.getObject_game()[4] = new Object_Door();
		gamePanel.getObject_game()[4].setWorld_x(2);
		gamePanel.getObject_game()[4].setWorld_y(24);
		
		gamePanel.getObject_game()[5] = new Object_Door();
		gamePanel.getObject_game()[5].setWorld_x(2);
		gamePanel.getObject_game()[5].setWorld_y(36);
		
		gamePanel.getObject_game()[6] = new Object_Chest();
		gamePanel.getObject_game()[6].setWorld_x(8);
		gamePanel.getObject_game()[6].setWorld_y(43);
		
		gamePanel.getObject_game()[7] = new Object_Boots();
		gamePanel.getObject_game()[7].setWorld_x(26);
		gamePanel.getObject_game()[7].setWorld_y(7);
	}
}
