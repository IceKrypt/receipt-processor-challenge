package driver;

import model.Item;
import model.Receipt;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import static java.lang.Math.floor;
import static java.lang.Math.round;

public class ReceiptProcessorDriver {
    HashMap<String, Receipt> receipts = new HashMap<>();
    public static void main(String[] args) {
    }

    public ReceiptProcessorDriver() {
    }

    public String generateId(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipts.put(id, receipt);
        return id;
    }
    public Receipt getReceiptById(String id) {
        return receipts.get(id);
    }
    public int getPointsById(String id) {
        Receipt receipt =  getReceiptById(id);
        if(receipt != null) {
            return calculatePoints(receipt);
        }
        return 0;
    }

    public int calculatePoints(Receipt receipt) {
        int points = 0;
        // One point for every alphanumeric character in the retailer name.
        String retailer = receipt.getRetailer().strip();
        if(!retailer.isEmpty()) {
            for (int i = 0; i < retailer.length(); i++) {
                char c = retailer.charAt(i);
                if (Character.isLetterOrDigit(c)) {
                    points++;
                }
            }
        }
        // 50 points if the total is a round dollar amount with no cents.
        if(floor(receipt.getTotal()) == receipt.getTotal()) {
            points += 50;
        }

        // 25 points if the total is a multiple of 0.25.
        if(receipt.getTotal() % .25 == 0) {
            points += 25;
        }
        // * 5 points for every two items on the receipt.
        if(receipt.getItems().size() > 1 ){
            points += (5 * receipt.getItems().size()/2);
        }

        // If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2 and round up to the nearest integer. The result is the number of points earned.
        List<Item> items = receipt.getItems();
        for (Item item : items) {
            if (item.getShortDescription().trim().length() % 3 == 0) {
                points += (int) round(item.getPrice() * .2);
            }

        }
        // 6 points if the day in the purchase date is odd.
        if(receipt.getPurchaseDate().getDayOfMonth() % 2 != 0){
            points += 6;
        }
        // 10 points if the time of purchase is after 2:00pm and before 4:00pm.
        LocalTime startTime = LocalTime.parse("14:00");
        LocalTime endTime = LocalTime.parse("16:00");
        if(receipt.getPurchaseTime().isAfter(startTime) && receipt.getPurchaseTime().isBefore(endTime)){
            points += 10;
        }

        return points;
    }
}
