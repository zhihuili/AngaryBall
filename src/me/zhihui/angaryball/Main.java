package me.zhihui.angaryball;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		Utils.initFrame(frame, 1440, 800);

		BallPanel bp = new BallPanel();
		frame.add(bp);
		frame.setVisible(true);
		bp.start();

	}

}
