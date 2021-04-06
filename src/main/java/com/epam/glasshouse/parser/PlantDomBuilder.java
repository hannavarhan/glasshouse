package com.epam.glasshouse.parser;

import com.epam.glasshouse.entity.*;
import com.epam.glasshouse.exception.GlasshouseException;
import com.epam.glasshouse.handler.PlantXmlTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;

public class PlantDomBuilder extends AbstractPlantBuilder {

    private final static Logger logger = LogManager.getLogger(PlantDomBuilder.class);

    private DocumentBuilder documentBuilder;

    public PlantDomBuilder() {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error("Error in PlantDomBuilder: " + e.getMessage());
        }
    }

    @Override
    public HashSet<Plant> getPlants() {
        return (HashSet<Plant>) plants.clone();
    }

    @Override
    public void buildSetPlants(String path) throws GlasshouseException {
        Document document;
        try {
            document = documentBuilder.parse(path);
            buildPlantsByElementName(document, PlantXmlTag.BLOOMING_FLOWER);
            buildPlantsByElementName(document, PlantXmlTag.EVERGREEN_PLANT);
        } catch (SAXException e) {
            logger.error("Error in dom buildSetPlants:" + e.getMessage());
            throw new GlasshouseException("Error in dom buildSetPlants", e);
        } catch (IOException e) {
            logger.error("Problems with path: " + path + " in dom buildSetPlants");
            throw new GlasshouseException("Problems with path");
        }
        logger.info("Plants in buildSetPlants method from dom builder are:\n" + plants);
    }

    private void buildPlantsByElementName(Document document, final PlantXmlTag ELEMENT_NAME) {
        Element root = document.getDocumentElement();
        NodeList plantsList = root.getElementsByTagName(ELEMENT_NAME.getTitle());
        for (int i = 0; i < plantsList.getLength(); i++) {
            Element plantElement = (Element) plantsList.item(i);
            Plant plant = null;
            switch (ELEMENT_NAME) {
                case BLOOMING_FLOWER:
                    plant = buildBloomingFlower(plantElement);
                    break;
                case EVERGREEN_PLANT:
                    plant = buildEvergreenPlant(plantElement);
                    break;
                default:
                    logger.error("Illegal argument " + ELEMENT_NAME + " in getPlantByElementName method");
            }
            plants.add(plant);
        }
    }

    private BloomingFlower buildBloomingFlower(Element element) {
        BloomingFlower flower = new BloomingFlower();
        setPlantCharacteristics(element, flower);

        flower.setBlossomColor(getElementTextContent(element, PlantXmlTag.BLOSSOM_COLOR.getTitle()));
        int days = Integer.parseInt(getElementTextContent(element, PlantXmlTag.BLOSSOM_PERIOD.getTitle()));
        Period period = Period.ofDays(days);
        flower.setBlossomPeriod(period);

        return flower;
    }

    private EvergreenPlant buildEvergreenPlant(Element element) {
        EvergreenPlant plant = new EvergreenPlant();
        setPlantCharacteristics(element, plant);

        Type type = Type.valueOf(getElementTextContent(element, PlantXmlTag.TYPE.getTitle()).toUpperCase());
        plant.setType(type);

        return plant;
    }

    private void setPlantCharacteristics(Element element, Plant plant) {
        plant.setName(element.getAttribute(PlantXmlTag.NAME.getTitle()));
        Soil soil = Soil.PODZOLIC;
        if (element.hasAttribute(PlantXmlTag.SOIL.getTitle())) {
            soil = Soil.valueOf(element.getAttribute(PlantXmlTag.SOIL.getTitle()).toUpperCase());
        }
        plant.setSoil(soil);
        plant.setOrigin(getElementTextContent(element, PlantXmlTag.ORIGIN.getTitle()));

        Element appearanceElement = (Element) element.getElementsByTagName(PlantXmlTag.VISUAL_PARAMETERS.getTitle()).item(0);
        Appearance appearance = new Appearance();
        appearance.setStemColor(getElementTextContent(appearanceElement, PlantXmlTag.STEM_COLOR.getTitle()));
        appearance.setLeafColor(getElementTextContent(appearanceElement, PlantXmlTag.LEAF_COLOR.getTitle()));
        int size = Integer.parseInt(getElementTextContent(appearanceElement, PlantXmlTag.AVERAGE_SIZE.getTitle()));
        appearance.setAverageSize(size);
        plant.setAppearance(appearance);

        Element growingTipsElement = (Element) element.getElementsByTagName(
                PlantXmlTag.GROWING_TIPS.getTitle()).item(0);
        GrowingTips tips = new GrowingTips();
        int temperature = Integer.parseInt(getElementTextContent(
                growingTipsElement, PlantXmlTag.TEMPERATURE.getTitle()));
        tips.setTemperature(temperature);
        boolean lightLoving = Boolean.parseBoolean(
                getElementTextContent(growingTipsElement, PlantXmlTag.LIGHT_LOVING.getTitle()));
        tips.setLightLoving(lightLoving);
        int irrigation = Integer.parseInt(getElementTextContent(growingTipsElement, PlantXmlTag.IRRIGATION.getTitle()));
        tips.setIrrigation(irrigation);
        plant.setGrowingTips(tips);

        Multiplying multiplying = Multiplying.valueOf(
                getElementTextContent(element, PlantXmlTag.MULTIPLYING.getTitle()).toUpperCase());
        plant.setMultiplying(multiplying);
        LocalDate landingDate = LocalDate.parse(getElementTextContent(element, PlantXmlTag.LANDING_DATE.getTitle()));
        plant.setLandingDate(landingDate);
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
