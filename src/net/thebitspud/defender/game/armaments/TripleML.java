package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Missile;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class TripleML extends Armament {
	public TripleML(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 3, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null;
	}

	@Override
	public void fire() {
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.45, -0.15, 2, false, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.5, 0, 2, false, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.45, 0.15, 2, false, gm));
		gm.getAL().playSound("triple_missile");
		
		delay.reset();
	}
}
