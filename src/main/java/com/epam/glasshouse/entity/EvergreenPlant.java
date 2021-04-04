package com.epam.glasshouse.entity;

import java.time.LocalDate;
import java.util.Objects;

public class EvergreenPlant extends Plant {

    private Type type;

    public EvergreenPlant(String name, Soil soil, String origin, Appearance appearance,
                          GrowingTips growingTips, Multiplying multiplying, LocalDate landingDate, Type type) {
        super(name, soil, origin, appearance, growingTips, multiplying, landingDate);
        this.type = type;
    }

    public EvergreenPlant() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EvergreenPlant that = (EvergreenPlant) o;
        return super.equals(that) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EvergreenPlant{");
        sb.append("type=").append(type);
        sb.append(super.toString());
        return sb.toString();
    }
}