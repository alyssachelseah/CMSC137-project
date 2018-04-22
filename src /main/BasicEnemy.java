package main;

import java.awt.*;
import java.util.LinkedList;

public class BasicEnemy extends GameObject{

	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = 1;
		velY = 1;
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
		g.setColor(Color.yellow);
		g.fillRect(x, y, 40, 40);
	}
}
