import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        new CommandLine(new Picocli()).execute("-p", "C:\\Users\\admin\\IdeaProjects\\AudioPlayer.AudioPlayer\\Audio.wav");
    }
}
