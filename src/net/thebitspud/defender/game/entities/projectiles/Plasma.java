package net.thebitspud.defender.game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.main.GameManager;

public class Plasma extends Projectile {
	public Plasma(int x, int y, double velX, double velY, int damage, boolean friendly, GameManager gm) {
		super(x, y, 64, 2, velX, velY, damage, friendly, gm);
	}

	@Override
	public void initValues() {
		destructible = false;
		
		speed = 12;
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
		g.setColor(new Color(0, 200, 200));
		g.fillRect((int) x, (int) y, width, height);
	}
}
