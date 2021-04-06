package com.epam.glasshouse.parser;

import com.epam.glasshouse.entity.Plant;
import com.epam.glasshouse.exception.GlasshouseException;
import com.epam.glasshouse.handler.PlantErrorHandler;
import com.epam.glasshouse.handler.PlantSaxHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.HashSet;

public class PlantSaxBuilder extends AbstractPlantBuilder {

    private final static Logger logger = LogManager.getLogger(PlantSaxBuilder.class);

    private PlantSaxHandler handler = new PlantSaxHandler();
    private PlantErrorHandler errorHandler = new PlantErrorHandler();
    private XMLReader reader;

    public PlantSaxBuilder() {
        super();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Error in PlantSaxBuilder: " + e.getMessage());
        }
        reader.setContentHandler(handler);
        reader.setErrorHandler(errorHandler);
    }

    @Override
    public HashSet<Plant> getPlants() {
        return (HashSet<Plant>) plants.clone();
    }

    @Override
    public void buildSetPlants(String path) throws GlasshouseException {
        try {
            reader.parse(path);
        } catch (SAXException e) {
            logger.error("Error in sax buildSetPlants:" + e.getMessage());
            throw new GlasshouseException("Error in sax buildSetPlants", e);
        } catch (IOException e) {
            logger.error("Problems with path: " + path + " in sax buildSetPlants");
            throw new GlasshouseException("Problems with path");
        }
        plants = handler.getPlants();
        logger.info("Plants in buildSetPlants method from sax builder are:\n" + plants);
    }
}
