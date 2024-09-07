package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;

    @BeforeEach
    public void runBefore() {
        e = new Event("Clothes added");
    }

    @Test
    public void testEvent() {
        assertEquals("Clothes added", e.getDescription());
    }

    @Test
    public void testToString() {
        assertEquals( "Clothes added", e.toString());
    }
}
