package us.navonod;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    public void CustomerCanDepositMoney() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.pay(200);
        int expected = 200;
        assertEquals(expected, vendingMachine.getCustomerBalance());
    }

    @Test
    public void CustomerCanSeeHowMuchTheyHaveDeposited() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.pay(200);
        String expected = "$2.00";
        String actual = vendingMachine.seeCustomerBalance();
        assertEquals(expected, actual);
    }

    @Test
    public void CustomerIsNotifiedIfTheyEnterANonExistentCode() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(100, 15));
        vendingMachine.buy("a2");
        assertEquals("Invalid code entered. Please make another selection.", vendingMachine.getMessage());
    }

    @Test
    public void CustomerIsNotifiedIfTheySelectASoldOutItem() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(100, 15));
        vendingMachine.buy("a1");
        assertEquals("Sold Out! Please make another selection.", vendingMachine.getMessage());
    }

    @Test
    public void CustomerIsNotifiedIfTheySelectAnItemThatCostsMoreThanTheyHavePaid() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(100, 15));
        vendingMachine.stockItem(new Item("SunChips", 100), 4, false);
        vendingMachine.pay(75);
        vendingMachine.buy("a1");
        assertEquals("Please deposit an additional $0.25", vendingMachine.getMessage());
    }

    @Test
    public void CustomerReceivesItemAndAnyChangeWhenSelectingAnItemTheyHaveDepositedEnoughFor() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(75, 15));
        vendingMachine.stockItem(new Item("Sunchips", 75), 4, false);
        vendingMachine.pay(100);
        Delivery expected = new Delivery(new Item("Sunchips", 75), 25);
        Delivery actual = vendingMachine.buy("a1");
        assertEquals(expected.getChange(), actual.getChange());
        assertEquals(expected.getItem(), actual.getItem());
    }

    @Test
    public void CustomerCanCancelAndReceiveTheirDepositBack() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.pay(100);
        Delivery expected = new Delivery(null, 100);
        Delivery actual = vendingMachine.cancel();
        assertEquals(expected.getChange(), actual.getChange());
        assertNull(actual.getItem());
    }

    @Test
    public void WhenItemIsPurchasedAvailableStockDecreases() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(75, 15));
        vendingMachine.stockItem(new Item("Sunchips", 75), 4, false);
        assertEquals(4, vendingMachine.getItemSlots().get("a1").getItems().size());
        vendingMachine.pay(100);
        vendingMachine.buy("a1");
        assertEquals(3, vendingMachine.getItemSlots().get("a1").getItems().size());
    }

    @Test
    public void WhenACustomerReceivesChangeTheCustomerDepositIsZero() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(75, 15));
        vendingMachine.stockItem(new Item("Sunchips", 75), 4, false);
        vendingMachine.pay(100);
        assertEquals(100, vendingMachine.getCustomerBalance());
        vendingMachine.buy("a1");
        assertEquals(0, vendingMachine.getCustomerBalance());
    }

    @Test
    public void MachineBalancesReflectCompletedPurchases() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setChangeBalance(1000);
        vendingMachine.setMachineBalance(1000);
        vendingMachine.addSlot(new ItemSlot(75, 15));
        vendingMachine.stockItem(new Item("Sunchips", 75), 4, false);
        vendingMachine.pay(100);
        Delivery delivery = vendingMachine.buy("a1");
        assertEquals(1100, vendingMachine.getMachineBalance());
        assertEquals(975, vendingMachine.getChangeBalance());
        assertEquals(2075, vendingMachine.getChangeBalance() + vendingMachine.getMachineBalance());
    }

    @Test
    public void CanSetMachineChangeBalance() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setChangeBalance(25);
        assertEquals(25, vendingMachine.getChangeBalance());
    }


    @Test
    public void CustomerCanSeeIfMachineCanMakeChange() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.setChangeBalance(20);
        vendingMachine.addSlot(new ItemSlot(75, 15));
        vendingMachine.stockItem(new Item("Sunchips", 75), 4, false);
        vendingMachine.pay(100);
        vendingMachine.buy("a1");
        String expected = "Insufficient Coins Available To Make Proper Change. Cancel or choose another item.";
        assertEquals(expected, vendingMachine.getMessage());
    }

    @Test
    public void CustomerIsNotNotifiedToCollectChangeIfTheyDepositExactItemCost() {
        VendingMachine vendingMachine = new VendingMachine();
        vendingMachine.addSlot(new ItemSlot(75, 15));
        vendingMachine.stockItem(new Item("Sunchips", 75), 4, false);
        vendingMachine.pay(75);
        vendingMachine.buy("a1");
        String expected = "Please collect your item.";
        assertEquals(expected, vendingMachine.getMessage());
    }
}
