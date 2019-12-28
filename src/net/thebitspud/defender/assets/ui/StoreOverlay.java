package net.thebitspud.defender.assets.ui;

import java.awt.Color;
import java.awt.Graphics;

import net.thebitspud.defender.game.entities.Player;
import net.thebitspud.defender.game.purchases.*;
import net.thebitspud.defender.main.GameManager;
import net.thebitspud.defender.states.State;

public class StoreOverlay {
	protected GameManager gm;
	protected UIManager uim;
	private Player player;
	private Purchase upgradeMG, upgradeMissiles;

	private boolean active;

	public StoreOverlay(GameManager gm) {
		this.gm = gm;

		uim = new UIManager();
		upgradeMG = new UpgradeMG(gm);
		upgradeMissiles = new UpgradeMissiles(gm);
	}

	public void reset() {
		upgradeMG.reset();
		upgradeMissiles.reset();
	}

	public void init() {
		player = gm.getGame().getStage().getEM().getPlayer();

		upgradeMG.init();
		upgradeMissiles.init();

		uim.addObject(new UITextButton(300, 487, 200, 40, 3, gm.getSM().getVerdana(2), "Back", gm, () -> { // back to game
			State.setState(State.getState());
			gm.getMouse().setUIM(State.getState().getUIM());
			active = false;
		}));

		uim.addObject(new UITextButton(170, 32, 80, 40, 3, gm.getSM().getVerdana(2), "$10 ", gm, () -> { // HP
			if(player.getFunds() >= 10 && player.getHP() < player.getMaxHP()) {
				player.setFunds(-10);
				player.setHP(1);
			}
		}));

		uim.addObject(new UITextButton(170, 92, 80, 40, 3, gm.getSM().getVerdana(2), "$10 ", gm, () -> { // bullets
			if(player.getFunds() >= 10) {
				player.setFunds(-10);
				player.getBullets().setAmount(1000);
			}
		}));

		uim.addObject(new UITextButton(170, 152, 80, 40, 3, gm.getSM().getVerdana(2), "$10 ", gm, () -> { // missiles
			if(player.getFunds() >= 10) {
				player.setFunds(-10);
				player.getMissiles().setAmount(150);
			}
		}));

		uim.addObject(new UITextButton(570, 32, 80, 40, 3, null, "", gm, () -> upgradeMG.buy()));

		uim.addObject(new UITextButton(570, 92, 80, 40, 3, null, "", gm, () -> upgradeMissiles.buy()));

		//fix this copy-paste

		uim.addObject(new UIImageButton(50, 540, 32, 32, gm.getAL().getButton(0), gm, () -> gm.getProgram().toggleSound()));

		uim.addObject(new UIImageButton(90, 540, 32, 32, gm.getAL().getButton(1), gm, () -> gm.getProgram().toggleMusic()));
	}

	public void tick() {
		if(gm.getKeyboard().getJustPressed(27)) { // Esc - back to game
			gm.getMouse().setUIM(State.getState().getUIM());
			active = false;
		}
	}

	public void render(Graphics g) {
		g.setColor(new Color(200, 160, 100));
		g.fillRect(0, 0, gm.getProgram().getWidth(), 416);

		uim.render(g);

		g.setColor(Color.BLACK);
		g.setFont(gm.getSM().getVerdana(1));

		gm.getSM().centre("Repair Damage:", 100, 50, g);
		gm.getSM().centre("Buy Bullets [1000]:", 100, 110, g);
		gm.getSM().centre("Buy Missiles [150]:", 100, 170, g);

		gm.getSM().centre("Upgrade MG:", 500, 50, g);
		gm.getSM().centre("Upgrade Missiles:", 500, 110, g);

		g.setColor(Color.DARK_GRAY);
		g.setFont(gm.getSM().getVerdana(2));

		if(upgradeMG.isAvailable()) gm.getSM().centre("$" + upgradeMG.getCost(), 607, 52, g);
		else gm.getSM().centre("MAX", 607, 52, g);

		if(upgradeMissiles.isAvailable()) gm.getSM().centre("$" + upgradeMissiles.getCost(), 607, 112, g);
		else gm.getSM().centre("MAX", 607, 112, g);
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
}
