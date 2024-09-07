package model;

import java.util.ArrayList;
import java.util.List;

// An inventory of the clothes that the user has added
public class Inventory {
    protected List<Clothing> clothes;

    // EFFECTS: Creates a new empty inventory of clothes with template types so
    //          clothes can be added to the correct spot
    public Inventory() {
        this.clothes = new ArrayList<>();

    }

    // MODIFIES: this
    // EFFECTS: Adds a piece of clothing to the inventory to closest similar type of clothing
    public void addToInventory(Clothing clothing) {
        this.clothes.add(findClosest(clothing), clothing);
    }



    // EFFECTS: if clothes matches the type of currrentClothing, return true, else return false
    public Boolean compareType(Clothing clothes, Clothing currentClothing) {
        return clothes.getType() == currentClothing.getType();
    }

    // EFFECTS: finds the closest matching type clothing
    public int findClosest(Clothing clothing) {
        int temp = 0;
        for (Clothing currentClothing: clothes) {
            if (compareType(currentClothing,clothing)) {
                return (clothes.indexOf(currentClothing));
            }
        }

        for (Clothing currentClothing: clothes) {
            if (currentClothing.getType() < clothing.getType()) {
                temp = clothes.indexOf(currentClothing) + 1;
            }
        }
        return temp;
    }


    public Boolean isEmpty() {
        return clothes.isEmpty();
    }

    // EFFECTS: adds clothing to the list of clothing
    public void add(Clothing clothes) {
        this.clothes.add(clothes);
    }

    // finds clothing and returns the index of it in the list
    public int getIndexOf(Clothing clothes) {
        return this.clothes.indexOf(clothes);
    }

    public List<Clothing> getClothes() {
        return clothes;
    }
}
