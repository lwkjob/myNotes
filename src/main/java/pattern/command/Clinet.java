package pattern.command;


/**
 * @author lwk
 * 没搞懂这模式有何用处，感觉是脱了裤子放屁
 * 客户点菜(client)服务员(invoker)拿着菜单(command)去找厨师(receiver)-命令模式.....
 */
public class Clinet {
    
    public static void main(String[]args){
        //创建接收者对象
        AudioPlayer audioPlayer = new AudioPlayer();
        //创建命令对象
        Command playCommand = new PlayCommand(audioPlayer);
        Command rewindCommand = new RewindCommand(audioPlayer);
        Command stopCommand = new StopCommand(audioPlayer);
        //创建调用者
        MacroCommand marco = new MacroAudioCommand();
        
        marco.add(playCommand);
        marco.add(rewindCommand);
        marco.add(stopCommand);
        marco.remove(stopCommand);
        marco.execute();
    }
}