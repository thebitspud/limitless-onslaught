package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.main.GameManager;

public class Probe extends Mob {
	public Probe(int x, int y, GameManager gm) {
		super(x, y, 16, 16, gm);
	}
	
	@Override
	public void initValues() {
		maxHP = 3; HP = maxHP;
		speed = 0.5;
		pointValue = 5;
		coinValue = 1;
		attack = 1;
		
		texture = gm.getAL().getMob(0, 0);
	}

	@Override
	public void tickAI() {
		defaultMoveAI();
	}
}
