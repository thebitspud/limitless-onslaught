package net.thebitspud.defender.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

//the frame and canvas of the program

public class Display {
	private JFrame f;
	private Canvas c;
	
	private Dimension size;
	private String title;
	
	public Display(int width, int height, String title) {
		this.title = title;
		
		size = new Dimension(width, height);
		f = new JFrame();
		c = new Canvas();
		
		initFrame();
	}
	
	//preparing the frame
	
	public void initFrame() {	
		f.setTitle(title);
		f.setSize(size);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		f.setVisible(true);
		
		initCanvas();
		
		f.add(c);
		f.pack();
	}
	
	//preparing the canvas
	
	public void initCanvas() {
		c.setPreferredSize(size);
		c.setMaximumSize(size);
		c.setMinimumSize(size);
		c.setFocusable(false);
	}
	
	public JFrame getFrame() {
		return f;
	}
	
	public Canvas getCanvas() {
		return c;
	}
}
