import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        new CommandLine(new Picocli()).execute("-a", "C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\test.pdf","C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.xml","C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.xsl");
        new CommandLine(new Picocli()).execute("-b", "C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\test1.pdf","C:\\LHgrp\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.JSON");
    }

}
