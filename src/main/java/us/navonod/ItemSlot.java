package us.navonod;

import java.util.LinkedList;
import java.util.Objects;

public class ItemSlot {
    private LinkedList<Item> items = new LinkedList<>();
    private int price;
    private int capacity;

    public ItemSlot(int price, int capacity) {
        this.price = price;
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public int getCapacity() {
        return capacity;
    }

    public int addItem(Item item, int quantity) {
        int added = 0;
        for (int i = 0; i < quantity; i++) {
            if (items.size() < capacity) {
                items.push(item);
                added += 1;
            }
        }
        return quantity - added;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemSlot itemSlot = (ItemSlot) o;
        return getPrice() == itemSlot.getPrice() &&
                getCapacity() == itemSlot.getCapacity() &&
                getItems().equals(itemSlot.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItems(), getPrice(), getCapacity());
    }

    @Override
    public String toString() {
        return "ItemSlot{" +
                "items=" + items +
                ", price=" + price +
                ", capacity=" + capacity +
                '}';
    }
}
