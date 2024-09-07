package ui.tabs;

import ui.ClosetUI;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Tab that allows user to add clothing
public class AddClothingTab extends Tab {

    private static final int LEFT_PADDING = 70;
    private static final int BOTTOM_PADDING = 5;
    private static final int CENTER = SwingConstants.CENTER;


    private JPanel namePanel;
    private JLabel nameText;
    private JTextField nameTextField;

    private JPanel descPanel;
    private JLabel descText;
    private JTextField descTextField;

    private JPanel typePanel;
    private JLabel typeText;
    private JTextField typeTextField;
    private JPanel textPanel;
    private JLabel exp;
    private ClosetUI controller;

    private GridLayout rowLayout;
    private Border border;

    private InputHandler inputHandler = new InputHandler();

    // Initializes all buttons and variables
    public AddClothingTab(ClosetUI controller) {
        super(controller);
        this.controller = controller;

        border = BorderFactory.createEmptyBorder(0, LEFT_PADDING, BOTTOM_PADDING, 0);
        rowLayout = new GridLayout(1, 1);

        placeNameInput();
        placeDescriptionInput();
        placeTypeInput();
        placeCreateButton();

    }

    // MODIFIES: this
    // EFFECTS: place name button
    private void placeNameInput() {
        namePanel = new JPanel(rowLayout);
        namePanel.setPreferredSize(new Dimension(400, 25));
        namePanel.setBorder(border);

        nameText = new JLabel("Enter Name of Clothing", CENTER);
        nameText.setVerticalAlignment(SwingConstants.TOP);
        namePanel.add(nameText);

        nameTextField = new JTextField("", 6);
        namePanel.add(nameTextField);

        this.add(namePanel);
    }

    // MODIFIES: this
    // EFFECTS: place the description JLabel
    private void placeDescriptionInput() {
        descPanel = new JPanel(rowLayout);
        descPanel.setPreferredSize(new Dimension(500,30));
        descPanel.setBorder(border);

        descText = new JLabel("Enter Description of Clothing");
        descText.setVerticalAlignment(SwingConstants.TOP);
        descPanel.add(descText);

        descTextField = new JTextField("", 6);
        descPanel.add(descTextField);

        this.add(descPanel);
    }

    // MODIFIES: this
    // EFFECTS: place the type JLabel
    private void placeTypeInput() {
        typePanel = new JPanel(rowLayout);
        typePanel.setPreferredSize(new Dimension(900,30));
        typePanel.setBorder(border);

        typeText = new JLabel("0 = InnerTop, 1 = OuterTop, 2 = InnerBottom,"
                + " 3 = OuterBottom, 4 = Accessories", CENTER);
        typeText.setVerticalAlignment(SwingConstants.TOP);
        typePanel.add(typeText);

        textPanel = new JPanel(rowLayout);
        typeTextField = new JTextField("", 6);

        exp = new JLabel("Enter integer based on type of clothing below");
        textPanel.add(exp);
        textPanel.add(typeTextField);

        this.add(textPanel);
        this.add(typePanel);

    }

    // MODIFIES: this
    // EFFECTS: place the creation JButton
    private void placeCreateButton() {
        JButton create = new JButton("Create");
        JPanel buttonRow = formatButtonRow(create);
        buttonRow.setSize(ClosetUI.WIDTH, ClosetUI.HEIGHT / 6);
        create.addActionListener(inputHandler);

        this.add(buttonRow);

    }

    public class InputHandler implements ActionListener {

        // MODIFIES: this, closet
        // EFFECTS: adds clothing to closet
        public void actionPerformed(ActionEvent event) {

            if (!typeTextField.getText().equals("")) {
                String name = nameTextField.getText();
                String desc = descTextField.getText();
                int type = Integer.parseInt(typeTextField.getText());

                controller.getCloset().addClothes(name, desc, type);
                controller.getInventoryTab().addClothing(controller.getCloset().getRecentlyAddedClothes());
                controller.getAddOutfitTab().addClothing(controller.getCloset().getRecentlyAddedClothes());

                nameTextField.setText("");
                descTextField.setText("");
                typeTextField.setText("");
            }
        }
    }


}
