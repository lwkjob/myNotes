package pattern.visitor;
public class Test {  
  
    /**
     * @param args
     * 访问者
     */
    public static void main(String[] args) {  
          
        Visitor visitor = new MyVisitor();  
        Subject sub = new MySubject();  
        sub.accept(visitor);      
    }  
}  