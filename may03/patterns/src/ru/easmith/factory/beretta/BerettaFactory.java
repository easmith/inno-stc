package ru.easmith.factory.beretta;

import ru.easmith.factory.Gun;
import ru.easmith.factory.Revolver;
import ru.easmith.factory.Rifle;
import ru.easmith.factory.WeaponFactory;

/**
 * Created by eku on 03.05.17.
 */
public class BerettaFactory implements WeaponFactory {
    @Override
    public Gun createGun() {
        return new BerettaGun();
    }

    @Override
    public Revolver createRevolver() {
        return new BerettaRevolver();
    }

    @Override
    public Rifle createRifle() {
        return new BerettaRifle();
    }
}
