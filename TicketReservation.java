/* Problem Statement: Design an online ticket reservation system using a circular linked list, where each node represents a booked ticket. Each node will store the following information: Ticket ID, Customer Name, Movie Name, Seat Number, and Booking Time. Implement the following functionalities:
Add a new ticket reservation at the end of the circular list.
Remove a ticket by Ticket ID.
Display the current tickets in the list.
Search for a ticket by Customer Name or Movie Name.
Calculate the total number of booked tickets.
*/

import java.util.Scanner;
class TicketNode {
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    TicketNode next;

    TicketNode(int id, String cName, String mName, String seat, String time) {
        ticketID = id;
        customerName = cName;
        movieName = mName;
        seatNumber = seat;
        bookingTime = time;
        next = null;
    }
}

class TicketCircularList {
    TicketNode head = null;

    void addTicket(int id, String cName, String mName, String seat, String time) {
        TicketNode newNode = new TicketNode(id, cName, mName, seat, time);

        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            TicketNode temp = head;
            while (temp.next != head) temp = temp.next;
            temp.next = newNode;
            newNode.next = head;
        }
        System.out.println("Ticket added.");
    }

    void removeTicket(int id) {
        if (head == null) return;

        TicketNode temp = head, prev;

        if (head.ticketID == id) {
            if (head.next == head) {
                head = null;
                return;
            }
            TicketNode last = head;
            while (last.next != head) last = last.next;
            head = head.next;
            last.next = head;
            return;
        }

        do {
            prev = temp;
            temp = temp.next;
            if (temp.ticketID == id) {
                prev.next = temp.next;
                return;
            }
        } while (temp != head);
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        TicketNode temp = head;
        do {
            System.out.println("\nTicket ID: " + temp.ticketID +
                    "\nCustomer: " + temp.customerName +
                    "\nMovie: " + temp.movieName +
                    "\nSeat: " + temp.seatNumber +
                    "\nTime: " + temp.bookingTime);
            temp = temp.next;
        } while (temp != head);
    }

    void search(String key, boolean byName) {
        if (head == null) {
            System.out.println("No tickets.");
            return;
        }
        TicketNode temp = head;
        boolean found = false;

        do {
            if ((byName && temp.customerName.equalsIgnoreCase(key)) ||
                (!byName && temp.movieName.equalsIgnoreCase(key))) {
                System.out.println("\nTicket ID: " + temp.ticketID +
                        " | Customer: " + temp.customerName +
                        " | Movie: " + temp.movieName +
                        " | Seat: " + temp.seatNumber);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) System.out.println("No matching ticket.");
    }

    int countTickets() {
        if (head == null) return 0;
        int count = 0;
        TicketNode temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}

public class TicketReservation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketCircularList list = new TicketCircularList();
        int choice;

        while (true) {
            System.out.println("\n--- Ticket Reservation Menu ---");
            System.out.println("1. Add Ticket");
            System.out.println("2. Remove Ticket");
            System.out.println("3. Display Tickets");
            System.out.println("4. Search by Customer Name");
            System.out.println("5. Search by Movie Name");
            System.out.println("6. Count Tickets");
            System.out.println("7. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            if (choice == 7) break;

            switch (choice) {
                case 1 -> {
                    System.out.print("Ticket ID: "); 
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Customer Name: "); 
                    String c = sc.nextLine();
                    System.out.print("Movie Name: "); 
                    String m = sc.nextLine();
                    System.out.print("Seat Number: "); 
                    String seat = sc.nextLine();
                    System.out.print("Booking Time: "); 
                    String time = sc.nextLine();
                    list.addTicket(id, c, m, seat, time);
                }
                case 2 -> {
                    System.out.print("Enter Ticket ID: ");
                    list.removeTicket(sc.nextInt());
                }
                case 3 -> list.displayTickets();
                case 4 -> {
                    sc.nextLine();
                    System.out.print("Customer Name: ");
                    list.search(sc.nextLine(), true);
                }
                case 5 -> {
                    sc.nextLine();
                    System.out.print("Movie Name: ");
                    list.search(sc.nextLine(), false);
                }
                case 6 -> System.out.println("Total Tickets: " + list.countTickets());
                default -> System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }
}
