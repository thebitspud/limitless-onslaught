package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.game.entities.projectiles.Laser;
import net.thebitspud.defender.main.GameManager;

public class SaucerV2 extends Mob {
	private Timer fireProjectile;
	private int type;
	
	public SaucerV2(int x, int y, int type, GameManager gm) {
		super(x, y, 32, 32, gm);
		
		this.type = type;
	}
	
	@Override
	public void initValues() {
		if(type == 0) {
			maxHP = 50; 
			pointValue = 80;
			coinValue = 16;
		}else if(type == 1) {
			maxHP = 80;
			pointValue = 100;
			coinValue = 20;
		}else{
			maxHP = 125; 
			pointValue = 125;
			coinValue = 25;
		}
		
		HP = maxHP;
		speed = 0.25;
		attack = 8;
		
		fireProjectile = new Timer(60, true);
		
		texture = gm.getAL().getMob(1, 1);
	}
	
	private int action, lastDirection;

	@Override
	public void tickAI() {
		action = r.nextInt(50);
		
		if(y == 0) lastDirection = 0;
		else if (y == 416 - width) lastDirection = 1;
		else if(action <= 1) lastDirection = action;
		driftMoveAI(lastDirection);
		
		shootLaser();
	}
	
	public void shootLaser() {
		if(fireProjectile.isActivated()) {
			em.addEntity(new Laser((int) x, (int) y + (width / 2) - 5, 0.9, 0.2, 1, false, gm));
			em.addEntity(new Laser((int) x, (int) y + (width / 2) - 1, 1, 0, 1, false, gm));
			em.addEntity(new Laser((int) x, (int) y + (width / 2) + 3, 0.9, -0.2, 1, false, gm));
			gm.getAL().playSound("triple_laser");
		}
	}
}
