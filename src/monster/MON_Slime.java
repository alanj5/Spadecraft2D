package monster;

import java.util.Random;

import entity.Entity;
import main.GamePanel;

public class MON_Slime extends Entity{
	
	GamePanel gp;
	
	public MON_Slime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = 2;
		name = "Slime";
		speed = 1;
		maxLife = 3;
		life = maxLife;

		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	
	}
	public void getImage() {
		
		up1 = setup("/monster/slime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/slime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/slime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/slime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/slime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/slime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/slime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/slime_down_2", gp.tileSize, gp.tileSize);
	}
	public void setAction() {
		
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
	public void damageReaction() {
		
		actionLockCounter = 0;
		direction = gp.player.direction;
	}

}
