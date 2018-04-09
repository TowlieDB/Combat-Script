package org.dreambot.utils;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.combat.Combat;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.tabs.Tabs;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.impl.Condition;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.core.Main;

import static org.dreambot.api.methods.MethodProvider.sleep;

public class Methods {

    protected Main s;

    public Methods(Main s) {
        this.s = s;
    }

    protected static void sleepUntil(Condition condition, long timeOut, int pollRate){
        MethodProvider.sleepUntil(() -> {
            sleep(pollRate);
            return condition.verify();
        }, timeOut);
    }

    protected void walkTo(Area walkArea){
        if (!walkArea.contains(getLocalPlayer())) {
            if (getWalking().getDestination() != null && walkArea.contains(getWalking().getDestination().getTile())) {
                sleep(800, 1200);
            } else {
                if (getWalking().walk(walkArea.getCenter().getRandomizedTile(1))) {
                    sleep(400, 800);
                    int randomInt = Calculations.random(6, 9);
                    sleepUntil(() -> (!getLocalPlayer().isMoving() || getWalking().shouldWalk(randomInt)), Calculations.random(4500,6000), 300);
                }
            }
        }
    }

    protected void openTab(Tab tab){
        if (getTabs().open(tab)){
            sleepUntil(()-> getTabs().isOpen(tab), Calculations.random(3500,4500),150);
        }
    }

    protected Player getLocalPlayer() {
        return s.getLocalPlayer();
    }

    protected Inventory getInventory() {
        return s.getInventory();
    }

    protected Walking getWalking() {
        return s.getWalking();
    }

    protected Tabs getTabs(){
        return s.getTabs();
    }

    protected Bank getBank(){
        return s.getBank();
    }

    protected NPCs getNpcs(){
        return s.getNpcs();
    }

    protected Map getMap(){
        return s.getMap();
    }

    protected Combat getCombat(){
        return s.getCombat();
    }
}
