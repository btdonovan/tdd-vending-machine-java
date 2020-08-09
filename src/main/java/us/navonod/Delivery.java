package us.navonod;

public class Delivery {
    private Item item;
    private int change;

    public Delivery(Item item, int change) {
        this.item = item;
        this.change = change;
    }

    public Item getItem() {
        return item;
    }

    public int getChange() {
        return change;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "item=" + item +
                ", change=" + change +
                '}';
    }
}
