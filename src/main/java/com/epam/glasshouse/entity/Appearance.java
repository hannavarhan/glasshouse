package com.epam.glasshouse.entity;

public class Appearance {
    private String stemColor;
    private String leafColor;
    private int averageSize;

    public Appearance(String stemColor, String leafColor, int averageSize) {
        this.stemColor = stemColor;
        this.leafColor = leafColor;
        this.averageSize = averageSize;
    }

    public Appearance() {
    }

    public String getStemColor() {
        return stemColor;
    }

    public void setStemColor(String stemColor) {
        this.stemColor = stemColor;
    }

    public String getLeafColor() {
        return leafColor;
    }

    public void setLeafColor(String leafColor) {
        this.leafColor = leafColor;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int averageSize) {
        this.averageSize = averageSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Appearance{");
        sb.append(stemColor).append("=stemColor, ");
        sb.append(leafColor).append("=leafColor, ");
        sb.append(averageSize).append("=averageSize}").append("\n");
        return sb.toString();
    }
}
