package pattern.command;
/**
 * 
 * 命令(Command)角色：声明了一个给所有具体命令类的抽象接口。
 * @author lwkjob
 *
 */
public interface Command {
    /**
     * 执行方法
     */
    public void execute();
}