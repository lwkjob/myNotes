package pattern.singleton;

import java.util.Vector;

/**
 * @author lwk 影子实例 更新单例属性
 */
public class SingletonShadow {
	
	private static SingletonShadow single = null;
	private Vector properties = null;

	private SingletonShadow() {
	};

	public Vector getProperties() {
		return properties;
	}
	private static synchronized SingletonShadow getInstance(){
		if(single==null){
			return new SingletonShadow();
		}
		return single;
	}
	public void updateProperties(){
		SingletonShadow shadow=new SingletonShadow();
		properties=shadow.getProperties();
	}
	
}
