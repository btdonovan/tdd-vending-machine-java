package us.navonod;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendingMachineTest {

    // As an administrator, I want to be able to see what items are currently in the vending machine so that I know what
    // we have in stock.

    @Test
    public void VendingMachineIsInitializedWithEmptyStock() {
        VendingMachine vendingMachine = new VendingMachine();
        HashMap<String, ItemSlot> expected = new HashMap<>();
        HashMap<String, ItemSlot> actual = vendingMachine.getItemSlots();

        assertEquals(expected, actual);
    }

    @Test
    public void AddingSlotAssignsItNextAvailableCode() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(75, 15));


    }

    // As an administrator, I want to be able to add items to the vending machine so that it never runs out of snacks.
    @Test
    public void CanAddItemsToStock() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(100, 15));
        vendingMachine.stockItem(new Item("SunChips", 100), 4, false);
        ArrayList<Item> expected = new ArrayList<>();
        expected.add(new Item("SunChips", 100));
        expected.add(new Item("SunChips", 100));
        expected.add(new Item("SunChips", 100));
        expected.add(new Item("SunChips", 100));
        assertEquals(expected, vendingMachine.getItemSlots().get("a1").getItems());
    }

    @Test
    public void CanOverFlowItemsWhenStocking() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(100, 15));
        vendingMachine.addSlot(new ItemSlot(100, 15));
        ArrayList<Item> expected1 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            expected1.add(new Item("SunChips", 100));
        }
        ArrayList<Item> expected2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            expected2.add(new Item("SunChips", 100));
        }
        vendingMachine.stockItem(new Item("SunChips", 100), 20, true);
        assertEquals(expected1, vendingMachine.getItemSlots().get("a1").getItems());
        assertEquals(expected2, vendingMachine.getItemSlots().get("a2").getItems());
    }

    @Test
    public void CanSeeCurrentMachineBalance() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setMachineBalance(2500);
        assertEquals(2500, vendingMachine.getMachineBalance());
    }

    @Test
    public void CustomerCanSeeCurrentlyAvailableItems() {
        VendingMachine vendingMachine = new VendingMachine();
        for (int i = 0; i < 5; i++) {
            vendingMachine.addSlot(new ItemSlot(75, 5));
        }
        vendingMachine.stockItem(new Item("SunChips", 75), 5, false);
        vendingMachine.stockItem(new Item("Snickers", 75), 5, false);
        vendingMachine.stockItem(new Item("MilkyWay", 75), 5, false);
        vendingMachine.stockItem(new Item("Doritos", 75), 5, false);
        vendingMachine.stockItem(new Item("Twix", 75), 5, false);

        ArrayList<String> expected = new ArrayList<>();
        expected.add("a1 - SunChips - $0.75");
        expected.add("a2 - Snickers - $0.75");
        expected.add("a3 - MilkyWay - $0.75");
        expected.add("a4 - Doritos - $0.75");
        expected.add("a5 - Twix - $0.75");
        assertEquals(expected, vendingMachine.seeAvailable());
    }

    @Test
    public void CustomerCanSeeHowMuchTheyHaveDeposited() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.pay(200);
        String expected = "$2.00";
        String actual = vendingMachine.seeCustomerBalance();
        assertEquals(expected, actual);
    }
}
