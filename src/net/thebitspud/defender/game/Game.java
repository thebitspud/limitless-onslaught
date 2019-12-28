package net.thebitspud.defender.game;

import java.awt.Graphics;

import net.thebitspud.defender.game.map.Backdrop;
import net.thebitspud.defender.game.map.Stage;
import net.thebitspud.defender.game.resources.Lives;
import net.thebitspud.defender.game.resources.Resource;
import net.thebitspud.defender.main.GameManager;
import net.thebitspud.defender.states.State;

//The main game class

public class Game {
	private GameManager gm;
	
	private Stage stage;
	private Backdrop backdrop[];
	private int activeBackdrop;
	
	private Resource lives;
	private Score score;
	
	public Game(GameManager gm) {
		this.gm = gm;
		
		stage = new Stage(gm);
		score = new Score(0);
		
		backdrop = new Backdrop[2];
		backdrop[0] = new Backdrop(gm.getAL().getForestBD());
		backdrop[1] = new Backdrop(gm.getAL().getSpaceBD());
		activeBackdrop = 0;
		
		reset();
	}

	public void init() {
		stage.init();
	}
	
	public void reset() {
		lives = new Lives(10, 10, gm);

		stage.reset();
		score.reset();
		gm.getStore().reset();
		
		for(Backdrop bd : backdrop) bd.reset();
	}
	
	public void endGame() {
		if(stage.getEM().getPlayer().isScoreValid()) {
			score.save("history");
			score.setHighScore("history");
			score.setLastScore();
		}
		
		State.setState(gm.getEndState());
		gm.getMouse().setUIM(gm.getEndState().getUIM());
		gm.getSettings().setActive(false);
		gm.getStore().setActive(false);
		
		reset();
		init();
	}
	
	public void cycleBackdrop() {
		if(activeBackdrop < backdrop.length - 1) activeBackdrop++;
		else activeBackdrop = 0;
	}

	public void tick() {
		stage.tick();
		backdrop[activeBackdrop].tick();
		lives.tick();
	}

	public void render(Graphics g) {
		backdrop[activeBackdrop].render(g);
		stage.render(g);
	}
	
	//getters and setters

	public Stage getStage() {
		return stage;
	}
	
	public Resource getLives() {
		return lives;
	}
	
	public Score getScore() {
		return score;
	}

	public int getActiveBackdrop() {
		return activeBackdrop;
	}
}