package ru.easmith.prototype;

import ru.easmith.prototype.prototypes.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by eku on 03.05.17.
 */
public class ArmyFactory {
    private static final Map<String, ArmyPropertyPrototype> prototypes = new HashMap<>();

    static {
        prototypes.put("solder", new SolderAble());
        prototypes.put("ammunition", new AmmunitionAble());
        prototypes.put("machine", new MachineAble());

    }

    public static ArmyPropertyPrototype getPrototype(String type) {
        try {
            return prototypes.get(type).clone();
        } catch (NullPointerException ex) {
            System.out.println("Prototype with name: " + type + ", doesn't exist");
            return null;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
