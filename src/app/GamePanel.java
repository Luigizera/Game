package app;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.NPC;
import entity.Player;
import object.ObjectGame;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	public enum GameState {
		PLAY(0),
		PAUSE(1),
		DIALOGUE(2);
		
		private int num;
		private GameState(int num) {
			this.num = num;
		}
		
		protected byte getByte() {
			return (byte)num;
		}
	}
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
	
	//SYSTEM
	private Thread game_Thread;
	private KeyHandler keyH = new KeyHandler(this);
	private TileManager tile_manager = new TileManager(this);
	private AssetSetter asset_setter = new AssetSetter(this);
	private CollisionChecker collision_checker = new CollisionChecker(this);
	private Sound music = new Sound();
	private Sound sfx = new Sound();
	private UI ui = new UI(this);
	//Entity and Object
	private Player player = new Player(keyH, this);
	private ObjectGame object_game[] = new ObjectGame[10];
	private NPC npc[] = new NPC[10];
	
	//Game State
	private GameState game_state = GameState.PLAY;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		setupGame();
		startGameThread();
	}
	public void setupGame() {
		asset_setter.setObject();
		asset_setter.setNPC();
		playMusic(0);
		game_state = GameState.PLAY;
	}
	public void startGameThread() {
		game_Thread = new Thread(this);
		game_Thread.start();
	}
	
	public void stopGameThread() {
		game_Thread = null;
	}
	
	public GameState getGame_state() {
		return game_state;
	}
	
	public void setGame_state(GameState game_state) {
		this.game_state = game_state;
	}
	
	public ObjectGame[] getObject_game() {
		return object_game;
	}
	
	public NPC[] getNPC() {
		return npc;
	}
	
	public Player getPlayer() {
		return player;
	}
	public TileManager getTile_manager() {
		return tile_manager;
	}
	
	public UI getGameUI() {
		return ui;
	}
	
	public CollisionChecker getCollision_checker() {
		return collision_checker;
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
		if(game_state == GameState.PLAY) {
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			player.update();
		}
		if(game_state == GameState.PAUSE) {
			
		}
	}
	
	public void paintComponent(Graphics g) {
		
		long drawStart = System.nanoTime();
		super.paintComponent(g);
		Graphics2D graphics2d = (Graphics2D)g;
		tile_manager.draw(graphics2d);
		for(int i = 0; i < object_game.length; i++) {
			if(object_game[i] != null) {
				object_game[i].draw(graphics2d, this);
			}
		}
		for(int i = 0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(graphics2d);
			}
		}
		player.draw(graphics2d);
		if(keyH.getCheckDrawTime()) {
			long drawEnd = System.nanoTime();
			graphics2d.setColor(Color.black);
			graphics2d.drawString("Draw time: " + (drawEnd - drawStart), 10, 400);
			System.out.println((drawEnd - drawStart));
		}
		ui.draw(graphics2d);
		
		graphics2d.dispose();
	}
	
	public void playMusic(int index) {
		music.setFile(index);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSFX(int index) {
		sfx.setFile(index);
		sfx.play();
	}
}
