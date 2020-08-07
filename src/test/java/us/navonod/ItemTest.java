package us.navonod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemTest {

    @Test
    public void CanSeeItemName() {
        Item item = new Item("SunChips", 100);
        String expected = "SunChips";
        String actual = item.getItemName();
        assertEquals(expected, actual);
    }

    @Test
    public void CanSeeItemValue() {
        Item item = new Item("SunChips", 100);
        int expected = 100;
        int actual = item.getValue();
        assertEquals(expected, actual);
    }

    @Test
    public void TwoItemsWithSameNameAndValueAreEqual() {
        Item item1 = new Item("SunChips", 100);
        Item item2 = new Item("SunChips", 100);

        assertTrue(item1.equals(item2));
    }

    @Test
    public void TwoEqualItemsHaveTheSameHash() {
        Item item1 = new Item("SunChips", 100);
        Item item2 = new Item("SunChips", 100);
        assertEquals(item1.hashCode(), item2.hashCode());
    }

}
