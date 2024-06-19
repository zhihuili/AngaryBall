package me.zhihui.angaryball;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Target {
	public int x = 1100;
	public int y = 200;
	public BufferedImage pig;
	public BufferedImage hurtPig;

	public Target() {
		try {
			pig = ImageIO.read(Target.class.getResource("/pig.png"));
			hurtPig = ImageIO.read(Target.class.getResource("/hurtpig.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean boom(int bx, int by) {
		System.out.println("bird:" + bx + "," + by + ";pig:" + x + "," + y + "," + (x + pig.getWidth()) + ","
				+ (y + pig.getHeight()));
		if (bx > x && bx < (x + pig.getWidth()) && by > y && by < (y + pig.getHeight()))
			return true;
		return false;
	}
}
