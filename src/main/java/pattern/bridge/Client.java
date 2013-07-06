package pattern.bridge;

public class Client {
	public static void main(String[] args) {
		Bridge bridge=new BridgeImpl();
		
		Diverable mysql=new MysqlDirver();
		bridge.setDriver(mysql);
		bridge.doDrivermethod();
		
		Diverable oracle=new OracleDirver();
		bridge.setDriver(oracle);
		bridge.doDrivermethod();
	}
}
