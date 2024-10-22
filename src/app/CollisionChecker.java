package app;

import entity.Entity;

public class CollisionChecker {

	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	
	public void check(Entity entity) {
		int entity_left_world_x = (int) (entity.getWorld_x() + entity.getCollision_area().getX());
		int entity_right_world_x = (int) (entity.getWorld_x() + entity.getCollision_area().getX() + entity.getCollision_area().getWidth());
		int entity_top_world_y = (int) (entity.getWorld_y() + entity.getCollision_area().getY());
		int entity_bottom_world_y = (int) (entity.getWorld_y() + entity.getCollision_area().getY() + entity.getCollision_area().getHeight());
		
		
		int entity_left_col = entity_left_world_x/GamePanel.TILESIZE;
		int entity_right_col = entity_right_world_x/GamePanel.TILESIZE;
		int entity_top_row = entity_top_world_y/GamePanel.TILESIZE;
		int entity_bottom_row = entity_bottom_world_y/GamePanel.TILESIZE;
		
		int tileNum1, tileNum2;
		
		switch (entity.getDirection()) {
			case 0: {
				entity_bottom_row = (entity_bottom_world_y + entity.getSpeed()) / GamePanel.TILESIZE;
				tileNum1 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_left_col, entity_bottom_row);
				tileNum2 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_right_col, entity_bottom_row);
				if(gamePanel.getTile_manager().getSpecificTile(tileNum1).getCollision() == true ||
				   gamePanel.getTile_manager().getSpecificTile(tileNum2).getCollision() == true) {
					entity.setCollision_on(true);
				}
				break;
			}
			case 1: {
				entity_left_col = (entity_left_world_x - entity.getSpeed()) / GamePanel.TILESIZE;
				tileNum1 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_left_col, entity_top_row);
				tileNum2 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_left_col, entity_bottom_row);
				if(gamePanel.getTile_manager().getSpecificTile(tileNum1).getCollision() == true ||
				   gamePanel.getTile_manager().getSpecificTile(tileNum2).getCollision() == true) {
					entity.setCollision_on(true);
				}
				break;
			}
			case 2: {
				entity_right_col = (entity_right_world_x + entity.getSpeed()) / GamePanel.TILESIZE;
				tileNum1 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_right_col, entity_top_row);
				tileNum2 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_right_col, entity_bottom_row);
				if(gamePanel.getTile_manager().getSpecificTile(tileNum1).getCollision() == true ||
				   gamePanel.getTile_manager().getSpecificTile(tileNum2).getCollision() == true) {
					entity.setCollision_on(true);
				}
				break;
			}
			case 3: {
				entity_top_row = (entity_top_world_y - entity.getSpeed()) / GamePanel.TILESIZE;
				tileNum1 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_left_col, entity_top_row);
				tileNum2 = gamePanel.getTile_manager().getSpecificMapTileNum(entity_right_col, entity_top_row);
				if(gamePanel.getTile_manager().getSpecificTile(tileNum1).getCollision() == true ||
				   gamePanel.getTile_manager().getSpecificTile(tileNum2).getCollision() == true) {
					entity.setCollision_on(true);
				}
				break;
			}
		}
	}
	
}
