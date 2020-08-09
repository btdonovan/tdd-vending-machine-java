package us.navonod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeliveryTest {

    @Test
    public void CanCreateItemAndChangeDelivery() {
        Delivery delivery = new Delivery(new Item("Sunchips", 75), 25);
        Item expectedItem = new Item("Sunchips", 75);
        int expectedChange = 25;
        assertEquals(expectedItem, delivery.getItem());
        assertEquals(expectedChange, delivery.getChange());
    }

    @Test
    public void DeliveryToString() {
        Delivery delivery = new Delivery(new Item("Sunchips", 75), 25);
        String expected = "Delivery{item=Item{itemName='Sunchips', value=75}, change=25}";
        assertEquals(expected, delivery.toString());
    }
    
}
