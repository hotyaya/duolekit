package cn.railsoft.simulate.model;

import java.io.Serializable;

public class Point implements Serializable {
	private static final long serialVersionUID = 2330505109621533436L;
	private long x = 0;
	private long y = 0;
	private long z = 0;
	
	public Point(long x, long y, long z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}


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
		return "坐标 [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
}
