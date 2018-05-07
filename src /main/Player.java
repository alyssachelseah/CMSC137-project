package main;

import java.awt.*;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Player extends GameObject{
	
	Random r = new Random();
	Handler handler;
	
	private int attackspeed, damage = 20;
	private boolean bulletCooldown = false, damageCooldown = false;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.attackspeed = 500;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 60, 60);
	}
	
	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 60);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					BasicEnemy bEnemy = (BasicEnemy)tempObject;
					bEnemy.reduceHealth();
					if(bEnemy.getHealth() <= 0) handler.removeObject(bEnemy);
					
					if(!isDamageCooldown()) {
						HUD.HEALTH -= 1;
						damageCooldown();
					}
				}
			}
			
			if(tempObject.getId() == ID.BossEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					BossEnemy boss = (BossEnemy) tempObject;
					if(!isDamageCooldown()) {
						HUD.HEALTH -= boss.getDamage();
						damageCooldown();
					}
				}
			}
			
			if(HUD.HEALTH <= 0) {
				JOptionPane.showMessageDialog(null, "GAME OVER!");
				handler.removeObject(this);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		
		if(id == ID.Player) {
//			g.setColor(Color.white);
//			g2d.draw(getBounds());
//			g.setColor(Color.red);
//			g.fillRect(x, y, 32, 32);
			ImageIcon ship = new ImageIcon("./src/img/spaceship.png"); // load the image to a imageIcon
			Image image = ship.getImage(); // transform it 
			Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			ship = new ImageIcon(newimg);
			g.drawImage(ship.getImage(),x,y,null);

		}else if(id == ID.Player2){
//			g.setColor(Color.white);
//			g2d.draw(getBounds());
			g.setColor(Color.blue);
			g.fillRect(x, y, 32, 32);
		}
	}
	
	public boolean isDamageCooldown() {
		return damageCooldown;
	}
	
	public void damageCooldown(){
		damageCooldown = true;

		new java.util.Timer().schedule(new TimerTask(){
			public void run(){
				damageCooldown = false;
			}
		},500);
	

	}
	public boolean isBulletCoolDown(){
		return bulletCooldown;
	}

	public void bulletCooldown(){
		bulletCooldown = true;

		new java.util.Timer().schedule(new TimerTask(){
			public void run(){
				bulletCooldown = false;
			}
		},attackspeed);
	

	}
	
	public int getDamage() {
		return damage;
	}
}
