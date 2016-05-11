package pattern.bridge;

public class OracleDirver implements Diverable {

	@Override
	public void diverMethod() {
		System.out.println("oracle 的驱动");
	}

}
