package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Objects;

// A class related to clothing, the fundamental parts of an outfit and in inventory
// toJson method modelled from JsonSerializationDemo
public class Clothing implements Writable {
    private String name;
    private String description;

    // 0 = InnerTop, 1 = OuterTop, 2 = InnerBottom, 3 = OuterBottom, 4 = Accessories
    private int type;



    // REQUIRES: name is not empty, type is an int including from 0-4
    // EFFECTS: creates a piece of clothing with a name, a description, and type of clothing
    public Clothing(String name, String description, int type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getType() {
        return this.type;
    }

    // EFFECTS: sets the current description to String description
    public void editDescription(String description) {
        this.description = description;

    }

    public void editName(String name) {
        this.name = name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", this.name);
        json.put("description", this.description);
        json.put("type", this.type);
        return json;

    }


}
