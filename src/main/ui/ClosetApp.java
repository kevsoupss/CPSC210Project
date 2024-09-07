package ui;

import model.Closet;
import java.util.Scanner;
import model.Clothing;
import model.Outfit;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


public class ClosetApp {

    private static final String JSON_STORE = "./data/closet.json";
    private Scanner input;
    private Closet closet;
    private String name;
    private String desc;
    private int type;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: run the closet app
    public ClosetApp() throws FileNotFoundException {
        init();
        runCloset();
    }

    // MODIFIES: this
    // EFFECTS: opens closet and takes user input
    private void runCloset() {
        boolean closetOpen = true;
        String command = null;

        displayCloset();
        while (closetOpen) {

            command = input.next();
            command = command.toLowerCase();

            if (command.equals("c")) {
                closetOpen = false;
            } else {
                processCommand(command);
            }

        }
        System.out.println("\nCloset Closed");

    }

    // EFFECTS: processes all the users inputs
    private void processCommand(String command) {
        if (command.equals("a")) {
            addClothing();
        } else if (command.equals("i")) {
            displayInventory();
        } else if (command.equals("o")) {
            outfitRelated();
        } else if (command.equals("d")) {
            displayCloset();
        } else if (command.equals("s")) {
            saveCloset();
        } else if (command.equals("l")) {
            loadCloset();
        } else {
            System.out.println("Selection not valid...");
        }
    }


    // MODIFIES: this
    // EFFECTS: creates new closet
    private void init() {
        closet = new Closet();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    // EFFECTS: gives commands to choose from
    private void displayCloset() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add clothes");
        System.out.println("\ti -> display inventory");
        System.out.println("\to -> outfit related commands");
        System.out.println("\ts -> save closet to file");
        System.out.println("\tl -> load closet from file");
        System.out.println("\td -> to return to menu");
        System.out.println("\tc -> to close closet");

    }

    // MODIFIES: this
    // EFFECTS: takes in a name, description and type
    private void addClothing() {
        System.out.println("Enter name of clothing:");
        name = input.next();

        System.out.println("Enter description of clothing");
        desc = input.next();

        System.out.println("Enter type of clothing: ");
        System.out.println("0 = InnerTop, 1 = OuterTop, 2 = InnerBottom, 3 = OuterBottom, 4 = Accessories");
        type = input.nextInt();

        if (type < 0 || type > 4) {
            System.out.println("Illegal Type...\n");
            displayCloset();
        } else {
            closet.addClothes(name, desc, type);
            System.out.println(name + " added to inventory");
            displayCloset();
        }
    }

