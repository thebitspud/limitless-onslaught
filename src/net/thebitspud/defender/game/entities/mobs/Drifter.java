package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.main.GameManager;

public class Drifter extends Mob {
	public Drifter(int x, int y, GameManager gm) {
		super(x, y, 16, 16, gm);
	}
	
	@Override
	public void initValues() {
		maxHP = 5; HP = maxHP;
		speed = 0.5;
		pointValue = 10;
		coinValue = 2;
		attack = 1;
		
		texture = gm.getAL().getMob(0, 3);
	}
	
	private int action, lastDirection;

	@Override
	public void tickAI() {
		action = r.nextInt(90);
		
		if(y == 0) lastDirection = 0;
		else if (y == 416 - width) lastDirection = 1;
		else if(action <= 1) lastDirection = action;
		driftMoveAI(lastDirection);
	}
}