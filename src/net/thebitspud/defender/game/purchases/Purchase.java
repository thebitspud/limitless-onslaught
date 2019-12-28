package net.thebitspud.defender.game.purchases;

import net.thebitspud.defender.game.entities.Player;
import net.thebitspud.defender.main.GameManager;

//purchases that augment your weapons and stats 

public abstract class Purchase {
	protected GameManager gm;
	protected Player player;
		
	protected int cost, tier, maxTier;
	protected boolean available, limited;
		
	public Purchase(boolean limited, GameManager gm) {
		this.gm = gm;
		this.limited = limited;
			
		available = true; //if purchasable, the upgrade will be aquirable
	}
		
	public abstract void init();
	public abstract void reset();
		
	public void initGameClasses() {
		player = gm.getGame().getStage().getEM().getPlayer();
	}
		
	public void buy() {
		if(available) {
			if(limited) {
				if(tier < maxTier) if(player.getFunds() >= cost) {
					player.setFunds(-cost);
					aquire();
					tier++;
				}
				
				if(tier >= maxTier) setAvailable(false);
			}else{
				if(player.getFunds() >= cost) {
					player.setFunds(-cost);
					aquire();
					tier++;
				}
			}
		}
	}
		
	public abstract void aquire();
		
	//getters and setters

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getCost() {
		return cost;
	}
	
	public int getTier() {
		return tier;
	}
	
	public int getMaxTier() {
		return maxTier;
	}
}
