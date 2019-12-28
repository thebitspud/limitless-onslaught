package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.game.entities.projectiles.Plasma;
import net.thebitspud.defender.main.GameManager;

public class Vaporizer extends Mob {
	private Timer fireProjectile;
	private int type;
	
	public Vaporizer(int x, int y, int type, GameManager gm) {
		super(x, y, 32, 32, gm);
		
		this.type = type;
	}
	
	@Override
	public void initValues() {
		if(type == 0) {
			maxHP = 25; 
			pointValue = 60;
			coinValue = 12;
		}else if(type == 1) {
			maxHP = 40; 
			pointValue = 75;
			coinValue = 15;
		}else{
			maxHP = 80; 
			pointValue = 100;
			coinValue = 20;
		}
		
		HP = maxHP;
		speed = 0.25;
		attack = 5;
		
		fireProjectile = new Timer(50, true);
		
		texture = gm.getAL().getMob(1, 3);
	}
	
	private int action, lastDirection;

	@Override
	public void tickAI() {
		action = r.nextInt(90);
		
		if(y == 0) lastDirection = 0;
		else if (y == 416 - width) lastDirection = 1;
		else if(action <= 1) lastDirection = action;
		
		driftMoveAI(lastDirection);
		
		shootPlasma();
	}
	
	public void shootPlasma() {
		if(fireProjectile.isActivated()) {
			em.addEntity(new Plasma((int) x - 32, (int) y + (width / 2) - 1, 1, 0, 3, false, gm));
			gm.getAL().playSound("plasma");
		}
	}
}
