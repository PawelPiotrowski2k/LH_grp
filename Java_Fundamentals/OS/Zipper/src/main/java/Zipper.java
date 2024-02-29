import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {
    private final File fileToZip;
    private final String pathToSaveZippedFile;

    public Zipper(String pathToFileToZip, String pathToSaveZippedFile) {
        this.fileToZip = new File(pathToFileToZip);
        this.pathToSaveZippedFile = pathToSaveZippedFile;
    }

    public void init() throws IOException {
        FileOutputStream fos = new FileOutputStream(pathToSaveZippedFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        zipFile(fileToZip,fileToZip.getName(),zipOut);
        zipOut.close();
    }

    private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
        if (fileToZip.isHidden()) {
            return;
        }
        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new ZipEntry(fileName));
                zipOut.closeEntry();
            } else {
                zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                zipOut.closeEntry();
            }
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        fis.close();
    }


    private String getFileName(){
        String[] pathArr = pathToSaveZippedFile.split("/");
        return pathArr[pathArr.length - 1];
    }

}

