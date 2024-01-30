package org.example;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.file.Path;

public class PdfCreator {
    private final Path pathToSavePdfFile;
    private Path pathToXMLSchema;
    private Path pathToXSLFile;

    public PdfCreator(Path pathToSavePdfFile, Path pathToXMLSchema, Path pathToXSLFile) {
        this.pathToSavePdfFile = pathToSavePdfFile;
        this.pathToXMLSchema = pathToXMLSchema;
        this.pathToXSLFile = pathToXSLFile;
    }

    public void createPdfFromXMLSchema() {
        try {
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(String.valueOf(pathToXSLFile))));
            File pdfFile = new File(String.valueOf(pathToSavePdfFile));
            OutputStream out = new FileOutputStream(pdfFile);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            Result result = new SAXResult(fop.getDefaultHandler());
            Source source = new StreamSource(new File(String.valueOf(pathToXMLSchema)));
            transformer.transform(source, result);
            out.close();
            System.out.println("Plik PDF utworzony pomyślnie.");
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (FOPException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
