package pattern.observer;

public class Observer1 implements Observer {

	@Override
	public void update(String data1,String data2) {
		System.out.println("第一个观察者数据展示"+data1+"===="+data2);
	}

}
