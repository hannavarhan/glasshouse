package com.epam.glasshouse.handler;

public enum PlantXmlTag {
    FLOWERS("flowers"),
    BLOOMING_FLOWER("blooming-flower"),
    EVERGREEN_PLANT("evergreen-plant"),
    NAME("name"),
    SOIL("soil"),
    ORIGIN("origin"),
    STEM_COLOR("stem-color"),
    LEAF_COLOR("leaf-color"),
    AVERAGE_SIZE("average-size"),
    TEMPERATURE("temperature"),
    LIGHT_LOVING("light-loving"),
    IRRIGATION("irrigation"),
    MULTIPLYING("multiplying"),
    LANDING_DATE("landing-date"),
    BLOSSOM_COLOR("blossom-color"),
    BLOSSOM_PERIOD("blossom-period"),
    TYPE("type"),
    VISUAL_PARAMETERS("visual-parameters"),
    GROWING_TIPS("growing-tips");

    String title;

    PlantXmlTag(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}