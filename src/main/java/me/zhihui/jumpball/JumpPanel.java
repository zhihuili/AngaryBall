package me.zhihui.jumpball;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JumpPanel extends JPanel {

	JumpBall ball = new JumpBall(this);
	boolean isRepare = true;
	boolean isInit = true;
	int mouseX, mouseY;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillOval(ball.x, ball.y, 30, 30);
		g.setColor(Color.RED);
		g.drawLine(0, 730, 1440, 730);
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
		while (ball.x < 1500) {
			ball.calculate(++i);
			repaint();
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (ball.y >= 700) {
				ball.updateBall(i, 0.2);
				i = 0;
			}

		}
	}
	



	public static void main(String[] args) {

		JFrame frame = new JFrame();
		initFrame(frame, 1440, 800);

		JumpPanel sp = new JumpPanel();
		frame.add(sp);
		frame.setVisible(true);

		sp.action();

	}

	static private void initFrame(JFrame frame, int width, int height) {
		// 获取一个与系统相关工具类对象
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		// 获取屏幕的分辨率
		Dimension d = toolkit.getScreenSize();
		int x = (int) d.getWidth();
		int y = (int) d.getHeight();
		frame.setBounds(0, 0, width, height);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}



}
