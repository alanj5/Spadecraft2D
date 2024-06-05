package main;

import java.awt.Rectangle;

public class EventHandler {
	
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRect = new Rectangle();
		eventRect.x = 3;
		eventRect.y = 23;
		eventRect.width = 20;
		eventRect.height = 2;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}
	public void checkEvent() {
		
		if(hit(32,17,"right") == true) {
			damagePit(gp.dialogueState);
		}
		if(hit(23,20,"up") == true) {
			healingPool(gp.dialogueState);
		}
	}
	public boolean hit(int eventCol, int eventRow, String reqDirection) {
		
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRect.x = eventCol*gp.tileSize + eventRect.x;
		eventRect.y = eventRow*gp.tileSize + eventRect.y;
		
		if(gp.player.solidArea.intersects(eventRect)) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				hit = true;
			}
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;
		
		return hit;	
	}
	public void damagePit(int gameState) {
		
		gp.gameState = gameState;
		gp.ui.currentDialogue = "Invincible!";
		if (gp.player.invincible == false) {
			gp.ui.currentDialogue = "Damage taken from pit!";
			gp.player.life -= 1;
		}
		gp.player.invincible = true;
	}
	public void healingPool(int gameState) {
		
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.ui.currentDialogue = "Healed from water!";
			gp.player.life = gp.player.maxLife;
		}
	}
}
