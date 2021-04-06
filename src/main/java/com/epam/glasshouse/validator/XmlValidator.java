package com.epam.glasshouse.validator;

import com.epam.glasshouse.parser.PlantStaxBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class XmlValidator {

    private final static Logger logger = LogManager.getLogger(PlantStaxBuilder.class);

    public static boolean validateGlasshouseXmlFile(String xmlFilePath) {
        final String SCHEMA_NAME = "file/glasshouse.xsd";
        ClassLoader loader = XmlValidator.class.getClassLoader();
        URL resource = loader.getResource(SCHEMA_NAME);
        String schemaPath = new File(resource.getFile()).getAbsolutePath();

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaFile = new File(schemaPath);
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFilePath);
            validator.validate(source);
        } catch (SAXException exception) {
            logger.error("File " + xmlFilePath + " is not valid because " + exception.getMessage());
            return false;
        } catch (IOException exception) {
            logger.error("File " + xmlFilePath + " is not found");
        }
        return true;
    }
}
