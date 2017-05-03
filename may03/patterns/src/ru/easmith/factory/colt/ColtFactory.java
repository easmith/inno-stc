package ru.easmith.factory.colt;

import ru.easmith.factory.Gun;
import ru.easmith.factory.Revolver;
import ru.easmith.factory.Rifle;
import ru.easmith.factory.WeaponFactory;

/**
 * Created by eku on 03.05.17.
 */
public class ColtFactory implements WeaponFactory {
    @Override
    public Gun createGun() {
        return new ColtGun();
    }

    @Override
    public Revolver createRevolver() {
        return new ColtRevolver();
    }

    @Override
    public Rifle createRifle() {
        return new ColtRifle();
    }
}
