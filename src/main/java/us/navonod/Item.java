package us.navonod;

import java.util.Objects;

public class Item {
    private String itemName;
    private int value;

    public Item(String itemName, int value) {
        this.itemName = itemName;
        this.value = value;
    }

    public String getItemName() {
        return itemName;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return getValue() == item.getValue() &&
                getItemName().equals(item.getItemName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getItemName(), getValue());
    }
}
