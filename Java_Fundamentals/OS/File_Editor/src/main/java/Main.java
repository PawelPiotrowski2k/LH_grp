package PACKAGE_NAME;
import Filemanager.FileManager;

import java.io.File;

public class Main {
//      public static void main(String[] args) {
//            File file = new File("file.txt");
//            FileManager fileManager1 = new FileManager(file);
//            System.out.println(fileManager1.countLinesInFile());
//      }
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
