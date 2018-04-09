package org.dreambot.core;

import org.dreambot.api.methods.map.Area;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Timer;
import org.dreambot.nodes.bank.BankNode;
import org.dreambot.nodes.combat.AttackNode;
import org.dreambot.nodes.combat.EatNode;
import org.dreambot.utils.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@ScriptManifest(category = Category.COMBAT, name = "Combat Script", author = "Koshei", version = 1.0)
public class Main extends AbstractScript {

    public String food;
    public boolean canEat = true;
    public List<String> monsterNames = new ArrayList<>();
    public Area attackArea;
    public Area bankLocation;
    private Node[] nodes;
    private String state = "Waiting for GUI input";
    private boolean isRunning;
    private Timer t = new Timer();

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void onStart() {
        GUI gui = new GUI(this);

        while (!isRunning || !getClient().isLoggedIn()){
            sleep(100);
        }
        nodes = new Node[]{
                new EatNode(this),
                new AttackNode(this),
                new BankNode(this)
        };

        attackArea = getLocalPlayer().getTile().getArea(10);

    }

    @Override
    public int onLoop() {
        for (Node n: nodes) {
            if (n.validate()){
                this.state = n.state();
                return n.execute();
            }
        }
        state = "Waiting";
        return 1000;
    }

    @Override
    public void onPaint(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.setColor(Color.YELLOW);
        g.drawString("Runtime: " + t.formatTime(), 25, 300);
        g.drawString("State: " + state, 25, 315);
    }
}
