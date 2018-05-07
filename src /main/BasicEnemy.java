package main;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class BasicEnemy extends GameObject{

	private Handler handler;
	private int health;
	private Color color;
	private String image;
	
	public BasicEnemy(int x, int y, ID id, Handler handler, Color color, int health, String image) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.health = health;
		this.image = image;
		velX = 1;
		velY = 1;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 80, 100);
	}
	
	public void tick(LinkedList<GameObject> object) {
		if(y+velY>=Game.HEIGHT) handler.removeObject(this);
		y += velY;
		
//		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
//		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
	}
	
	public void render(Graphics g) {
		ImageIcon cow = new ImageIcon(image); // load the image to a imageIcon
		g.drawImage(cow.getImage(),x,y,80,100,null);
	}
	

	public void reduceHealth() {
		this.health-=1;
	}
	
	public int getHealth() {
		return this.health;
	}
}
