package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Healing_Water extends Entity{
	
	public OBJ_Healing_Water(GamePanel gp) {
		
		super(gp);
		
		name = "Healing water";
		down1 = setup("/objects/healing_water", gp.tileSize, gp.tileSize);
		collision = true;
		
		solidArea.x = 2;
		solidArea.y = 10;
		solidArea.width = 36;
		solidArea.height = 8;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}

