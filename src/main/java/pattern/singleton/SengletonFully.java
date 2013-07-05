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
public class SengletonFully {

    private static SengletonFully singleton = new SengletonFully();

    private SengletonFully() {
    };

    public static SengletonFully getSingleton() {
        return singleton;
    }
}
