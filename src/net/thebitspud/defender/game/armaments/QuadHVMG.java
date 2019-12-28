package net.thebitspud.defender.game.armaments;

import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.projectiles.Bullet;
import net.thebitspud.defender.game.resources.Ammunition;
import net.thebitspud.defender.main.GameManager;

public class QuadHVMG extends Armament {
	public QuadHVMG(Ammunition ammo, int fireRate, int keycode, GameManager gm, EntityManager em) {
		super(ammo, 2, fireRate, keycode, gm, em);
	}

	@Override
	public void init() {
		unlocked = true;
		icon = null; //fix
	}
	
	private int direction = 0;

	@Override
	public void fire() {
		switch(direction) {
		case 0:
			em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() + 10, 1.5, 0, 3, true, true, gm));
			em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 12, 1.5, 0, 3, true, true, gm));
			direction++;
			
			break;
		case 1:
			em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() - 7, 1.5, 0, 3, true, true, gm));
			em.addEntity(new Bullet(em.getPlayer().getXCentre(), em.getPlayer().getYCentre() + 5, 1.5, 0, 3, true, true, gm));
			direction = 0;
			break;
		default:
			direction = 0;
			break;
		}
		
		gm.getAL().playSound("dual_hmg");
		
		delay.reset();
	}
}
