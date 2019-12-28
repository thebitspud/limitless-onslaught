package net.thebitspud.defender.game.entities.projectiles;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.game.entities.Explosion;
import net.thebitspud.defender.main.GameManager;

public class Missile extends Projectile {
	private boolean augmented, destroyed;
	
	public Missile(int x, int y, double velX, double velY, int damage, boolean augmented, GameManager gm) {
		super(x, y, 8, 4, velX, velY, damage, true, gm);
		
		this.augmented = augmented;
	}

	@Override
	public void initValues() {
		destructible = false;
		destroyed = false;
		
		speed = 5;
	}
	
	@Override
	public void kill() {
		attack = 0;
		type = "placeholder";
		
		gm.getAL().playSound("missile_hit");
		destroyed = true;
	}
	
	public void remove() {
		active = false;
		
		if(augmented) em.addEntity(new Explosion((int) (x - 52), (int) y - 47, 5, true, gm));
		else em.addEntity(new Explosion((int) (x - 36), (int) y - 31, 3, false, gm));
	}
	
	public void tickAI() {
		if(destroyed) remove();
		defaultAI();
	}
	
	public void render(Graphics g) {
		if(augmented) g.setColor(new Color(200, 75, 0));
		else g.setColor(new Color(150, 0, 150));
		g.fillRect((int) (x - (speed * velX)), (int) y, width, height);
	}
}
