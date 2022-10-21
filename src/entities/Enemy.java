package entities;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.DebugGraphics;

import main.Game;
import world.World;

public class Enemy  extends Entity {
	
	private double spd = 1;
	
	private int frames = 0,maxFrames = 5,index = 0, maxIndex = 1;
	
	private Image sprites;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(112, 16, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(112+16, 16, 16, 16);
		
	}

	public void tick() {
		if(Game.rand.nextInt(100) < 30);
		if((int)x < Game.player.getX() && World.isFree((int)(x+spd),this.getY())) {
			x+=spd;
		}else if((int)x > Game.player.getX() && World.isFree((int)(x+spd),this.getY())) {
			x-=spd;
		}
		if((int)y < Game.player.getY() && World.isFree(this.getX(),(int)(y+spd))) {
			y+=spd;
		}else if((int)y > Game.player.getY() && World.isFree(this.getX(),(int)(y-spd))) {
			y-=spd;
		}
		
		
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index == maxIndex) 
				index = 0;
		}
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprites[index], getX(), getY(), null);
		//ai esta o erro n quer reconhecer o graphics e não quer importar por algum motivo.
	}
	
}
	
	
