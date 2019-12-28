package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Bullet;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class SingleHMG extends Armament {
	public SingleHMG(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 1, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null; //fix
	}

	@Override
	public void fire() {
		em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 1, 1, 0, 1, true, false, gm));
		gm.getAL().playSound("single_hmg");
		
		delay.reset();
	}
}