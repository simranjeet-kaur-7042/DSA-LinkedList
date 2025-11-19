/*Problem Statement: Design a library management system using a doubly linked list. Each node represents a book and contains the following attributes: Book Title, Author, Genre, Book ID, and Availability Status. Implement the following functionalities:
Add a new book at the beginning, end, or at a specific position.
Remove a book by Book ID.
Search for a book by Book Title or Author.
Update a book’s Availability Status.
Display all books in forward and reverse order.
Count the total number of books in the library.
*/

import java.util.Scanner;
class BookNode {
    String title;
    String author;
    String genre;
    int bookID;
    boolean isAvailable;

    BookNode prev, next;

    BookNode(String title, String author, String genre, int bookID, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookID = bookID;
        this.isAvailable = isAvailable;
        this.prev = this.next = null;
    }
}
class LibraryList {
    BookNode head = null;
    BookNode tail = null;
    void addAtBeginning(String title, String author, String genre, int id, boolean available) {
        BookNode newNode = new BookNode(title, author, genre, id, available);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        System.out.println("Book added at beginning.");
    }
    void addAtEnd(String title, String author, String genre, int id, boolean available) {
        BookNode newNode = new BookNode(title, author, genre, id, available);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        System.out.println("Book added at end.");
    }
    void addAtPosition(String title, String author, String genre, int id, boolean available, int position) {
        if (position == 1) {
            addAtBeginning(title, author, genre, id, available);
            return;
        }

        BookNode newNode = new BookNode(title, author, genre, id, available);
        BookNode temp = head;

        for (int i = 1; i < position - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null || temp == tail) {
            addAtEnd(title, author, genre, id, available);
            return;
        }

        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next.prev = newNode;
        temp.next = newNode;

        System.out.println("Book added at position " + position);
    }
    void removeByID(int id) {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        BookNode temp = head;

        while (temp != null && temp.bookID != id) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book not found.");
            return;
        }

        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        System.out.println("Book removed successfully.");
    }
    void searchByTitle(String title) {
        BookNode temp = head;
        boolean found = false;

        System.out.println("\nSearch results by Title:");
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No book found with this title.");
    }
    void searchByAuthor(String author) {
        BookNode temp = head;
        boolean found = false;

        System.out.println("\nSearch results by Author:");
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No book found by this author.");
    }
    void updateAvailability(int id, boolean status) {
        BookNode temp = head;

        while (temp != null) {
            if (temp.bookID == id) {
                temp.isAvailable = status;
                System.out.println("Availability status updated.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Book not found.");
    }
    void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        System.out.println("\n--- Books (Forward) ---");
        BookNode temp = head;
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }

        System.out.println("\n--- Books (Reverse) ---");
        BookNode temp = tail;
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }
    void countBooks() {
        int count = 0;
        BookNode temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        System.out.println("Total number of books: " + count);
    }

    void printBook(BookNode b) {
        System.out.println("Title: " + b.title +
                " | Author: " + b.author +
                " | Genre: " + b.genre +
                " | ID: " + b.bookID +
                " | Available: " + (b.isAvailable ? "Yes" : "No"));
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LibraryList library = new LibraryList();

        int choice;

        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search Book by Title");
            System.out.println("6. Search Book by Author");
            System.out.println("7. Update Availability Status");
            System.out.println("8. Display Books (Forward)");
            System.out.println("9. Display Books (Reverse)");
            System.out.println("10. Count Total Books");
            System.out.println("11. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 11) break;

            String title, author, genre;
            int id, position;
            boolean status;

            switch (choice) {
                case 1:
                    System.out.print("Title: ");
                    title = sc.nextLine();
                    System.out.print("Author: ");
                    author = sc.nextLine();
                    System.out.print("Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    id = sc.nextInt();
                    System.out.print("Available? (true/false): ");
                    status = sc.nextBoolean();
                    library.addAtBeginning(title, author, genre, id, status);
                    break;

                case 2:
                    System.out.print("Title: ");
                    title = sc.nextLine();
                    System.out.print("Author: ");
                    author = sc.nextLine();
                    System.out.print("Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    id = sc.nextInt();
                    System.out.print("Available? (true/false): ");
                    status = sc.nextBoolean();
                    library.addAtEnd(title, author, genre, id, status);
                    break;

                case 3:
                    System.out.print("Title: ");
                    title = sc.nextLine();
                    System.out.print("Author: ");
                    author = sc.nextLine();
                    System.out.print("Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    id = sc.nextInt();
                    System.out.print("Available? (true/false): ");
                    status = sc.nextBoolean();
                    System.out.print("Position: ");
                    position = sc.nextInt();
                    library.addAtPosition(title, author, genre, id, status, position);
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    library.removeByID(id);
                    break;

                case 5:
                    System.out.print("Enter Book Title: ");
                    title = sc.nextLine();
                    library.searchByTitle(title);
                    break;

                case 6:
                    System.out.print("Enter Author Name: ");
                    author = sc.nextLine();
                    library.searchByAuthor(author);
                    break;

                case 7:
                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    System.out.print("New Availability (true/false): ");
                    status = sc.nextBoolean();
                    library.updateAvailability(id, status);
                    break;

                case 8:
                    library.displayForward();
                    break;

                case 9:
                    library.displayReverse();
                    break;

                case 10:
                    library.countBooks();
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
