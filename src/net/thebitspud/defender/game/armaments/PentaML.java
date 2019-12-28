package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Missile;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class PentaML extends Armament {
	public PentaML(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 5, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null;
	}

	@Override
	public void fire() {
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.90, -0.3, 2, false, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.96, -0.15, 2, false, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 2, 0, 5, false, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.96, 0.15, 2, false, gm));
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1.90, 0.3, 2, false, gm));
		gm.getAL().playSound("penta_missile");
		
		delay.reset();
	}
}