    // EFFECTS: save closet to file
    private void saveCloset() {
        try {
            jsonWriter.open();
            jsonWriter.write(closet);
            jsonWriter.close();
            System.out.println("Closet saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads closet from file
    private void loadCloset() {
        try {
            closet = jsonReader.read();
            System.out.println("Loaded closet from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    // prints out the name of the items in inventory
    private void displayInventory() {
        String command = null;
        System.out.println("Displaying Inventory:");
        for (Clothing clothing : closet.getInventory().getClothes()) {
            System.out.println(clothing.getName() + "(" + clothing.getDescription() + ")");

        }
        System.out.println("Type d to return to menu");
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("d")) {
            displayCloset();
        }
    }

    // EFFECTS: prints out the name and description of all items in inventory without the menu
    private void displayInventoryForOutfit() {
        System.out.println("Displaying Inventory:");
        for (Clothing clothing : closet.getInventory().getClothes()) {
            System.out.println(" -" + clothing.getName() + "(" + clothing.getDescription() + ")");
        }
    }

    // EFFECTS: prints out the names of all summer outfits
    private void displaySummerOutfits() {
        String command = null;
        System.out.println("Displaying Summer Outfits:");
        for (Outfit summerOutfit : closet.getOutfitCollection().getSummerCollection()) {
            System.out.println(summerOutfit.getName());
        }
        System.out.println("Type d to return to menu");
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("d")) {
            displayCloset();
        }

    }

    // EFFECTS: prints out the name of all winter outfits
    private void displayWinterOutfits() {
        String command = null;
        System.out.println("Displaying Winter Outfits:");
        for (Outfit winterOutfit : closet.getOutfitCollection().getWinterCollection()) {
            System.out.println(winterOutfit.getName());
        }
        System.out.println("Type d to return to menu");
        command = input.next();
        command = command.toLowerCase();
        if (command.equals("d")) {
            displayCloset();
        }
    }

    // EFFECTS: takes another input,
    private void outfitRelated() {
        String command = null;
        System.out.println("Outfit Commands");
        System.out.println("\tcs -> create summer outfit");
        System.out.println("\tcw -> create winter outfit");
        System.out.println("\tds -> display summer outfits");
        System.out.println("\tdw -> display summer winters");

        command = input.next();
        if (command.equals("cs")) {
            createSummerOutfit();
        } else if (command.equals("cw")) {
            createWinterOutfit();
        } else if (command.equals("ds")) {
            displaySummerOutfits();
        } else if (command.equals("dw")) {
            displayWinterOutfits();
        }


    }



    // MODIFIES: this
    // EFFECTS: takes name of summer outfit and then displays inventory,
    //          user can add clothing only from inventory to outfit
    private void createSummerOutfit() {
        boolean creating = true;
        String command = null;
        System.out.println("Type name of outfit:");
        name = input.next();
        closet.addNewSummerOutfit(name);

        while (creating) {
            displayInventoryForOutfit();

            System.out.println("This is the current outfit:");
            displaySummerOutfit();
            System.out.println("Type name of clothing in inventory to add to outfit or d to return to menu");
            command = input.next();
            if (closet.isInInventory(command)) {
                closet.addToSummerOutfit(command);
                System.out.println("Added " + command + " to outfit");

            } else if (command.equals("d")) {
                creating = false;
                System.out.println("Added to Summer Outfits");
                displayCloset();

            } else {
                System.out.println("Misspelled name or does not exist in inventory");
            }

        }

    }

    // EFFECTS: prints out the current summer outfit
    private void displaySummerOutfit() {
        System.out.println(" -Inner top:" + closet.getRecentlyAddedSummerOutfit().getInnerTop().getName());
        System.out.println(" -Outer top:" + closet.getRecentlyAddedSummerOutfit().getOuterTop().getName());
        System.out.println(" -Inner bottom:" + closet.getRecentlyAddedSummerOutfit().getInnerBottom().getName());
        System.out.println(" -Outer bottom:" + closet.getRecentlyAddedSummerOutfit().getOuterBottom().getName());
        System.out.println(" -Accessories:" + closet.getRecentlyAddedSummerOutfit().getAccessories().getName());
    }

    // MODIFIES: this
    // EFFECTS: takes name of winter outfit and then displays inventory,
    //          user can add clothing only from inventory to outfit
    private void createWinterOutfit() {
        boolean creating = true;
        String command = null;
        System.out.println("Type name of outfit:");
        name = input.next();
        closet.addNewWinterOutfit(name);
        displayInventoryForOutfit();
        while (creating) {

            System.out.println("This is the current outfit:");
            displayWinterOutfit();
            System.out.println("Type name of clothing in inventory to add to outfit or d to return to menu");
            command = input.next();
            if (closet.isInInventory(command)) {
                closet.addToWinterOutfit(command);
                System.out.println("Added " + command + " to outfit");

            } else if (command.equals("d")) {
                creating = false;
                System.out.println("Added to Winter Outfits");
                displayCloset();

            } else {
                System.out.println("Misspelled name or does not exist in inventory");
            }

        }

    }

    // EFFECTS: print out the current winter outfit
    private void displayWinterOutfit() {
        System.out.println(" -Inner top:" + closet.getRecentlyAddedWinterOutfit().getInnerTop().getName());
        System.out.println(" -Outer top:" + closet.getRecentlyAddedWinterOutfit().getOuterTop().getName());
        System.out.println(" -Inner bottom:" + closet.getRecentlyAddedWinterOutfit().getInnerBottom().getName());
        System.out.println(" -Outer bottom:" + closet.getRecentlyAddedWinterOutfit().getOuterBottom().getName());
        System.out.println(" -Accessories:" + closet.getRecentlyAddedWinterOutfit().getAccessories().getName());
    }
}
