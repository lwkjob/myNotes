package pattern.bridge;

public class BridgeClient {
	public static void main(String[] args) {
		Bridge bridge=new MyBridge();
		
		Diverable mysql=new MysqlDirver();
		bridge.setBridge(mysql);
		bridge.method();
		
		Diverable oracle=new OracleDirver();
		bridge.setBridge(oracle);
		bridge.method();
	}
}
