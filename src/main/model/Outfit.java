package model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import persistence.Writable;


// A class that takes clothes from the inventory to create outfits
// toJson modelled after JsonSerializationDemo
public class Outfit implements Writable {
    private Clothing innerTop;
    private Clothing outerTop;
    private Clothing innerBottom;
    private Clothing outerBottom;
    private Clothing accessories;
    private List<Clothing> outfit;
    private String name;


    // EFFECTS: creates a new outfit with no current clothes
    public Outfit(String name) {
        this.name = name;
        innerTop = new Clothing("empty", "empty", 0);
        outerTop = new Clothing("empty", "empty", 1);
        innerBottom = new Clothing("empty", "empty", 2);
        outerBottom = new Clothing("empty", "empty", 3);
        accessories = new Clothing("empty", "empty", 4);
        outfit = new ArrayList<>();
        outfit.add(innerTop);
        outfit.add(outerTop);
        outfit.add(innerBottom);
        outfit.add(outerBottom);
        outfit.add(accessories);


    }



    // MODIFIES: this
    // EFFECTS: adds clothing to correct slot with matching type
    public void addToOutfit(Clothing clothing) {
        if (clothing.getType() == 0) {
            innerTop = clothing;
        } else if (clothing.getType() == 1) {
            outerTop = clothing;
        } else if (clothing.getType() == 2) {
            innerBottom = clothing;
        } else if (clothing.getType() == 3) {
            outerBottom = clothing;
        } else {
            accessories = clothing;
        }
        EventLog.getInstance().logEvent(new Event("Added " + clothing.getName() + " to outfit: " + this.name));
    }



    public Clothing getOuterTop() {
        return outerTop;
    }

    public Clothing getInnerTop() {
        return innerTop;
    }

    public Clothing getOuterBottom() {
        return outerBottom;
    }

    public Clothing getInnerBottom() {
        return innerBottom;
    }

    public Clothing getAccessories() {
        return accessories;
    }

    public String getName() {
        return this.name;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json =  new JSONObject();


        json.put("name", this.name);
        json.put("inner top", this.innerTop.getName());
        json.put("outer top", this.outerTop.getName());
        json.put("inner bottom", this.innerBottom.getName());
        json.put("outer bottom", this.outerBottom.getName());
        json.put("accessories", this.accessories.getName());
        return json;


    }
}




