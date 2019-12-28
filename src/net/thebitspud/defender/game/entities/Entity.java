package net.thebitspud.defender.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import net.thebitspud.defender.game.Game;
import net.thebitspud.defender.main.GameManager;

//any non-tile game object

public abstract class Entity {
	protected GameManager gm;
	protected EntityManager em;
	protected Game game;
	
	protected double x, y;
	protected int width, height, HP, maxHP, attack;
	private Rectangle bounds;
	protected boolean active, inited;
	protected double speed;
	protected String type;
	
	protected Random r;
	
	protected BufferedImage texture;
	
	public static final int DEFAULT_HEALTH = 5;
	public static final int DEFAULT_SPEED = 1;
	
	public Entity(int x, int y, int width, int height, GameManager gm) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.gm = gm;
		
		bounds = new Rectangle(0, 0, width, height);
		r = new Random();
		
		active = true;
		inited = false;
		
		maxHP = DEFAULT_HEALTH; HP = maxHP;
		speed = DEFAULT_SPEED;
	}
	
	public void damage(int dmg) {
		setHP(-dmg);
		
		if(HP <= 0) kill();
	}

	public void init() {
		if(!inited) {
			game = gm.getGame();
			em = game.getStage().getEM();
			
			initValues();
			inited = true;
		}
	}
	
	public Rectangle getCollisionBounds() {
		setBounds((int) x, (int) y, width, height);
		return bounds;
	}
	
	public abstract void initValues();
	public abstract void kill();
	public abstract void tick();
	public abstract void render(Graphics g);

	//getters and setters
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		bounds.setBounds((int) x, (int) y, width, height);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public int getXCentre() {
		return (int) (x + (width / 2));
	}
	
	public int getYCentre() {
		return (int) (y + (height / 2));
	}

	public int getHP() {
		return HP;
	}
	
	public void setHP(int HP) {
		this.HP += HP;
		
		if(this.HP < 0) this.HP = 0;
		if(this.HP > maxHP) this.HP = maxHP;
	}
	
	public int getMaxHP() {
		return maxHP;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public String getType() {
		return type;
	}

	public boolean isActive() {
		return active;
	}
}
