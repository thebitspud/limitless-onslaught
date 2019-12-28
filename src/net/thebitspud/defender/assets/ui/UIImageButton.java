package net.thebitspud.defender.assets.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.thebitspud.defender.main.GameManager;

public class UIImageButton extends UIObject {
	private ClickListener cl;
	private BufferedImage[] texture;
	
	public UIImageButton(int x, int y, int width, int height, BufferedImage[] texture, GameManager gm, ClickListener cl) {
		super(x, y, width, height, gm);
		
		this.cl = cl;
		this.texture = texture;
	}

	@Override
	public void render(Graphics g) {
		if(texture != null) g.drawImage(texture[state], bounds.x, bounds.y, bounds.width, bounds.height, null);
	}

	@Override
	public void onClick() {
		cl.onClick();
	}
}
