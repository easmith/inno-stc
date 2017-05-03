package ru.easmith.builder.Svetlyakov;

import ru.easmith.builder.HouseBuilder;

/**
 * Created by eku on 03.05.17.
 */
public class Nasyalnik {
    private HouseBuilder builder;

    public Nasyalnik(HouseBuilder builder) {
        this.builder = builder;
    }

    public void build() {
        builder.fillBasement();
        builder.createWalls();
        builder.createRoof();

        System.out.println(builder.getResult());
    }
}
