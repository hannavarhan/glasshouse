package com.epam.glasshouse.parser;

import com.epam.glasshouse.entity.Plant;
import com.epam.glasshouse.exception.GlasshouseException;

import java.util.HashSet;

public abstract class AbstractPlantBuilder {

    protected HashSet<Plant> plants;

    public AbstractPlantBuilder() {
        plants = new HashSet<>();
    }

    public AbstractPlantBuilder(HashSet<Plant> students) {
        this.plants = students;
    }

    public HashSet<Plant> getPlants() {
        return (HashSet<Plant>) plants.clone();
    }
    public abstract void buildSetPlants(String filename) throws GlasshouseException;
}
