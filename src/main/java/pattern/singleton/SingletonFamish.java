/**
 *	@author lwk
 *	@2013-7-5上午10:47:32
 *	@SingletonFamish.java
 */
package pattern.singleton;

/**
 * @author lwk 饿汉式单例
 */
public class SingletonFamish {
    private static SingletonFamish singletonFamish = null;

    private SingletonFamish() {
    };

    public synchronized SingletonFamish getInstences() {
        if (singletonFamish == null) {
            return new SingletonFamish();
        }
        return singletonFamish;
    }
}
