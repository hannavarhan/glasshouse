package com.epam.glasshouse.parser;

import com.epam.glasshouse.entity.Plant;
import com.epam.glasshouse.exception.GlasshouseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class TestPlantSaxBuilder {

    AbstractPlantBuilder builder;
    String filepath;

    @BeforeClass
    public void createParser() {
        ClassLoader loader = TestPlantDomBuilder.class.getClassLoader();
        URL resource = loader.getResource("file/glasshouse.xml");
        filepath = new File(resource.getFile()).getAbsolutePath();
        builder = PlantBuilderFactory.createPlantBuilder("sax");
    }

    @Test(expectedExceptions = GlasshouseException.class)
    public void testException() throws GlasshouseException {
        builder.buildSetPlants("");
    }

    @Test
    public void testParsingNames() throws GlasshouseException {
        String[] actual = new String[]{"Petunia", "Cyclamen", "Geraniums", "Salvia", "Palma", "Caladium", "Fern",
                "Coleus", "Poinsettia", "GazaniaTreasure", "Chrysanthemum", "Pansies"};
        builder.buildSetPlants(filepath);
        Object[] expected = builder.getPlants().stream()
                .map(Plant::getName)
                .toArray();
        Assert.assertEqualsNoOrder(actual, expected);
    }
}
