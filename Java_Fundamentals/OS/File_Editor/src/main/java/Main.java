package PACKAGE_NAME;
import PACKAGE_NAME.Filemanager.FileManager;

import java.io.IOException;
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
