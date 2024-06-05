package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pit extends Entity{
	
	public OBJ_Pit(GamePanel gp) {
		
		super(gp);
		
		name = "Pit";
		down1 = setup("/objects/pit", gp.tileSize, gp.tileSize);
		collision = true;
		
		solidArea.x = 8;
		solidArea.y = 16;
		solidArea.width = 32;
		solidArea.height = 32;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}

}

