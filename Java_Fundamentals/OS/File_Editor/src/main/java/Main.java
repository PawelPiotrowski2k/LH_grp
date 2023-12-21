package PACKAGE_NAME;
import PACKAGE_NAME.Filemanager.FileManager;

import java.io.IOException;

/*
Create a library that allows to manage files
Features to implement:
Application should support .txt and .md files
Checking if a file even exists
Deleting a file
Modifying a file:
Text to append or delete
Appending text to the end of a file or specified line
Deleting text from whole file or specified line
Write tests for your implementation
 */
public class Main {
      FileManager fileManager;
      public Main(FileManager fileManager) {
            this.fileManager = fileManager;
      }

      public boolean fileExist(String path){
            return fileManager.fileExist();
      }
      public void deleteFile(String path){
            fileManager.deleteFile();
      }
      public void appendText(String textToAppend){
            fileManager.appendText(textToAppend);
      }
      public void appendTextToLine(String textToAppend,int lineToAppendText){
            fileManager.appendTextToLine(textToAppend,lineToAppendText);
      }
      public void deleteTextOnLine(int lineToAppendText){
            fileManager.deleteTextOnLine(lineToAppendText);
      }
}
