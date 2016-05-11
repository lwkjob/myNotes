package pattern.command;
/**
 * 
 * 真正的命令执行着，接收者(Receiver)角色
 * @author lwkjob
 *
 */
public class AudioPlayer {
    
    public void play(){
        System.out.println("播放...");
    }
    
    public void rewind(){
        System.out.println("倒带...");
    }
    
    public void stop(){
        System.out.println("停止...");
    }
}