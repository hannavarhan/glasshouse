package com.epam.glasshouse.entity;

import java.util.Objects;

public class GrowingTips {

    private int temperature;
    private boolean isLightLoving;
    private int irrigation;

    public GrowingTips(int temperature, boolean isLightLoving, int irrigation) {
        this.temperature = temperature;
        this.isLightLoving = isLightLoving;
        this.irrigation = irrigation;
    }

    public GrowingTips() {}

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public boolean getIsLightLoving() {
        return isLightLoving;
    }

    public void setLightLoving(boolean lightLoving) {
        isLightLoving = lightLoving;
    }

    public int getIrrigation() {
        return irrigation;
    }

    public void setIrrigation(int irrigation) {
        this.irrigation = irrigation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowingTips that = (GrowingTips) o;
        return temperature == that.temperature &&
                isLightLoving == that.isLightLoving &&
                irrigation == that.irrigation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temperature, isLightLoving, irrigation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GrowingTips{");
        sb.append("temperature=").append(temperature);
        sb.append(", isLightLoving=").append(isLightLoving);
        sb.append(", irrigation=").append(irrigation);
        sb.append(" ml per week}").append("\n");
        return sb.toString();
    }
}
