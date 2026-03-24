// Version 2.1 - Refactored Implementation

abstract class Room {
    private String roomType;
    private int numberOfBeds;
    private double size;
    private double pricePerNight;

    // Constructor
    public Room(String roomType, int numberOfBeds, double size, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.size = size;
        this.pricePerNight = pricePerNight;
    }

    // Getters (Encapsulation)
    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSize() {
        return size;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Common display method (can be overridden if needed)
    public void displayDetails() {
        System.out.println("Room Type       : " + roomType);
        System.out.println("Beds            : " + numberOfBeds);
        System.out.println("Size (sq.ft)    : " + size);
        System.out.println("Price/Night (₹) : " + pricePerNight);
    }
}

// Concrete Classes (Inheritance)

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1, 200.0, 1500.0);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2, 350.0, 2500.0);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 3, 600.0, 5000.0);
    }
}

// Main Application Class

 class UseCase2RoomInitialization {

    public static void main(String[] args) {

        // Creating room objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static availability (simple variables)
        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        // Display details
        System.out.println("===== HOTEL ROOM AVAILABILITY =====\n");

        System.out.println("--- Single Room ---");
        single.displayDetails();
        System.out.println("Available Rooms  : " + singleAvailability);

        System.out.println("\n--- Double Room ---");
        doubleRoom.displayDetails();
        System.out.println("Available Rooms  : " + doubleAvailability);

        System.out.println("\n--- Suite Room ---");
        suite.displayDetails();
        System.out.println("Available Rooms  : " + suiteAvailability);

        System.out.println("\n===== END OF LIST =====");
    }
}
