package pattern.observer;

/**
 * @author lwk
 * 观察者实现统一接口
 */
public interface Observer {
	/**
	 * 提供给“被观察者”调用，接收“被观察者”传来是数据
	 * @param data1
	 * @param data2
	 */
	void update(String data1,String data2);
}
