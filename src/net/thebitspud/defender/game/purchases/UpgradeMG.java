package net.thebitspud.defender.game.purchases;

import net.thebitspud.defender.game.armaments.DualHMG;
import net.thebitspud.defender.game.armaments.QuadHVMG;
import net.thebitspud.defender.game.armaments.TripleHMG;
import net.thebitspud.defender.main.GameManager;

//improves the stats and function of the machine gun

public class UpgradeMG extends Purchase {
	public UpgradeMG(GameManager gm) {
		super(true, gm);
	}

	@Override
	public void init() {
		initGameClasses();
		
		reset();
	}
	
	public void reset() {
		setAvailable(true);
		
		tier = 0;
		maxTier = 8;
		cost = 5;
	}

	@Override
	public void aquire() {
		switch(tier) {
		case 0:
			player.getPrimary().getDelay().setDelay(6);
			cost = 10;
			
			break;
		case 1:
			player.getPrimary().getDelay().setDelay(5);
			player.getBullets().getReload().setDelay(75);
			cost = 25;
			
			break;
		case 2:
			player.getPrimary().getDelay().setDelay(4);
			player.getBullets().setAmountPerClip(100);
			cost = 60;
			
			break;
		case 3:
			player.setPrimary(new DualHMG(player.getBullets(), 6, 32, gm, player.getEM()));
			cost = 100;
			
			break;
		case 4:
			player.getPrimary().getDelay().setDelay(4);
			player.getBullets().setAmountPerClip(300);
			cost = 225;
			
			break;
		case 5:
			player.setPrimary(new TripleHMG(player.getBullets(), 7, 32, gm, player.getEM()));
			cost = 400;
			
			break;
		case 6:
			player.getPrimary().getDelay().setDelay(5);
			player.getBullets().getReload().setDelay(25);
			cost = 750;
			
			break;
		case 7:
			player.getBullets().setAmountPerClip(500);
			player.setPrimary(new QuadHVMG(player.getBullets(), 4, 32, gm, player.getEM()));
			
			break;
		}
	}
}
