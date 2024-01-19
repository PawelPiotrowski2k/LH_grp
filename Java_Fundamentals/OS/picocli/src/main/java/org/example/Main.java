package org.example;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

public class Main {
    public static void main(String[] args) {
        //new CommandLine(new Picocli()).execute("-a", "C:\\Users\\admin\\IdeaProjects\\picocli\\src\\main\\resources\\test.pdf","C:\\Users\\admin\\IdeaProjects\\picocli\\src\\main\\resources\\schema.xml","C:\\Users\\admin\\IdeaProjects\\picocli\\src\\main\\resources\\schema.xsl");
        new CommandLine(new Picocli()).execute("-a", "C:\\Users\\admin\\IdeaProjects\\picocli\\src\\main\\resources\\test.pdf","C:\\Users\\admin\\IdeaProjects\\picocli\\src\\main\\resources\\schema.JSON");
    }

}
