package net.thebitspud.defender.main;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import net.thebitspud.defender.assets.ResourceLoader;
import net.thebitspud.defender.states.State;

//the class that initializes the gameloop

public class Program implements Runnable {
	private Display display;
	private GameManager gm;
	
	private int width, height;
	private String title;
	
	//gameloop
	
	private Thread thread;
	private boolean running;
	private int maxFPS;
	
	//graphics
	
	private Graphics g;
	private BufferStrategy bs;
	private ResourceLoader rl;
	
	public Program(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		
		display = new Display(width, height, title);
		rl = new ResourceLoader();
		gm = new GameManager(this);
		
		running = false;
		sound = true;
		music = true;
	}
	
	//preparing objects and variables
	
	public void init() {
		gm.init();
		
		rl.loadIcons(display.getFrame());
		maxFPS = 60;
	}
	
	private boolean sound, music;
	
	public void toggleSound() {
		sound = !sound;
		
		gm.getAL().setSound(sound);
	}
	
	public void toggleMusic() {
		music = !music;
		gm.getAL().setMusic(music);
		
		if(music) gm.getAL().playMusic();
		else gm.getAL().stopMusic();
	}
	
	public boolean getSound() {
		return sound;
	}
	
	public boolean getMusic() {
		return music;
	}
	
	//drawing objects to the screen
	
	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, width, height);
		
		//rendering the active state
		
		if(State.getState() != null) State.getState().render(g);
		
		if(!sound) g.drawImage(gm.getAL().getIcon(7, 7), 50, 540, 32, 32, null);
		if(!music) g.drawImage(gm.getAL().getIcon(7, 7), 90, 540, 32, 32, null);
		
		bs.show();
		g.dispose();
	}
	
	private int FPS;
	
	@Override
	public void run() {
		init();
		
		double tickDelay = 1000000000 / maxFPS;
		double delta = 0;
		long currentTime;
		long lastTime = System.nanoTime();
		
		long timer = 0;
		int frames = 0;
		
		while(running) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / tickDelay;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			
			if(delta >= 1) {
				gm.tick();
				render();
				
				frames++;
				delta--;
			}
			
			if(timer >= 1000000000) {
				FPS = frames;
				frames = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if(running) return;
		
		running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		
		running = false;
		
		try{
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//getters and setters

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String getTitle() {
		return title;
	}

	public int getMaxFPS() {
		return maxFPS;
	}

	public int getFPS() {
		return FPS;
	}
	
	public Display getDisplay() {
		return display;
	}
	
	public ResourceLoader getRL() {
		return rl;
	}
}
