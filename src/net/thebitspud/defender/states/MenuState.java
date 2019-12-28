package net.thebitspud.defender.states;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.assets.ui.UIImageButton;
import net.thebitspud.defender.assets.ui.UITextButton;
import net.thebitspud.defender.main.GameManager;

public class MenuState extends State {
	public MenuState(GameManager gm) {
		super(gm);
	}

	@Override
	public void init() {
		gm.getMouse().setUIM(uim);

		uim.addObject(new UITextButton(300, 300, 200, 40, 3, gm.getSM().getVerdana(2), "Play", gm, () -> {
			State.setState(gm.getGameState());
			gm.getMouse().setUIM(gm.getGameState().getUIM());
		}));

		uim.addObject(new UITextButton(300, 375, 200, 40, 3, gm.getSM().getVerdana(2), "Settings", gm, () -> {
			gm.getMouse().setUIM(gm.getSettings().getUIM());
			gm.getSettings().setActive(true);
		}));

		uim.addObject(new UITextButton(300, 450, 200, 40, 3, gm.getSM().getVerdana(2), "Exit", gm, () -> System.exit(1)));

		//fix this copy-paste

		uim.addObject(new UIImageButton(50, 540, 32, 32, gm.getAL().getButton(0), gm, () -> gm.getProgram().toggleSound()));

		uim.addObject(new UIImageButton(90, 540, 32, 32, gm.getAL().getButton(1), gm, () -> gm.getProgram().toggleMusic()));

		gm.getGame().getScore().setHighScore("history");
	}

	@Override
	public void tick() {
		if(gm.getSettings().isActive()) gm.getSettings().tick();
		else if(gm.getKeyboard().getJustPressed(79)) { // O - to settings
			gm.getMouse().setUIM(gm.getSettings().getUIM());
			gm.getSettings().setActive(true);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, gm.getProgram().getWidth(), gm.getProgram().getHeight());

		if(gm.getSettings().isActive()) {
			gm.getSettings().render(g);
			return;
		}

		g.setColor(Color.BLACK);
		g.setFont(gm.getSM().getVerdana(4));
		gm.getSM().centre("Limitless Onslaught", 400, 120, g);

		g.setFont(gm.getSM().getVerdana(1));
		gm.getSM().centre("A game by Thebitspud", 400, 160, g);

		uim.render(g);

		g.setColor(Color.BLACK);
		g.setFont(gm.getSM().getVerdana(2));

		gm.getSM().centre("High Score: " + gm.getGame().getScore().getHighScore(), 400, 220, g);
		gm.getSM().centre("Last Score: " + gm.getGame().getScore().getLastScore(), 400, 255, g);
	}
}
