package com.epam.glasshouse.entity;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class BloomingFlower extends Plant {

    private String blossomColor;
    private Period blossomPeriod;

    public BloomingFlower() {
    }

    public BloomingFlower(String name, Soil soil, String origin, Appearance appearance, GrowingTips growingTips,
                          Multiplying multiplying, LocalDate landingDate, String blossomColor, Period blossomPeriod) {
        super(name, soil, origin, appearance, growingTips, multiplying, landingDate);
        this.blossomColor = blossomColor;
        this.blossomPeriod = blossomPeriod;
    }

    public String getBlossomColor() {
        return blossomColor;
    }

    public void setBlossomColor(String blossomColor) {
        this.blossomColor = blossomColor;
    }

    public Period getBlossomPeriod() {
        return blossomPeriod;
    }

    public void setBlossomPeriod(Period blossomPeriod) {
        this.blossomPeriod = blossomPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BloomingFlower that = (BloomingFlower) o;
        return blossomColor.equals(that.blossomColor) &&
                blossomPeriod.equals(that.blossomPeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), blossomColor, blossomPeriod);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BloomingFlower{");
        sb.append("blossomColor='").append(blossomColor).append('\'');
        sb.append(", blossomPeriod=").append(blossomPeriod);
        sb.append(super.toString());
        return sb.toString();
    }
}
