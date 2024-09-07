package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SummerOutfitTest {
    private Clothing bshirt;
    private Clothing rshirt;
    private Clothing coat;
    private Clothing underwear;
    private Clothing pants;
    private Inventory clothes;
    private Clothing ring;
    private SummerOutfit summerOutfit;
    private Outfit outfit;

    @BeforeEach
    public void setup(){
        bshirt = new Clothing("blue shirt", "blue with buttons", 0);
        rshirt = new Clothing("red shirt", "red with buttons", 0);
        coat = new Clothing("nice coat", "warm", 1);
        underwear = new Clothing("calvin kleins", "black", 2);
        pants = new Clothing("pants", "grey, long", 3);
        ring = new Clothing("diamond ring", "shiny", 4);
        clothes = new Inventory();
        summerOutfit = new SummerOutfit("comfy");
        outfit = new Outfit("any");
    }

    @Test
    public void testOutfit(){
        assertEquals(0, summerOutfit.getInnerTop().getType());
        assertEquals(1, summerOutfit.getOuterTop().getType());
        assertEquals(2, summerOutfit.getInnerBottom().getType());
        assertEquals(3, summerOutfit.getOuterBottom().getType());
        assertEquals(4, summerOutfit.getAccessories().getType());

    }

    @Test
    public void testAddToOutfit(){
        clothes.addToInventory(bshirt);
        summerOutfit.addToOutfit(bshirt);
        assertEquals(bshirt, summerOutfit.getInnerTop());
        summerOutfit.addToOutfit(rshirt);
        assertEquals(rshirt, summerOutfit.getInnerTop());
        clothes.addToInventory(rshirt);
        summerOutfit.addToOutfit(rshirt);
        assertEquals(rshirt, summerOutfit.getInnerTop());
        clothes.addToInventory(coat);
        clothes.addToInventory(underwear);
        summerOutfit.addToOutfit(coat);
        summerOutfit.addToOutfit(underwear);
        assertEquals(underwear, summerOutfit.getInnerBottom());
        assertEquals(coat, summerOutfit.getOuterTop());
        outfit.addToOutfit(coat);
        assertEquals(coat, outfit.getOuterTop());
    }
}

