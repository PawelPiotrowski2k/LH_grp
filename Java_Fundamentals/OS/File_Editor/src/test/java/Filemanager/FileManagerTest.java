package Filemanager;

import FileManagerExeption.FileManagerExeption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {
    FileManager fileManager;
    File tempFile;

    @BeforeEach
    void setup(@TempDir Path tempDir) throws IOException, FileManagerExeption {
        tempFile = new File(tempDir.toFile(), "testFile.txt");
        tempFile.createNewFile();
        fillFileWithText(tempFile);
        fileManager = new FileManager(tempFile);

    }

    void fillFileWithText(File file) throws FileNotFoundException {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            for (int i = 0; i < 9; i++) {
                fileWriter.write("text");
                fileWriter.append(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void fileExist() {
        assertTrue(fileManager.fileExist());
    }

    @Test
    void appendText() throws FileManagerExeption, IOException {
        fileManager.appendText("text");
        fileManager.deleteTextOnLine(3, "xt");
        List<String> actualLines = Files.readAllLines(tempFile.toPath());
        assertEquals("te", actualLines.get(2));
    }

    @Test
    void appendTextToLine() throws FileManagerExeption, IOException {
        fileManager.appendTextToLine("string", 3);
        List<String> actualLines = Files.readAllLines(tempFile.toPath());
        assertEquals("string", actualLines.get(2));
    }

    @Test
    void deleteTextOnLine() throws FileManagerExeption, IOException {
        fileManager.deleteTextOnLine(3, "xt");
        List<String> actualLines = Files.readAllLines(tempFile.toPath());
        assertEquals("te", actualLines.get(2));
    }

    @Test
    void deleteTextFromFile() throws FileManagerExeption {
        fileManager.deleteTextInFile("xt");
    }

    @Test
    void deleteFile() {
        assertTrue(fileManager.deleteFile());
    }
}