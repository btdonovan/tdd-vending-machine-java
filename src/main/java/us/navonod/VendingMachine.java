package us.navonod;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class VendingMachine {

    private HashMap<String, ItemSlot> itemSlots = new HashMap<>();
    private int machineBalance = 0;
    private int customerBalance = 0;
    private String message;


    public HashMap<String, ItemSlot> getItemSlots() {
        return itemSlots;
    }

    public int stockItem(Item item, int qty, boolean overflow) {
        int left = qty;
        // Find an empty slot with the same value or a slot with the same items. If items left and overflow, find next slot
        for (String key : itemSlots.keySet()) {
            if ((itemSlots.get(key).getPrice() == item.getValue()) && ((itemSlots.get(key).getItems().size() == 0) || (itemSlots.get(key).getItems().get(0) == item))) {
                left = itemSlots.get(key).addItem(item, left);
                if (!overflow) {
                    break;
                }
            }
        }
        return qty - left;
    }

    public String getMessage() {
        return message;
    }

    public int getMachineBalance() {
        return machineBalance;
    }

    public void setMachineBalance(int machineBalance) {
        this.machineBalance = machineBalance;
    }

    public void addSlot(ItemSlot itemSlot) {
        String[] codes = new String[100];
        String letters = "abcdefghij";
        String nums = "1234567890";
        int counter = 0;
        for (int i = 0; i < letters.length(); i++) {
            for (int j = 0; j < nums.length(); j++) {
                codes[counter] = letters.charAt(i) + "" + nums.charAt(j);
                counter += 1;
            }

        }
        for (String code : codes) {
            if (!this.itemSlots.containsKey(code)) {
                this.itemSlots.put(code, itemSlot);
                break;
            }
        }
    }

    public ArrayList<String> seeAvailable() {
        ArrayList<String> result = new ArrayList<>();
        for (String key : itemSlots.keySet()) {
            if (itemSlots.get(key).getItems().size() > 0) {
                double cents = itemSlots.get(key).getPrice();
                double dollars = cents / 100;
                String value = NumberFormat.getCurrencyInstance().format(dollars);
                result.add(key + " - " + itemSlots.get(key).getItems().getLast().getItemName() + " - " + value);
            }
        }

        Collections.sort(result, String.CASE_INSENSITIVE_ORDER);
        return result;
    }

    public int getCustomerBalance() {
        return customerBalance;
    }

    public void pay(int cents) {
        customerBalance += cents;
    }

    public String seeCustomerBalance() {
        double cents = customerBalance;
        double dollars = cents / 100;
        String result = NumberFormat.getCurrencyInstance().format(dollars);

        return result;
    }


    public Item buy(String code) {
        if (!itemSlots.containsKey(code)) {
            this.message = "Invalid code entered. Please try again.";
        }

        return new Item("null", 100);
    }
}
