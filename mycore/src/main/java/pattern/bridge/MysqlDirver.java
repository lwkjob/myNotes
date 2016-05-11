package pattern.bridge;

public class MysqlDirver implements Diverable{

	@Override
	public void diverMethod() {
		System.out.println("mysql 的驱动");
	}
}
