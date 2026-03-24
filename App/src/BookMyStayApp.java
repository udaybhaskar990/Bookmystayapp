import java.util.*;

// Inventory class (read + write, but search will use only read methods)
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Expose all inventory (read-only usage expected)
    public Map<String, Integer> getAllAvailability() {
        return inventory;
    }
}

// Room Domain Model
class Room {
    private String type;
    private double price;
    private String amenities;

    public Room(String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }
}

// Search Service (STRICTLY READ-ONLY)
class RoomSearchService {

    public void searchAvailableRooms(RoomInventory inventory, List<Room> rooms) {

        System.out.println("\n--- Available Rooms ---");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getType());

            // Defensive check: only show available rooms
            if (available > 0) {
                System.out.println("Room Type: " + room.getType());
                System.out.println("Price: " + room.getPrice());
                System.out.println("Amenities: " + room.getAmenities());
                System.out.println("Available: " + available);
                System.out.println("---------------------------");
            }
        }
    }
}

// Main Class
 class UseCase4RoomSearch {

    public static void main(String[] args) {

        // Step 1: Setup Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Standard", 10);
        inventory.addRoomType("Deluxe", 0);   // unavailable
        inventory.addRoomType("Suite", 3);

        // Step 2: Setup Room Data (Domain Model)
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Standard", 2000, "WiFi, TV"));
        rooms.add(new Room("Deluxe", 3500, "WiFi, TV, AC"));
        rooms.add(new Room("Suite", 5000, "WiFi, TV, AC, Mini Bar"));

        // Step 3: Guest searches rooms
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, rooms);

        // Step 4: Prove inventory is unchanged
        System.out.println("\n--- Inventory After Search (Unchanged) ---");
        for (Map.Entry<String, Integer> entry : inventory.getAllAvailability().entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}