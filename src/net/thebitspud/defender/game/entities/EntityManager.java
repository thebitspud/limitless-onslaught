package net.thebitspud.defender.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import net.thebitspud.defender.main.GameManager;

//the class that controls and all the entities in the game

public class EntityManager {
	private Player player;
	private ArrayList<Entity> entities;

	public EntityManager(GameManager gm) {
		player = new Player(100, 230, gm);
	}

	public void reset() {
		entities =  new ArrayList<>();
		player.initValues();
		player.resetLocation();
	}

	public void init() {
		for(Entity e : entities) e.init();
		player.init();
	}

	public void tick() {
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.init();
			e.tick();

			if(!e.isActive()) entities.remove(e);
		}

		player.tick();
	}

	public void render(Graphics g) {
		for(Entity e : entities) e.render(g);
		player.render(g);
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	//getters and setters

	public Player getPlayer() {
		return player;
	}
}