package ui;

// Class with name of buttons
public enum ButtonNames {
    LOAD("Load a saved closet"),
    SAVE("Save current closet"),
    GO_TO_INVENTORY("Inventory"),
    GO_TO_SOUTFITS("Summer Outfits"),
    GO_TO_WOUTFITS("Winter Outfits");

    private final String name;

    ButtonNames(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }

}
