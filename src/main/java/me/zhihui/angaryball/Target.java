package me.zhihui.angaryball;

public class Target {
	public int x = 1300;
	public int y = 700;

	public boolean boom(int bx, int by) {
		if (Math.abs(x - bx) < 20 && Math.abs(y - by) < 20)
			return true;
		return false;
	}
}
