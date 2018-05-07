package main;

import java.awt.Color;
import java.util.*;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private boolean isSpawned = false;
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
				for(int i = 0; i < 20; i++) {
					int space = i * 140;
					handler.addObject(new BasicEnemy(30 + space, 50, ID.BasicEnemy, handler, Color.yellow, 1, "./src/img/cow.png"));
				}
			}
		}else if(hud.getLevel() == 2) {
			if(timer >= 350) {
				timer = 0;
				for(int i = 0; i < 50; i++) {
					int space = i * 140;
					handler.addObject(new BasicEnemy(30 + space, 50, ID.BasicEnemy, handler, Color.pink, 2, "./src/img/cow2.png"));
				}
			}
		}else if(hud.getLevel() == 3) {
			if(timer >= 263) {
				timer = 0;
				for(int i = 0; i < 50; i++) {
					int space = i * 140;
					handler.addObject(new BasicEnemy(30 + space, 50, ID.BasicEnemy, handler, Color.white, 3, "./src/img/cow3.png"));
				}
			}
		}else if(hud.getLevel() == 4) {
			if(timer >= 500 && isSpawned == false) {
				handler.addObject(new BossEnemy(452, 96, ID.BossEnemy, handler));
				this.isSpawned = true;
			}
		}
	}
}
