package pattern.bridge;

/**
 * @author lwk 组织者(桥)
 */
public class Bridge {
	
	private Diverable bridge;

	public void method() {
		bridge.bridgeMethod();
	}

	public Diverable getBridge() {
		return bridge;
	}

	public void setBridge(Diverable bridge) {
		this.bridge = bridge;
	}
	
}
