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
		if (!target.boom(ball.x, ball.y)) {
			// 设置颜色
			g.setColor(new Color(255, 100, 100));
			// 画球
			g.fillOval(ball.x, ball.y, 31, 31);

			g.setColor(new Color(2, 100, 255));
			g.fillRect(target.x, target.y, 50, 50);
		} else {
			g.setColor(Color.BLACK);
			g.drawChars(boomChar, 0, 7, target.x, target.y);
		}
	}

	public void start() {
		for (double i = 0; i < 5000; i++) {
			ball.calculate(i);
			repaint();
			if (target.boom(ball.x, ball.y)) {
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
