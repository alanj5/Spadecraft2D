package main;

import entity.NPC_Librarian;
import monster.MON_Slime;
import object.OBJ_Healing_Water;
import object.OBJ_Pit;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Pit(gp);
		gp.obj[0].worldX = gp.tileSize*32;
		gp.obj[0].worldY = gp.tileSize*17;
		
		gp.obj[1] = new OBJ_Healing_Water(gp);
		gp.obj[1].worldX = gp.tileSize*23;
		gp.obj[1].worldY = gp.tileSize*20;
		
	}
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_Librarian(gp);
		gp.npc[0].worldX = gp.tileSize*10;
		gp.npc[0].worldY = gp.tileSize*10;
	}
	
	public void setMonster() {
		
		gp.mon[0] = new MON_Slime(gp);
		gp.mon[0].worldX = gp.tileSize*23;
		gp.mon[0].worldY = gp.tileSize*40;
		
		gp.mon[1] = new MON_Slime(gp);
		gp.mon[1].worldX = gp.tileSize*22;
		gp.mon[1].worldY = gp.tileSize*40;
	}
}
