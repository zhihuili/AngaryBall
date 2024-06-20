package me.zhihui.angaryball;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		Utils.initFrame(frame, 1440, 800);

		BallPanel bp = new BallPanel();
		frame.add(bp);
		frame.setVisible(true);

		bp.action();
//		bp.start();

	}

}
