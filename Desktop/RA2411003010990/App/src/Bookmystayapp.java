import java.util.*;

public class Bookmystayapp {

    // Reservation class
    static class Reservation {
        private String bookingId;
        private String customerName;
        private String roomType;
        private int nights;

        public Reservation(String bookingId, String customerName, String roomType, int nights) {
            this.bookingId = bookingId;
            this.customerName = customerName;
            this.roomType = roomType;
            this.nights = nights;
        }

        public String getRoomType() {
            return roomType;
        }

        public int getNights() {
            return nights;
        }

        @Override
        public String toString() {
            return "BookingID: " + bookingId +
                    ", Customer: " + customerName +
                    ", Room: " + roomType +
                    ", Nights: " + nights;
        }
    }

    // Booking History class
    static class BookingHistory {
        private List<Reservation> reservations = new ArrayList<>();

        public void addReservation(Reservation reservation) {
            reservations.add(reservation);
        }

        public List<Reservation> getAllReservations() {
            return Collections.unmodifiableList(reservations);
        }
    }

    // Report Service class
    static class BookingReportService {

        public void generateSummary(List<Reservation> reservations) {
            System.out.println("\n--- Booking Summary Report ---");

            int totalBookings = reservations.size();
            int totalNights = 0;

            Map<String, Integer> roomTypeCount = new HashMap<>();

            for (Reservation r : reservations) {
                totalNights += r.getNights();
                roomTypeCount.put(
                        r.getRoomType(),
                        roomTypeCount.getOrDefault(r.getRoomType(), 0) + 1
                );
            }

            System.out.println("Total Bookings: " + totalBookings);
            System.out.println("Total Nights Booked: " + totalNights);

            System.out.println("\nBookings by Room Type:");
            for (String type : roomTypeCount.keySet()) {
                System.out.println(type + ": " + roomTypeCount.get(type));
            }
        }
    }

    // Main method
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        history.addReservation(new Reservation("B101", "Alice", "Deluxe", 3));
        history.addReservation(new Reservation("B102", "Bob", "Standard", 2));
        history.addReservation(new Reservation("B103", "Charlie", "Suite", 5));
        history.addReservation(new Reservation("B104", "Diana", "Deluxe", 1));

        // Display Booking History
        System.out.println("--- Booking History ---");
        for (Reservation r : history.getAllReservations()) {
            System.out.println(r);
        }

        // Generate Report
        reportService.generateSummary(history.getAllReservations());
    }
}
