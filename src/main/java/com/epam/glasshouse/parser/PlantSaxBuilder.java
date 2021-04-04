package com.epam.glasshouse.parser;

import com.epam.glasshouse.entity.Plant;
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

public class PlantSaxBuilder {

    private final static Logger logger = LogManager.getLogger(PlantSaxBuilder.class);

    private HashSet<Plant> plants;
    private PlantSaxHandler handler = new PlantSaxHandler();
    private XMLReader reader;

    public PlantSaxBuilder() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Error in PlantSaxBuilder");
        }
        reader.setContentHandler(handler);
    }
    public HashSet<Plant> getPlants() {
        return (HashSet<Plant>) plants.clone();
    }

    public void buildSetPlants(String path) {
        try {
            reader.parse(path);
        } catch (IOException | SAXException e) {
            logger.error("Error in buildSetPlants");
        }
        plants = handler.getPlants();
    }
}
