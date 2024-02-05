import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        //new CommandLine(new Picocli()).execute("-a", "C:\\LHgrp\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\test.pdf","C:\\LHgrp\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.xml","C:\\LHgrp\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.xsl");
       new CommandLine(new Picocli()).execute("-b", "C:\\LHgrp\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\dzialajjson.pdf","C:\\LHgrp\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.JSON");
    }

}
