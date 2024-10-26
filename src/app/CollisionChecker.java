package app;

import java.awt.Rectangle;
import entity.Entity;
import entity.Player;

public class CollisionChecker {

	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	
	public void checkEntityCollision(Entity entity) {
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
	
	public int checkObject(Entity entity) {
		int index = -1;
		Rectangle entityCollision = new Rectangle(0, 0, 0, 0);
		Rectangle objectCollision = new Rectangle(0, 0, 0, 0);
		
		for (int loop_i = 0; loop_i < gamePanel.getObject_game().length; loop_i++) {
			if(gamePanel.getObject_game()[loop_i] != null) {
				entityCollision = entity.getCollision_area();
				entityCollision.x += entity.getWorld_x();
				entityCollision.y += entity.getWorld_y();
				objectCollision = gamePanel.getObject_game()[loop_i].getCollision_area();
				objectCollision.x +=  gamePanel.getObject_game()[loop_i].getWorld_x();
				objectCollision.y +=  gamePanel.getObject_game()[loop_i].getWorld_y();
				
				switch (entity.getDirection()) {
					case 0: {
						entityCollision.y += entity.getSpeed();
						if(entityCollision.intersects(objectCollision)) {
							if(gamePanel.getObject_game()[loop_i].getCollision()) {
								entity.setCollision_on(true);
							}
							if(entity.getClass() == Player.class) {
								return loop_i;
							}
						}
						break;
					}
					case 1: {
						entityCollision.x -= entity.getSpeed();
						if(entityCollision.intersects(objectCollision)) {
							if(gamePanel.getObject_game()[loop_i].getCollision()) {
								entity.setCollision_on(true);
							}
							if(entity.getClass() == Player.class) {
								return loop_i;
							}
						}
						break;
					}
					case 2: {
						entityCollision.x += entity.getSpeed();
						if(entityCollision.intersects(objectCollision)) {
							if(gamePanel.getObject_game()[loop_i].getCollision()) {
								entity.setCollision_on(true);
							}
							if(entity.getClass() == Player.class) {
								return loop_i;
							}
						}
						break;
					}
					case 3: {
						entityCollision.y -= entity.getSpeed();
						if(entityCollision.intersects(objectCollision)) {
							if(gamePanel.getObject_game()[loop_i].getCollision()) {
								entity.setCollision_on(true);
							}
							if(entity.getClass() == Player.class) {
								return loop_i;
							}
						}
						break;
					}
				}
				entityCollision.x = entity.getCollision_default_x();
				entityCollision.y = entity.getCollision_default_y();
				objectCollision.x = (int) gamePanel.getObject_game()[loop_i].getCollision_default_x();
				objectCollision.y = (int) gamePanel.getObject_game()[loop_i].getCollision_default_y();
			}
		}
		
		return index;
	}
	
	public int checkEntity(Entity entity, Entity[] target) {
		int index = -1;
		Rectangle entityCollision = new Rectangle(0, 0, 0, 0);
		Rectangle targetCollision = new Rectangle(0, 0, 0, 0);
		
		for (int loop_i = 0; loop_i < target.length; loop_i++) {
			if(target[loop_i] != null) {
				entityCollision = entity.getCollision_area();
				entityCollision.x += entity.getWorld_x();
				entityCollision.y += entity.getWorld_y();
				targetCollision = target[loop_i].getCollision_area();
				targetCollision.x +=  target[loop_i].getWorld_x();
				targetCollision.y +=  target[loop_i].getWorld_y();
				
				switch (entity.getDirection()) {
					case 0: {
						entityCollision.y += entity.getSpeed();
						if(entityCollision.intersects(targetCollision)) {
							entity.setCollision_on(true);
							target[loop_i].setCollision_on(true);
							index = loop_i;
						}
						break;
					}
					case 1: {
						entityCollision.x -= entity.getSpeed();
						if(entityCollision.intersects(targetCollision)) {
							entity.setCollision_on(true);
							target[loop_i].setCollision_on(true);
							index = loop_i;
						}
						break;
					}
					case 2: {
						entityCollision.x += entity.getSpeed();
						if(entityCollision.intersects(targetCollision)) {
							entity.setCollision_on(true);
							target[loop_i].setCollision_on(true);
							index = loop_i;
						}
						break;
					}
					case 3: {
						entityCollision.y -= entity.getSpeed();
						if(entityCollision.intersects(targetCollision)) {
							entity.setCollision_on(true);
							target[loop_i].setCollision_on(true);
							index = loop_i;
						}
						break;
					}
				}
				entityCollision.x = entity.getCollision_default_x();
				entityCollision.y = entity.getCollision_default_y();
				targetCollision.x = target[loop_i].getCollision_default_x();
				targetCollision.y = target[loop_i].getCollision_default_y();
			}
		}
		
		return index;
	}
	
	public void checkPlayer(Entity entity) {
		
		Rectangle entityCollision = new Rectangle(0, 0, 0, 0);
		Rectangle targetCollision = new Rectangle(0, 0, 0, 0);
		
		entityCollision = entity.getCollision_area();
		entityCollision.x += entity.getWorld_x();
		entityCollision.y += entity.getWorld_y();
		targetCollision = gamePanel.getPlayer().getCollision_area();
		targetCollision.x +=  gamePanel.getPlayer().getWorld_x();
		targetCollision.y +=  gamePanel.getPlayer().getWorld_y();
		
		switch (entity.getDirection()) {
			case 0: {
				entityCollision.y += entity.getSpeed();
				if(entityCollision.intersects(targetCollision)) {
					entity.setCollision_on(true);
				}
				break;
			}
			case 1: {
				entityCollision.x -= entity.getSpeed();
				if(entityCollision.intersects(targetCollision)) {
					entity.setCollision_on(true);
				}
				break;
			}
			case 2: {
				entityCollision.x += entity.getSpeed();
				if(entityCollision.intersects(targetCollision)) {
					entity.setCollision_on(true);
				}
				break;
			}
			case 3: {
				entityCollision.y -= entity.getSpeed();
				if(entityCollision.intersects(targetCollision)) {
					entity.setCollision_on(true);
				}
				break;
			}
		}
		entityCollision.x = entity.getCollision_default_x();
		entityCollision.y = entity.getCollision_default_y();
		targetCollision.x = gamePanel.getPlayer().getCollision_default_x();
		targetCollision.y = gamePanel.getPlayer().getCollision_default_y();
	}
	
}
