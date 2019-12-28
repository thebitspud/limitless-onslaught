package net.thebitspud.defender.states;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import net.thebitspud.defender.assets.ui.UIImageButton;
import net.thebitspud.defender.assets.ui.UITextButton;
import net.thebitspud.defender.game.Game;
import net.thebitspud.defender.game.entities.Player;
import net.thebitspud.defender.main.GameManager;

// the state in which you play the game

public class GameState extends State {
	private Game game;
	private Player player;

	private NumberFormat dec0;

	public GameState(GameManager gm) {
		super(gm);

		game = gm.getGame();

		dec0 = new DecimalFormat("#0");
	}

	@Override
	public void init() {
		uim.addObject(new UITextButton(300, 450, 200, 40, 3, gm.getSM().getVerdana(2), "Store", gm, () -> { // to store
			gm.getMouse().setUIM(gm.getStore().getUIM());
			gm.getStore().setActive(true);
		}));

		uim.addObject(new UITextButton(300, 525, 200, 40, 3, gm.getSM().getVerdana(2), "Pause", gm, () -> { // pause game
			State.setState(gm.getPauseState());
			gm.getMouse().setUIM(gm.getPauseState().getUIM());
		}));

		uim.addObject(new UITextButton(620, 540, 130, 32, 2, gm.getSM().getVerdana(2), "End Game", gm, () -> gm.getGame().endGame()));

		//fix this copy-paste

		uim.addObject(new UIImageButton(50, 540, 32, 32, gm.getAL().getButton(0), gm, () -> gm.getProgram().toggleSound()));

		uim.addObject(new UIImageButton(90, 540, 32, 32, gm.getAL().getButton(1), gm, () -> gm.getProgram().toggleMusic()));

		player = gm.getGame().getStage().getEM().getPlayer();
	}

	@Override
	public void tick() {
		if(gm.getStore().isActive()) gm.getStore().tick();
		else{
			game.tick();

			if(gm.getKeyboard().getJustPressed(27)) { // Esc - pause game
				State.setState(gm.getPauseState());
				gm.getMouse().setUIM(gm.getPauseState().getUIM());
			}

			if(gm.getKeyboard().getJustPressed(80)) { // P - to store
				gm.getMouse().setUIM(gm.getStore().getUIM());
				gm.getStore().setActive(true);
			}

			if(gm.getKeyboard().getJustPressed(79)) { // O - to settings
				State.setState(gm.getPauseState());
				gm.getMouse().setUIM(gm.getSettings().getUIM());
				gm.getSettings().setActive(true);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if(!gm.getStore().isActive()) game.render(g);

		g.setColor(Color.GRAY);
		g.fillRect(0, 420, gm.getProgram().getWidth(), 180);

		g.setColor(new Color(50, 50, 120));
		g.fillRect(0, 416, gm.getProgram().getWidth(), 4);

		if(gm.getStore().isActive()) gm.getStore().render(g);
		else uim.render(g);

		g.setColor(Color.BLACK);
		g.setFont(gm.getSM().getVerdana(2));
		g.drawString("HP: " + player.getHP() + " / " + player.getMaxHP(), 50, 470);
		g.drawString("Lives: " + dec0.format(game.getLives().getAmount()) + " / "
		+ dec0.format(game.getLives().getMaxAmount()), 50, 500);
		g.drawString("Funds: $" + player.getFunds(), 50, 530);

		if(player.getBullets().getAmount() <= 0 && player.getBullets().getCurrentClip() <= 0)
			g.drawString("Out of Ammo", 550, 470);
		else if(player.getBullets().isReloading()) g.drawString("Reloading", 550, 470);
		else g.drawString("Bullets: " + player.getBullets().getCurrentClip() + " / "
		+ dec0.format(player.getBullets().getAmount()), 550, 470);

		if(player.getMissiles().getAmount() <= 0 && player.getMissiles().getCurrentClip() <= 0)
			g.drawString("Out of Ammo", 550, 500);
		else if(player.getMissiles().isReloading()) g.drawString("Reloading", 550, 500);
		else g.drawString("Missiles: "  + player.getMissiles().getCurrentClip() + " / "
		+ dec0.format(player.getMissiles().getAmount()), 550, 500);

		if(!player.isScoreValid()) g.setColor(new Color(127, 0, 0));
		g.drawString("Score: " + gm.getGame().getScore().getPoints(), 550, 530);

	}
}