package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class InventoryTest {
    private Clothing bshirt;
    private Clothing rshirt;
    private Clothing coat;
    private Clothing underwear;
    private Clothing pants;
    private Inventory clothes;
    private Clothing ring;

    @BeforeEach
    public void setup(){
        bshirt = new Clothing("blue shirt", "blue with buttons", 0);
        rshirt = new Clothing("red shirt", "red with buttons", 0);
        coat = new Clothing("nice coat", "warm", 1);
        underwear = new Clothing("calvin kleins", "black", 2);
        pants = new Clothing("pants", "grey, long", 3);
        ring = new Clothing("diamond ring", "shiny", 4);
        clothes = new Inventory();
    }

    @Test
    public void testInventory(){
        assertTrue(clothes.isEmpty());
    }

    @Test
    public void testCompareType(){
        assertFalse(clothes.compareType(bshirt, coat));
        assertTrue(clothes.compareType(bshirt,rshirt));
    }

    @Test
    public void testFindClosest(){
        clothes.add(bshirt);
        assertEquals(0, clothes.findClosest(rshirt));
        clothes.add(underwear);
        assertEquals(1, clothes.findClosest(coat));
        assertEquals(2, clothes.findClosest(pants));
        clothes.add(pants);
        assertEquals(0, clothes.findClosest(rshirt));
        assertEquals(3, clothes.findClosest(ring));
    }

    @Test
    public void testAddToInventory(){
        clothes.addToInventory(bshirt);
        assertEquals(0, clothes.getIndexOf(bshirt));
        clothes.addToInventory(rshirt);
        assertEquals(0, clothes.getIndexOf(rshirt));
        clothes.addToInventory(pants);
        assertEquals(2, clothes.getIndexOf(pants));
        clothes.addToInventory(underwear);
        assertEquals(2, clothes.getIndexOf(underwear));
        assertEquals(3, clothes.getIndexOf(pants));
        clothes.addToInventory(ring);
        assertEquals(0, clothes.getIndexOf(rshirt));
        assertEquals(1, clothes.getIndexOf(bshirt));
        assertEquals(2, clothes.getIndexOf(underwear));
        assertEquals(3, clothes.getIndexOf(pants));
        assertEquals(4, clothes.getIndexOf(ring));
        clothes.addToInventory(coat);
        assertEquals(2, clothes.getIndexOf(coat));
    }


}
