package me.zhihui.angaryball;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BallPanel extends JPanel {

	Ball ball;
	Target target;
	char[] boomChar = "Boom!!!".toCharArray();
	boolean isRepare = true;
	int mouseX, mouseY;

	public BallPanel() {
		ball = new Ball(this);
		target = new Target();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (isRepare) {
			g.drawImage(target.pig, target.x, target.y, null);
			g.drawImage(ball.getBallImage(), ball.x, ball.y, null);
			g.drawLine(mouseX, mouseY, ball.x, ball.y);
		} else {
			if (!target.boom(ball.getBirdPoint()[0], ball.getBirdPoint()[1])) {
				g.drawImage(ball.getBallImage(), ball.x, ball.y, null);
				g.drawImage(target.pig, target.x, target.y, null);
			} else {
				g.drawImage(target.hurtPig, target.x, target.y, null);
			}
		}
	}

	public void action() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ball.x = e.getX();
				ball.y = e.getY();
				mouseX = e.getX();
				mouseY = e.getY();
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isRepare = false;

				ball.initBall(mouseX - e.getX(), mouseY - e.getY());
				new Thread(() -> {
					start();
				}).start();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				if (x > mouseX) {
					x = mouseX;
				}
				if (y < mouseY) {
					y = mouseY;
				}

				ball.x = x;
				ball.y = y;
				repaint();
			}
		});

	}

	public void start() {
		for (double i = 0; i < 1000; i++) {
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
