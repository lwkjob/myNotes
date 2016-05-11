package pattern.bridge;

/**
 * @author lwk
 * 我是第三者 负责调用 和 set  
 * 客户直接调用我， 因为父类Bridge不能直接实例化所以他还需要一个继承者就是我
 */
public class BridgeImpl extends Bridge{
	
		@Override
		public void doDrivermethod(){
			getDriver().diverMethod();
		}
}
