package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class ClosetTest {
    private Closet closet;
    private Clothing rshirt;
    private Clothing bshirt;
    private Clothing coat;

    @BeforeEach
    public void setup() {
        closet = new Closet();
    }

    @Test
    public void testCloset() {
        assertTrue(closet.getOutfitCollection().isSummerEmpty());
        assertTrue(closet.getOutfitCollection().isWinterEmpty());
        assertTrue(closet.getInventory().isEmpty());
    }

    @Test
    public void testAddClothes() {
        closet.addClothes("shirt", "blue", 0);
        assertEquals(closet.getRecentlyAddedClothes(), closet.getClothingAtIndex(0));
        closet.addClothes("hi", "red", 2);
        assertEquals(closet.getRecentlyAddedClothes(), closet.getClothingAtIndex(1));
        closet.addClothes("1", "yellow" , 1);
        assertEquals(closet.getRecentlyAddedClothes(), closet.getClothingAtIndex(1));

    }

    @Test
    public void testAddNewSummerOutfit() {
        closet.addNewSummerOutfit("cozy");
        assertEquals(closet.getRecentlyAddedSummerOutfit(), closet.getOutfitCollection().getSummerCollection().get(0));
    }

    @Test
    public void testAddNewWinterOutfit() {
        closet.addNewWinterOutfit("cozy");
        assertEquals(closet.getRecentlyAddedWinterOutfit(), closet.getOutfitCollection().getWinterCollection().get(0));
    }

    @Test
    public void testEditSummerOutfit(){
        rshirt = new Clothing("rshirt", "red", 0);
        closet.addNewSummerOutfit("cozy");
        closet.editSummerOutfit(rshirt);
        assertEquals(rshirt, closet.getOutfitInSummerCollection(0).getInnerTop());

    }
    @Test
    public void testEditWinterOutfit(){
        rshirt = new Clothing("rshirt", "red", 0);
        closet.addNewWinterOutfit("cozy");
        closet.editWinterOutfit(rshirt);
        assertEquals(rshirt, closet.getOutfitInWinterCollection(0).getInnerTop());

    }

    @Test
    public void testisInInventory() {
        rshirt = new Clothing("shirt", "red", 0);
        closet.addToInventory(rshirt);
        assertTrue(closet.isInInventory("shirt"));
        assertFalse(closet.isInInventory("blue"));
    }

    @Test
    public void testFindClothing() {
        bshirt = new Clothing("bshirt", "red", 0);
        rshirt = new Clothing("shirt", "red", 0);
        coat = new Clothing("coat", "red", 1);

        closet.addToInventory(rshirt);
        closet.addToInventory(bshirt);
        closet.addToInventory(coat);
        assertEquals(rshirt, closet.findClothing("shirt"));
        assertEquals(coat, closet.findClothing("coat"));
    }



}
