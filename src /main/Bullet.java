package main;

import java.awt.Color;
import java.awt.Graphics;

public class Bullet extends GameObject{
	
	public Bullet(int x, int y, ID id) {
		super(x, y, id);
		
		velY = 10;
	}
	public void tick() {
		y -= velY;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 10, 10);
	}
}
