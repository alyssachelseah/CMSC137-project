package main;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 32);
		y = Game.clamp(y, 0, Game.HEIGHT - 32);
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.Enemy2 || tempObject.getId() == ID.Enemy3) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
				
					HUD.HEALTH -= 1;
				}
			}
			
		}
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		
		if(id == ID.Player) {
//			g.setColor(Color.white);
//			g2d.draw(getBounds());
			g.setColor(Color.red);
			g.fillRect(x, y, 32, 32);
		}else if(id == ID.Player2){
//			g.setColor(Color.white);
//			g2d.draw(getBounds());
			g.setColor(Color.blue);
			g.fillRect(x, y, 32, 32);
		}
	}
	
	
	
}
