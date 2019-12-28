package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Missile;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class SingleML extends Armament {
	public SingleML(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 1, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null;
	}

	@Override
	public void fire() {
		em.addEntity(new Missile(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 2, 1, 0, 2, false, gm));
		gm.getAL().playSound("single_missile");
		
		delay.reset();
	}
}