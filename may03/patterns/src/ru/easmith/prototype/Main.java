package ru.easmith.prototype;

import ru.easmith.prototype.prototypes.ArmyPropertyPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eku on 03.05.17.
 */
public class Main {


    public static void main(String[] args) {
        ArmyPropertyPrototype solder = ArmyFactory.getPrototype("solder");

        ArrayList<ArmyPropertyPrototype> podrasdelenie = new ArrayList<>();

        for (int solderCount = 0; solderCount < 10; solderCount++) {
            podrasdelenie.add(ArmyFactory.getPrototype("solder"));
        }
    }
}
