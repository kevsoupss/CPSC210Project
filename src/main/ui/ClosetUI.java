package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;

import model.Closet;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;
import model.Event;
import model.EventLog;


// Runs the UI for Closet
public class ClosetUI extends JFrame implements WindowListener {
    public static final int CLOSET_TAB_INDEX = 0;
    public static final int INVENTORY_TAB_INDEX = 1;
    public static final int ADD_CLOTHING_TAB_INDEX = 2;
    public static final int SUMMER_OUTFITS_TAB_INDEX = 3;
    public static final int WINTER_OUTFITS_TAB_INDEX = 4;
    public static final int ADD_OUTFIT_TAB_INDEX = 5;
    private static final String JSON_STORE = "./data/closetUI.json";

    public static final int WIDTH = 900;
    public static final int HEIGHT = 600;
    private Closet closet;
    private JTabbedPane sidebar;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JPanel homeTab;
    private JPanel inventoryTab;
    private JPanel addClothingTab;
    private JPanel summerOutfitsTab;
    private JPanel winterOutfitsTab;
    private JPanel addOutfitTab;

    public static void main(String[] args) {
        new ClosetUI();
    }

    // MODIFIES: this
    // EFFECTS: creates ClosetUI, loads Closet app, displays sidebars and tabs
    public ClosetUI() {
        super("My Closet");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addWindowListener(this);

        closet = new Closet();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.TOP);

        loadTabs();
        add(sidebar);

        setVisible(true);

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }




    public void windowActivated(WindowEvent arg0) {
    }


    public void windowClosed(WindowEvent arg0) {
    }

    public void windowClosing(WindowEvent arg0) {
        printLog(EventLog.getInstance());

    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString());
        }

    }


    public void windowDeactivated(WindowEvent arg0) {
    }


    public void windowDeiconified(WindowEvent arg0) {
    }


    public void windowIconified(WindowEvent arg0) {
    }


    public void windowOpened(WindowEvent arg0) {
    }


    // EFFECTS: returns Closet object controlled by this UI;
    public Closet getCloset() {
        return closet;
    }

    // MODIFIES: this
    // EFFECTS: loads closet from file
    public String loadCloset() {
        try {
            closet = jsonReader.read();
            getInventoryTab().updateInventory();
            getAddOutfitTab().updateInventory();
            getSummerOutfitTab().updateSummerOutfit();
            getWinterOutfitTab().updateWinterOutfit();
            return ("Closet Successfully Loaded");
        } catch (IOException e) {
            return ("Unable to find closet");
        }
    }

    // EFFECTS: save closet to file
    public String saveCloset() {
        try {
            jsonWriter.open();
            jsonWriter.write(closet);
            jsonWriter.close();
            return ("Closet Saved To File");
        } catch (FileNotFoundException e) {
            return ("Unable to write to file ");
        }
    }

    // MODIFIES: this
    //EFFECTS:adds home tab, inventory tab, addClothing tab, summerOutfit tab, winterOutfit tab and addOutfit tab to UI
    private void loadTabs() {
        homeTab = new HomeTab(this);
        inventoryTab = new InventoryTab(this);
        addClothingTab = new AddClothingTab(this);
        summerOutfitsTab = new SummerOutfitsTab(this);
        winterOutfitsTab = new WinterOutfitsTab(this);
        addOutfitTab = new AddOutfitTab(this);

        sidebar.add(homeTab, CLOSET_TAB_INDEX);
        sidebar.setTitleAt(CLOSET_TAB_INDEX, "Closet");
        sidebar.add(inventoryTab, INVENTORY_TAB_INDEX);
        sidebar.setTitleAt(INVENTORY_TAB_INDEX, "Inventory");
        sidebar.add(addClothingTab, ADD_CLOTHING_TAB_INDEX);
        sidebar.setTitleAt(ADD_CLOTHING_TAB_INDEX, "Add Clothing");
        sidebar.add(summerOutfitsTab, SUMMER_OUTFITS_TAB_INDEX);
        sidebar.setTitleAt(SUMMER_OUTFITS_TAB_INDEX, "Summer Outfits");
        sidebar.add(winterOutfitsTab, WINTER_OUTFITS_TAB_INDEX);
        sidebar.setTitleAt(WINTER_OUTFITS_TAB_INDEX, "Winter Outfits");
        sidebar.add(addOutfitTab, ADD_OUTFIT_TAB_INDEX);
        sidebar.setTitleAt(ADD_OUTFIT_TAB_INDEX, "Add Outfit");
    }

    // EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    public InventoryTab getInventoryTab() {
        return (InventoryTab) inventoryTab;
    }

    public AddOutfitTab getAddOutfitTab() {
        return (AddOutfitTab) addOutfitTab;
    }

    public SummerOutfitsTab getSummerOutfitTab() {
        return (SummerOutfitsTab) summerOutfitsTab;
    }

    public WinterOutfitsTab getWinterOutfitTab() {
        return (WinterOutfitsTab) winterOutfitsTab;
    }

}
