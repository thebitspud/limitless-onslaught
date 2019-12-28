package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.main.GameManager;

public class Shield extends Mob {
	public Shield(int x, int y, GameManager gm) {
		super(x, y, 32, 32, gm);
	}
	
	@Override
	public void initValues() {
		maxHP = 100; HP = maxHP;
		speed = 0.25;
		pointValue = 40;
		coinValue = 8;
		attack = 3;
		
		texture = gm.getAL().getMob(1, 2);
	}

	@Override
	public void tickAI() {
		defaultMoveAI();
	}
}
