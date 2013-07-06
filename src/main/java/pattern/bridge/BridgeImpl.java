package pattern.bridge;

/**
 * @author lwk
 * 我是第三者 复制调用 和 set  
 * 没看懂为什么要这个类
 */
public class BridgeImpl extends Bridge{
	
		@Override
		public void doDrivermethod(){
			getDriver().diverMethod();
		}
}
