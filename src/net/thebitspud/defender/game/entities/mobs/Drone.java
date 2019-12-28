package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.game.entities.projectiles.Laser;
import net.thebitspud.defender.main.GameManager;

//the drone mob

public class Drone extends Mob {
	private Timer fireProjectile;
	
	public Drone(int x, int y, GameManager gm) {
		super(x, y, 16, 16, gm);
	}
	
	@Override
	public void initValues() {
		maxHP = 5; HP = maxHP;
		speed = 0.25;
		pointValue = 15;
		coinValue = 3;
		attack = 2;
		
		fireProjectile = new Timer(120, true);
		
		texture = gm.getAL().getMob(0, 1);
	}

	@Override
	public void tickAI() {
		defaultMoveAI();
		
		shootLaser();
	}
	
	public void shootLaser() {
		if(fireProjectile.isActivated()) {
			em.addEntity(new Laser((int) x, (int) y + (width / 2) - 1, 1, 0, 1, false, gm));
			gm.getAL().playSound("single_laser");
		}
	}
}