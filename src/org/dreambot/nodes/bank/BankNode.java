package org.dreambot.nodes.bank;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.core.Main;
import org.dreambot.utils.Node;

public class BankNode extends Node {

    public BankNode(Main s) {
        super(s);
    }

    @Override
    public boolean validate() {
        return !s.canEat;
    }

    @Override
    public int execute() {
        if (!getInventory().contains(s.food)) {
            if (s.bankLocation.contains(getLocalPlayer())) {
                if (getBank().isOpen()) {
                    if (getBank().contains(s.food)) {
                        if (getBank().withdrawAll(s.food)) {
                            sleepUntil(() -> getInventory().contains(s.food), Calculations.random(3500, 4500), 250);
                        }
                    } else {
                        MethodProvider.log("Out of " + s.food);
                        s.stop();
                    }
                } else {
                    if (getBank().open()) {
                        sleepUntil(() -> getBank().isOpen(), Calculations.random(3500, 4500), 250);
                    }
                }
            } else {
                walkTo(s.bankLocation);
            }
        } else {
            s.canEat = true;
        }
        return Calculations.random(400,600);
    }

    @Override
    public String state() {
        return "Banking";
    }
}
