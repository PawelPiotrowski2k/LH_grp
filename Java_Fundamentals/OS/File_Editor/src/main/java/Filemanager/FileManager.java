package PACKAGE_NAME.Filemanager;

import java.io.*;

public class FileManager {
    private final File file;

    public FileManager(File file) {
        try {
            this.file = file;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean fileExist(){
        try {
            return file.exists();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public boolean deleteFile(){
        return file.delete();
    }
    public void appendText(String textToAppend) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(textToAppend);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void appendTextToLine(String textToAppend, int lineToAppendText){
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null){
                if(currentLine == lineToAppendText){
                    bufferedWriter.write(textToAppend);
                    bufferedWriter.newLine();
                }else {
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                currentLine++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteTextOnLine(int lineToDelete){
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null){
                if(currentLine != lineToDelete){
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                }
                currentLine++;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
