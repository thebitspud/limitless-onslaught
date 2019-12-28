package net.thebitspud.defender.game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.main.GameManager;

public class Laser extends Projectile {
	public Laser(int x, int y, double velX, double velY, int damage, boolean friendly, GameManager gm) {
		super(x, y, 16, 2, velX, velY, damage, friendly, gm);
	}

	@Override
	public void initValues() {
		destructible = false;
		
		speed = 8;
	}
	
	@Override
	public void kill() {
		active = false;
		gm.getAL().playSound("laser_hit");
	}
	
	public void tickAI() {
		defaultAI();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);
	}
}
