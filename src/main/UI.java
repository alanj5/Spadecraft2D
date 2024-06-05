package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Entity;
import object.OBJ_Heart;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	
	BufferedImage heart_full, heart_half, heart_blank;
	
	Font arial_40, algerian_40, algerian_80B;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	public String currentDialogue = "";
	
	public int commandNum = 0;
	
	int subState = 0;
	
	public UI(GamePanel gp) {
		
		//Sets fonts and images
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		algerian_80B = new Font("Constantia", Font.BOLD, 80);
		algerian_40 = new Font("Constantia", Font.PLAIN, 40);
		
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	public void showMessage(String text) {
		
		//Displays text
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		//Draw method for each frame
		this.g2 = g2;
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if(gp.gameState == gp.playState) {
			drawPlayerLife();
		}
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();	
			drawPlayerLife();
		}
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
			drawPlayerLife();
		}
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawPlayerLife();
		}
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
			drawPlayerLife();
		}
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
			drawPlayerLife();
			gp.stopMusic();
		}
	}
	public void drawPlayerLife() {
		
		//Player heart status
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x+= gp.tileSize;
		}
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, gp);
			}
			i++;
			x += gp.tileSize;
		}
	}
	
	public void drawTitleScreen() {
		
		//Title screen
		g2.setFont(algerian_80B);
		String text = "SPADECRAFT 2D";
		int x = getXforCentredText(text);
		int y = gp.tileSize*3;
		
		g2.setColor(Color.gray);
		g2.drawString(text, x+3, y+3);
		
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		x = gp.screenWidth/2 - gp.tileSize*3/2;
		y += gp.tileSize*3/2;
		g2.drawImage(gp.player.down1, x, y, gp.tileSize*3, gp.tileSize*3, null);
		
		g2.setFont(algerian_40);
		
		text = "PLAY GAME";
		x = getXforCentredText(text);
		y += gp.tileSize*5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-gp.tileSize, y-4);
		}
		
		text = "EXIT";
		x = getXforCentredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-gp.tileSize, y-4);
		}
	}
	
	public void drawPauseScreen() {
		
		//Pause screen
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text = "PAUSED";
		int x = getXforCentredText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
	}
	public void drawDialogueScreen() {
		
		//Dialogue window and text
		int x = gp.tileSize*4;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*5);
		int height = gp.tileSize*4;
		
		drawSubWindow(x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+=40;
		}
	}
	public void drawCharacterScreen() {
		
		//Character screen
		final int frameX = gp.tileSize*2;
		final int frameY = gp.tileSize*2;
		final int frameWidth = gp.tileSize*5;
		final int frameHeight = gp.tileSize*9;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
		
		int textX = frameX + 20;
		int textY = frameY + 40;
		final int lineHeight = 35;
		
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coin", textX, textY);
		textY += lineHeight;
		
		int tailX = (frameX + frameWidth) - 20;
		textY = frameY + 40;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
	}
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		String text = "Game Over";
		int x = getXforCentredText(text)-gp.tileSize*2;
		int y = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,80F));
		g2.setColor(Color.black);
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,36F));
		text = "Retry";
		x = getXforCentredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x - 25, y);
		}
		
		text = "Quit";
		x = getXforCentredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x - 25, y);
		}
	}
	public void drawOptionsScreen() {
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,30F));
		
		int frameX = gp.tileSize*4;
		int frameY = gp.tileSize;
		int frameWidth = gp.tileSize*8;
		int frameHeight = gp.tileSize*10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		options_top(frameX, frameY);
			
	}
	public void options_top(int frameX, int frameY) {
		
		int textX;
		int textY;
		String text = "Options";
		textX = getXforCentredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,25F));
		textX = frameX + gp.tileSize;
		textY += gp.tileSize*5/2;
		g2.drawString("Music", textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
		}
		
		textY += gp.tileSize;
		g2.drawString("Sound effects", textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
		}
		
		textY += gp.tileSize;
		g2.drawString("End game", textX, textY);
		if(commandNum == 2) {
			g2.drawString(">", textX - 25, textY);
		}
		
		String text2 = "Back";
		textX = getXforCentredText(text2);
		textY += gp.tileSize*5/2;
		g2.drawString(text2, textX, textY);
		if(commandNum == 3) {
			g2.drawString(">", textX - 25, textY);
		}
		
		textX = frameX + gp.tileSize*5;
		textY = frameY + gp.tileSize*3 + 4;
		g2.drawRect(textX, textY, 120, 24);
		int volumeWidth = 24*gp.music.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
		textY += gp.tileSize;
		g2.drawRect(textX, textY, 120, 24);
		volumeWidth = 24*gp.se.volumeScale;
		g2.fillRect(textX, textY, volumeWidth, 24);
		
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		
		//Draws mini-windows
		Color c = new Color(0,0,0,200);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	public int getXforCentredText(String text) {
		
		//Finds centre of screen
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		return x;
	}
	public int getXforAlignToRightText(String text, int tailX) {
		
		//Finds right of screen
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}





