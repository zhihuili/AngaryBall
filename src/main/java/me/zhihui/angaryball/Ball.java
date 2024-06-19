package me.zhihui.angaryball;

import javax.swing.JPanel;

public class Ball {
	public int x = 0;
	public int y = 0;
	int startx = 0;
	int starty = 560;
	int speedx = 50;
	int speedy = -100;
	JPanel panel;

	public Ball(JPanel panel) {
		this.panel = panel;
	}

	public void calculate(double i) {
		x = (int) (startx + speedx * i / 100);
		y = (int) (starty + speedy * i / 100 + 0.5 * 9.8 * Math.pow(i / 100, 2));
		System.out.println(x + "," + y);
	}
}
