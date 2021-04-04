package com.epam.glasshouse.entity;

import com.epam.glasshouse.parser.PlantDomBuilder;
import com.epam.glasshouse.parser.PlantSaxBuilder;
import com.epam.glasshouse.parser.PlantStaxBuilder;

public class Main {
    public static void main(String[] args) {
        /*PlantStaxBuilder builder = new PlantStaxBuilder();
        builder.buildSetPlants("src\\main\\resources\\file\\glasshouse.xml");
        builder.getPlants().forEach(System.out::println);*/

        PlantDomBuilder builder = new PlantDomBuilder();
        builder.buildSetPlants("src\\main\\resources\\file\\glasshouse.xml");
        builder.getPlants().forEach(System.out::println);
    }
}
