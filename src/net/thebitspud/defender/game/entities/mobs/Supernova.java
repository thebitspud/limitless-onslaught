package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.main.GameManager;

public class Supernova extends Mob {
	public Supernova(int x, int y, GameManager gm) {
		super(x, y, 32, 32, gm);
	}
	
	@Override
	public void initValues() {
		maxHP = 690; HP = maxHP;
		speed = 0.25;
		coinValue = 50;
		pointValue = 250;
		attack = 25;
		
		texture = gm.getAL().getMob(2, 0);
	}

	@Override
	public void tickAI() {
		defaultMoveAI();
	}
}
