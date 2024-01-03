package Filemanager;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

public class FileManager {
    private final File file;

    public FileManager(File file) {
        this.file = file;
    }

    public boolean fileExist() throws FileNotFoundException {
        return file.exists();
    }

    public boolean deleteFile() {
        if (isTxtOrMdFile()) {
            return file.delete();
        }
        return false;
    }

    public void appendText(String textToAppend) throws FileNotFoundException {
        if (!isTxtOrMdFile()) {
            return;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.newLine();
            bufferedWriter.append(textToAppend);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendTextToLine(String textToAppend, int lineToAppendText) throws FileNotFoundException {
        if (lineToAppendText > countLinesInFile() && !isTxtOrMdFile()) {
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
            Files.move(tempFile.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteTextOnLine(int lineToDelete, String textToDelete) {
        if (lineToDelete > countLinesInFile() && !isTxtOrMdFile()) {
            return;
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){

            StringBuilder content = new StringBuilder();
            String line = "";
            int currentLine = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (currentLine - lineToDelete == -1) {
                    content.append(line.replace(textToDelete, "")).append(System.lineSeparator());
                } else {
                    content.append(line).append(System.lineSeparator());
                }
                currentLine++;
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTextInFile(String textToDelete) {
        if (!isTxtOrMdFile()) {
            return;
        }
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isTxtOrMdFile() {
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
