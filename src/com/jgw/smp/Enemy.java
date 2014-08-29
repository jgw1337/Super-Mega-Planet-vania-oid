package com.jgw.smp;

import java.awt.Rectangle;

public class Enemy {
	private int power, speedX, speedY, centerX, centerY;
	private Background bg = StartingClass.getBg1();
	public static Rectangle rBody = new Rectangle(0, 0, 0, 0);
	public int health = 5;
	
	private int movementSpeedX, movementSpeedY;

	public void update() {
		follow();
		centerX += speedX;
		centerY += speedY;

		speedX = bg.getSpeedX() * 5 + movementSpeedX;
		speedY = movementSpeedY;
		rBody.setBounds(centerX - 25, centerY - 25, 50, 60);
		if (rBody.intersects(Hero.yellowRed)) {
			checkCollision();
		}
	}

	private void follow() {
		if (centerX < -95 || centerX > 810) {
			movementSpeedX = 0;
		} else if (Math.abs(Hero.getCenterX() - centerX) < 5) {
			movementSpeedX = 0;
		} else {
			if (Hero.getCenterX() >= centerX) {
				movementSpeedX = 1;
			} else {
				movementSpeedX = -1;
			}

		}
		if (centerY < 0 || centerY > 480) {
			movementSpeedY = 0;
		} else if (Math.abs(Hero.getCenterY() - centerY) < 5) {
			movementSpeedY = 0;
		} else {
			if (Hero.getCenterY() >= centerY) {
				movementSpeedY = 1;
			} else {
				movementSpeedY = -1;
			}
		}
		movementSpeedY = 10;
	}

	private void checkCollision() {
		if (rBody.intersects(Hero.bodyUpper) || rBody.intersects(Hero.bodyLower)) {
			System.out.println("collision");
		}
	}

	public void die() {
		// TODO Auto-generated method stub

	}

	public void attack() {
		// TODO Auto-generated method stub

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
}
