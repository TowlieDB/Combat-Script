package org.dreambot.nodes.combat;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.core.Main;
import org.dreambot.utils.Node;

import java.util.concurrent.ThreadLocalRandom;

public class EatNode extends Node {

    private int r;

    private void randomPercent(){
        r =  ThreadLocalRandom.current().nextInt(35, 65);
    }

    public EatNode(Main s) {
        super(s);
        randomPercent();
    }

    @Override
    public boolean validate() {
        return getCombat().getHealthPercent() < r && s.canEat;
    }

    @Override
    public int execute() {
        if (getInventory().contains(s.food)){
            if (getTabs().isOpen(Tab.INVENTORY)) {
                if (getInventory().interact(s.food, "Eat")) {
                    randomPercent();
                    return Calculations.random(800, 1200);
                }
            } else {
                openTab(Tab.INVENTORY);
            }
        } else {
            s.canEat = false;
            s.bankLocation = s.getBank().getClosestBankLocation().getArea(5);
        }
        return Calculations.random(400,600);
    }

    @Override
    public String state() {
        return "Eating";
    }
}
