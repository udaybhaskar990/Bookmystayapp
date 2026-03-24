import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    // Centralized inventory storage
    private Map<String, Integer> inventory;

    // Constructor to initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();
    }

    // Register a new room type
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Error: Not enough rooms available for " + roomType);
            return;
        }

        inventory.put(roomType, updated);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

 class UseCase3InventorySetup {

    public static void main(String[] args) {

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types
        inventory.addRoomType("Standard", 10);
        inventory.addRoomType("Deluxe", 5);
        inventory.addRoomType("Suite", 2);

        // Display initial inventory
        inventory.displayInventory();

        // Simulate booking
        System.out.println("\nBooking 2 Deluxe rooms...");
        inventory.updateAvailability("Deluxe", -2);

        // Simulate cancellation
        System.out.println("Cancelling 1 Standard room...");
        inventory.updateAvailability("Standard", 1);

        // Check availability
        System.out.println("\nAvailable Suites: " + inventory.getAvailability("Suite"));

        // Display updated inventory
        inventory.displayInventory();
    }
}