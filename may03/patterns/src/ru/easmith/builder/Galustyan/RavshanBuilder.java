package ru.easmith.builder.Galustyan;

import ru.easmith.builder.HouseBuilder;

/**
 * Created by eku on 03.05.17.
 */
public class RavshanBuilder implements HouseBuilder {
    private String basement;
    private String walls;
    private String roof;

    @Override
    public void fillBasement() {
        basement = "Равшан залил фундамент";
    }

    @Override
    public void createWalls() {
        walls = "Равшан сделал стены";
    }

    @Override
    public void createRoof() {
        roof = "Равшан крышу сделал";
    }

    @Override
    public String getResult() {
        return basement + " " + walls + " " + roof;
    }
}
