package com.jgw.smp;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
	private int tileX, tileY, speedX;
	private int type;
	public Image tileImage;
	private int pixelSize = 40;
	private Rectangle r;

	private Hero hero = StartingClass.getHero();
	private Background bg = StartingClass.getBg1();

	public Tile(int x, int y, int typeInt) {
		tileX = x * pixelSize;
		tileY = y * pixelSize;

		type = typeInt;

		r = new Rectangle();

		/*
		 * switch (type) { case 1: tileImage = StartingClass.tileOcean; break;
		 * case 2: tileImage = StartingClass.tileDirt; break; }
		 */
		switch (type) {
		/* Ground */
		case 5:
			tileImage = StartingClass.tileDirt;
			break;
		case 8:
			tileImage = StartingClass.tileGrassTop;
			break;
		case 4:
			tileImage = StartingClass.tileGrassLeft;
			break;
		case 6:
			tileImage = StartingClass.tileGrassRight;
			break;
		case 2:
			tileImage = StartingClass.tileGrassBottom;
			break;
		/* Stuff */
		case 1:
			tileImage = StartingClass.tileCharMegaman;
			break;
		case 3:
			tileImage = StartingClass.tileCharMario;
			break;
		case 7:
			tileImage = StartingClass.tileCharSamus;
			break;
		case 9:
			tileImage = StartingClass.tileCharSimon;
			break;
		default:
			type = 0;
			break;
		}
	}

	public void update() {
		/*
		 * For parallax effect if (type == 1) { if (bg.getSpeedX() == 0) {
		 * speedX = -1; } else { speedX = -2; } } else { speedX = bg.getSpeedX()
		 * * 5; } speedX = -1;
		 */
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		r.setBounds(tileX, tileY, 40, 40);

		if (r.intersects(Hero.yellowRed) && type != 0) {
			if (checkVerticalCollision(Hero.bodyUpper)) {
				hero.setJumped(false);
				hero.setSpeedY(0);
				hero.setCenterY(tileY - 63);
				System.out.println("Upper collision.");
			}
			if (checkVerticalCollision(Hero.bodyLower)) {
				hero.setJumped(false);
				hero.setSpeedY(0);
				hero.setCenterY(tileY - 63);
				System.out.println("Lower collision.");
			}
/*
			if (checkSideCollision(Hero.bodyUpper)) {
				hero.setCenterX(tileX + 102);
				hero.setSpeedX(0);
				System.out.println("Side collision.");
			}
*/
		}
	}

	public boolean checkVerticalCollision(Rectangle rect) {
		if (rect.intersects(r) && type == 8) {
			return true;
		} else {
			return false;
		}
	}

	public boolean checkSideCollision(Rectangle rect) {
		if (type != 1 && type != 3 && type != 7 && type != 9 && type != 0) {
			if (rect.intersects(r)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
}
