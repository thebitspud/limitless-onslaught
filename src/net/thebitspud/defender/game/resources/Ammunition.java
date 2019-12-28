package net.thebitspud.defender.game.resources;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.main.GameManager;

//any amuntion used by the player

public class Ammunition extends Resource {
	private int amountPerClip, currentClip;
	private Timer reload;
	private boolean reloading;
	
	public Ammunition(int amount, int amountPerClip, int reloadTime, GameManager gm) {
		super(amount, 1000000000, gm);
		
		this.amountPerClip = amountPerClip;
		this.currentClip = amountPerClip;
		
		reload = new Timer(reloadTime, true);
		
		reloading = false;
	}

	@Override
	public void tick() {
		//ticking for reloads
		
		if(reloading) {
			if(reload.isActivated()) {
				reload();
				reloading = false;
			}
		}else if(currentClip <= 0 || gm.getKeyboard().getJustPressed(82)) reloading = true;
	}
	
	//reloading a weapons system
	
	public void reload() {
		if(amount > (amountPerClip - currentClip)) {
			amount -= (amountPerClip - currentClip);
			currentClip += (amountPerClip - currentClip);
		}else if(amount > 0) {
			currentClip += amount;
			amount = 0;
		}
	}
	
	//getters and setters
	
	public int getAmountPerClip() {
		return amountPerClip;
	}
	
	public int getCurrentClip() {
		return currentClip;
	}
	
	public void setCurrentClip(int amount) {
		currentClip -= amount;
	}
	
	public boolean isReloading() {
		return reloading;
	}

	public Timer getReload() {
		return reload;
	}

	public void setReload(Timer reload) {
		this.reload = reload;
	}

	public void setAmountPerClip(int amountPerClip) {
		this.amountPerClip = amountPerClip;
	}
}