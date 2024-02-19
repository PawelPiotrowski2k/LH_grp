package PdfCreatorFromXML;

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
    private final Path pathToXMLSchema;
    private final Path pathToXSLFile;

    public PdfCreator(Path pathToSavePdfFile, Path pathToXMLSchema, Path pathToXSLFile) {
        this.pathToSavePdfFile = pathToSavePdfFile;
        this.pathToXMLSchema = pathToXMLSchema;
        this.pathToXSLFile = pathToXSLFile;
    }

    public void createPdfFromXMLSchema() throws PdfCreatorException {
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
            System.out.println("pdf File Created Succesfully");
        } catch (TransformerConfigurationException e) {
            throw new PdfCreatorException("transformer cnfiguration exception");
        } catch (FileNotFoundException e) {
            throw new PdfCreatorException("File not found");
        } catch (FOPException e) {
            throw new PdfCreatorException("Acces denied");
        } catch (TransformerException e) {
            throw new PdfCreatorException("transformer exception");
        } catch (IOException e) {
            throw new PdfCreatorException("IO Exception there is something wrong with file");
        }
    }
}
