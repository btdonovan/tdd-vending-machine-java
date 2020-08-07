package us.navonod;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ItemSlotTest {

    @Test
    public void ItemSlotInitializesEmpty() {
        ItemSlot slot = new ItemSlot(100, 15);
        LinkedList<Item> expected = new LinkedList<>();
        assertEquals(expected, slot.getItems());
    }

    @Test
    public void CanAddItemsToSlot() {
        ItemSlot slot = new ItemSlot(100, 15);
        Item item = new Item("Sunchips", 100);
        slot.addItem(item, 4);
        LinkedList<Item> expected = new LinkedList<>();
        expected.add(new Item("Sunchips", 100));
        expected.add(new Item("Sunchips", 100));
        expected.add(new Item("Sunchips", 100));
        expected.add(new Item("Sunchips", 100));
        assertEquals(expected, slot.getItems());
    }

    @Test
    public void TwoSlotsWithTheSamePriceCapacityItemsAreEqual() {
        ItemSlot slot1 = new ItemSlot(100, 15);
        ItemSlot slot2 = new ItemSlot(100, 15);
        slot1.addItem(new Item("Sunchips", 100), 2);
        slot2.addItem(new Item("Sunchips", 100), 2);
        assertEquals(slot1, slot2);
    }

    @Test
    public void CanSetSlotPrice() {
        ItemSlot slot = new ItemSlot(100, 15);
        slot.setPrice(125);
        int expected = 125;
        assertEquals(expected, slot.getPrice());
    }

    @Test
    public void testEquals() {
        ItemSlot slot1 = new ItemSlot(100, 15);
        ItemSlot slot2 = new ItemSlot(100, 15);
        assertEquals(slot1, slot2);
        slot2.addItem(new Item("Sunchips", 100), 1);
        assertNotEquals(slot1, slot2);
    }

    @Test
    public void testHash() {
        ItemSlot slot1 = new ItemSlot(100, 15);
        ItemSlot slot2 = new ItemSlot(100, 15);
        assertEquals(slot1.hashCode(), slot2.hashCode());
    }

    @Test
    public void stringRepr() {
        ItemSlot slot1 = new ItemSlot(100, 15);

        String expectedEmpty = "ItemSlot{items=[], price=100, capacity=15}";
        assertEquals(expectedEmpty, slot1.toString());
        slot1.addItem(new Item("Sunchips", 100), 1);
        String expectedItems = "ItemSlot{items=[Item{itemName='Sunchips', value=100}], price=100, capacity=15}";
        assertEquals(expectedItems, slot1.toString());
    }


}
