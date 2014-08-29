package com.jgw.smp;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Hero {
	final int JUMPSPEED = -20;
	final int MOVESPEED = 5;

	private static int centerX = 100;
	private static int centerY = -377;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private int speedX = 0;
	private int speedY = 0;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	public static Rectangle bodyUpper = new Rectangle(0, 0, 0, 0);
	public static Rectangle bodyLower = new Rectangle(0, 0, 0, 0);
	public static Rectangle armStageRight = new Rectangle(0, 0, 0, 0);
	public static Rectangle armStageLeft = new Rectangle(0, 0, 0, 0);
	public static Rectangle footStageRight = new Rectangle(0, 0, 0, 0);
	public static Rectangle footStageLeft = new Rectangle(0, 0, 0, 0);

	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);
	
	public void update() {
		// X Position
		if (speedX < 0) {
			centerX += speedX;
		}

		if (speedX <= 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}

		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}

		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED/5);
			bg2.setSpeedX(-MOVESPEED/5);
		}

		// Y Position
		// centerY += speedY;
		centerY += speedY;

		// Jump
		speedY += 1;
		if (speedY > 3) {
			jumped = true;
		}

		// Prevent going beyond X Coordinate
		if (centerX + speedX <= 60) {
			centerX = 61;

		}
		
		bodyUpper.setRect(centerX - 34, centerY - 63, 68, 63);
		bodyLower.setRect(bodyUpper.getX(), bodyUpper.getY() + 63, 68, 64);
		armStageLeft.setRect(bodyUpper.getX() - 26, bodyUpper.getY() + 32, 26, 20);
		armStageRight.setRect(bodyUpper.getX() + 68, bodyUpper.getY() + 32, 26, 20);
		footStageLeft.setRect(centerX - 50, centerY + 20, 50, 15);
		footStageRight.setRect(centerX, centerY + 20, 50, 15);
		
		yellowRed.setRect(centerX - 110, centerY - 110, 180, 180);
	}

	public void moveLeft() {
		if (!ducked) {
			speedX = -MOVESPEED;
		}

	}

	public void moveRight() {
		if (!ducked) {
			speedX = MOVESPEED;
		}
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stop() {
		if (!isMovingRight() && !isMovingLeft()) {
			speedX = 0;
		}
		if (!isMovingRight() && isMovingLeft()) {
			moveLeft();
		}
		if (isMovingRight() && !isMovingLeft()) {
			moveRight();
		}
	}

	public void jump() {
		if (!jumped) {
			speedY = JUMPSPEED;
			jumped = true;
		}
	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}
	
	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	public int getMOVESPEED() {
		return MOVESPEED;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public boolean isDucked() {
		return ducked;
	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public static void setBg1(Background bg1) {
		Hero.bg1 = bg1;
	}

	public static void setBg2(Background bg2) {
		Hero.bg2 = bg2;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public static int getCenterX() {
		return centerX;
	}

	public static int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

}
