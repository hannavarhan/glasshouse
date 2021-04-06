package com.epam.glasshouse.parser;

public class PlantBuilderFactory {

    private enum ParserType {
        DOM, SAX, STAX
    }

    private PlantBuilderFactory() {}

    public static AbstractPlantBuilder createPlantBuilder(String parserType) {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type) {
            case DOM:
                return new PlantDomBuilder();
            case SAX:
                return new PlantSaxBuilder();
            case STAX:
                return new PlantStaxBuilder();
            default:
                throw new EnumConstantNotPresentException(type.getDeclaringClass(), type.name());
        }
    }
}
