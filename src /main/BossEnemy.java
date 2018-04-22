package main;

import java.awt.*;
import java.util.LinkedList;

public class BossEnemy extends GameObject{
	
	private Handler handler;
	
	private int timer = 100;
	private int timer2 = 50;

	public BossEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
//		velX = 0;
//		velY = 2;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 96, 96);
	}
	
	public synchronized void tick(LinkedList<GameObject> object) {
		y += velY;
		
//		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		
//		
//		if(timer <= 0) velY = 0;
//		else timer--;
//		
//		if(timer <= 0) timer2--;
//		if(timer2 <= 0) {
//			if(velX == 0) velX = 5;
//		}
//		
//		if(x <= 0 || x >= Game.WIDTH - 96) velX *= -1;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 96, 96);
	}
}
