package net.thebitspud.defender.assets.ui;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.main.GameManager;
import net.thebitspud.defender.states.State;

//the menu in which you manage the settings

public class SettingsOverlay {
	protected GameManager gm;
	protected UIManager uim;

	private boolean active, invincible;
	private String util;

	public SettingsOverlay(GameManager gm) {
		this.gm = gm;

		uim = new UIManager();
	}

	public void init() {
		invincible = false;

		uim.addObject(new UITextButton(200, 32, 60, 40, 3, null, "", gm, () -> { //toggling invincibility
			if(invincible) invincible = false;
			else invincible = true;
		}));

		uim.addObject(new UITextButton(560, 32, 160, 40, 3, null, "", gm, () -> gm.getGame().cycleBackdrop()));

		uim.addObject(new UITextButton(560, 112, 160, 40, 3, null, "", gm, () -> gm.getAL().cycleMusicTrack()));

		uim.addObject(new UITextButton(300, 500, 200, 40, 3, gm.getSM().getVerdana(2), "Back", gm, () -> {
			State.setState(State.getState());
			gm.getMouse().setUIM(State.getState().getUIM());
			active = false;
		}));

		//fix this copy-paste

		uim.addObject(new UIImageButton(50, 540, 32, 32, gm.getAL().getButton(0), gm, () -> gm.getProgram().toggleSound()));

		uim.addObject(new UIImageButton(90, 540, 32, 32, gm.getAL().getButton(1), gm, () -> gm.getProgram().toggleMusic()));
	}

	public void tick() {
		if(gm.getKeyboard().getJustPressed(27) || gm.getKeyboard().getJustPressed(79)) { // Esc / O - back to game
			gm.getMouse().setUIM(State.getState().getUIM());
			active = false;
		}
	}

	public void render(Graphics g) {
		uim.render(g);

		g.setColor(Color.BLACK);
		gm.getSM().centre("Invincibility:", 130, 50, g);
		gm.getSM().centre("Backdrop:", 500, 50, g);
		gm.getSM().centre("Music:", 500, 130, g);

		if(invincible) {
			g.setColor(Color.WHITE);
			gm.getSM().centre("On", 230, 52, g);
		}else gm.getSM().centre("Off", 230, 52, g);

		g.setColor(Color.BLACK);

		switch(gm.getGame().getActiveBackdrop()) {
			case 0: util = "Forest"; break;
			case 1: util = "Space"; break;
			default: break;
		}

		gm.getSM().centre(util, 640, 52, g);

		switch(gm.getAL().getActiveTrack()) {
			case 0: util = "Digital Ether"; break;
			case 1: util = "Space Battle"; break;
			default: break;
		}

		gm.getSM().centre(util, 640, 132, g);
	}

	public UIManager getUIM() {
		return uim;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isInvincible() {
		return invincible;
	}
}
