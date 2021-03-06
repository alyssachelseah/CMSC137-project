package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Bullet extends GameObject{
	
	Handler handler;
	private int damage;
	private String name;
	
	public Bullet(String name, int x, int y, ID id, Handler handler, int damage) {
		super(x, y, id);
		this.name = name;
		this.handler = handler;
		this.damage = damage;
		velY = 15;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 7, 20);
	}
	
	public void tick(LinkedList<GameObject> object) {
		if(y-velY<=0) handler.removeObject(this);
		y-=velY;
		collision(object);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, 7, 20);
	}
	
	public void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);

			if(tempObject.getId() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					BasicEnemy bEnemy = (BasicEnemy)tempObject;
					bEnemy.reduceHealth();
					
					if(bEnemy.getHealth() <= 0) {
						int score;
						
						handler.removeObject(bEnemy);
						
					}
					

					if(getName().equals("Player1")) {
						HUD.SCORE += 10;
					}
				}
			}
			
			if(tempObject.getId() == ID.BossEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					handler.removeObject(this);
					BossEnemy bEnemy = (BossEnemy)tempObject;
					bEnemy.damage(this.damage);
					
					if(bEnemy.getHealth() <= 0) {
						handler.removeObject(bEnemy);
						JOptionPane.showMessageDialog(null, "YOU WON!");
					} 
					

					if(getName().equals("Player1")) {
						HUD.SCORE += 20;
					}
					
				}
			}
		}
	}
	
	public String getName() {
		return name;
	}
}
