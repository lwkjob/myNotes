package pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 请求者(Invoker)角色
 * 命令管理和请求者实现类，实现命令的调用和增删
 * @author lwkjob
 *
 */
public class MacroAudioCommand implements MacroCommand {
    
    private List<Command> commandList = new ArrayList<Command>();
    /**
     * 宏命令聚集管理方法
     */
    @Override
    public void add(Command cmd) {
        commandList.add(cmd);
    }
    /**
     * 宏命令聚集管理方法
     */
    @Override
    public void remove(Command cmd) {
        commandList.remove(cmd);
    }
    /**
     * 执行方法
     */
    @Override
    public void execute() {
        for(Command cmd : commandList){
            cmd.execute();
        }
    }

}