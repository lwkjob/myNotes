package pattern.bridge;

/**
 * @author lwk
 * 我的第三者 复制调用 和 set
 */
public class MyBridge extends Bridge{
		public void method(){
			getBridge().bridgeMethod();
		}
}
