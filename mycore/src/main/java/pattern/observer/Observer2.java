package pattern.observer;

public class Observer2 implements Observer {

	@Override
	public void update(String data1,String data2) {
		System.out.println("第二个的这种:"+data1+"++++++++++++"+data2);
	}


}
