package ui.tabs;

import ui.ClosetUI;
import model.Outfit;

import javax.swing.*;
import java.awt.*;

// Tab displaying all current Winter Outfits
public class WinterOutfitsTab extends Tab {

    private JPanel list;
    private JLabel outfits;

    // EFFECTS: creates a WinterOutfit JPanel
    public WinterOutfitsTab(ClosetUI controller) {
        super(controller);

        list = new JPanel();
        list.setPreferredSize(new Dimension(800, 25));
        list.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (Outfit outfit: this.getController().getCloset().getOutfitCollection().getWinterCollection()) {
            outfits = new JLabel("[Name: " + outfit.getName() + "]" + " " + getOutfitParts(outfit));
            outfits.setSize(WIDTH, HEIGHT / 3);
            list.add(outfits);
            this.add(list);
        }

    }

    // EFFECTS: puts the name of the outfit parts together
    private String getOutfitParts(Outfit outfit) {
        return "[Clothes: "
                + outfit.getOuterTop().getName() + "," + outfit.getInnerTop().getName()
                + "," + outfit.getOuterBottom().getName()
                + "," + outfit.getInnerBottom().getName() +  "," + outfit.getAccessories().getName() + "]";
    }

    // EFFECTS: updates the currently displayed outfits
    public void updateWinterOutfit() {
        if (outfits != null) {
            this.removeAll();
            list = new JPanel();
            list.setPreferredSize(new Dimension(800, 25));
            list.setLayout(new FlowLayout(FlowLayout.LEFT));
        }
        for (Outfit outfit: this.getController().getCloset().getOutfitCollection().getWinterCollection()) {
            outfits = new JLabel("[Name: " + outfit.getName() + "]" + " " + getOutfitParts(outfit));
            list = new JPanel();
            outfits.setSize(WIDTH, HEIGHT / 3);
            list.add(outfits);
            this.add(list);

        }


    }

}








