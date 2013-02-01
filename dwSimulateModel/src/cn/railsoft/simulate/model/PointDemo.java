package cn.railsoft.simulate.model;

import java.io.Serializable;

public class PointDemo implements Serializable {

	private int i = 0;

	public void run() {
		i++;
		x = x + i * 100; // 米
	}

	private long x = 0;
	private long y = 0;
	private long z = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2722161395748066132L;

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public long getZ() {
		return z;
	}

	public void setZ(long z) {
		this.z = z;
	}

	@Override
	public String toString() {
		return "坐标 [i=" + i + ", x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}
