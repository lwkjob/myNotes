/**
 *	@author lwk
 *	@2013-7-5上午10:41:52
 *	@Singleton.java
 */
package pattern.singleton;

/**
 * @author lwk
 * 饱汉式单例
 */
public class SingletonFully {

    private static SingletonFully singleton = new SingletonFully();

    private SingletonFully() {
    };

    public static SingletonFully getSingleton() {
        return singleton;
    }
}
