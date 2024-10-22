package app;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	//SCREEN SETTINGS
	public final static int TILESIZE_ORIGINAL = 16;
	public final static int TILESIZE_SCALE = 3;
	public final static int MAXSCREEN_COL = 16;
	public final static int MAXSCREEN_ROW = 12;
	public final static int FPS = 60;
	public final static int TILESIZE = TILESIZE_ORIGINAL * TILESIZE_SCALE;
	public final static int SCREEN_WIDTH = (TILESIZE_ORIGINAL * TILESIZE_SCALE) * MAXSCREEN_COL; // 768 pixels
	public final static int SCREEN_HEIGHT = (TILESIZE_ORIGINAL * TILESIZE_SCALE) * MAXSCREEN_ROW; // 576 pixels
	
	//WORLD SETTINGS
	public final static int MAXWORLD_COL = 50;
	public final static int MAXWORLD_ROW = 50;
	public final static int WORLD_WIDTH = TILESIZE * MAXWORLD_COL;
	public final static int WORLD_HEIGHT = TILESIZE * MAXWORLD_ROW;
	
	//OBJECTS SETTINGS
	private Thread game_Thread;
	private KeyHandler keyH = new KeyHandler();
	private Player player = new Player(keyH, this);
	private TileManager tile_manager = new TileManager(player);
	private CollisionChecker collision_checker = new CollisionChecker(this);
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		startGameThread();
	}
	public TileManager getTile_manager() {
		return tile_manager;
	}
	
	public CollisionChecker getCollision_checker() {
		return collision_checker;
	}
	public void startGameThread() {
		game_Thread = new Thread(this);
		game_Thread.start();
	}
	
	@Override
	public void run() {
		double draw_Interval = (double)1000000000/FPS;
		double draw_delta = 0;
		long draw_lastTime = System.nanoTime();
		long draw_currentTime = 0;
		
		while(game_Thread != null) {
			
			draw_currentTime = System.nanoTime();
			
			draw_delta += (draw_currentTime - draw_lastTime) / draw_Interval;
			draw_lastTime = draw_currentTime;
			
			if(draw_delta >= 1) {
				//update info
				update();
				//draw
				repaint();
				draw_delta--;
			}
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D)g;
		tile_manager.draw(graphics2d);
		player.draw(graphics2d);
		graphics2d.dispose();
	}
}
