import java.util.*;

// Reservation (Booking Request)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Room Inventory Service
class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceAvailability(String type) {
        int current = inventory.getOrDefault(type, 0);
        if (current > 0) {
            inventory.put(type, current - 1);
        }
    }

    public void displayInventory() {
        System.out.println("\n--- Current Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}

// Booking Request Queue (FIFO)
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
        System.out.println("Request Added: " + r.getGuestName() + " -> " + r.getRoomType());
    }

    public Reservation getNextRequest() {
        return queue.poll(); // FIFO removal
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Booking Service (Allocation Engine)
class BookingService {

    private RoomInventory inventory;

    // Track all allocated room IDs globally
    private Set<String> allocatedRoomIds = new HashSet<>();

    // Map room type → allocated room IDs
    private Map<String, Set<String>> roomAllocations = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Process queue and allocate rooms
    public void processRequests(BookingRequestQueue queue) {

        System.out.println("\n--- Processing Booking Requests ---");

        while (!queue.isEmpty()) {

            Reservation request = queue.getNextRequest();
            String type = request.getRoomType();

            // Step 1: Check availability
            if (inventory.getAvailability(type) <= 0) {
                System.out.println("Booking Failed for " + request.getGuestName() +
                        " (No " + type + " rooms available)");
                continue;
            }

            // Step 2: Generate unique room ID
            String roomId = generateRoomId(type);

            // Step 3: Ensure uniqueness (extra safety)
            while (allocatedRoomIds.contains(roomId)) {
                roomId = generateRoomId(type);
            }

            // Step 4: Allocate room
            allocatedRoomIds.add(roomId);

            roomAllocations
                    .computeIfAbsent(type, k -> new HashSet<>())
                    .add(roomId);

            // Step 5: Update inventory immediately
            inventory.reduceAvailability(type);

            // Step 6: Confirm booking
            System.out.println("Booking Confirmed: " + request.getGuestName() +
                    " -> " + type + " Room | ID: " + roomId);
        }
    }

    // Generate unique room ID
    private String generateRoomId(String type) {
        return type.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 6);
    }

    // Display allocations
    public void displayAllocations() {
        System.out.println("\n--- Room Allocations ---");
        for (Map.Entry<String, Set<String>> entry : roomAllocations.entrySet()) {
            System.out.println(entry.getKey() + " Rooms: " + entry.getValue());
        }
    }
}

// Main Class
 class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        // Step 1: Setup inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Standard", 2);
        inventory.addRoomType("Deluxe", 1);
        inventory.addRoomType("Suite", 1);

        // Step 2: Setup booking queue
        BookingRequestQueue queue = new BookingRequestQueue();
        queue.addRequest(new Reservation("Alice", "Standard"));
        queue.addRequest(new Reservation("Bob", "Standard"));
        queue.addRequest(new Reservation("Charlie", "Standard")); // should fail
        queue.addRequest(new Reservation("Diana", "Deluxe"));
        queue.addRequest(new Reservation("Eve", "Suite"));

        // Step 3: Process bookings
        BookingService service = new BookingService(inventory);
        service.processRequests(queue);

        // Step 4: Show final allocations
        service.displayAllocations();

        // Step 5: Show updated inventory
        inventory.displayInventory();
    }
}