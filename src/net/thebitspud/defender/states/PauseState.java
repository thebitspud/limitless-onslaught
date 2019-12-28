package net.thebitspud.defender.states;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.assets.ui.UIImageButton;
import net.thebitspud.defender.assets.ui.UITextButton;
import net.thebitspud.defender.main.GameManager;

public class PauseState extends State {
	public PauseState(GameManager gm) {
		super(gm);
	}

	@Override
	public void init() {
		uim.addObject(new UITextButton(300, 200, 200, 40, 3, gm.getSM().getVerdana(2), "Resume", gm, () -> {
			State.setState(gm.getGameState());
			gm.getMouse().setUIM(gm.getGameState().getUIM());
		}));

		uim.addObject(new UITextButton(300, 300, 200, 40, 3, gm.getSM().getVerdana(2), "Settings", gm, () -> {
			gm.getMouse().setUIM(gm.getSettings().getUIM());
			gm.getSettings().setActive(true);
		}));

		uim.addObject(new UITextButton(300, 400, 200, 40, 3, gm.getSM().getVerdana(2), "Menu", gm, () -> {
			State.setState(gm.getMenuState());
			gm.getMouse().setUIM(gm.getMenuState().getUIM());
		}));

		//fix this copy-paste

		uim.addObject(new UIImageButton(50, 540, 32, 32, gm.getAL().getButton(0), gm, () -> gm.getProgram().toggleSound()));

		uim.addObject(new UIImageButton(90, 540, 32, 32, gm.getAL().getButton(1), gm, () -> gm.getProgram().toggleMusic()));
	}

	@Override
	public void tick() {
		if(gm.getSettings().isActive()) gm.getSettings().tick();
		else {
			if(gm.getKeyboard().getJustPressed(27)) { // Esc - back to game
				State.setState(gm.getGameState());
				gm.getMouse().setUIM(gm.getGameState().getUIM());
			}

			if(gm.getKeyboard().getJustPressed(79)) { // O - to settings
				gm.getMouse().setUIM(gm.getSettings().getUIM());
				gm.getSettings().setActive(true);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, gm.getProgram().getWidth(), gm.getProgram().getHeight());

		if(gm.getSettings().isActive()) gm.getSettings().render(g);
		else uim.render(g);
	}
}
