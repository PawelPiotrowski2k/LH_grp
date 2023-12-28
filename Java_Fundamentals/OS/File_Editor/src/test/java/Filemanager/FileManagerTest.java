package Filemanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
class FileManagerTest {
    FileManager fileManager;
    File file;

    @BeforeEach
    void setup() throws IOException {
        file = new File("file.txt");
        file.createNewFile();
        fileManager = new FileManager(file);
    }

    @Test
    void fileExist() throws FileNotFoundException{
        assertTrue(fileManager.fileExist());
    }

    @Test
    void deleteFile() {
        assertTrue(fileManager.deleteFile());
    }

    @Test
    void appendText() throws FileNotFoundException{
        //@TempDir
        fileManager.appendText("text");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
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
        fileManager.deleteTextOnLine(3,"ing");
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = "";
            int currentline = 1;
            while ((line = bufferedReader.readLine()) != null){
                if(currentline == 3){
                    break;
                }
                currentline++;
            }
            assertTrue(line.equals("str"));
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
}