package org.dreambot.nodes.combat;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.core.Main;
import org.dreambot.utils.Node;

public class AttackNode extends Node {


    public AttackNode(Main s) {
        super(s);
    }

    @Override
    public boolean validate() {
        return !getLocalPlayer().isInCombat() && !getLocalPlayer().isHealthBarVisible() && s.canEat;
    }

    @Override
    public int execute() {
        NPC monster = getNpcs().closest(n-> n != null && s.monsterNames.contains(n.getName()) && !n.isInteractedWith() && !n.isInCombat() && getMap().canReach(n));
        if (monster != null){
            if (monster.interact("Attack")){
                return Calculations.random(800,1200);
            }
        } else {
            if (!s.attackArea.contains(getLocalPlayer())){
                walkTo(s.attackArea);
            }
        }
        return Calculations.random(500,800);
    }

    @Override
    public String state() {
        return "Attacking";
    }
}
