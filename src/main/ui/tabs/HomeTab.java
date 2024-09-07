package ui.tabs;

import ui.ButtonNames;
import ui.ClosetUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

// The home tab, opened when the ClosetUI is run
public class HomeTab extends Tab {
    private static final String INIT_GREETING = "Would you like to load or save your closet?";
    private JLabel greeting;

    // EFFECTS: constructs a home tab for console with buttons and a greeting
    public HomeTab(ClosetUI controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));

        placeGreeting();

        placeImage();

        placeHomeButtons();
        placeActionButton();
        placeOutfitButtons();


    }

    // EFFECTS: creates greeting at top of console
    private void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 3);
        this.add(greeting);
    }

    // EFFECTS: places image of closet
    private void placeImage() {
        try {
            BufferedImage myPicture = ImageIO.read(new File("./data/Closet3.png"));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setSize(500, 800);
            this.add(picLabel);
        } catch (Exception e) {
            JLabel picLabel = new JLabel("No such picture");
            this.add(picLabel);
        }
    }

    // EFFECTS: creates Load and Save buttons that change greeting message
    private void placeHomeButtons() {
        JButton b1 = new JButton(ButtonNames.LOAD.getValue());
        JButton b2 = new JButton(ButtonNames.SAVE.getValue());

        JPanel buttonRow = formatButtonRow(b1);
        buttonRow.add(b2);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        b1.addActionListener(e -> {
            greeting.setText(getController().loadCloset());
        });

        b2.addActionListener(e -> {
            greeting.setText(getController().saveCloset());
        });

        this.add(buttonRow);
    }

    //EFFECTS: constructs button that switches to inventory
    private void placeActionButton() {
        JPanel actionBlock = new JPanel();
        JButton inventoryButton = new JButton(ButtonNames.GO_TO_INVENTORY.getValue());
        actionBlock.add(formatButtonRow(inventoryButton));

        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.GO_TO_INVENTORY.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(ClosetUI.INVENTORY_TAB_INDEX);
                }
            }
        });
        this.add(actionBlock);
    }


    //EFFECTS: place outfit buttons
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void placeOutfitButtons() {
        JPanel actionBlock = new JPanel();
        JButton summerOutfitButton = new JButton(ButtonNames.GO_TO_SOUTFITS.getValue());
        actionBlock.add(formatButtonRow(summerOutfitButton));
        summerOutfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.GO_TO_SOUTFITS.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(ClosetUI.SUMMER_OUTFITS_TAB_INDEX);
                }
            }
        });
        JButton winterOutfitButton = new JButton(ButtonNames.GO_TO_WOUTFITS.getValue());
        actionBlock.add(formatButtonRow(winterOutfitButton));
        winterOutfitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals(ButtonNames.GO_TO_WOUTFITS.getValue())) {
                    getController().getTabbedPane().setSelectedIndex(ClosetUI.WINTER_OUTFITS_TAB_INDEX);
                }
            }
        });
        this.add(actionBlock);
    }
}


