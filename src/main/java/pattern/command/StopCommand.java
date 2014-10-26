package pattern.command;


/**
 * 具体命令(ConcreteCommand)角色
 * @author lwkjob
 *
 */
public class StopCommand implements Command {
    private AudioPlayer myAudio;
    
    public StopCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.stop();
    }

}