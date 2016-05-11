package pattern.proxy;

/**
 * @author lwk
 * 代理类 内部组合进了目标对象
 */
public class client {
    public static void main(String[] args) {
    	Target target = new Proxy();  
    	target.targetMethod();  
    }
}
