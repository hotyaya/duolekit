package cn.rs.bbsadmin.mem.filter;

import java.util.Vector;

/**
 * 变化率
 * 
 * @author Hui
 * 
 */
public class ChangeRateUtil {
	private Vector<Integer> voriginal = null;
	//private int iSliceLength = 3; // 统计的切片数据组的长度；

	public ChangeRateUtil() {
		super();
		voriginal = new Vector<Integer>();
	}

	public void addValue(Integer i) {
		voriginal.add(i);
	}

	/**
	 * @param iSliceLength 统计数组的长度 3秒
	 * @param ioffset 切片数组内的偏差 4秒
	 */
	
	private boolean getStatics(int iSliceLength,int ioffset) {
		boolean isBeSatisfied = false; 
		if (voriginal.size() < iSliceLength) {
			return isBeSatisfied;
		}
		for (int i = 0; i < voriginal.size()&& ((i + iSliceLength - 1) < voriginal.size()); i++) {
			int[] staticsSlice = new int [iSliceLength];
			int[] staticsSlicecount = new int [iSliceLength];
			for (int j = 0;j<iSliceLength;j++){
				staticsSlice[j]=((Integer)voriginal.elementAt(i+j)).intValue();
			}
			staticsSlicecount[0] = 0;
			int mstatics = 1;
			for (int j = 1;j<iSliceLength;j++){
				staticsSlicecount[j] = staticsSlice[j] -staticsSlice[j-1];
				if (Math.abs(staticsSlicecount[j])<=ioffset){
					mstatics ++ ;
				}
			}
			if (iSliceLength == mstatics){
				System.out.println("发现一组切片满足");
				isBeSatisfied = true;
			}
			if (isBeSatisfied )break;
		}
		return isBeSatisfied;
	}
	
	public boolean isBestBeSatisfied(){
		return (getStatics(3,3) && getStatics(4,5)) || getStatics(8,40);
	}
}
