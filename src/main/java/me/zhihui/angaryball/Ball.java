package me.zhihui.angaryball;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Ball {
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

	public Ball(JPanel panel) {
		try {
			birdImages[0] = ImageIO.read(Ball.class.getResource("/bird0.png"));
			birdImages[1] = ImageIO.read(Ball.class.getResource("/bird1.png"));
			birdImages[2] = ImageIO.read(Ball.class.getResource("/bird2.png"));
			birdImages[3] = ImageIO.read(Ball.class.getResource("/bird3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		centerX = birdImages[0].getWidth() / 2;
		centerY = birdImages[1].getHeight() / 2;
	}

	public int[] getBirdPoint() {
		int[] point = new int[2];
		point[0] = x + centerX;
		point[1] = y + centerY;
		return point;
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
}
