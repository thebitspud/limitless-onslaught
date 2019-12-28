package net.thebitspud.defender.game.entities.projectiles;

import net.thebitspud.defender.game.entities.Entity;
import net.thebitspud.defender.main.GameManager;

public abstract class Projectile extends Entity {
	protected boolean destructible, friendly;
	protected double velX, velY;
	
	public Projectile(int x, int y, int width, int height, double velX, double velY, int damage, boolean friendly, GameManager gm) {
		super(x, y, width, height, gm);
		this.friendly = friendly;
		this.velX = velX;
		this.velY = velY;
		this.attack = damage;
		
		if(friendly) type = "projectile_friendly";
		else type = "projectile_enemy";
	}

	@Override
	public abstract void kill();

	@Override
	public void tick() {
		tickAI();
		checkBounds();
	}
	
	public abstract void tickAI();
	
	public void defaultAI() {
		if(friendly) {
			x += speed * velX;
			y += speed * velY;
		}else{
			x -= speed * velX;
			y -= speed * velY;
		}
	}
	
	public void checkBounds() {
		if(x < 0 - width || x > gm.getProgram().getWidth() + 20
				|| y < 0 - height || y > 416) active = false;
	}
	
	public boolean isDestructible() {
		return destructible;
	}
	
	public boolean isFriendly() {
		return friendly;
	}
	
	public void setFriendly(boolean friendly) {
		this.friendly = friendly;
	}
}
