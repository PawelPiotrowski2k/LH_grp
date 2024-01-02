package Filemanager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
class FileManagerTest {
    FileManager fileManager;
    File tempFile;

    @BeforeEach
    void setup(@TempDir Path tempDir) throws IOException {
        tempFile = new File(tempDir.toFile(),"testFile.txt");
        tempFile.createNewFile();
        fillFileWithText(tempFile);
//        File tempFile = File.createTempFile("tmp",".txt");
//        fillFileWithText(tempFile);
        fileManager = new FileManager(tempFile);
    }
    void fillFileWithText(File file) throws FileNotFoundException {
        try {
            FileWriter fileWriter = new FileWriter(file,true);
            for (int i = 0; i < 9; i++) {
                fileWriter.write("text");
                fileWriter.append(System.lineSeparator());
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fileExist() throws FileNotFoundException{
        assertTrue(fileManager.fileExist());
    }



    @Test
    void appendText() throws IOException {
        //@TempDir

        fileManager.appendText("text");
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(tempFile.toPath(), StandardCharsets.UTF_8);
            String lastLine = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                lastLine = line;
            }
            assertTrue(lastLine.equals("text"));
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void appendTextToLine() throws FileNotFoundException {
        fileManager.appendTextToLine("string",3);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(tempFile));
            String line = "";
            int currentline = 1;
            while ((line = bufferedReader.readLine()) != null){
                if(currentline == 3){
                    break;
                }
                currentline++;
            }
            assertTrue(line.equals("string"));
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteTextOnLine() {
        fileManager.deleteTextOnLine(3,"xt");
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(tempFile.toPath(), StandardCharsets.UTF_8);
            String line = "";
            int currentline = 1;
            while ((line = bufferedReader.readLine()) != null){
                if(currentline == 3){
                    break;
                }
                currentline++;
            }
            assertTrue(line.equals("te"));
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void deleteTextFromFile(){
        fileManager.deleteTextInFile("xt");
    }
    @Test
    void deleteFile() {
        assertTrue(fileManager.deleteFile());
    }
}