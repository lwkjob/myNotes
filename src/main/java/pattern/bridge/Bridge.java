package pattern.bridge;

/**
 * @author lwk 组织者(桥)
 * 
 * 这个类规定了客户需要的方法，并做了部分实现，因为他不能直接实例化所以他还需要一个继承者
 */
public abstract  class Bridge {
	
	private Diverable driver;

	public abstract void doDrivermethod() ;

	public Diverable getDriver() {
		return driver;
	}

	public void setDriver(Diverable driver) {
		this.driver = driver;
	}
}
