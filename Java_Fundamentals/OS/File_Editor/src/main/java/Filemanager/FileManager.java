package Filemanager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileManager {
    private final File file;

    public FileManager(File file) {
        this.file = file;
    }

    public boolean fileExist(){
        try {
            return file.exists();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public boolean deleteFile(){
        if(isTxtOrMdFile()){
            return file.delete();
        }
        return false;
    }
    public void appendText(String textToAppend) {
        if(!isTxtOrMdFile()) {
            return;
        }
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
                bufferedWriter.newLine();
                bufferedWriter.append(textToAppend);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
    public void appendTextToLine(String textToAppend, int lineToAppendText)  {
        if(lineToAppendText > countLinesInFile()){
            return;
        }
        try (
             FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
                ){
            StringBuilder content = new StringBuilder();
            String line = "";
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null){
                if(currentLine - lineToAppendText == -1){
                    content.append(textToAppend).append(System.lineSeparator());
                    content.append(line).append(System.lineSeparator());
                }else{
                    content.append(line).append(System.lineSeparator());
                }
                currentLine++;
            }
            bufferedWriter.write(content.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //usuwanie konretnego tekstu z wszystkich lini koniecznie o wyjątkach
    public void deleteTextOnLine(int lineToDelete, String textToDelete){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line = "";
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null){
                if(currentLine - lineToDelete == -1){
                    content.append("").append(System.lineSeparator());
                }else {
                    content.append(line).append(System.lineSeparator());
                }
                currentLine++;
            }
            bufferedReader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content.toString());
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteTextInFile(String textToDelete){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))
        ){
            StringBuilder content = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
              deletePhraseFromLine(textToDelete, line);
            }
            bufferedWriter.write(content.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean isTxtOrMdFile(){
        return (file.getPath().endsWith(".txt") || file.getPath().endsWith(".md"));
    }
    private int countLinesInFile() {
        int noOfLines = -1;
        try (Stream<String> fileStream = Files.lines(Paths.get(file.getPath()))) {
            return noOfLines = (int) fileStream.count();
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
    private StringBuilder deletePhraseFromLine(String phrase, String line){
        StringBuilder content = new StringBuilder();
        if (line.contains(phrase)) {
            content.append(line.replace(phrase,""));
        }else {
            content.append(line);
        }
        return content;
    }
}
