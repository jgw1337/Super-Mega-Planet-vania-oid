package com.jgw.smp;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.plaf.FontUIResource;

import com.jgw.framework.Animation1;

public class StartingClass extends Applet implements Runnable, KeyListener {

	public static Hero hero;
	public static Heliboy hb1, hb2;
	public static Boy b1, b2;
	private Image image, currentSprite, character, character2, character3, characterJumped, background;
	private Image heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
	private Image boy, boy2, boy3, boy4, boy5;
	public static Image tileOcean, tileDirt, tileGrassTop, tileGrassBottom,
			tileGrassLeft, tileGrassRight;
	public static Image tileCharLink, tileCharMegaman, tileCharMario, tileCharSamus, tileCharSimon;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private Animation1 anim, hAnim, bAnim;
	private ArrayList<Tile> tileArray = new ArrayList<Tile>();
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	enum GameState {
		Ready, Running, GameOver
	}

	GameState state = GameState.Running;

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Super Mega Planet-vania-oid");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		character = getImage(base, "data/images/character.png");
		character2 = getImage(base, "data/images/character2.png");
		character3 = getImage(base, "data/images/character3.png");

		characterJumped = getImage(base, "data/images/jumped.png");

		heliboy = getImage(base, "data/images/heliboy.png");
		heliboy2 = getImage(base, "data/images/heliboy2.png");
		heliboy3 = getImage(base, "data/images/heliboy3.png");
		heliboy4 = getImage(base, "data/images/heliboy4.png");
		heliboy5 = getImage(base, "data/images/heliboy5.png");

		boy = getImage(base, "data/images/heliboy.png");
		boy2 = getImage(base, "data/images/heliboy2.png");
		boy3 = getImage(base, "data/images/heliboy3.png");
		boy4 = getImage(base, "data/images/heliboy4.png");
		boy5 = getImage(base, "data/images/heliboy5.png");

		background = getImage(base, "data/images/background.png");

		tileOcean = getImage(base, "data/images/tileocean.png");
		tileDirt = getImage(base, "data/images/tiledirt.png");
		tileGrassTop = getImage(base, "data/images/tilegrasstop.png");
		tileGrassBottom = getImage(base, "data/images/tilegrassbottom.png");
		tileGrassLeft = getImage(base, "data/images/tilegrassleft.png");
		tileGrassRight = getImage(base, "data/images/tilegrassright.png");

		tileCharLink = getImage(base, "data/images/char_link.png");
		tileCharMegaman = getImage(base, "data/images/char_megaman.png");
		tileCharMario = getImage(base, "data/images/char_mario.png");
		tileCharSamus = getImage(base, "data/images/char_samus.png");
		tileCharSimon = getImage(base, "data/images/char_simon.png");

		anim = new Animation1();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		hAnim = new Animation1();
		hAnim.addFrame(heliboy, 100);
		hAnim.addFrame(heliboy2, 100);
		hAnim.addFrame(heliboy3, 100);
		hAnim.addFrame(heliboy4, 100);
		hAnim.addFrame(heliboy5, 100);
		hAnim.addFrame(heliboy4, 100);
		hAnim.addFrame(heliboy3, 100);
		hAnim.addFrame(heliboy2, 100);

		bAnim = new Animation1();
		bAnim.addFrame(boy, 100);
		bAnim.addFrame(boy2, 100);
		bAnim.addFrame(boy3, 100);
		bAnim.addFrame(boy4, 100);
		bAnim.addFrame(boy5, 100);
		bAnim.addFrame(boy4, 100);
		bAnim.addFrame(boy3, 100);
		bAnim.addFrame(boy2, 100);

		currentSprite = anim.getImage();
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		hero = new Hero();

		try {
			loadMap("data/maps/map1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		hb1 = new Heliboy(340, 100);
		hb2 = new Heliboy(700, 100);
		b1 = new Boy(340, 100);
		b2 = new Boy(700, 100);
		Thread thread = new Thread(this);
		thread.start();
	}

	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}

			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height = lines.size();

		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				System.out.println(i + "is i");
				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tileArray.add(t);
				}
			}
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void run() {
		if (state == GameState.Running) {
			while (true) {
				hero.update();
				if (hero.isJumped()) {
					currentSprite = characterJumped;
				} else if (!hero.isJumped()) {
					currentSprite = anim.getImage();
				}
				ArrayList<Projectile> projectiles = hero.getProjectiles();
				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = (Projectile) projectiles.get(i);
					if (p.isVisible()) {
						p.update();
					} else {
						projectiles.remove(i);
					}
				}
				updateTiles();
				hb1.update();
				hb2.update();
				b1.update();
				b2.update();
				bg1.update();
				bg2.update();
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (hero.getCenterY() > 500) {
					state = GameState.GameOver;
				}
			}
		}
	}

	public void animate() {
		anim.update(10);
		hAnim.update(50);
		bAnim.update(50);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, this.getWidth(), this.getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		if (state == GameState.Running) {
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
			paintTiles(g);
			ArrayList<Projectile> projectiles = hero.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}
			g.drawImage(hAnim.getImage(), hb1.getCenterX() - 48,
					hb1.getCenterY() - 48, this);
			g.drawImage(hAnim.getImage(), hb2.getCenterX() - 48,
					hb2.getCenterY() - 48, this);
			g.drawImage(bAnim.getImage(), b1.getCenterX() - 48,
					b1.getCenterY() - 48, this);
			g.drawImage(bAnim.getImage(), b2.getCenterX() - 48,
					b2.getCenterY() - 48, this);
			g.drawRect((int) hero.bodyUpper.getX(),
					(int) hero.bodyUpper.getY(),
					(int) hero.bodyUpper.getWidth(),
					(int) hero.bodyUpper.getHeight());
			g.drawRect((int) hero.bodyLower.getX(),
					(int) hero.bodyLower.getY(),
					(int) hero.bodyLower.getWidth(),
					(int) hero.bodyLower.getHeight());
			g.drawImage(currentSprite, hero.getCenterX() - 61,
					hero.getCenterY() - 63, this);

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 730, 30);
		} else if (state == GameState.GameOver) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Game Over", 360, 240);
		}
	}

	private void updateTiles() {
		for (int i = 0; i < tileArray.size(); i++) {
			Tile t = (Tile) tileArray.get(i);
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tileArray.size(); i++) {
			Tile t = (Tile) tileArray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up.");
			break;
		case KeyEvent.VK_LEFT:
			hero.moveLeft();
			hero.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			hero.moveRight();
			hero.setMovingRight(true);
			break;
		case KeyEvent.VK_SPACE:
			hero.jump();
			break;
		case KeyEvent.VK_CONTROL:
			// if (!hero.isDucked() && !hero.isJumped()) {
				hero.shoot();
				hero.setReadyToFire(false);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up.");
			break;
		case KeyEvent.VK_LEFT:
			hero.stopLeft();
			break;
		case KeyEvent.VK_RIGHT:
			hero.stopRight();
			break;
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_CONTROL:
			hero.setReadyToFire(true);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static Hero getHero() {
		return hero;
	}

	public static Heliboy getHb1() {
		return hb1;
	}

	public static Heliboy getHb2() {
		return hb2;
	}

	public static Boy getB1() {
		return b1;
	}

	public static Boy getB2() {
		return b2;
	}

}