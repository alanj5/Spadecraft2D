package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Librarian extends Entity{
	
	public NPC_Librarian(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		
		//Imports images from res folder
		up1 = setup("/npc/librarian_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/librarian_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/npc/librarian_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/librarian_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/npc/librarian_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/librarian_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/npc/librarian_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/librarian_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue() {
		
		//Predetermines librarian text
		dialogues[0] = "Hello there young adventurer.";
		dialogues[1] = "So you have come here to look for \nhidden chests.";
		dialogues[2] = "My name is Sergacious, I used to be \na young adventurer like you, but now \nI am the island's librarian.";
		dialogues[3] = "Well, nice meeting you, and good \nluck!";
		
	}
	
	public void setAction() {
		
		//Sets characteristics of specific NPC
		actionLockCounter++;
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(4)+1;
			
			if(i == 1) {
				direction = "up";
			}
			if(i == 2) {
				direction = "down";
			}
			if(i == 3) {
				direction = "left";
			}
			if(i == 4) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
	}
	public void speak() {
		
		//Adds to Entity speak method if required
		super.speak();
	}

}
