package com.jgw.smp;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Hero {
	final int JUMP_SPEED = -20;
	final int MOVE_SPEED = 5;

	final int BODY_WIDTH = 80;
	final int BODY_HEIGHT = 120;
	final int FACE_WIDTH = 30;
	final int FACE_HEIGHT = 35;
	
	private static int centerX = 100;
	private static int centerY = -377;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean readyToFire = true;

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private int speedX = 0;
	private int speedY = 0;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	public static Rectangle body = new Rectangle(0, 0, 0, 0);
	public static Rectangle head = new Rectangle(0, 0, 0, 0);

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
			bg1.setSpeedX(-MOVE_SPEED/5);
			bg2.setSpeedX(-MOVE_SPEED/5);
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
		
		body.setRect(centerX - (BODY_WIDTH/2), centerY - (BODY_HEIGHT/2), BODY_WIDTH, BODY_HEIGHT);
		head.setRect(centerX - (FACE_WIDTH/2), centerY - (BODY_HEIGHT/2), FACE_WIDTH, FACE_HEIGHT);
		
		yellowRed.setRect(centerX - (BODY_WIDTH/2), centerY - (BODY_HEIGHT/2), BODY_WIDTH, BODY_HEIGHT);
	}

	public void moveLeft() {
			speedX = -MOVE_SPEED;

	}

	public void moveRight() {
			speedX = MOVE_SPEED;
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
			speedY = JUMP_SPEED;
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
		return JUMP_SPEED;
	}

	public int getMOVESPEED() {
		return MOVE_SPEED;
	}

	public int getBODYWIDTH() {
		return BODY_WIDTH;
	}

	public int getBODYHEIGHT() {
		return BODY_HEIGHT;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
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
