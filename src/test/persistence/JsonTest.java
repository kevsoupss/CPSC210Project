package persistence;

import model.Clothing;
import model.Outfit;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkClothing(String name, String description, int type, Clothing clothing) {
        assertEquals(name, clothing.getName());
        assertEquals(description, clothing.getDescription());
        assertEquals(type, clothing.getType());
    }

    protected void checkOutfit(String innerTop, String outerTop, String innerBottom, String outerBottom,
                               String accessories, Outfit outfit) {
        assertEquals(innerTop, outfit.getInnerTop().getName());
        assertEquals(outerTop, outfit.getOuterTop().getName());
        assertEquals(innerBottom, outfit.getInnerBottom().getName());
        assertEquals(outerBottom, outfit.getOuterBottom().getName());
        assertEquals(accessories, outfit.getAccessories().getName());
    }
}



