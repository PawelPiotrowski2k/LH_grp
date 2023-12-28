import Filemanager.FileManager;

import java.io.File;
import java.io.FileNotFoundException;

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

      public boolean fileExist(String path) throws FileNotFoundException {
            return fileManager.fileExist();
      }
      public void deleteFile(String path){
            fileManager.deleteFile();
      }
      public void appendText(String textToAppend) throws FileNotFoundException{
            fileManager.appendText(textToAppend);
      }
      public void appendTextToLine(String textToAppend,int lineToAppendText) throws FileNotFoundException{
            fileManager.appendTextToLine(textToAppend,lineToAppendText);
      }
      public void deleteTextOnLine(int lineToAppendText, String textToAppend){
            fileManager.deleteTextOnLine(lineToAppendText, textToAppend);
      }
}
