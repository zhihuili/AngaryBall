package me.zhihui.jumpball;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class JumpBall {
	public int x = 0;
	public int y = 0;
	int centerX;
	int centerY;
	int startx = 0;
	int starty = 390;
	int speedx = 145;
	int speedy = -70;
	BufferedImage[] birdImages = new BufferedImage[4];
	int imageCounter = 0;
	int imageIndex = 0;

	public JumpBall(JPanel panel) {
	}

	public int[] getBirdPoint() {
		int[] point = new int[2];
		point[0] = x + centerX;
		point[1] = y + centerY;
		return point;
	}

	public void initBall(int speedX, int speedY) {
		startx = x;
		starty = y;
		this.speedx = speedX;
		this.speedy = speedY;
	}

	BufferedImage getBallImage() {
		if (imageCounter++ % 30 == 0)
			imageIndex++;
		return birdImages[imageIndex % 4];

	}

	public void calculate(double i) {
		x = (int) (startx + speedx * i / 100);
		y = (int) (starty + speedy * i / 100 + 0.5 * 9.8 * Math.pow(i / 100, 2));
	}

	public void updateBall(double i, double lost) {
		speedy = -(int) Math.round((speedy + 9.8 * i / 100) * (1 - lost));
		startx = x;
		starty = y;
//		y--;
	}
}
