import java.util.*;

// Reservation (Represents booking intent)
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

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

// Booking Request Queue (FIFO handling)
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request added -> " + reservation);
    }

    // View all queued requests (without removing)
    public void viewRequests() {
        System.out.println("\n--- Booking Request Queue (FIFO Order) ---");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            System.out.println(r);
        }
    }

    // Peek next request (without removing)
    public Reservation peekNext() {
        return queue.peek();
    }

    // Get queue size
    public int getQueueSize() {
        return queue.size();
    }
}

// Main Class
 class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        // Step 1: Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Step 2: Simulate guest booking requests (arrival order)
        bookingQueue.addRequest(new Reservation("Alice", "Deluxe"));
        bookingQueue.addRequest(new Reservation("Bob", "Standard"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite"));
        bookingQueue.addRequest(new Reservation("Diana", "Deluxe"));

        // Step 3: View all requests (FIFO order preserved)
        bookingQueue.viewRequests();

        // Step 4: Peek next request (who will be served first)
        System.out.println("\nNext request to process: " + bookingQueue.peekNext());

        // Step 5: Show total requests
        System.out.println("Total pending requests: " + bookingQueue.getQueueSize());

        // IMPORTANT: No inventory updates or booking allocation here
    }
}