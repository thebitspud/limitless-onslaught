package net.thebitspud.defender.game.entities.mobs;

import java.awt.Graphics;

import net.thebitspud.defender.game.entities.Entity;
import net.thebitspud.defender.main.GameManager;

public abstract class Mob extends Entity {
	protected int pointValue, coinValue;
	
	public Mob(int x, int y, int width, int height, GameManager gm) {
		super(x, y, width, height, gm);
		
		type = "mob_enemy";
	}

	@Override
	public void kill() {
		active = false;
		em.getPlayer().setFunds(coinValue);
		gm.getGame().getScore().setPoints(pointValue);
	}

	@Override
	public void tick() {
		tickAI();
		
		if(x <= 0 - width) {
			kill();
			gm.getAL().playSound("laser_hit");
			if(!gm.getSettings().isInvincible()) gm.getGame().getLives().setAmount(-attack);
			return;
		}
		
		checkEntityCollisions();
	}
	
	public abstract void tickAI();
	
	public void defaultMoveAI() {
		x -= speed;
	}
	
	public void driftMoveAI(int direction) {
		x -= speed;
		
		if(direction == 0) {
			y += speed;
			if(y > 416 - width) y = 416 - width;
		}else if(direction == 1) {
			y -= speed;
			if(y < 0) y = 0;
		}
	}

	public void checkEntityCollisions() {
		for(Entity e : em.getEntities()) {
			if(e.getType() == "projectile_friendly") {
				if(getCollisionBounds().intersects(e.getCollisionBounds())) {
					damage(e.getAttack());
					e.kill();
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(texture, (int) x, (int) y, width, height, null);
	}
}