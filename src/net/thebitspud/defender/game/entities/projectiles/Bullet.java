package net.thebitspud.defender.game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.main.GameManager;

public class Bullet extends Projectile {
	public Bullet(int x, int y, double velX, double velY, int damage, boolean friendly, boolean accelerated, GameManager gm) {
		super(x, y, 8, 2, velX, velY, damage, friendly, gm);
		
		this.accelerated = accelerated;
	}
	
	private boolean accelerated;

	@Override
	public void initValues() {
		destructible = false;
		
		speed = 15;
	}
	
	@Override
	public void kill() {
		active = false;
		gm.getAL().playSound("bullet_hit");
	}
	
	public void tickAI() {
		defaultAI();
	}
	
	public void render(Graphics g) {
		if(accelerated) g.setColor(new Color(200, 200, 0));
		else g.setColor(Color.GRAY);
		
		g.fillRect((int) (x - (speed * velX)) , (int) y, width, height);
	}
}
