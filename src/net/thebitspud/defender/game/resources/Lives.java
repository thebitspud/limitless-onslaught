package net.thebitspud.defender.game.resources;

import net.thebitspud.defender.main.GameManager;

public class Lives extends Resource {
	public Lives(int amount, int maxAmount, GameManager gm) {
		super(amount, maxAmount, gm);
	}

	@Override
	public void tick() {
		if(amount <= 0) gm.getGame().endGame();
	}
}
