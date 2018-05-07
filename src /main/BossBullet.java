package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class BossBullet extends GameObject{
	
	Handler handler;
	
	public BossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velY = 10;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 40);
	}
	
	public void tick(LinkedList<GameObject> object) {
		if(y+velY<=Game.HEIGHT) handler.removeObject(this);
		y+=velY;
		collision(object);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, 20, 40);
	}
	
	public void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			
		}
	}
}
