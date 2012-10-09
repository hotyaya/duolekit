package cn.rdm.biz;

public class TimeMillisRandomIdGenerator implements IIdGenerator {

	@Override
	public long getIdGenerator() {
		// TODO Auto-generated method stub
		return System.currentTimeMillis();
	}

}
