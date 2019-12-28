package net.thebitspud.defender.assets.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

//the class that manages all ui elements on the screen

public class UIManager {
	private ArrayList<UIObject> objects;

	public UIManager() {
		objects = new ArrayList<>();
	}

	public void onMouseMove(MouseEvent e) {
		for(UIObject o : objects) o.onMouseMove(e);
	}

	public void onMousePress(MouseEvent e) {
		for(UIObject o : objects) o.onMousePress(e);
	}

	public void onMouseRelease(MouseEvent e) {
		for(UIObject o : objects) o.onMouseRelease(e);
	}

	public void render(Graphics g) {
		for(UIObject o : objects) o.render(g);
	}

	//getters and setters

	public void addObject(UIObject o) {
		objects.add(o);
	}

	public void removeObjects(UIObject o) {
		objects.remove(o);
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}
}
