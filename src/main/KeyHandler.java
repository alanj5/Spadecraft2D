package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		//Handles different key presses
		int code = e.getKeyCode();
		
		//Title state
		if(gp.gameState == gp.titleState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 1) {
					gp.gameState = gp.playState;
					gp.playMusic(0);
				}
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
		}
		
		//Play state 
		else if(gp.gameState == gp.playState) {
			if(code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
			}
			if(code == KeyEvent.VK_C) {
				gp.gameState = gp.characterState;
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.gameState = gp.optionsState;
			}
			if(code == KeyEvent.VK_ENTER) {
				enterPressed = true;
			}
		}	
		//Pause state
		else if(gp.gameState == gp.pauseState) {
			if(code == KeyEvent.VK_P) {
				gp.gameState = gp.playState;
			}	
		}
		//Dialogue state
		else if(gp.gameState == gp.dialogueState) {
			if(code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		//Character state
		else if(gp.gameState == gp.characterState) {
			if(code == KeyEvent.VK_C) {
				gp.gameState = gp.playState;
			}
		}
		//Options state	
		else if(gp.gameState == gp.optionsState) {	
			if(code == KeyEvent.VK_ESCAPE) {	
				gp.gameState = gp.playState;
				}
			
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 3) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_A) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
				}
				if(gp.ui.commandNum == 1 && gp.se.volumeScale > 0) {
					gp.se.volumeScale--;
				}
			}
			if(code == KeyEvent.VK_D) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
				}
				if(gp.ui.commandNum == 1 && gp.se.volumeScale < 5) {
					gp.se.volumeScale++;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
				if(gp.ui.commandNum == 3) {
					gp.gameState = gp.playState;
				}
				
			}
		}
		//Game over state
		else if(gp.gameState == gp.gameOverState) {
			if(code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.titleState;
					gp.stopMusic();
					gp.retry();
				}
				if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
 
}










