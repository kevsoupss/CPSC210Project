package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class OutfitCollectionTest {

    private Clothing bshirt;
    private Clothing rshirt;
    private Clothing coat;
    private Clothing underwear;
    private Clothing pants;
    private Inventory clothes;
    private Clothing ring;
    private Clothing hoodie;
    private SummerOutfit summerOutfit1;
    private SummerOutfit summerOutfit2;
    private WinterOutfit winterOutfit1;
    private WinterOutfit winterOutfit2;
    private OutfitCollection outfitCollection;



    @BeforeEach
    public void setup(){
        bshirt = new Clothing("blue shirt", "blue with buttons", 0);
        rshirt = new Clothing("red shirt", "red with buttons", 0);
        coat = new Clothing("nice coat", "warm", 1);
        hoodie = new Clothing("hoodie", "black", 1);
        underwear = new Clothing("calvin kleins", "black", 2);
        pants = new Clothing("pants", "grey, long", 3);
        ring = new Clothing("diamond ring", "shiny", 4);
        clothes = new Inventory();
        summerOutfit1 = new SummerOutfit("comfortable fit");
        summerOutfit2 = new SummerOutfit("breezy fit");
        winterOutfit1 = new WinterOutfit("warm");
        winterOutfit2 = new WinterOutfit("cozy fit");
        outfitCollection = new OutfitCollection();

        clothes.addToInventory(bshirt);
        clothes.addToInventory(rshirt);
        clothes.addToInventory(coat);
        clothes.addToInventory(underwear);
        clothes.addToInventory(pants);
        clothes.addToInventory(hoodie);

        summerOutfit1.addToOutfit(bshirt);
        summerOutfit1.addToOutfit(underwear);
        summerOutfit1.addToOutfit(pants);

        summerOutfit2.addToOutfit(rshirt);
        summerOutfit2.addToOutfit(pants);

        winterOutfit1.addToOutfit(rshirt);
        winterOutfit1.addToOutfit(coat);
        winterOutfit2.addToOutfit(pants);

        winterOutfit2.addToOutfit(rshirt);
        winterOutfit2.addToOutfit(hoodie);
        winterOutfit2.addToOutfit(pants);


    }

    @Test
    public void testOutfitCollection(){
        assertTrue(outfitCollection.getSummerCollection().isEmpty());
        assertTrue(outfitCollection.getWinterCollection().isEmpty());
    }


    @Test
    public void testAddToSummerCollection(){
        outfitCollection.addToSummerCollection(summerOutfit1);
        assertEquals(summerOutfit1, outfitCollection.getSummerCollection().get(0));
        outfitCollection.addToWinterCollection(winterOutfit1);
        assertEquals(winterOutfit1, outfitCollection.getWinterCollection().get(0));
        outfitCollection.addToSummerCollection(summerOutfit2);
        assertEquals(summerOutfit2, outfitCollection.getSummerCollection().get(1));

    }
}
