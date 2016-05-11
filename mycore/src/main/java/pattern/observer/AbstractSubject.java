package pattern.observer;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author lwk
 * 实现了推送的观察者模式
 */
public abstract class AbstractSubject implements Subject {

	private String data1;
	
	private String data2;
	
	
	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	private Vector<Observer> vector = new Vector<Observer>();

	@Override
	public void add(Observer observer) {

		vector.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		vector.remove(observer);
	}

	@Override
	public void notifyObserver() {
		Enumeration<Observer> enumeration = vector.elements();
		while(enumeration.hasMoreElements()){
			enumeration.nextElement().update(getData1(), getData2());
		}
		//通知完以后 写下日志
		writeLog();
	}

}
