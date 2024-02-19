package PdfFromJSON;


import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.json.JSONObject;
import org.json.XML;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


public class PdfFromJSON {
    private final static String pathToXMLSchema = "C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.xsl";
    private final String outputPath;
    private final static String xmlFilePath = "C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\xmlSchemaFromJSON.xml";
    private final String jsonString;

    public PdfFromJSON(String outputPath, String inputPath) throws PdfFromJSONException {
        this.outputPath = outputPath;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(inputPath)), StandardCharsets.UTF_8);
            JSONObject jsonObject = new JSONObject(jsonString);
            File file = new File(xmlFilePath);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(XML.toString(jsonObject));
            fileWriter.close();
        } catch (IOException e) {
            throw new PdfFromJSONException("couldn't open file " + inputPath + e);
        }
    }

    public static void main(String[] args) throws Exception {
        PdfFromJSON pdfFromJSON = new PdfFromJSON("C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\test1.pdf","C:\\Nowy folder\\LH_grp\\Java_Fundamentals\\OS\\picocli\\src\\main\\resources\\schema.JSON");
        pdfFromJSON.init();
    }
    public void init() throws Exception {
        createPDF();
    }

    private void createPDF() throws PdfFromJSONException {
        try {
            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(pathToXMLSchema)));
            File pdfFile = new File(String.valueOf(outputPath));
            OutputStream out = new FileOutputStream(pdfFile);
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            Result result = new SAXResult(fop.getDefaultHandler());
            Source source = new StreamSource(new File((xmlFilePath)));
            transformer.transform(source, result);
            out.close();
            System.out.println("pdf File Created Succesfully");
        } catch (TransformerFactoryConfigurationError e) {
            throw new PdfFromJSONException("transform configuration error " + e);
        } catch (FOPException e) {
            throw new PdfFromJSONException("FOP exception " + e);
        } catch (TransformerException e) {
            throw new PdfFromJSONException("Transformer exception"  + e);
        } catch (IOException e) {
            throw new PdfFromJSONException("couldnt open file " + e);
        }
    }
    }


