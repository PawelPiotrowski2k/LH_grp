import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class AudioPlayer {
    private final String filePath;

    public AudioPlayer(String filePath) {
        this.filePath = filePath;
    }
    public void playSound(){
        File musicFile = new File(filePath);
        if(musicFile.exists()){
            try {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                AudioFormat audioFormat = audioInput.getFormat();
                long audioFileLength = musicFile.length();
                int frameSize = audioFormat.getFrameSize();
                float frameRate = audioFormat.getFrameRate();
                float durationInSeconds = (audioFileLength / (frameSize * frameRate));
                long durationInMS = (long) (durationInSeconds * 1000);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                Thread.sleep(durationInMS);
                clip.close();
            } catch (UnsupportedAudioFileException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else {
            System.out.println("Cant find File");
        }
    }
}
