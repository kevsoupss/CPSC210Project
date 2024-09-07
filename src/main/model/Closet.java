package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import model.Event;
import model.EventLog;

// A closet containing an Inventory and outfitCollection
// Modelled after JsonSerializationDemo
public class Closet implements Writable {

    private Inventory inventory;
    private OutfitCollection outfitCollection;
    private SummerOutfit summerOutfit;
    private WinterOutfit winterOutfit;
    private Clothing clothing;


    //EFFECTS: creates a new closet which includes inventory and outfit collection
    public Closet() {
        inventory = new Inventory();
        outfitCollection = new OutfitCollection();

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("inventory", inventoryToJson());
        json.put("summer outfit", summerOutfitToJson());
        json.put("winter outfit", winterOutfitToJson());
        return json;
    }

    // EFFECTS: returns inventory in closet as a JSON array
    public JSONArray inventoryToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Clothing c : inventory.getClothes()) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns summer outfits as a JSONArray
    public JSONArray summerOutfitToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Outfit o: outfitCollection.getSummerCollection()) {
            jsonArray.put(o.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: returns winter outfits as a JSONArray
    public JSONArray winterOutfitToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Outfit o: outfitCollection.getWinterCollection()) {
            jsonArray.put(o.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: create new clothing and add to inventory
    public void addClothes(String name, String description, int type) {
        Clothing clothing = new Clothing(name, description, type);
        this.clothing = clothing;
        addToInventory(clothing);
        EventLog.getInstance().logEvent(new Event("Added clothing to inventory: " + clothing.getName()));
    }

    // MODIFIES: this
    // EFFECTS: adds clothing to inventory
    public void addToInventory(Clothing clothing) {
        inventory.addToInventory(clothing);

    }

    // EFFECTS:adds a new empty summer outfit to the summerOutFits
    public void addNewSummerOutfit(String name) {
        SummerOutfit summerOutfit = new SummerOutfit(name);
        this.summerOutfit = summerOutfit;
        outfitCollection.addToSummerCollection(summerOutfit);
        EventLog.getInstance().logEvent(new Event("Added summer outfit: " + summerOutfit.getName()));

    }

    // MODIFIES: this
    // EFFECTS: adds clothing to outfit
    public void editSummerOutfit(Clothing clothing) {
        this.summerOutfit.addToOutfit(clothing);

    }

    // MODIFIES: this
    // EFFECTS: adds clothing to winter outfit
    public void addNewWinterOutfit(String name) {
        WinterOutfit winterOutfit = new WinterOutfit(name);
        this.winterOutfit = winterOutfit;
        outfitCollection.addToWinterCollection(winterOutfit);
        EventLog.getInstance().logEvent(new Event("Added winter outfit: " + winterOutfit.getName()));

    }

    // MODIFIES: this
    // EFFECTS: edits clothing to outfit
    public void editWinterOutfit(Clothing clothing) {
        this.winterOutfit.addToOutfit(clothing);

    }

    public OutfitCollection getOutfitCollection() {
        return outfitCollection;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Clothing getRecentlyAddedClothes() {
        return clothing;
    }

    // EFFECTS: returns clothing at index num
    public Clothing getClothingAtIndex(int num) {
        return this.inventory.getClothes().get(num);
    }


    public SummerOutfit getRecentlyAddedSummerOutfit() {
        return summerOutfit;
    }

    public WinterOutfit getRecentlyAddedWinterOutfit() {
        return winterOutfit;
    }


    // EFFECTS: returns outfit in summer collection at index num
    public Outfit getOutfitInSummerCollection(int num) {
        return outfitCollection.getSummerCollection().get(num);
    }

    // EFFECTS: returns outfit in winter collection at index num
    public Outfit getOutfitInWinterCollection(int num) {
        return outfitCollection.getWinterCollection().get(num);
    }

    // EFFECTS: checks if the name matches a clothing with same name
    public Boolean isInInventory(String name) {
        for (Clothing clothing: inventory.getClothes()) {
            if (name.equals(clothing.getName())) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: isInventory is true
    // EFFECTS: returns the clothing with the same name
    public Clothing findClothing(String name) {
        Clothing temp = null;
        for (Clothing clothing : inventory.getClothes()) {
            if (name.equals(clothing.getName())) {
                temp = clothing;
            }
        }
        return temp;
    }

    // EFFECTS: returns the size of the inventory
    public int numClothing() {
        return getInventory().getClothes().size();
    }

    // EFFECTS: returns the number of summer items
    public int summerSize() {
        return getOutfitCollection().getSummerCollection().size();
    }

    public int winterSize() {
        return getOutfitCollection().getWinterCollection().size();
    }

    // MODIFIES: this
    // EFFECTS: finds clothing with name and adds to outfit
    public void addToSummerOutfit(String name) {

        getRecentlyAddedSummerOutfit().addToOutfit(findClothing(name));
    }

    // MODIFIES: this
    // EFFECTS: finds clothing with name and adds to outfit
    public void addToWinterOutfit(String name) {
        getRecentlyAddedWinterOutfit().addToOutfit(findClothing(name));
    }









}
