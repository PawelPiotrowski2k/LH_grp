package PACKAGE_NAME;
import Filemanager;
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
      public boolean fileExist(String path){}
      public void deleteFile(String path){}
      public void appendText(String path, String textToAppend){}
      public void appendTextToLine(String path, String textToAppend,int lineToAppendText){}
      public void deleteTextOnLine(String path, String textToDelete,int lineToAppendText){}
}
