package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import world.Camera;
import world.World;


public class Player extends Entity{
	
	public double spd = 1.5;
	public boolean right,up,down,left;
	public int right_dir = 0, left_dir = 1;
	public int dir = right_dir;
	
	private int frames = 0,maxFrames = 5,index = 0, maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		
		for(int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16),0,16,16);
		}
		for(int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16),16,16,16);
		}
	}
	
	public void tick( ) {
		moved = false;
		if(right && World.isFree((int)(x+spd),this.getY())){
			moved = true;
			dir = right_dir;
			x+=spd;

		}else if(left && World.isFree((int)(x-spd),this.getY())) {
			moved = true;
			dir = left_dir;
			x-=spd;
		
		}
	if(up && World.isFree(this.getX(),(int)(y-spd))) {
		moved = true;
		y-=spd;
	}else if(down && World.isFree(this.getX(),(int)(y+spd))) {
		moved = true;
		y+=spd;
	}
	if(moved) {
		frames++;
		if(frames == maxFrames) {
			frames = 0;
			index++;
			if(index == maxIndex) 
				index = 0;
			}
		}
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0,World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0,World.HEIGHT*16 - Game.HEIGHT);
	}
	

	public void render(Graphics g) {
		if(dir == right_dir) {
		g.drawImage(rightPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		}else if(dir == left_dir) {
		g.drawImage(leftPlayer[index], this.getX() - Camera.x,this.getY() - Camera.y, null);
		}
	}


	public void setX(int i) {
		
		
	}

	public void setY(int i) {
		
		
	}

}
