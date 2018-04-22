package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bullet extends GameObject{
	
	Handler handler;
	
	public Bullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velY = 10;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}
	
	public void tick(LinkedList<GameObject> object) {
		if(y-velY<=0) handler.removeObject(this);
		y-=velY;
		collision(object);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, 10, 10);
	}
	
	public void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					BasicEnemy bEnemy = (BasicEnemy)tempObject;
					
					handler.removeObject(bEnemy);
				}
			}
			
			if(tempObject.getId() == ID.Enemy2){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					Enemy2 bEnemy = (Enemy2)tempObject;
					bEnemy.reduceHealth();
					
					if(bEnemy.getHealth() <= 0) handler.removeObject(bEnemy);
				}
			}
			
			if(tempObject.getId() == ID.Enemy3){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					Enemy3 bEnemy = (Enemy3)tempObject;
					bEnemy.reduceHealth();
					
					if(bEnemy.getHealth() <= 0) handler.removeObject(bEnemy);
				}
			}
		}
	}
}
