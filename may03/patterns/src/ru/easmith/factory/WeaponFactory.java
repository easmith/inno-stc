package ru.easmith.factory;

/**
 * Created by eku on 03.05.17.
 */
public interface WeaponFactory {
    Gun createGun();
    Revolver createRevolver();
    Rifle createRifle();
}
