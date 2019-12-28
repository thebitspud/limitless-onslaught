package net.thebitspud.defender.game.map;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import net.thebitspud.defender.assets.Timer;

//the class that creates and manages the background

public class Backdrop {
	private BufferedImage[] bg;
	private Timer cycleImage, shiftOffset;
	private int xOffset, imageNumber;
	
	public Backdrop(BufferedImage[] bg) {
		this.bg = bg;
	}
	
	public void reset() {
		cycleImage = new Timer(3200, true);
		shiftOffset = new Timer(3, true);
		xOffset = 0;
		imageNumber = 0;
	}
	
	public void tick() {
		if(shiftOffset.isActivated()) xOffset++;
		
		if(cycleImage.isActivated()) {
			xOffset = 0;
			
			if(imageNumber >= bg.length - 1) imageNumber = 0;
			else imageNumber++;
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(bg[imageNumber], -xOffset, 0, null);
		if(imageNumber == bg.length - 1) g.drawImage(bg[0], -xOffset + 800, 0, null);
		else g.drawImage(bg[imageNumber + 1], -xOffset + 800, 0, null);
	}
}
