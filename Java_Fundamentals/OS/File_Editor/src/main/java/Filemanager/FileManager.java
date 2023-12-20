package PACKAGE_NAME.Filemanager;

import java.io.File;

public class FileManager {
    private final File file;

    public FileManager(File file) {
        this.file = file;
    }

    public boolean fileExist(){
        return file.exists();
    }
    public boolean deleteFile(){
        return file.delete();
    }
    public void appendText(){

    }
    public void appendTextToLine(){}
    public void deleteTextOnLine(){}
}
