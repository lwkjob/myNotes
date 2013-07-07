package pattern.observer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SubjectImpl extends AbstractSubject {
	
	public SubjectImpl(String data1,String data2) {
		this.setData1(data1);
		this.setData2(data2);
	}
	@Override
	public void writeLog() {
		String notifyTime=SimpleDateFormat.getDateInstance().format(new Date());
		System.out.println("我在这个日期:"+notifyTime+",通知了");
	}

}
