package ru.easmith.builder;

/**
 * Created by eku on 03.05.17.
 */
public interface HouseBuilder {
    void fillBasement();
    void createWalls();
    void createRoof();
    String getResult();
}
