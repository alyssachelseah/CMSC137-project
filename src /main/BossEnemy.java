package main;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class BossEnemy extends GameObject{
	
	private Handler handler;
	
	private int x = 0, y = 0;
	private int speed = 30, attackspeed = 2500;
	private int health = 500;
	private Random random = new Random();
	private boolean cooldown = false;
	
	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.x = x;
		this.y = y;
		velX = 2;
		velY = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 96, 96);
	}
	
	
	public synchronized void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(x + 96 >= Game.WIDTH) {
			velX = -2;
		} else if (x - 96 <= 0) {
			velX = 2;
		} else if (y + 96 >= Game.HEIGHT) {
			velY = -speed + 10;
		} else if (y - 96 <= 0) {
			velY = 0;
		}
		
		if(random.nextInt(500) <= 30 && isCooldown() == false) {
			velY = speed;
			cooldown();
		}
		
		if(health <= 300) {
			speed+=10;
		}
	}
	public void render(Graphics g) {
		ImageIcon boss = new ImageIcon("./src/img/boss.png"); // load the image to a imageIcon
		Image image = boss.getImage(); // transform it 
		Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		boss = new ImageIcon(newimg);
		g.drawImage(boss.getImage(),x,y,null);

	}
	
	public boolean isCooldown(){
		return cooldown;
	}

	public void cooldown(){
		cooldown = true;

		new java.util.Timer().schedule(new TimerTask(){
			public void run(){
				cooldown = false;
			}
		},attackspeed);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void damage(int damage) {
		this.health -= damage;
	}
}
