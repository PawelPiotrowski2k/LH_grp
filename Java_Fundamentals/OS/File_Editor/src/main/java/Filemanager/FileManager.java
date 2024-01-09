package Filemanager;

import FileManagerExeption.FileManagerExeption;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileManager {
    private final File file;

    public FileManager(File file) throws FileManagerExeption {
        if(isTxtOrMdFile(file)){
            this.file = file;
        }else {
            throw new FileManagerExeption("There was a problem with File");
        }
    }

    public boolean fileExist(){
        return file.exists();
    }

    public boolean deleteFile() {
            return file.delete();
    }

    public void appendText(String textToAppend) throws FileManagerExeption {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.newLine();
            bufferedWriter.append(textToAppend);
        } catch (FileNotFoundException e) {
            throw new FileManagerExeption("File not found");
        } catch (IOException e) {
            throw  new FileManagerExeption("There was a problem with File");
        }
    }
    //RandomAccessFile
    //FileUtils/FilesUtil -> appache io
    //Files
    public void appendTextToLine(String textToAppend, int lineToAppendText) throws FileManagerExeption {
        if (lineToAppendText > countLinesInFile()) {
            return;
        }
        try (FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader)){
            File tempFile = File.createTempFile("tempfile", ".tmp");
            BufferedWriter writeTemp = new BufferedWriter(new FileWriter(tempFile));
            String line = "";
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine - lineToAppendText == -1) {
                    writeTemp.write(textToAppend);
                    writeTemp.newLine();
                } else {
                    writeTemp.write(line);
                    writeTemp.newLine();
                }
                currentLine++;
            }
            writeTemp.close();
            bufferedReader.close();
            Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            throw new FileManagerExeption("file not found");
        } catch (IOException e) {
            throw new FileManagerExeption("There was a problem with file");
        }
    }

    public void deleteTextOnLine(int lineToDelete, String textToDelete) throws FileManagerExeption {
        if (lineToDelete > countLinesInFile()) {
            return;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            File tempFile = File.createTempFile("tempfile", ".tmp");
            BufferedWriter writeTemp = new BufferedWriter(new FileWriter(tempFile));
            String line = "";
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine - lineToDelete == -1) {
                    writeTemp.write("");
                } else {
                    writeTemp.write(line.replaceAll(textToDelete,""));
                    writeTemp.newLine();
                }
                currentLine++;
            }
            writeTemp.close();
            bufferedReader.close();
            Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new FileManagerExeption("file not found");
        }
    }

    public void deleteTextInFile(String textToDelete) throws FileManagerExeption {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))
        ) {
            StringBuilder content = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(textToDelete)) {
                    content.append(deletePhraseFromLine(textToDelete, line)).append(System.lineSeparator());
                } else {
                    content.append(line).append(System.lineSeparator());
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content.toString());
            bufferedWriter.close();
        } catch (FileNotFoundException e){
            throw new FileManagerExeption("file not found");
        } catch(IOException e) {
            throw new FileManagerExeption("There was a problem with file");
        }
    }

    private boolean isTxtOrMdFile(File file) {
        return file.getPath().endsWith(".txt") || file.getPath().endsWith(".md");
    }

    private int countLinesInFile() throws FileManagerExeption {
        try (Stream<String> fileStream = Files.lines(Paths.get(file.getPath()))) {
            return (int) fileStream.count();
        } catch (FileNotFoundException e) {
            throw new FileManagerExeption("File not found");
        }catch (IOException e){
            throw new FileManagerExeption("There was a problem with file");
        }
    }

    private StringBuilder deletePhraseFromLine(String phrase, String line) {
        StringBuilder content = new StringBuilder();
        if (line.contains(phrase)) {
            content.append(line.replace(phrase, ""));
        } else {
            content.append(line);
        }
        return content;
    }
}
