package main;

import java.awt.*;
import java.util.LinkedList;

public class Enemy2 extends GameObject{
	
	private Handler handler;
	private int health = 2;

	public Enemy2(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 1;
		velY = 2;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 40, 40);
	}
	
	public void tick(LinkedList<GameObject> object) {
		if(y+velY>=Game.HEIGHT) handler.removeObject(this);
		y += velY;
		
//		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
//		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.pink);
		g.fillRect(x, y, 40, 40);
	}
	
	public void reduceHealth() {
		health-=1;
	}
	
	public int getHealth() {
		return health;
	}
}
