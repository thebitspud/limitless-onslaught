package net.thebitspud.defender.game.resources;

import net.thebitspud.defender.main.GameManager;

public abstract class Resource {
	protected GameManager gm;
	protected double amount, maxAmount;
	
	public Resource(int amount, int maxAmount, GameManager gm) {
		this.amount = amount;
		this.maxAmount = maxAmount;
		this.gm = gm;
	}
	
	public abstract void tick();

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount += amount;
		
		if(this.amount < 0) this.amount = 0;
		if(this.amount > maxAmount) this.amount = maxAmount;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(double amount) {
		this.maxAmount += maxAmount;
	}
}
