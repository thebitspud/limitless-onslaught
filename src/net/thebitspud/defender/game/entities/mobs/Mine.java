package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.main.GameManager;

public class Mine extends Mob {
	private double velX, velY;
	
	public Mine(int x, int y, double velX, double velY,  GameManager gm) {
		super(x, y, 16, 16, gm);
		
		this.velX = velX;
		this.velY = velY;
	}
	
	@Override
	public void initValues() {
		maxHP = 10; HP = maxHP;
		speed = 0.75;
		pointValue = 15;
		coinValue = 3;
		attack = 3;
		
		texture = gm.getAL().getMob(0, 2);
	}
	
	//lazy temporary AI

	@Override
	public void tickAI() {
		x -= velX * speed;
		y += velY * speed;
		
		if(y > 416 - width) {
			y = 416 - width;
			velY = -velY;
		}
		
		if(y < 0) {
			y = 0;
			velY = -velY;
		}
		
		if(x > 800) velX = -velX;
	}
}