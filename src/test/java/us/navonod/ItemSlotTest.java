package us.navonod;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
