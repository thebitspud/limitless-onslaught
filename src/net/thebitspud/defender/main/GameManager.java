package net.thebitspud.defender.main;

import net.thebitspud.defender.assets.AssetLoader;
import net.thebitspud.defender.assets.ui.*;
import net.thebitspud.defender.game.Game;
import net.thebitspud.defender.input.*;
import net.thebitspud.defender.states.*;

//the universally accessible class that initializes, ticks, and renders the program's various components

public class GameManager {
	//main
	
	private Program program;
	private Game game;
	
	//states
	
	private State menuState, gameState, pauseState, endState;
	
	//assets
	
	private AssetLoader al;
	private StringManager sm;
	private SettingsOverlay settings;
	private StoreOverlay store;
	
	//input
	
	private KeyManager keyboard;
	private MouseManager mouse;
	
	public GameManager(Program program) {
		this.program = program;
		
		mouse = new MouseManager();
		keyboard = new KeyManager();
		
		al = new AssetLoader(program.getRL());
		sm = new StringManager();
		settings = new SettingsOverlay(this);
		store = new StoreOverlay(this);
		
		game = new Game(this);
		
		menuState = new MenuState(this);
		gameState = new GameState(this);
		pauseState = new PauseState(this);
		endState = new EndState(this);
		
		this.getProgram().getDisplay().getFrame().addKeyListener(keyboard);
		this.getProgram().getDisplay().getFrame().addMouseListener(mouse);
		this.getProgram().getDisplay().getFrame().addMouseMotionListener(mouse);
		this.getProgram().getDisplay().getCanvas().addKeyListener(keyboard);
		this.getProgram().getDisplay().getCanvas().addMouseListener(mouse);
		this.getProgram().getDisplay().getCanvas().addMouseMotionListener(mouse);
	}

	public void init() {
		game.init();
		settings.init();
		store.init();
		
		menuState.init();
		gameState.init();
		pauseState.init();
		endState.init();
	}
	
	//ticking the active state
	
	public void tick() {
		keyboard.tick();
		
		if(State.getState() == null) State.setState(menuState);
		else State.getState().tick();
	}
	
	public double getFPS() {
		return program.getFPS();
	}

	public double getMaxFPS() {
		return program.getMaxFPS();
	}

	public Game getGame() {
		return game;
	}
	
	public Program getProgram() {
		return program;
	}

	public AssetLoader getAL() {
		return al;
	}
	
	public StringManager getSM() {
		return sm;
	}
	
	public SettingsOverlay getSettings() {
		return settings;
	}
	
	public StoreOverlay getStore() {
		return store;
	}

	public State getMenuState() {
		return menuState;
	}

	public State getGameState() {
		return gameState;
	}
	
	public State getPauseState() {
		return pauseState;
	}
	
	public State getEndState() {
		return endState;
	}

	public KeyManager getKeyboard() {
		return keyboard;
	}

	public MouseManager getMouse() {
		return mouse;
	}
}
