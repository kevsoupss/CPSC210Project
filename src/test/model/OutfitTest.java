package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OutfitTest {
    private Clothing bshirt;
    private Clothing rshirt;
    private Clothing coat;
    private Clothing underwear;
    private Clothing pants;
    private Inventory clothes;
    private Clothing ring;
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
        outfit = new Outfit("Comfortable Fit");
    }

    @Test
    public void testOutfit(){
        assertEquals("Comfortable Fit", outfit.getName());
        assertEquals(0, outfit.getInnerTop().getType());
        assertEquals(1, outfit.getOuterTop().getType());
        assertEquals(2, outfit.getInnerBottom().getType());
        assertEquals(3, outfit.getOuterBottom().getType());
        assertEquals(4, outfit.getAccessories().getType());

    }

    @Test
    public void testAddToOutfit(){
        clothes.addToInventory(bshirt);
        outfit.addToOutfit(bshirt);
        assertEquals(bshirt, outfit.getInnerTop());
        outfit.addToOutfit(rshirt);
        assertEquals(rshirt, outfit.getInnerTop());
        clothes.addToInventory(rshirt);
        outfit.addToOutfit(rshirt);
        assertEquals(rshirt, outfit.getInnerTop());
        clothes.addToInventory(coat);
        clothes.addToInventory(underwear);
        outfit.addToOutfit(coat);
        outfit.addToOutfit(underwear);
        assertEquals(underwear, outfit.getInnerBottom());
        assertEquals(coat, outfit.getOuterTop());
        clothes.addToInventory(ring);
        outfit.addToOutfit(ring);
        assertEquals(ring, outfit.getAccessories());
    }
}
