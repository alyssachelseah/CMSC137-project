package main;

import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable{
	
	public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	
	public Game(){
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "Alien Cows", this);
	
		
		r = new Random();
		
//		for(int i = 0; i < 50; i++) {
//			handler.addObject(new Player(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.Player));
//		}
		handler.addObject(new Player(400, 600, ID.Player));
		handler.addObject(new Player(600, 600, ID.Player2));
		handler.addObject(new BasicEnemy(30, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(100, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(170, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(240, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(310, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(380, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(450, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(520, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(590, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(660, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(730, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(800, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(870, 50, ID.BasicEnemy));
		handler.addObject(new BasicEnemy(940, 50, ID.BasicEnemy));
		
	}
	
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running) {
				render();
			}
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var >= max) return var = max;
		else if(var <= min)return var = min;
		else return var;
	}
	
	public static void main(String args[]) {
		System.setProperty("sun.java2d.opengl", "true");
		
		new Game();
		
	}
	
}
