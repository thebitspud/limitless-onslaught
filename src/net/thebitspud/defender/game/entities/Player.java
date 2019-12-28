package net.thebitspud.defender.game.entities;

import java.awt.Graphics;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.game.armaments.*;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

//the player class that the user controls

public class Player extends Entity {
	private Armament primary, secondary; //primary / secondary weapons systems
	private boolean scoreValid, moving;
	private Ammunition missiles, bullets;
	private Timer fireExhaust;
	private int rWidth, flameCycle;
	
	private long funds;
	
	public Player(int x, int y, GameManager gm) {
		super(x, y, 24, 32, gm);
		
		type = "player";
	}

	@Override
	public void kill() {
		game.endGame();
	}
	
	@Override
	public void initValues() {
		maxHP = 10; HP = maxHP;
		speed = 3;
		attack = 1;
		funds = 0;
		
		texture = gm.getAL().getPlayer();
		rWidth = 32;
		
		missiles = new Ammunition(300, 5, 150, gm);
		bullets = new Ammunition(1000, 50, 120, gm);
		
		//armaments
		
		primary = new SingleHMG(bullets, 7, 32, gm, em);
		secondary = new SingleML(missiles, 50, 77, gm, em);
		
		scoreValid = true;
		
		moving = false;
		fireExhaust = new Timer(8, true);
		flameCycle = 0;
	}
	
	public void resetLocation() {
		x = 100;
		y = 230;
	}

	@Override
	public void tick() {
		getInput();
		checkEntityCollisions();
		
		if(scoreValid && gm.getSettings().isInvincible()) scoreValid = false;
		
		if(fireExhaust.isActivated()) {
			if(flameCycle < 3) flameCycle++;
			else flameCycle = 0;
		}
		
		//ticking weapons
		
		bullets.tick();
		missiles.tick();
		
		primary.tick();
		secondary.tick();
	}
	
	public void checkEntityCollisions() {
		for(Entity e : em.getEntities()) {
			if(getCollisionBounds().intersects(e.getCollisionBounds())) {
				if(e.getType() == "mob_enemy") {
					e.kill();
					gm.getAL().playSound("laser_hit");
					if(!gm.getSettings().isInvincible()) damage(e.getAttack());
				}else if(e.getType() == "projectile_enemy") {
					e.kill();
					if(!gm.getSettings().isInvincible()) damage(e.getAttack());
				}
			}
		}
	}
	
	public void getInput() {
		moving = false;
		
		if(gm.getKeyboard().getKey(87)) {
			y -= speed; //W / up

			if(y < 0) y = 0;
		}
		
		if(gm.getKeyboard().getKey(65)) {
			x -= speed; //A / left
			
			if(x < 0) x = 0;
		}
		
		if(gm.getKeyboard().getKey(83)) {
			y += speed; //S / down

			if(y + height > 416) y = 416 - height;
		}
		
		if(gm.getKeyboard().getKey(68)) {
			x += speed; //D / right
			moving = true;
			
			if(x + rWidth > 800) x = 800 - rWidth;
		}
	}
	
	public void render(Graphics g) {
		if(moving) g.drawImage(gm.getAL().getFlame(flameCycle), (int) x - 14, (int) y, 16, height, null);
		else g.drawImage(gm.getAL().getFlame(flameCycle + 4), (int) x - 14, (int) y, 16, height, null);
		
		g.drawImage(texture, (int) x, (int) y, rWidth, height, null);
	}
	
	//getters and setters
	
	public Ammunition getMissiles() {
		return missiles;
	}
	
	public Ammunition getBullets() {
		return bullets;
	}
	
	public Armament getPrimary() {
		return primary;
	}
	
	public Armament getSecondary() {
		return secondary;
	}
	
	public long getFunds() {
		return funds;
	}
	
	public void setFunds(long money) {
		funds += money;
		
		if(funds < 0) funds = 0;
	}
	
	public void setPrimary(Armament p) {
		this.primary = p;
	}
	
	public void setSecondary(Armament s) {
		this.secondary = s;
	}
	
	public boolean isScoreValid() {
		return scoreValid;
	}

	public EntityManager getEM() {
		return em;
	}
}
