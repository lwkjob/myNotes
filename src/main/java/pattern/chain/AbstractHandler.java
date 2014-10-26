package pattern.chain;
/**
 * 
 * 责任链模式，比如：java的过滤器
 * 客户端可以搞多个filte分别完成不在一条链子上的不同的事，然后交给下一个
 * @author lwkjob
 *
 */
public abstract class AbstractHandler {  
      
    private Handler handler;  
  
    public Handler getHandler() {  
        return handler;  
    }  
  
    public void setHandler(Handler handler) {  
        this.handler = handler;  
    }  
      
}  