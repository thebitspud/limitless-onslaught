package net.thebitspud.defender.assets.ui;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

//utility class that helps shrink drawstring code

public class StringManager {
	private FontMetrics fm;
	
	private int xOffset, yOffset;
	private Font verdana[];
	
	public StringManager() {
		verdana = new Font[5];
		
		verdana[0] = new Font("Verdana", Font.PLAIN, 12);
		verdana[1] = new Font("Verdana", Font.PLAIN, 14);
		verdana[2] = new Font("Verdana", Font.PLAIN, 20);
		verdana[3] = new Font("Verdana", Font.PLAIN, 28);
		verdana[4] = new Font("Verdana", Font.BOLD, 48);
	}
	
	public void centre(String s, int x, int y, Graphics g) {
		fm = g.getFontMetrics(g.getFont());
		xOffset = fm.stringWidth(s) / 2;
		yOffset = fm.getHeight() / 2 - fm.getAscent();
		
		g.drawString(s, x - xOffset, y - yOffset);
	}
	
	public Font getVerdana(int size) {
		return verdana[size];
	}
}