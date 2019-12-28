package net.thebitspud.defender.assets.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import net.thebitspud.defender.main.GameManager;

//a simple button that displays text and can be clicked

public class UITextButton extends UIObject {
	private ClickListener cl;
	private Font f;
	private String title;
	private int margin;
	
	public UITextButton(int x, int y, int width, int height, int margin, Font f, String title, GameManager gm, ClickListener cl) {
		super(x, y, width, height, gm);
		
		this.margin = margin;
		this.title = title;
		this.cl = cl;
		this.f = f;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
		
		if(state == HOLDING) g.setColor(new Color(120, 120, 120));
		else if(state == HOVERING) g.setColor(new Color(150, 150, 150));
		else g.setColor(Color.LIGHT_GRAY);
		
		g.fillRect(bounds.x + margin, bounds.y + margin, bounds.width - (margin * 2), bounds.height - (margin * 2));
		
		g.setColor(Color.DARK_GRAY);
		g.setFont(f);
		
		gm.getSM().centre(title, bounds.x + (bounds.width / 2), bounds.y + (bounds.height / 2), g);
	}

	@Override
	public void onClick() {
		cl.onClick();
	}
}
