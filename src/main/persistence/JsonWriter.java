package persistence;

import model.Closet;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of closet to file
// Modelled off the JsonSerializationDemo
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this;
    // EFFECTS: opens the writer and throws FileNotFoundException if the destination
    //          file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    public void write(Closet closet) {
        JSONObject json = closet.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }



}
