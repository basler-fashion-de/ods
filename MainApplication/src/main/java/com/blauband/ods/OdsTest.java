package com.blauband.ods;

import com.blauband.ods.converter.StatusKeeper;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang.CharEncoding;
import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.FopFactoryBuilder;
import org.apache.xmlgraphics.util.MimeConstants;

/**
 *
 * @author sergeizenevich
 */
public class OdsTest {

    private static final Logger LOGGER = Logger.getLogger(OdsTest.class.getName());

    private FopFactory fopFactory;
    private final String folder;

    public OdsTest() {
        this(Settings.FILES_FOLDER);
    }

    public OdsTest(final String folder) {
        this.folder = folder;
    }

    public static void main(final String[] args) throws Exception {
        final CatalogueDto dto = new CatalogueDto();
        dto.items = ItemDto.getDefault();
        new OdsTest("/tmp/").createUserInvoicePDF(dto);
    }

    public void createUserInvoicePDF(final CatalogueDto catalogueDto) {
        catalogueDto.init();
        final String invoiceSource = marshalUserInvoice(catalogueDto);
        StatusKeeper.INSTANCE.addLine("XML was created.");
        final StreamSource source = new StreamSource(new StringReader(invoiceSource));
        generatePDF(folder + StatusKeeper.INSTANCE.status.file, getInvoiceTemplate(), source);
    }

    private String marshalUserInvoice(final CatalogueDto userInvoice) {
        StringWriter sw = new StringWriter();
        try {
            JAXBContext context;
            context = JAXBContext.newInstance(CatalogueDto.class);
            Marshaller jaxbMarshaller = context.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, CharEncoding.UTF_8);
            jaxbMarshaller.marshal(userInvoice, sw);
            return sw.toString();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Problem with creation xml", e);
            StatusKeeper.INSTANCE.handleError(e);
            throw new RuntimeException(e);
        }
    }

    private String getInvoiceTemplate() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("xsl/catalogue-template.xml");
        return new Scanner(is, CharEncoding.UTF_8).useDelimiter("\\A").next();
    }

    private Transformer getTransformer(final StreamSource streamSource) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Templates template = factory.newTemplates(streamSource);
            return template.newTransformer();
        } catch (TransformerConfigurationException e) {
            LOGGER.log(Level.SEVERE, "Problem with creation transformer", e);
            throw new RuntimeException(e);
        }
    }

    private FopFactory getFopFactory() {
        if (this.fopFactory == null) {
            try {
                ClassLoader classloader = Thread.currentThread().getContextClassLoader();
                FopFactoryBuilder fopFactoryBuilder = new FopFactoryBuilder(classloader.getResource("xsl").toURI()).setStrictUserConfigValidation(false)
                        .setStrictFOValidation(false);
                FopFactory fopFactory = fopFactoryBuilder.build();
                fopFactory.validateStrictly();
                this.fopFactory = fopFactory;
            } catch (URISyntaxException e) {
                LOGGER.log(Level.SEVERE, "Problem with creation fop factory", e);
                throw new RuntimeException(e);
            }
        }
        return this.fopFactory;
    }

    private void generatePDF(final String fileName, final String xslt, final Source src) {
        StreamSource transformSource = new StreamSource(new StringReader(xslt));
        // a user agent is needed for transformation
        FOUserAgent foUserAgent = getFopFactory().newFOUserAgent();

        Transformer xslfoTransformer;
        FileOutputStream str = null;
        OutputStream outs = null;
        try {
            xslfoTransformer = getTransformer(transformSource);
            outs = new BufferedOutputStream(new FileOutputStream(fileName + ".pdf"));

            final Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outs);
            final Result res = new SAXResult(fop.getDefaultHandler());

            // Start XSLT transformation and FOP processing
            // everything will happen here..
            xslfoTransformer.transform(src, res);
        } catch (FOPException | TransformerException | IOException e) {
            LOGGER.log(Level.SEVERE, "Problem with creation pdf", e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (str != null) {
                    str.close();
                }

                if (outs != null) {
                    outs.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Problem with closing stream", e);
            }
        }
    }

}
