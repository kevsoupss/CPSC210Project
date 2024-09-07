package ui.tabs;

import ui.ClosetUI;
import model.Clothing;
import java.util.List;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Keeps track and displays all current clothes added with name and description
public class InventoryTab extends Tab implements ActionListener {

    private JButton clothes;
    private JPanel list;
    private List<JButton> buttons;


    // REQUIRES: ClosetUI controller that holds this tab
    // EFFECTS: creates inventory tab with all created clothing with description
    public InventoryTab(ClosetUI controller) {
        super(controller);
        buttons = new ArrayList<>();
        list = new JPanel();
        list.setPreferredSize(new Dimension(850, 30));
        list.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (Clothing clothing: this.getController().getCloset().getInventory().getClothes()) {
            clothes = new JButton("Name: " + clothing.getName() + "\nDescription: " + clothing.getDescription());
            clothes.setSize(WIDTH, HEIGHT / 3);
            list.add(clothes);
            this.add(list);
        }
    }

    // EFFECTS: puts new clothing into the inventory
    public void updateInventory() {

        if (clothes != null) {
            this.removeAll();
            list = new JPanel();
            list.setPreferredSize(new Dimension(850, 30));
        }
        for (Clothing clothing : this.getController().getCloset().getInventory().getClothes()) {
            clothes = new JButton("[Name: " + clothing.getName() + "]" + "\n [Description: "
                    + clothing.getDescription() + "]");
            clothes.setSize(WIDTH, HEIGHT / 3);
            list = new JPanel();
            list.setPreferredSize(new Dimension(850, 30));
            list.setLayout(new FlowLayout(FlowLayout.LEFT));

            clothes.addActionListener(this);
            clothes.setBackground(Color.WHITE);
            clothes.setOpaque(true);
            clothes.setBorderPainted(true);
            buttons.add(clothes);
            list.add(clothes);
            this.add(list);
        }

    }

    // EFFECTS: changes the color of button after clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        for (Clothing clothing: this.getController().getCloset().getInventory().getClothes()) {
            if (buttonPressed.equals("[Name: " + clothing.getName() + "]" + "\n [Description: "
                    + clothing.getDescription() + "]")) {
                if (find(clothing).getBackground() == (Color.WHITE)) {
                    find(clothing).setBackground(Color.RED);
                } else if (find(clothing).getBackground() == (Color.RED)) {
                    find(clothing).setBackground(Color.ORANGE);
                } else if (find(clothing).getBackground() == (Color.ORANGE)) {
                    find(clothing).setBackground(Color.YELLOW);
                } else if (find(clothing).getBackground() == (Color.YELLOW)) {
                    find(clothing).setBackground(Color.GREEN);
                } else if (find(clothing).getBackground() == (Color.GREEN)) {
                    find(clothing).setBackground(Color.BLUE);
                } else if (find(clothing).getBackground() == (Color.BLUE)) {
                    find(clothing).setBackground(Color.PINK);
                } else if (find(clothing).getBackground() == (Color.PINK)) {
                    find(clothing).setBackground(Color.BLACK);
                } else if (find(clothing).getBackground() == (Color.BLACK)) {
                    find(clothing).setBackground(Color.WHITE);
                }
            }

        }
    }

    // EFFECTS: finds Button with clothing name and description
    private JButton find(Clothing clothing) {
        for (JButton button: buttons) {
            if (button.getText().equals("[Name: " + clothing.getName() + "]" + "\n [Description: "
                    + clothing.getDescription() + "]")) {
                return button;
            }
        }
        return null;
    }

    // EFFECTS: adds clothing to list
    public void addClothing(Clothing clothing) {
        clothes = new JButton("[Name: " + clothing.getName() + "]" + "\n [Description: "
                + clothing.getDescription() + "]");
        clothes.setSize(WIDTH, HEIGHT / 3);
        list = new JPanel();
        list.setPreferredSize(new Dimension(850, 30));
        list.setLayout(new FlowLayout(FlowLayout.LEFT));

        clothes.addActionListener(this);
        clothes.setBackground(Color.WHITE);
        clothes.setOpaque(true);
        clothes.setBorderPainted(true);
        buttons.add(clothes);
        list.add(clothes);
        this.add(list);
    }











}
