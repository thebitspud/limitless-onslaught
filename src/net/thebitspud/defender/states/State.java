package net.thebitspud.defender.states;

import java.awt.Graphics;

import net.thebitspud.defender.assets.ui.UIManager;
import net.thebitspud.defender.main.GameManager;

//initializes and indirectly manages the in-game states

public abstract class State {
	protected GameManager gm;
	protected UIManager uim;
	
	private static State activeState = null;
	
	public State(GameManager gm) {
		this.gm = gm;
		
		uim = new UIManager();
	}
	
	//getting and setting the active state
	
	public static State getState() {
		return activeState;
	}
	
	public static void setState(State state) {
		activeState = state;
	}
	
	//constructors
	
	public abstract void init();
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public UIManager getUIM() {
		return uim;
	}
}
