package ru.easmith.builder.Galustyan;

import ru.easmith.builder.HouseBuilder;

/**
 * Created by eku on 03.05.17.
 */
public class DjamshutBuilder implements HouseBuilder {
    private String basement;
    private String walls;
    private String roof;

    @Override
    public void fillBasement() {
        basement = "Джамшут залил фундамент";
    }

    @Override
    public void createWalls() {
        walls = "Джамшут сделал стены";
    }

    @Override
    public void createRoof() {
        roof = "Джамшут крышу сделал тоже";
    }

    @Override
    public String getResult() {
        return basement + " " + walls + " " + roof;
    }
}
