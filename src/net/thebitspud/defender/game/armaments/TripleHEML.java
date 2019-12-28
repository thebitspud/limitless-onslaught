package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Missile;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class TripleHEML extends Armament {
	public TripleHEML(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 3, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null;
	}

	@Override
	public void fire() {
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.92, -0.15, 2, true, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 2, 0, 2, true, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.92, 0.15, 2, true, gm));
		
		gm.getAL().playSound("triple_missile");
		
		delay.reset();
	}
}
