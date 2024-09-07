package persistence;

import model.Closet;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Closet c = new Closet();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCloset() {
        try {
            Closet c = new Closet();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCloset.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCloset.json");
            c = reader.read();
            assertEquals(0, c.numClothing());
            assertEquals(0, c.summerSize());
            assertEquals(0, c.winterSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCloset() {
        try {
            Closet c = new Closet();
            c.addClothes("shirt", "blue", 0);
            c.addClothes("coat", "black", 1);
            c.addNewSummerOutfit("warm");
            c.addToSummerOutfit("shirt");
            c.addToSummerOutfit("coat");
            c.addNewWinterOutfit("cold");
            c.addToWinterOutfit("shirt");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCloset.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralCloset.json");
            c = reader.read();
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
