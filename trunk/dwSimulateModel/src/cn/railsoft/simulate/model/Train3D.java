package cn.railsoft.simulate.model;

import java.io.Serializable;

public class Train3D  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1463531198601795373L;
	private TrainDemo train = null;
	private PointDemo head = null;
	private PointDemo tail = null;	//根据坐标，车长，算出车尾的距离；
	public TrainDemo getTrain() {
		return train;
	}
	public void setTrain(TrainDemo train) {
		this.train = train;
	}
	public PointDemo getHead() {
		return head;
	}
	public void setHead(PointDemo head) {
		this.head = head;
	}
	public PointDemo getTail() {
		return tail;
	}
	public void setTail(PointDemo tail) {
		this.tail = tail;
	}
	

}
