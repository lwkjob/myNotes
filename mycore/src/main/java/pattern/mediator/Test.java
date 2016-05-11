package pattern.mediator;
/**
 * 
 * 调停者模式
 * @author lwkjob
 *
 */
public class Test {  
  
    public static void main(String[] args) {  
        Mediator mediator = new MyMediator();  
        mediator.createMediator();  
        mediator.workAll();  
    }  
}  