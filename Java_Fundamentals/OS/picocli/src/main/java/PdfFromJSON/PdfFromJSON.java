package PdfFromJSON;

import com.aspose.cells.Workbook;

import java.io.File;


public class PdfFromJSON {
    private final String output;
    private final String input;

    public PdfFromJSON(String output, String input) {
        this.output = output;
        this.input = input;
    }

    public void createPDF() throws Exception {
        File file = new File(output);
        file.createNewFile();
        Workbook workbook = new Workbook(output);
        workbook.save(input);
        System.out.println("pdf file created succesfully");
    }
}
