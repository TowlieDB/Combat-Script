package org.dreambot.utils;

import org.dreambot.core.Main;

public abstract class Node extends Methods{

    public Node(Main s) {
        super(s);
    }

    public abstract boolean validate();

    public abstract int execute();

    public abstract String state();

}
