package pattern.command;

/**
 * @author lwk
 * 客户点菜(client)服务员(invoker)拿着菜单(command)去找厨师(receiver)-命令模式.....
 */
public class Client {

    public static void main(String[] args) {
        //创建接收者
        Receiver receiver = new Receiver();//厨师
        //创建命令对象，设定它的接收者
        Command command = new ConcreteCommand(receiver);//菜单
        //创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker(command);//服务员
        //执行方法
        invoker.action();
    }

}