package net.thebitspud.defender.game.map;

import java.awt.Graphics;
import java.util.Random;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.game.entities.EntityManager;
import net.thebitspud.defender.game.entities.mobs.*;
import net.thebitspud.defender.main.GameManager;

//the class that controls spawning and levels

public class Stage {
	private GameManager gm;
	private EntityManager em;
	
	private Random r;
	private Timer spawnProbe, spawnDrone, spawnMine, spawnDrifter,
			spawnSaucer, spawnSaucerV2, spawnShield, spawnVaporizer,
			spawnSupernova, spawnObstructor;
	
	public Stage(GameManager gm) {
		this.gm = gm;
		
		em = new EntityManager(gm);
		r = new Random();
	}
	
	public void init() {
		em.init();
	}
	
	//setting automated spawn timers
	
	public void reset() {
		spawnProbe = new Timer(250, true);
		spawnDrone = new Timer(800, true);
		spawnMine = new Timer(1500, true);
		spawnDrifter = new Timer(600, true);
		spawnSaucer = new Timer(4000, true);
		spawnSaucerV2 = new Timer(9000, true);
		spawnShield = new Timer(2000, true);
		spawnVaporizer = new Timer(4000, true);
		spawnObstructor = new Timer(7500, true);
		spawnSupernova = new Timer(11000, true);
		
		em.reset();
		
		//preparing requirement mobs
		
		spawnShield.activateOnNext();
		spawnVaporizer.activateOnNext();
		spawnSupernova.activateOnNext();
		spawnObstructor.activateOnNext();
	}
	
	private int type;
	
	//spawning mobs
	
	public void tick() {
		em.tick();
		
		if(gm.getGame().getScore().getPoints() < 4000 && spawnProbe.isActivated()) {
			em.addEntity(new Probe(800, r.nextInt(392) + 8, gm));
			if(spawnProbe.getDelay() > 100) spawnProbe.setDelay(spawnProbe.getDelay() - 5);
		}
		
		if(spawnDrone.isActivated()) {
			if(spawnDrone.getDelay() > 200) spawnDrone.setDelay(spawnDrone.getDelay() - 10);
			
			if(gm.getGame().getScore().getPoints() < 15000) em.addEntity(new Drone(800, r.nextInt(392) + 8, gm));
			else spawnShieldedDrone(r.nextInt(384));
		}
		
		if(gm.getGame().getScore().getPoints() < 15000 && spawnMine.isActivated()) {
			em.addEntity(new Mine(800, r.nextInt(392) + 8, 1, 0, gm));
			if(spawnMine.getDelay() > 100) spawnMine.setDelay(spawnMine.getDelay() - 25);
		}
		
		if(gm.getGame().getScore().getPoints() < 8000 && spawnDrifter.isActivated()) {
			em.addEntity(new Drifter(800, r.nextInt(392) + 8, gm));
			if(spawnDrifter.getDelay() > 100) spawnDrifter.setDelay(spawnDrifter.getDelay() - 20);
		}
		
		if(spawnSaucer.isActivated()) {
			if(gm.getGame().getScore().getPoints() < 4000) type = 0;
			else if(gm.getGame().getScore().getPoints() < 8000) type = 1;
			else type = 2;
			
			em.addEntity(new Saucer(800, r.nextInt(384), type, gm));
			if(spawnSaucer.getDelay() > 500) spawnSaucer.setDelay(spawnSaucer.getDelay() - 250);
		}
		
		if(spawnSaucerV2.isActivated()) {
			if(gm.getGame().getScore().getPoints() < 8000) type = 0;
			else if(gm.getGame().getScore().getPoints() < 12500) type = 1;
			else type = 2;
			
			em.addEntity(new SaucerV2(800, r.nextInt(384), type, gm));
			if(spawnSaucerV2.getDelay() > 1000) spawnSaucerV2.setDelay(spawnSaucerV2.getDelay() - 500);
		}
		
		if(gm.getGame().getScore().getPoints() >= 2000 && spawnShield.isActivated()) {
			em.addEntity(new Shield(800, r.nextInt(384), gm));
			if(spawnShield.getDelay() > 200) spawnShield.setDelay(spawnShield.getDelay() - 50);
		}
		
		if(gm.getGame().getScore().getPoints() >= 4000 && spawnVaporizer.isActivated()) {
			if(gm.getGame().getScore().getPoints() < 10000) type = 0;
			else if(gm.getGame().getScore().getPoints() < 15000) type = 1;
			else type = 2;
			
			em.addEntity(new Vaporizer(800, r.nextInt(384), type, gm));
			if(spawnVaporizer.getDelay() > 500) spawnVaporizer.setDelay(spawnVaporizer.getDelay() - 100);
		}
		
		if(gm.getGame().getScore().getPoints() >= 8000 && spawnObstructor.isActivated()) {
			em.addEntity(new Obstructor(800, r.nextInt(384), gm));
			
			if(spawnObstructor.getDelay() > 500) spawnObstructor.setDelay(spawnObstructor.getDelay() - 1000);
		}
		
		if(gm.getGame().getScore().getPoints() >= 10000 && spawnSupernova.isActivated()) {
			spawnVanguard(r.nextInt(320) + 32);
			
			if(spawnSupernova.getDelay() > 1000) spawnSupernova.setDelay(spawnSupernova.getDelay() - 2000);
		}
	}
	
	public void render(Graphics g) {
		em.render(g);
	}

	public EntityManager getEM() {
		return em;
	}
	
	public void spawnMineOctal(int x, int y) {
		em.addEntity(new Mine(x, y, 1, 0, gm));
		em.addEntity(new Mine(x, y, 0.7, 0.7, gm));
		em.addEntity(new Mine(x, y, 0, 1, gm));
		em.addEntity(new Mine(x, y, -0.7, 0.7, gm));
		em.addEntity(new Mine(x, y, -1, 0, gm));
		em.addEntity(new Mine(x, y, -0.7, -0.7, gm));
		em.addEntity(new Mine(x, y, 0, -1, gm));
		em.addEntity(new Mine(x, y, 0.7, -0.7, gm));
	}
	
	public void spawnVanguard(int y) {
		em.addEntity(new Supernova(900, y, gm));
		em.addEntity(new Shield(860, y - 30, gm));
		em.addEntity(new Shield(860, y + 30, gm));
		em.addEntity(new Shield(848, y - 15, gm));
		em.addEntity(new Shield(848, y+ 15, gm));
		em.addEntity(new Shield(840, y, gm));
	}
	
	public void spawnShieldedDrone(int y) {
		em.addEntity(new Drone(860, y + 8, gm));
		em.addEntity(new Shield(800, y, gm));
	}
}
