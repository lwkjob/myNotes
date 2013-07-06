package pattern.bridge;

/**
 * @author lwk 组织者(桥)
 * 
 * 给用户调用，没看懂为什么要这个是抽象类
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
