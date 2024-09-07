package persistence;

import model.Closet;

import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Closet c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCloset() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCloset.json");
        try {
            Closet c = reader.read();
            assertEquals(0, c.numClothing());
            assertEquals(0, c.summerSize());
            assertEquals(0, c.winterSize());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCloset() {
        try {

            JsonReader reader = new JsonReader("./data/testReaderGeneralCloset.json");
            Closet c = reader.read();
            assertEquals(2, c.numClothing());
            assertEquals(1, c.summerSize());
            assertEquals(1, c.winterSize());

            checkClothing("shirt", "blue", 0, c.getClothingAtIndex(0));
            checkClothing("coat", "black", 1, c.getClothingAtIndex(1));
            checkOutfit("shirt", "coat",
                    "empty", "empty", "empty",
                    c.getOutfitCollection().getSummerCollection().get(0));
            checkOutfit("shirt", "empty",
                    "empty", "empty", "empty",
                    c.getOutfitCollection().getWinterCollection().get(0));



        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}