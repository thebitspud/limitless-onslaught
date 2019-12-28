package net.thebitspud.defender.game.entities;

import java.awt.Graphics;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.main.GameManager;

public class Explosion extends Entity {
	private Timer animation;
	private boolean large;
	
	public Explosion(int x, int y, int damage, boolean large, GameManager gm) {
		super(x, y, 64, 64, gm);
		if(large) { width = 96; height = 96; }
		
		this.attack = damage;
		this.large = large;
		
		type = "explosion";
	}
	
	@Override
	public void initValues() {
		texture = gm.getAL().getExplosion(1, 0);
		
		if(large) animation = new Timer(35, false);
		else animation = new Timer(15, false);
	}
	
	public void checkEntityCollisions() {
		for(Entity e : em.getEntities())
			if(e.getType() == "mob_enemy")
				if(getCollisionBounds().intersects(e.getCollisionBounds()))
					e.damage(attack);
	}

	@Override
	public void kill() {
		active = false;
	}
	
	@Override
	public void tick() {
		if(animation.isActivated()) kill();
		if(animation.getTicks() == 1) checkEntityCollisions();
		
		if(large) texture = gm.getAL().getExplosion(2, (int) (animation.getTicks() / 3));
		else texture = gm.getAL().getExplosion(1, (int) (animation.getTicks() / 2));
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(texture, (int) x, (int) y, width, height, null);
	}
}
