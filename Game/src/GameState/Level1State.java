package GameState;

import java.awt.*;
import java.util.ArrayList;
import main.Board;
import TileMap.*;
import Creature.*;
import Creature.Enemies.Meat;

import java.awt.event.KeyEvent;

public class Level1State extends GameState {
	
	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	
	private ArrayList<Enemy> enemies; 
	private ArrayList<Boom> Boom;  //plahvatused
	
	private HUD hud;
	
	
	public Level1State(Manager gsm){
		this.gsm = gsm;
		init();
	}
	public void init() {
		
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/level1-1.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(0.07);
		
		bg = new Background ("/Backgrounds/grassbg1.gif", 0.1);
		
		player = new Player(tileMap);
		player.setPosition(100, 100);
		
		enemies = new ArrayList<Enemy>();
		Meat s;
		s = new Meat(tileMap);
		s.setPosition(100, 100);
		enemies.add(s);
		
		Boom = new ArrayList<Boom>();
		
		hud = new HUD(player);
		
	}
	
	
	public void update() {
	
		//update player position
		player.update();
		tileMap.setPosition(
				Board.WIDTH / 2 - player.getx(),
				Board.HEIGHT / 2 - player.gety()
			);
		
		//background moving
		bg.setPosition(tileMap.getx(), tileMap.gety());
		
		//attack enemies
		player.checkAttack(enemies);
		
		//update enemy
		for(int i = 0; i < enemies.size(); i++){
			Enemy e = enemies.get(i);
			e.update();
			if(e. isDead()){
				enemies.remove(i);
				i--;
				Boom.add(new Boom(e.getx(), e.gety()));
			}
		}
		//update explosion
		for(int i = 0; i < Boom.size(); i++){
			Boom.get(i) .update();
			if(Boom.get(i) .shouldRemove()){
				Boom.remove(i);
				i--;
			}
		}
	}
	public void draw (Graphics2D g){
		//tausta joonistamine
		bg.draw(g);
		
		//raja joonistamine
		tileMap.draw(g);
		
		//draw player 
	player.draw(g);
	
	//draw enemy
	for(int i = 0; i < enemies.size(); i++) {
		enemies.get(i).draw(g);
	}
	
	//draw explosion
	for(int i = 0; i < Boom.size(); i++) {
		Boom.get(i).draw(g);
	}
	
	hud.draw(g);
	}
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.setRight(true);
		if(k == KeyEvent.VK_UP) player.setUp(true);
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		if(k == KeyEvent.VK_W) player.setJumping(true);
		if(k == KeyEvent.VK_E) player.setFlying(true);
		if(k == KeyEvent.VK_Q) player.setScratching();
		if(k == KeyEvent.VK_R) player.setFiring();
	}
	
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.setLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_E) player.setFlying(false);
	}
	
}
