package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Bullet;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class TripleHMG extends Armament {
	public TripleHMG(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 3, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null;
	}

	@Override
	public void fire() {
		em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() + 6, 1.2, 0.01, 1, true, false, gm));
		em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 1, 1.2, 0, 3, true, true, gm));
		em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 8, 1.2, -0.01, 1, true, false, gm));
		gm.getAL().playSound("triple_hmg");
		
		delay.reset();
	}
}
