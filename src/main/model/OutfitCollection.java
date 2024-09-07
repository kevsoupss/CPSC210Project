package model;

import java.util.ArrayList;
import java.util.List;

// A collection of the outfits created
public class OutfitCollection {
    private List<Outfit> summerCollection;
    private List<Outfit> winterCollection;

    // EFFECTS: Creates an empty summer collection and empty winter collection
    public OutfitCollection() {
        summerCollection = new ArrayList<>();
        winterCollection = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: add summer outfit to summer collection
    public void addToSummerCollection(SummerOutfit summerOutfit) {
        summerCollection.add(summerOutfit);
    }

    // MODIFIES: this
    // EFFECTS: add winter outfit to winter collection
    public void addToWinterCollection(WinterOutfit winterOutfit) {
        winterCollection.add(winterOutfit);
    }

    public List<Outfit> getSummerCollection() {
        return summerCollection;
    }

    public List<Outfit> getWinterCollection() {
        return winterCollection;
    }

    public Boolean isSummerEmpty() {
        return summerCollection.isEmpty();
    }

    public Boolean isWinterEmpty() {
        return winterCollection.isEmpty();
    }






}
