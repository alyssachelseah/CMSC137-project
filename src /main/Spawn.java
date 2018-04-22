package main;

import java.util.*;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	private int timer = 0;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		timer++;
		scoreKeep++;
		
		if(scoreKeep >= 1000){
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
		}
		
		
		if(hud.getLevel() == 1) {
			if(timer >= 700) {
				timer = 0;
				for(int i = 0; i < 50; i++) {
					int space = i * 70;
					handler.addObject(new BasicEnemy(30 + space, 50, ID.BasicEnemy, handler));
				}
			}
		}else if(hud.getLevel() == 2) {
			if(timer >= 350) {
				timer = 0;
				for(int i = 0; i < 50; i++) {
					int space = i * 70;
					handler.addObject(new Enemy2(30 + space, 50, ID.Enemy2, handler));
				}
			}
		}else if(hud.getLevel() == 3) {
			if(timer >= 263) {
				timer = 0;
				for(int i = 0; i < 50; i++) {
					int space = i * 70;
					handler.addObject(new Enemy3(30 + space, 50, ID.Enemy3, handler));
				}
			}
		}
	}
}
