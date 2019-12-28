package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Bullet;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class DualHMG extends Armament {
	public DualHMG(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 2, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null; //fix
	}

	@Override
	public void fire() {
		em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() + 2, 1, 0, 1, true, false, gm));
		em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 4, 1, 0, 1, true, false, gm));
		gm.getAL().playSound("dual_hmg");
		
		delay.reset();
	}
}
