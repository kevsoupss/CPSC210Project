package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClothingTest {

    private Clothing bshirt;

    @BeforeEach
    public void setup(){
        bshirt = new Clothing("shirt", "blue",0);
    }

    @Test
    public void testClothing(){
        assertEquals("shirt", bshirt.getName());
        assertEquals("blue", bshirt.getDescription());
        assertEquals(0, bshirt.getType());

    }

    @Test
    public void testEditName(){
        assertEquals("shirt", bshirt.getName());
        bshirt.editName("bshirt");
        assertEquals("bshirt", bshirt.getName());
    }

    @Test
    public void testEditDescription(){
        assertEquals("blue", bshirt.getDescription());
        bshirt.editDescription("buttons");
        assertEquals("buttons", bshirt.getDescription());
    }
}
