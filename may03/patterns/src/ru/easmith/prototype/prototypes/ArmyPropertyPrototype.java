package ru.easmith.prototype.prototypes;

/**
 * Created by eku on 03.05.17.
 */
public abstract class ArmyPropertyPrototype implements Cloneable {
    public ArmyPropertyPrototype clone() throws CloneNotSupportedException{
        return (ArmyPropertyPrototype) super.clone();
    }
}
