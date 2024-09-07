package ui.tabs;

import ui.ClosetUI;

import javax.swing.*;
import java.awt.*;

// Class for the tabs
public abstract class Tab extends JPanel {

    private final ClosetUI controller;

    // REQUIRES: ClosetUI controller that holds this tab
    public Tab(ClosetUI controller) {
        this.controller = controller;
    }

    // EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    // EFFECTS: returns the ClosetUI controller for this tab
    public ClosetUI getController() {
        return controller;
    }
}
