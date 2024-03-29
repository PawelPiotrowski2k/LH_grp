import PdfCreatorFromXML.PdfCreator;
import PdfCreatorFromXML.PdfCreatorException;
import picocli.CommandLine;

import java.nio.file.Paths;
import java.util.List;

@CommandLine.Command(version = "1.0.0", mixinStandardHelpOptions = true)
public class Picocli implements Runnable{
    @CommandLine.Option(names = {"-a", "--optionA"}, arity = "3", description = "create pdf from xml. Path to save pdf file/ Path to xml file/ Path to xsl file")
    private List<String> optionA;

    @CommandLine.Option(names = {"-b", "--optionB"}, arity = "2", description = "create pdf from JSON. Path to save pdf file/ Path to JSON file")
    private List<String> optionB;

    @Override
    public void run() {
        if (optionA != null){
            PdfCreator pdfCreator = new PdfCreator(Paths.get(optionA.get(0)),Paths.get(optionA.get(1)),Paths.get(optionA.get(2)));
            try {
                pdfCreator.createPdfFromXMLSchema();
            } catch (PdfCreatorException e) {
                throw new RuntimeException(e);
            }
        }
//        else if (optionB != null){
//            PdfFromJSON pdfFromJSON = new PdfFromJSON(optionB.get(0),optionB.get(1));
//            try {
//                pdfFromJSON.createPDF();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
}
