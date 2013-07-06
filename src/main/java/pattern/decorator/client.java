package pattern.decorator;

/**
 * @author lwk
 * 装饰模式 感觉有点象代理模式
 */
public class client {
    public static void main(String[] args) {
    	DecoratorSourceable source = new DecoratorSource();  
    	DecoratorSourceable obj = new Decorator(source);  
        obj.method();  
    }
}
