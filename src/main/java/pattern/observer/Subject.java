package pattern.observer;

/**
 * @author lwk
 * 被观察者
 */
public interface Subject {
	
	/**
	 * @param observer
	 * 添加观察者
	 */
	void add(Observer observer);
	/**
	 * @param observer
	 * 删除观察者
	 */
	void remove(Observer observer);
	 
	/**
	 * 记录日志
	 */
	void writeLog();
	
	
	/**
	 *通知观察者 
	 */
	void notifyObserver();
}
