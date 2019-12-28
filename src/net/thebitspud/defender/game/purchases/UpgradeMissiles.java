package net.thebitspud.defender.game.purchases;

import net.thebitspud.defender.game.armaments.*;
import net.thebitspud.defender.main.GameManager;

public class UpgradeMissiles extends Purchase {
	public UpgradeMissiles(GameManager gm) {
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
		cost = 10;
	}

	@Override
	public void aquire() {
		switch(tier) {
		case 0:
			player.getMissiles().setAmountPerClip(10);
			player.getSecondary().getDelay().setDelay(45);
			cost = 20;
			
			break;
		case 1:
			player.getMissiles().getReload().setDelay(100);
			player.getSecondary().getDelay().setDelay(35);
			cost = 35;
			
			break;
		case 2:
			player.getSecondary().getDelay().setDelay(25);
			player.getMissiles().setAmountPerClip(15);
			cost = 80;
			
			break;
		case 3:
			player.setSecondary(new TripleML(player.getMissiles(), 40, 77, gm, player.getEM()));
			cost = 150;
			
			break;
		case 4:
			player.getMissiles().setAmountPerClip(45);
			player.getSecondary().getDelay().setDelay(35);
			cost = 250;
			
			break;
		case 5:
			player.getSecondary().getDelay().setDelay(30);
			player.getMissiles().getReload().setDelay(50);
			cost = 500;
			
			break;
		case 6:
			player.setSecondary(new TripleHEML(player.getMissiles(), 35, 77, gm, player.getEM()));
			cost = 1000;
			
			break;
		case 7:
			player.getMissiles().setAmountPerClip(120);
			player.getSecondary().getDelay().setDelay(20);
			
			break;
		}
	}
}
