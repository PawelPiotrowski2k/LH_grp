import AudioPlayer.AudioPlayer;
import AudioPlayer.AudioPlayerException;
import picocli.CommandLine;


@CommandLine.Command(version = "1.0.0", mixinStandardHelpOptions = true)
public class Picocli implements Runnable{
    @CommandLine.Option(names = {"-p", "--play"}, arity = "1", description = "play .wav sound. Insert wav file path")
    private String pathToFile;

    @Override
    public void run() {
        AudioPlayer audioPlayer = new AudioPlayer(pathToFile);
        try {
            audioPlayer.playSound();
        } catch (AudioPlayerException e) {
            throw new RuntimeException("Audio player has stopped for some reason");
        }
    }
}
