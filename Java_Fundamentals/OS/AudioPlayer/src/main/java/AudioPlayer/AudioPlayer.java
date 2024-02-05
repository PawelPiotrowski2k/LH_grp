package AudioPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private final String filePath;

    public AudioPlayer(String filePath) {
        this.filePath = filePath;
    }
    public void playSound() throws AudioPlayerException {
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
                throw new AudioPlayerException("File should be .wav file");
            } catch (IOException e) {
                throw new AudioPlayerException("There was a trouble to open file");
            } catch (LineUnavailableException e) {
                throw new AudioPlayerException("Line Unavailable Exception");
            } catch (InterruptedException e) {
                throw new AudioPlayerException("Sound was interupted for some reason");
            }
        }else {
            System.out.println("Cant find File");
        }
    }
}
