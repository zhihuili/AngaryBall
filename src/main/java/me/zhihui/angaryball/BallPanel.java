package me.zhihui.angaryball;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BallPanel extends JPanel {

	Image backGround;
	Ball ball;
	Target target;
	char[] boomChar = "Boom!!!".toCharArray();
	boolean isRepare = true;
	boolean isInit = true;
	int mouseX, mouseY;

	public BallPanel() {
		ball = new Ball(this);
		target = new Target();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(backGround, 0, 0, null);
		if (isInit) {
			initBackGround();
			g.drawImage(target.pig, target.x, target.y, null);
		} else if (isRepare) {
			g.drawImage(target.pig, target.x, target.y, null);
			g.drawImage(ball.getBallImage(), ball.x, ball.y, null);
			g.drawLine(mouseX, mouseY, ball.x, ball.y);
		} else if (!target.boom(ball.getBirdPoint()[0], ball.getBirdPoint()[1])) {
			g.drawImage(ball.getBallImage(), ball.x, ball.y, null);
			g.drawImage(target.pig, target.x, target.y, null);
		} else {
			g.drawImage(target.hurtPig, target.x, target.y, null);
		}
	}

	private void initBackGround() {
		try {

			backGround = ImageIO.read(BallPanel.class.getResource("/background.png")).getScaledInstance(getWidth(),
					getHeight(), Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void action() {

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				isRepare = true;
				isInit = false;
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
		int i = 0;// 1/100 millisecond
		while (ball.x < 1600 && ball.y < 1200 && (ball.x < target.x + 100 || ball.y < target.y + 100)) {
			ball.calculate(i++);
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
