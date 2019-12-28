package net.thebitspud.defender.states;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.assets.ui.UIImageButton;
import net.thebitspud.defender.assets.ui.UITextButton;
import net.thebitspud.defender.main.GameManager;

//the end game screen that you get when you die

public class EndState extends State {
	public EndState(GameManager gm) {
		super(gm);
	}

	@Override
	public void init() {
		uim.addObject(new UITextButton(300, 300, 200, 40, 3, gm.getSM().getVerdana(2), "Restart", gm, () -> {
			State.setState(gm.getGameState());
			gm.getMouse().setUIM(gm.getGameState().getUIM());
			gm.getAL().playMusic();
		}));

		uim.addObject(new UITextButton(300, 375, 200, 40, 3, gm.getSM().getVerdana(2), "Menu", gm, () -> {
			gm.getMouse().setUIM(gm.getMenuState().getUIM());
			State.setState(gm.getMenuState());
		}));

		//fix this copy-paste

		uim.addObject(new UIImageButton(50, 540, 32, 32, gm.getAL().getButton(0), gm, () -> gm.getProgram().toggleSound()));

		uim.addObject(new UIImageButton(90, 540, 32, 32, gm.getAL().getButton(1), gm, () -> gm.getProgram().toggleMusic()));
	}

	@Override
	public void tick() {
		if(gm.getKeyboard().getJustPressed(27)) { // Esc - to main menu
			gm.getMouse().setUIM(gm.getMenuState().getUIM());
			State.setState(gm.getMenuState());
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, gm.getProgram().getWidth(), gm.getProgram().getHeight());

		g.setColor(Color.BLACK);
		g.setFont(gm.getSM().getVerdana(4));
		gm.getSM().centre("You Lose.", gm.getProgram().getWidth() / 2, 150, g);

		g.setFont(gm.getSM().getVerdana(2));
		gm.getSM().centre("High Score: " + gm.getGame().getScore().getHighScore(), 400, 220, g);
		gm.getSM().centre("Last Score: " + gm.getGame().getScore().getLastScore(), 400, 255, g);

		uim.render(g);
	}
}
