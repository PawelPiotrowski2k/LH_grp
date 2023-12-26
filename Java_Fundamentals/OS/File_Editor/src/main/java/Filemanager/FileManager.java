package Filemanager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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
        if(file.isFile()){
            return file.delete();
        }
        return false;
    }
    //walicja jaki plik został dostarczony
    //więcej walidacji
    public void appendText(String textToAppend) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.newLine();
            bufferedWriter.append(textToAppend);
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //walicja na linijke
    public void appendTextToLine(String textToAppend, int lineToAppendText){
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
    //usuwanie konretnego tekstu z wszystkich lini
    public void deleteTextOnLine(int lineToDelete){
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
//            Files
            bufferedReader.close();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content.toString());
            bufferedWriter.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
