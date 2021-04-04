package com.epam.glasshouse.handler;

import com.epam.glasshouse.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.Period;
import java.util.EnumSet;
import java.util.HashSet;

public class PlantSaxHandler extends DefaultHandler {

    private HashSet<Plant> plants;
    private Plant current;
    private PlantXmlTag currentXmlTag;
    private EnumSet<PlantXmlTag> withText;

    public PlantSaxHandler() {
        plants = new HashSet<>();
        withText = EnumSet.range(PlantXmlTag.ORIGIN, PlantXmlTag.TYPE);
    }

    public HashSet<Plant> getPlants() {
        return (HashSet<Plant>) plants.clone();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (PlantXmlTag.BLOOMING_FLOWER.getTitle().equals(qName)) {
            current = new BloomingFlower();
            current.setName(attrs.getValue(0));
            if (attrs.getLength() == 2) { // warning!!!!
                current.setSoil(Soil.valueOf(attrs.getValue(1).toUpperCase()));
            }
        } else if (PlantXmlTag.EVERGREEN_PLANT.getTitle().equals(qName)) {
            current = new EvergreenPlant();
            current.setName(attrs.getValue(0));
            if (attrs.getLength() == 2) { // warning!!!!
                current.setSoil(Soil.valueOf(attrs.getValue(1).toUpperCase()));
            }
        } else {
            PlantXmlTag temp = PlantXmlTag.valueOf(qName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (PlantXmlTag.BLOOMING_FLOWER.getTitle().equals(qName)
                || PlantXmlTag.EVERGREEN_PLANT.getTitle().equals(qName)) {
            plants.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case ORIGIN:
                    current.setOrigin(data);
                    break;
                case STEM_COLOR:
                    current.getAppearance().setStemColor(data);
                    break;
                case LEAF_COLOR:
                    current.getAppearance().setLeafColor(data);
                    break;
                case AVERAGE_SIZE:
                    current.getAppearance().setAverageSize(Integer.parseInt(data));
                    break;
                case TEMPERATURE:
                    current.getGrowingTips().setTemperature(Integer.parseInt(data));
                    break;
                case LIGHT_LOVING:
                    current.getGrowingTips().setLightLoving(Boolean.parseBoolean(data));
                    break;
                case IRRIGATION:
                    current.getGrowingTips().setIrrigation(Integer.parseInt(data));
                    break;
                case MULTIPLYING:
                    current.setMultiplying(Multiplying.valueOf(data.toUpperCase()));
                    break;
                case LANDING_DATE:
                    current.setLandingDate(LocalDate.parse(data));
                    break;
                case BLOSSOM_COLOR:
                    ((BloomingFlower) current).setBlossomColor(data);
                    break;
                case BLOSSOM_PERIOD:
                    ((BloomingFlower) current).setBlossomPeriod(Period.ofDays(Integer.parseInt(data)));
                    break;
                case TYPE:
                    ((EvergreenPlant) current).setType(Type.valueOf(data.toUpperCase()));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }
}