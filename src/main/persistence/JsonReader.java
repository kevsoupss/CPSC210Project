package persistence;

import model.Closet;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;

import org.json.*;

// Represents a reader that reads closet from JSON data stored in file
// Modelled after JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads closet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Closet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCloset(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses closet from JSON object and returns it
    private Closet parseCloset(JSONObject jsonObject) {
        Closet c = new Closet();
        addInventory(c, jsonObject);
        addSummerOutfit(c, jsonObject);
        addWinterOutfit(c, jsonObject);

        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses inventory from JSON object and adds them to closet
    private void addInventory(Closet c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("inventory");
        for (Object json : jsonArray) {
            JSONObject clothing = (JSONObject) json;
            addClothing(c, clothing);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses clothing from JSON object and adds it to closet
    private void addClothing(Closet c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String description = jsonObject.getString("description");
        int type = jsonObject.getInt("type");
        c.addClothes(name, description, type);
    }

    // MODIFIES: c
    // EFFECTS: parses summer outfits from jsonArray and adds to closet
    private void addSummerOutfit(Closet c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("summer outfit");
        for (Object json : jsonArray) {
            JSONObject summerOutfit = (JSONObject) json;
            addToSummerOutfit(c, summerOutfit);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses a summer outfit from JSONObject and adds to closet
    private void addToSummerOutfit(Closet c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        c.addNewSummerOutfit(name);
        String innerTop = jsonObject.getString("inner top");
        String outerTop = jsonObject.getString("outer top");
        String innerBottom = jsonObject.getString("inner bottom");
        String outerBottom = jsonObject.getString("outer bottom");
        String accessories = jsonObject.getString("accessories");

        List<String> clothing = new ArrayList<String>();
        clothing.add(innerTop);
        clothing.add(outerTop);
        clothing.add(innerBottom);
        clothing.add(outerBottom);
        clothing.add(accessories);

        for (String title: clothing) {
            if (!(title.equals("empty"))) {
                c.getRecentlyAddedSummerOutfit().addToOutfit(c.findClothing(title));
            }
        }

    }

    // MODIFIES: c
    // EFFECTS: parses winter outfits from JSONArray and adds to closet
    private void addWinterOutfit(Closet c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("winter outfit");
        for (Object json : jsonArray) {
            JSONObject winterOutfit = (JSONObject) json;
            addToWinterOutfit(c, winterOutfit);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses a winter outfit from JSONObject and adds to closet
    private void addToWinterOutfit(Closet c, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        c.addNewWinterOutfit(name);
        String innerTop = jsonObject.getString("inner top");
        String outerTop = jsonObject.getString("outer top");
        String innerBottom = jsonObject.getString("inner bottom");
        String outerBottom = jsonObject.getString("outer bottom");
        String accessories = jsonObject.getString("accessories");


        List<String> clothing = new ArrayList<String>();
        clothing.add(innerTop);
        clothing.add(outerTop);
        clothing.add(innerBottom);
        clothing.add(outerBottom);
        clothing.add(accessories);

        for (String title: clothing) {
            if (!(title.equals("empty"))) {
                c.getRecentlyAddedWinterOutfit().addToOutfit(c.findClothing(title));
            }
        }

    }



}
