import java.util.*;

// Add-On Service (represents optional feature)
class AddOnService {
    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return serviceName + " (₹" + cost + ")";
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID → List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println("Added service to " + reservationId + ": " + service);
    }

    // View services for a reservation
    public void viewServices(String reservationId) {
        System.out.println("\n--- Services for Reservation: " + reservationId + " ---");

        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (AddOnService service : services) {
            System.out.println(service);
        }
    }

    // Calculate total cost of services
    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services == null) return 0;

        double total = 0;
        for (AddOnService s : services) {
            total += s.getCost();
        }
        return total;
    }
}

// Main Class
class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        // Step 1: Assume existing confirmed reservation IDs
        String res1 = "RES-101";
        String res2 = "RES-102";

        // Step 2: Create Add-On Services
        AddOnService wifi = new AddOnService("Premium WiFi", 500);
        AddOnService breakfast = new AddOnService("Breakfast", 300);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 800);
        AddOnService spa = new AddOnService("Spa Access", 1200);

        // Step 3: Initialize service manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Step 4: Guest selects services
        manager.addService(res1, wifi);
        manager.addService(res1, breakfast);
        manager.addService(res1, spa);

        manager.addService(res2, airportPickup);

        // Step 5: View selected services
        manager.viewServices(res1);
        manager.viewServices(res2);

        // Step 6: Calculate additional cost
        System.out.println("\nTotal Add-On Cost for " + res1 + ": ₹" +
                manager.calculateTotalCost(res1));

        System.out.println("Total Add-On Cost for " + res2 + ": ₹" +
                manager.calculateTotalCost(res2));

        // IMPORTANT: No booking or inventory changes here
    }
}