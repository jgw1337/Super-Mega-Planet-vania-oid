package com.jgw.smp;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {
	private int tileX, tileY, speedX, type;
	public Image tileImage;
	private int pixelSize = 40;
	private Rectangle r;

	private Hero robot = StartingClass.getRobot();
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
			checkVerticalCollision(Hero.bodyUpper, Hero.bodyLower);
			checkSideCollision(Hero.armStageLeft, Hero.armStageRight,
					Hero.footStageLeft, Hero.footStageRight);
		}
	}

	public void checkVerticalCollision(Rectangle rTop, Rectangle rBottom) {
		if (rTop.intersects(r)) {
			System.out.println("Upper collision.");
		}

		if (rBottom.intersects(r) && type == 8) {
			robot.setJumped(false);
			robot.setSpeedY(0);
			robot.setCenterY(tileY - 63);
			System.out.println("Lower collision.");
		}
	}

	public void checkSideCollision(Rectangle rArmStageLeft,
			Rectangle rArmStageRight, Rectangle rFootStageLeft,
			Rectangle rFootStageRight) {
		if (type != 5 && type != 2 && type != 0) {
			if (rArmStageLeft.intersects(r)) {
				robot.setCenterX(tileX + 102);
				robot.setSpeedX(0);
			} else if (rFootStageLeft.intersects(r)) {
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}
			
			if (rArmStageRight.intersects(r)) {
				robot.setCenterX(tileX - 62);
				robot.setSpeedX(0);
			} else if(rFootStageRight.intersects(r)) {
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
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
