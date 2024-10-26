package app;

import entity.NPC_Noob;

public class AssetSetter {
	GamePanel gamePanel;
	
	public AssetSetter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void setObject() {
		
	}
	
	public void setNPC() {
		gamePanel.getNPC()[0] = new NPC_Noob(gamePanel);
	}
}
