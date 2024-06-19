package me.zhihui.angaryball;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BallPanel extends JPanel {

	Ball ball;
	Target target;
	char[] boomChar = "Boom!!!".toCharArray();

	public BallPanel() {
		ball = new Ball(this);
		target = new Target();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (!target.boom(ball.getBirdPoint()[0], ball.getBirdPoint()[1])) {

			g.drawImage(ball.getBallImage(), ball.x, ball.y, null);

			g.drawImage(target.pig, target.x, target.y, null);
		} else {
			g.drawImage(target.hurtPig, target.x, target.y, null);
		}
	}

	public void start() {
		for (double i = 0; i < 5000; i++) {
			ball.calculate(i);
			repaint();
			if (target.boom(ball.getBirdPoint()[0], ball.getBirdPoint()[1])) {
				break;
			}
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
