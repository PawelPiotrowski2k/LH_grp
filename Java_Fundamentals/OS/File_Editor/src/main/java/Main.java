import FileManagerExeption.FileManagerExeption;
import Filemanager.FileManager;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
      FileManager fileManager;
      public Main(FileManager fileManager) {
            this.fileManager = fileManager;
      }

      public boolean fileExist(String path) {
            return fileManager.fileExist();
      }
      public void deleteFile(String path){
            fileManager.deleteFile();
      }
      public void appendText(String textToAppend) throws FileManagerExeption {
            fileManager.appendText(textToAppend);
      }
      public void appendTextToLine(String textToAppend,int lineToAppendText) throws FileManagerExeption{
            fileManager.appendTextToLine(textToAppend,lineToAppendText);
      }
      public void deleteTextOnLine(int lineToAppendText, String textToAppend) throws FileManagerExeption {
            fileManager.deleteTextOnLine(lineToAppendText, textToAppend);
      }
}
