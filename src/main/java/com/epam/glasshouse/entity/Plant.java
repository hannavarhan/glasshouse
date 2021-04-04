package com.epam.glasshouse.entity;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Plant {

    protected String name;
    protected Soil soil;
    protected String origin;
    protected Appearance appearance = new Appearance();
    protected GrowingTips growingTips = new GrowingTips();
    protected Multiplying multiplying;
    protected LocalDate landingDate;

    public Plant() {
    }

    public Plant(String name, Soil soil, String origin, Appearance appearance,
                 GrowingTips growingTips, Multiplying multiplying, LocalDate landingDate) {
        this.name = name;
        this.soil = soil;
        this.origin = origin;
        this.appearance = appearance;
        this.growingTips = growingTips;
        this.multiplying = multiplying;
        this.landingDate = landingDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Soil getSoil() {
        return soil;
    }

    public void setSoil(Soil soil) {
        this.soil = soil;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public void setAppearance(Appearance appearance) {
        this.appearance = appearance;
    }

    public GrowingTips getGrowingTips() {
        return growingTips;
    }

    public void setGrowingTips(GrowingTips growingTips) {
        this.growingTips = growingTips;
    }

    public Multiplying getMultiplying() {
        return multiplying;
    }

    public void setMultiplying(Multiplying multiplying) {
        this.multiplying = multiplying;
    }

    public LocalDate getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(LocalDate landingDate) {
        this.landingDate = landingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return name.equals(plant.name) &&
                soil == plant.soil &&
                origin.equals(plant.origin) &&
                appearance.equals(plant.appearance) &&
                growingTips.equals(plant.growingTips) &&
                multiplying == plant.multiplying &&
                landingDate.equals(plant.landingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, soil, origin, appearance, growingTips, multiplying, landingDate);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(" name='").append(name).append('\'');
        sb.append(", soil=").append(soil);
        sb.append(", origin='").append(origin).append('\'').append("\n");
        sb.append(appearance);
        sb.append(growingTips);
        sb.append(" multiplying=").append(multiplying);
        sb.append(", landingDate=").append(landingDate);
        sb.append('}');
        return sb.toString();
    }
}
