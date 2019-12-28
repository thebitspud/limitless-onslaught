package net.thebitspud.defender.game.armaments;

import java.awt.image.BufferedImage;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public abstract class Armament {
	protected GameManager gm;
	protected EntityManager em;
	
	protected Timer delay;
	protected Ammunition ammo;
	protected BufferedImage icon;
	protected int ammoPerCycle, keycode;
	protected boolean unlocked;
	
	public Armament(Ammunition ammo, int ammoPerCycle, int fireDelay, int keycode, GameManager gm, EntityManager em) {
		this.gm = gm;
		this.em = em;
		this.ammo = ammo;
		this.ammoPerCycle = ammoPerCycle;
		this.keycode = keycode;
		
		this.delay = new Timer(fireDelay, false);
	}
	
	public void tick() {
		if(delay.isActivated() && gm.getKeyboard().getKey(keycode) && !ammo.isReloading() && ammo.getCurrentClip() > 0) {
			ammo.setCurrentClip(ammoPerCycle);
			fire();
		}
	}
	
	public abstract void init();
	public abstract void fire();
	
	//getters and setters
	
	public Timer getDelay() {
		return delay;
	}
}
