package pattern.command;


/**
 * 具体命令(ConcreteCommand)角色
 * @author lwkjob
 *
 */
public class RewindCommand implements Command {

    private AudioPlayer myAudio;
    
    public RewindCommand(AudioPlayer audioPlayer){
        myAudio = audioPlayer;
    }
    @Override
    public void execute() {
        myAudio.rewind();
    }

}