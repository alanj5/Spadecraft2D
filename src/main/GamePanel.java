package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable{
	
	//Screen settings
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity mon[] = new Entity[10];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	public Player player = new Player(this,keyH);
	public AssetSetter aSetter = new AssetSetter(this);
	
	
	//Defining game states
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	
	public GamePanel() {
		
		//Determines game window attributes
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		//Sets non-game loop attributes up
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		//playMusic(0);
		gameState = titleState;
	}
	public void retry() {
		player.setDefaultPositions();
		player.resetLife();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
	}
	
	public void startGameThread() {
		
		//Starts main game loop
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		//Main game loop
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {
			
			update();
			
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
			
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}
	public void update() {
		
		//Updates for every game frame
		if(gameState == playState) {
			player.update();
			
			for(int i = 0;i< npc.length;i++) {
				if(npc[i]  != null) {
					npc[i].update();
				}
			}
			for(int i = 0;i< mon.length;i++) {
				if(mon[i]  != null) {
					mon[i].update();
				}
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		
		//Draw actions for every game frame
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		else {
			tileM.draw(g2);
			
			entityList.add(player);
			
			for(int i= 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for(int i= 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			for(int i= 0; i < mon.length; i++) {
				if(mon[i] != null) {
					entityList.add(mon [i]);
				}
			}
			
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
			});	
			
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw((Graphics2D) g2);
			}
			
			entityList.clear();
			
			ui.draw((Graphics2D) g2);
		}
	}
	
	public void playMusic(int i) {
		
		//Plays music
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		//Stops music
		music.stop();
	}
	
	public void playSE(int i) {
		
		//Plays sound effects
		se.setFile(i);
		se.play();
	}
}















