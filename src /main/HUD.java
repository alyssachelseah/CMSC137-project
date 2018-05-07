package main;

import java.awt.*;

public class HUD{

	public static int HEALTH = 5;
	public static int SCORE = 0;
	private static int level = 1;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 5);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(15, 15, HEALTH * 40, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	
		g.drawString("Score: " + SCORE, 15, 64);
		g.drawString("Level: " + level, 15, 80);
	} 
	
//	public void addScore(int score) {
//		this.score= score;
//	}
//	
//	public int getScore() {
//		return score;
//	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
