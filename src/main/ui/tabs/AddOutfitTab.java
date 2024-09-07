package ui.tabs;

import model.Clothing;
import ui.ClosetUI;
import model.Outfit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Tab where you can add outfits
public class AddOutfitTab extends Tab implements ActionListener {


    private static final int LEFT_PADDING = 70;
    private static final int BOTTOM_PADDING = 5;
    private static final int CENTER = SwingConstants.CENTER;
    private static final String GREETING = "Click on clothes to add to outfit:";

    private JButton clothes;
    private JPanel list;
    private List<JButton> buttons;
    private JLabel greeting;

    private JPanel panel;
    private JLabel nameText;
    private JTextField nameTextField;

    private JCheckBox summerBox;
    private JCheckBox winterBox;

    private GridLayout rowLayout;
    private Border border;

    private Outfit outfit;

    private Boolean isSummerOutfit;
    private Boolean isWinterOutfit;

    private List<Clothing> curOutfit;

    private InputHandler inputHandler = new InputHandler();

    // EFFECTS: sets up JPanels, lists and variables
    public AddOutfitTab(ClosetUI controller) {

        super(controller);

        isSummerOutfit = false;
        isWinterOutfit = false;

        buttons = new ArrayList<>();

        curOutfit = new ArrayList<>();

        placeGreeting();

        list = new JPanel();
        list.setPreferredSize(new Dimension(850, 30));
        list.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (Clothing clothing : this.getController().getCloset().getInventory().getClothes()) {
            clothes = new JButton("Name: " + clothing.getName() + "\nDescription: " + clothing.getDescription());
            clothes.setSize(WIDTH, HEIGHT / 3);
            list.add(clothes);
            this.add(list);
        }

        border = BorderFactory.createEmptyBorder(0, LEFT_PADDING, BOTTOM_PADDING, 0);
        rowLayout = new GridLayout(2, 1);

        placeInputs();
        placeCreateButton();

    }

    // MODIFIES: this
    // EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // MODIFIES: this
    // EFFECTS: adds an inventory for users to add clothing
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
            buttons.add(clothes);
            list.add(clothes);
            this.add(list);
        }

    }

    // EFFECTS: adds clothing to list
    public void addClothing(Clothing clothing) {
        clothes = new JButton("[Name: " + clothing.getName() + "]" + "\n [Description: "
                + clothing.getDescription() + "]");
        clothes.setSize(WIDTH, HEIGHT / 3);
        list = new JPanel();
        list.setPreferredSize(new Dimension(850, 30));
        list.setLayout(new FlowLayout(FlowLayout.LEFT));
        clothes.setBackground(Color.WHITE);
        clothes.setOpaque(true);
        clothes.addActionListener(this);
        buttons.add(clothes);
        list.add(clothes);
        this.add(list);
    }

    // MODIFIES: this
    // EFFECTS: checks if a clothing button is clicked, and if so, add to outfit
    public void actionPerformed(ActionEvent e) {
        String buttonPressed = e.getActionCommand();
        for (Clothing clothing : this.getController().getCloset().getInventory().getClothes()) {
            if (buttonPressed.equals("[Name: " + clothing.getName() + "]" + "\n [Description: "
                    + clothing.getDescription() + "]")) {
                removeType(clothing);
                curOutfit.add(clothing);
                for (Clothing c: this.getController().getCloset().getInventory().getClothes()) {
                    if (curOutfit.contains(c)) {
                        find(c).setBackground(Color.YELLOW);
                    } else if (!curOutfit.contains(c)) {
                        find(c).setBackground(Color.WHITE);
                    }
                }


            }
        }
    }

    // MODIFIES: this
    // EFFECTS: remove the same type of clothing in outfit
    private void removeType(Clothing clothing) {

        Clothing temp = null;
        for (Clothing c: curOutfit) {
            if (clothing.getType() == c.getType()) {
                temp = c;
            }
        }
        if (temp != null) {
            curOutfit.remove(temp);
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

    // MODIFIES: this
    // EFFECTS: place the name JLabel and summer and winter checkBoxes
    private void placeInputs() {
        panel = new JPanel(rowLayout);
        panel.setPreferredSize(new Dimension(400, 50));
        panel.setBorder(border);

        nameText = new JLabel("Enter Name of Outfit", CENTER);
        nameText.setVerticalAlignment(SwingConstants.TOP);
        panel.add(nameText);

        nameTextField = new JTextField("", 6);
        panel.add(nameTextField);

        summerBox = new JCheckBox("Summer Outfit");
        summerBox.setSelected(false);
        summerBox.addActionListener(new CheckboxListener(summerBox.getText()));
        panel.add(summerBox);

        winterBox = new JCheckBox("Winter Outfit");
        winterBox.setSelected(false);
        winterBox.addActionListener(new CheckboxListener(winterBox.getText()));
        panel.add(winterBox);

        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS: place the outfit create button
    private void placeCreateButton() {
        JButton create = new JButton("Create");
        JPanel buttonRow = formatButtonRow(create);
        buttonRow.setSize(ClosetUI.WIDTH, ClosetUI.HEIGHT / 6);
        create.addActionListener(inputHandler);

        this.add(buttonRow);

    }

    public void setSummerBoolean(Boolean b) {
        isSummerOutfit = b;
    }

    public void setWinterBoolean(Boolean b) {
        isWinterOutfit = b;
    }

    // Checks the checkbox
    private class CheckboxListener implements ActionListener {

        boolean type;

        // EFFECTS: checks which checkbox is clicked
        public CheckboxListener(String type) {
            if (type.equals("Summer Outfit")) {
                this.type = true;
            } else if (type.equals("Winter Outfit")) {
                this.type = false;
            }
        }

        //MODIFIES: this, a
        //EFFECTS: sets appliance to run while away
        @Override
        public void actionPerformed(ActionEvent e) {
            JCheckBox checkBox = (JCheckBox) e.getSource();
            if (type) {
                boolean checked = checkBox.getModel().isSelected();
                getController().getAddOutfitTab().setSummerBoolean(checked);
            } else if (!type) {
                boolean checked = checkBox.getModel().isSelected();
                getController().getAddOutfitTab().setWinterBoolean(checked);
            }

        }
    }


    public class InputHandler implements ActionListener {

        // MODIFIES: this, closet
        public void actionPerformed(ActionEvent event) {
            if (!nameTextField.getText().equals("")) {
                String name = nameTextField.getText();
                outfit = new Outfit(name);
                for (Clothing clothing: curOutfit) {
                    outfit.addToOutfit(clothing);
                }
                if (isSummerOutfit) {
                    getController().getCloset().getOutfitCollection().getSummerCollection().add(outfit);
                }
                if (isWinterOutfit) {
                    getController().getCloset().getOutfitCollection().getWinterCollection().add(outfit);
                }
                nameTextField.setText("");
                summerBox.setSelected(false);
                winterBox.setSelected(false);
                setWinterBoolean(false);
                setSummerBoolean(false);
                for (JButton button: buttons) {
                    button.setBackground(Color.WHITE);
                }
                curOutfit.clear();
                updateOutfits();

            }
        }
    }

    // MODIFIES: closet
    // EFFECTS: updates both summer and winter outfits
    private void updateOutfits() {
        getController().getSummerOutfitTab().updateSummerOutfit();
        getController().getWinterOutfitTab().updateWinterOutfit();
    }


}



