package main;

import java.awt.Color;
import java.awt.Graphics;

public class BasicEnemy extends GameObject{

	public BasicEnemy(int x, int y, ID id) {
		super(x, y, id);
		
		velX = 1;
		velY = 1;
	}
	
	public void tick() {
		y += velY;
		
//		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
//		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 40, 40);
	}
}
