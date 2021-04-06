package com.epam.glasshouse.parser;

import com.epam.glasshouse.entity.*;
import com.epam.glasshouse.exception.GlasshouseException;
import com.epam.glasshouse.handler.PlantXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;

public class PlantStaxBuilder extends AbstractPlantBuilder {

    private final static Logger logger = LogManager.getLogger(PlantStaxBuilder.class);

    private XMLInputFactory inputFactory;

    public PlantStaxBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public HashSet<Plant> getPlants() {
        return (HashSet<Plant>) plants.clone();
    }

    @Override
    public void buildSetPlants(String path) throws GlasshouseException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(path))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(PlantXmlTag.BLOOMING_FLOWER.getTitle())) {
                        BloomingFlower flower = buildBloomingFlower(reader);
                        plants.add(flower);
                    } else if (name.equals(PlantXmlTag.EVERGREEN_PLANT.getTitle())) {
                        EvergreenPlant evergreenPlant = buildEvergreenPlant(reader);
                        plants.add(evergreenPlant);
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.error("Error in stax buildSetPlants:" + e.getMessage());
            throw new GlasshouseException("Error in sax buildSetPlants", e);
        } catch (IOException e) {
            logger.error("Problems with path: " + path + " in stax buildSetPlants");
            throw new GlasshouseException("Problems with path");
        }
        logger.info("Plants in buildSetPlants method from stax builder are:\n" + plants);
    }

    private BloomingFlower buildBloomingFlower(XMLStreamReader reader) throws XMLStreamException {
        BloomingFlower flower = new BloomingFlower();
        fillAttrValue(flower, reader);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case ORIGIN:
                            flower.setOrigin(getXMLText(reader));
                            break;
                        case VISUAL_PARAMETERS:
                            flower.setAppearance(getXMLAppearance(reader));
                            break;
                        case GROWING_TIPS:
                            flower.setGrowingTips(getXMLGrowingTips(reader));
                            break;
                        case MULTIPLYING:
                            String multiplyingString = getXMLText(reader);
                            Multiplying multiplying = Multiplying.valueOf(multiplyingString.toUpperCase());
                            flower.setMultiplying(multiplying);
                            break;
                        case LANDING_DATE:
                            flower.setLandingDate(LocalDate.parse(getXMLText(reader)));
                            break;
                        case BLOSSOM_COLOR:
                            flower.setBlossomColor(getXMLText(reader));
                            break;
                        case BLOSSOM_PERIOD:
                            flower.setBlossomPeriod(Period.ofDays(Integer.parseInt(getXMLText(reader))));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))
                            == PlantXmlTag.BLOOMING_FLOWER) {
                        return flower;
                    }
            }
        }
        logger.error("Unknown element in tag <blooming-flower>");
        throw new XMLStreamException("Unknown element in tag <blooming-flower>");
    }

    private EvergreenPlant buildEvergreenPlant(XMLStreamReader reader) throws XMLStreamException {
        EvergreenPlant plant = new EvergreenPlant();
        fillAttrValue(plant, reader);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case ORIGIN:
                            plant.setOrigin(getXMLText(reader));
                            break;
                        case VISUAL_PARAMETERS:
                            plant.setAppearance(getXMLAppearance(reader));
                            break;
                        case GROWING_TIPS:
                            plant.setGrowingTips(getXMLGrowingTips(reader));
                            break;
                        case MULTIPLYING:
                            String multiplyingString = getXMLText(reader);
                            Multiplying multiplying = Multiplying.valueOf(multiplyingString.toUpperCase());
                            plant.setMultiplying(multiplying);
                            break;
                        case LANDING_DATE:
                            plant.setLandingDate(LocalDate.parse(getXMLText(reader)));
                            break;
                        case TYPE:
                            String typeString = getXMLText(reader).replace("-", "_");
                            Type typePlant = Type.valueOf(typeString.toUpperCase());
                            plant.setType(typePlant);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))
                            == PlantXmlTag.EVERGREEN_PLANT) {
                        return plant;
                    }
            }
        }
        logger.error("Unknown element in tag <evergreen-plant>");
        throw new XMLStreamException("Unknown element in tag <evergreen-plant>");
    }

    private void fillAttrValue(Plant plant, XMLStreamReader reader) {
        plant.setName(reader.getAttributeValue(null, PlantXmlTag.NAME.getTitle()));
        Soil soil = Soil.PODZOLIC;
        if (reader.getAttributeCount() > 1) {
            String soilString = reader.getAttributeValue(null, PlantXmlTag.SOIL.getTitle()).toUpperCase();
            soil = Soil.valueOf(soilString);
        }
        plant.setSoil(soil);
    }

    private Appearance getXMLAppearance(XMLStreamReader reader) throws XMLStreamException {
        Appearance appearance = new Appearance();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case STEM_COLOR:
                            appearance.setStemColor(getXMLText(reader));
                            break;
                        case LEAF_COLOR:
                            appearance.setLeafColor(getXMLText(reader));
                            break;
                        case AVERAGE_SIZE:
                            appearance.setAverageSize(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))
                            == PlantXmlTag.VISUAL_PARAMETERS) {
                        return appearance;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <address>");
    }

    private GrowingTips getXMLGrowingTips(XMLStreamReader reader) throws XMLStreamException {
        GrowingTips growingTips = new GrowingTips();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))) {
                        case TEMPERATURE:
                            growingTips.setTemperature(Integer.parseInt(getXMLText(reader)));
                            break;
                        case LIGHT_LOVING:
                            growingTips.setLightLoving(Boolean.parseBoolean(getXMLText(reader)));
                            break;
                        case IRRIGATION:
                            growingTips.setIrrigation(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlantXmlTag.valueOf(name.toUpperCase().replace("-", "_"))
                            == PlantXmlTag.GROWING_TIPS) {
                        return growingTips;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <address>");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}