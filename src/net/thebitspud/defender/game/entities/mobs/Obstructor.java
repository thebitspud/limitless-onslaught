package net.thebitspud.defender.game.entities.mobs;

import net.thebitspud.defender.assets.Timer;
import net.thebitspud.defender.main.GameManager;

public class Obstructor extends Mob {
	private Timer spawnMine, switchAI;
	private boolean dead;
	private int AI;

	public Obstructor(int x, int y, GameManager gm) {
		super(x, y, 32, 32, gm);
	}

	@Override
	public void initValues() {
		maxHP = 350; HP = maxHP;
		speed = 0.25;
		coinValue = 25;
		pointValue = 125;
		attack = 10;
		AI = 0;

		dead = false;

		spawnMine = new Timer(60, true);
		switchAI = new Timer(720, true);
		switchAI.setTicks(600);

		texture = gm.getAL().getMob(2, 1);
	}

	@Override
	public void kill() {
		dead = true;
		em.getPlayer().setFunds(coinValue);
		gm.getGame().getScore().setPoints(pointValue);
	}

	private int action, lastDirection;

	@Override
	public void tickAI() {
		if(switchAI.isActivated())
			if(AI == 0) AI = 1;
			else AI = 0;

		if(AI == 1)
			spawnMinion();
		else {
			action = r.nextInt(60);

			if(y == 0) lastDirection = 0;
			else if (y == 416 - width) lastDirection = 1;
			else if(action <= 1) lastDirection = action;
			driftMoveAI(lastDirection);
		}

		if(dead) {
			gm.getGame().getStage().spawnMineOctal((int) x + 8, (int) y + 8);
			active = false;
		}
	}

	private double minionTraj;

	public void spawnMinion() {
		if(spawnMine.isActivated()) {
			minionTraj = r.nextInt(5) - 2;
			em.addEntity(new Mine((int) x + 8, (int) y + 8, 1 - (Math.abs(minionTraj) / 10), minionTraj / 5, gm));
		}
	}
}
