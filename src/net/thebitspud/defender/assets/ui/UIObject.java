package net.thebitspud.defender.assets.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import net.thebitspud.defender.main.GameManager;

//any graphical user interface object that can be interacted with

public abstract class UIObject {
	protected GameManager gm;
	protected Rectangle bounds;

	protected final byte RESTING = 0, HOVERING = 1, HOLDING = 2;
	protected byte state;

	public UIObject(int x, int y, int width, int height, GameManager gm) {
		this.gm = gm;

		bounds = new Rectangle(x, y, width, height);
		state = RESTING;
	}

	public void onMouseMove(MouseEvent e) {
		if(bounds.contains(e.getX(), e.getY())) {
			if(state != HOLDING) state = HOVERING;
		}else state = RESTING;
	}

	public void onMousePress(MouseEvent e) {
		if(bounds.contains(e.getX(), e.getY())) state = HOLDING;
		else state = RESTING;
	}

	public void onMouseRelease(MouseEvent e) {
		if(state != HOLDING) return;

		onClick();
		gm.getAL().playSound("button_click");
		state = HOVERING;
	}

	public abstract void render(Graphics g);
	public abstract void onClick();
}
