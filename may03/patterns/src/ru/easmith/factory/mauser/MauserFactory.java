package ru.easmith.factory.mauser;

import ru.easmith.factory.Gun;
import ru.easmith.factory.Revolver;
import ru.easmith.factory.Rifle;
import ru.easmith.factory.WeaponFactory;

/**
 * Created by eku on 03.05.17.
 */
public class MauserFactory implements WeaponFactory {
    @Override
    public Gun createGun() {
        return new MauserGun();
    }

    @Override
    public Revolver createRevolver() {
        return new MauserRevolver();
    }

    @Override
    public Rifle createRifle() {
        return new MauserRifle();
    }
}
