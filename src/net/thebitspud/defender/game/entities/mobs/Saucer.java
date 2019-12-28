package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.game.entities.projectiles.Laser;
import net.thebitspud.defender.main.GameManager;

public class Saucer extends Mob {
	private Timer fireProjectile, spawnMob;
	private int type;
	
	public Saucer(int x, int y, int type, GameManager gm) {
		super(x, y, 32, 32, gm);
		
		this.type = type;
	}
	
	@Override
	public void initValues() {
		if(type == 0) {
			maxHP = 25;
			pointValue = 40;
			coinValue = 8;
			
			spawnMob = new Timer(200, true);
		}else if(type == 1) {
			maxHP = 50;
			pointValue = 50;
			coinValue = 10;
			
			spawnMob = new Timer(180, true);
		}else {
			maxHP = 100;
			pointValue = 75;
			coinValue = 15;
			
			spawnMob = new Timer(150, true);
		}
		
		speed = 0.25;
		HP = maxHP;
		attack = 5;
		
		fireProjectile = new Timer(60, true);
		
		texture = gm.getAL().getMob(1, 0);
	}
	
	private int action, lastDirection;

	@Override
	public void tickAI() {
		action = r.nextInt(60);
		
		if(y == 0) lastDirection = 0;
		else if (y == 416 - width) lastDirection = 1;
		else if(action <= 1) lastDirection = action;
		driftMoveAI(lastDirection);
		
		shootLaser();
		spawnMinion();
	}
	
	public void shootLaser() {
		if(fireProjectile.isActivated()) {
			em.addEntity(new Laser((int) x, (int) y + (width / 2) - 1, 1, 0, 1, false, gm));
			gm.getAL().playSound("single_laser");
		}
	}
	
	public void spawnMinion() {
		if(spawnMob.isActivated()) {
			if(type == 0) em.addEntity(new Probe((int) x + 8, (int) y + 8, gm));
			else if(type == 1) em.addEntity(new Drifter((int) x + 8, (int) y + 8, gm));
			else em.addEntity(new Mine((int) x + 8, (int) y + 8, 1, 0, gm));
		}
	}
}
