package entity;

import java.util.Random;

import app.GamePanel;

public class NPC_Noob extends NPC{

	public NPC_Noob(GamePanel gamePanel) {
		super(gamePanel);
		setDefaultValues();
	}
	public void setDefaultValues() {
		speed = 1;
		setCollision(0, 0, 48, 48);
		setWorldLocation(23, 20);
		setDefaultSprite("npc0");
		setDefaultDialogues();
	}
	
	public void setDefaultDialogues() {
		dialogues[0] = "Hello, world!";
		dialogues[1] = "I'm noob nice to meet you!";
		dialogues[2] = "Time to start our adventure together, \\nI'm here to help you!";
	}
	
	public void speak() {
		super.speak();
	}
	
	@Override
	public void setAction() {
		action_lock_counter++;
		
		if(action_lock_counter >= GamePanel.FPS * 2) {
			Random random = new Random();
			int i = random.nextInt(100);
			if(i <= 25) {
				direction = Direction.UP.getByte();
			}
			if(i > 25 && i <= 50) {
				direction = Direction.DOWN.getByte();
			}
			if(i > 50 && i <= 75) {
				direction = Direction.LEFT.getByte();
			}
			if(i > 75 && i <= 100) {
				direction = Direction.RIGHT.getByte();
			}
			action_lock_counter = 0;
		}
	}
}
