import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {

    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize rooms with sample data
        rooms.add(new Room("101", "Single", 100.0, true));
        rooms.add(new Room("102", "Double", 150.0, false));
        rooms.add(new Room("201", "Suite", 250.0, true));
        rooms.add(new Room("202", "Single", 250.0, true));
        rooms.add(new Room("301", "Suite", 250.0, true));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nHotel Reservation System");
            System.out.println("1. Search Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Booking Details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    searchRooms(scanner);
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void searchRooms(Scanner scanner) {
        System.out.println("\nSearch Rooms");
        System.out.print("Enter room type (Single, Double, Suite): ");
        String roomType = scanner.nextLine();

        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getType().equalsIgnoreCase(roomType) && room.isAvailable()) {
                availableRooms.add(room);
            }
        }

        if (availableRooms.isEmpty()) {
            System.out.println("No rooms of type " + roomType + " available.");
        } else {
            System.out.println("\nAvailable Rooms:");
            for (Room room : availableRooms) {
                System.out.println(room);
            }
        }
    }
    private static void makeReservation(Scanner scanner) {
        System.out.println("\nMake Reservation");
        System.out.print("Enter room number: ");
        String roomNumber = scanner.nextLine();
        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber().equals(roomNumber) && room.isAvailable()) {
                selectedRoom = room;
                break;
            }
        }
        if (selectedRoom == null) {
            System.out.println("Room not found or unavailable.");
            return;
        }
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();
        System.out.print("Enter number of guests: ");
        int numGuests = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter check-in date (YYYY-MM-DD): ");
        String checkInDate = scanner.nextLine();
        System.out.print("Enter check-out date (YYYY-MM-DD): ");
        String checkOutDate = scanner.nextLine();
        Reservation reservation = new Reservation(selectedRoom, guestName, numGuests, checkInDate, checkOutDate);
        reservations.add(reservation);
        selectedRoom.setAvailable(false);
        System.out.println("\nReservation successful!");
        System.out.println(reservation);
        // Simulate payment processing
        System.out.print("Enter payment amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        double price=selectedRoom.getPrice();
        if(amount==price){
            System.out.println("Payment successful!");
        }else{
        System.out.println("Payment unsuccessful!");}
    }
    private static void viewBookingDetails(Scanner scanner) {
        System.out.println("\nView Booking Details");
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Reservation reservation = null;
        for (Reservation res : reservations) {
            if (res.getReservationId() == reservationId) {
                reservation = res;
                break;
            }
        }
        if (reservation == null) {
            System.out.println("Reservation not found.");
        } else {
            System.out.println(reservation);
        }
    }
}

class Room {
    private String roomNumber;
    private String type;
    private double price;
    private boolean isAvailable;

    public Room(String roomNumber, String type, double price, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    // Getters and setters for roomNumber, type, price, and isAvailable

    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                ", Type: " + type +
                ", Price: " + price +
                ", Available: " + isAvailable;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
class Reservation {
    private static int nextReservationId = 1;
    private int reservationId;
    private Room room;
    private String guestName;
    private int numGuests;
    private String checkInDate;
    private String checkOutDate;
    public Reservation(Room room, String guestName, int numGuests, String checkInDate, String checkOutDate) {
        this.reservationId = nextReservationId++;
        this.room = room;
        this.guestName = guestName;
        this.numGuests = numGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    // Getters for reservationId, room, guestName, numGuests, checkInDate, and checkOutDate
    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Room: " + room.getRoomNumber() +
                ", Guest Name: " + guestName +
                ", Number of Guests: " + numGuests +
                ", Check-in Date: " + checkInDate +
                ", Check-out Date: " + checkOutDate;
    }
    public int getReservationId() {
        return reservationId;
    }
}