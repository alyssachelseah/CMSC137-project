package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 32);
	}
	

	@Override
	public void render(Graphics g) {
		if(id == ID.Player) g.setColor(Color.white);
		else if(id == ID.Player2) g.setColor(Color.red);
		g.fillRect(x ,y, 32, 32);
		
	}
	
	
	
}
