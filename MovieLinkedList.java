/*Problem Statement: Implement a movie management system using a doubly linked list. Each node will represent a movie and contain Movie Title, Director, Year of Release, and Rating. Implement the following functionalities:
Add a movie record at the beginning, end, or at a specific position.
Remove a movie record by Movie Title.
Search for a movie record by Director or Rating.
Display all movie records in both forward and reverse order.
Update a movie's Rating based on the Movie Title.
*/

import java.util.Scanner;
class Movie {
    String title;
    String director;
    int year;
    double rating;
    Movie prev;
    Movie next;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }
}

class MovieList {
    Movie head = null;
    Movie tail = null;

    void addAtBeginning(String title, String director, int year, double rating) {
        Movie newNode = new Movie(title, director, year, rating);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        System.out.println("Movie added at beginning.");
    }

    void addAtEnd(String title, String director, int year, double rating) {
        Movie newNode = new Movie(title, director, year, rating);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        System.out.println("Movie added at end.");
    }

    void addAtPosition(String title, String director, int year, double rating, int position) {
        Movie newNode = new Movie(title, director, year, rating);

        if (position == 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }

        Movie temp = head;
        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        newNode.next = temp.next;

        if (temp.next != null)
            temp.next.prev = newNode;
        else
            tail = newNode;

        temp.next = newNode;
        newNode.prev = temp;

        System.out.println("Movie added at position " + position);
    }
    void removeByTitle(String title) {
        Movie temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {

                if (temp == head) {
                    head = head.next;
                    if (head != null) head.prev = null;
                } else {
                    temp.prev.next = temp.next;
                }

                if (temp == tail) {
                    tail = tail.prev;
                    if (tail != null) tail.next = null;
                } else if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }

                System.out.println("Movie removed: " + title);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Movie not found.");
    }

    void searchByDirector(String director) {
        Movie temp = head;
        boolean found = false;

        System.out.println("\nMovies directed by: " + director);
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                System.out.println(temp.title + " (" + temp.year + ") | Rating: " + temp.rating);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) System.out.println("No movies found.");
    }
    void searchByRating(double rating) {
        Movie temp = head;
        boolean found = false;

        System.out.println("\nMovies with rating " + rating + ":");
        while (temp != null) {
            if (temp.rating == rating) {
                System.out.println(temp.title + " directed by " + temp.director + " (" + temp.year + ")");
                found = true;
            }
            temp = temp.next;
        }

        if (!found) System.out.println("No movies found.");
    }
    void displayForward() {
        if (head == null) {
            System.out.println("No movies to display.");
            return;
        }

        Movie temp = head;
        System.out.println("\n--- Movies (Forward) ---");
        while (temp != null) {
            System.out.println(temp.title + " | Director: " + temp.director + " | Year: " + temp.year + " | Rating: " + temp.rating);
            temp = temp.next;
        }
    }

    void displayReverse() {
        if (tail == null) {
            System.out.println("No movies to display.");
            return;
        }

        Movie temp = tail;
        System.out.println("\n--- Movies (Reverse) ---");
        while (temp != null) {
            System.out.println(temp.title + " | Director: " + temp.director + " | Year: " + temp.year + " | Rating: " + temp.rating);
            temp = temp.prev;
        }
    }

    void updateRating(String title, double newRating) {
        Movie temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                System.out.println("Rating updated for: " + title);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Movie not found.");
    }
}

public class MovieLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieList list = new MovieList();
        int choice;

        while (true) {
            System.out.println("\n--- Movie Management System ---");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search by Director");
            System.out.println("6. Search by Rating");
            System.out.println("7. Display Movies (Forward)");
            System.out.println("8. Display Movies (Reverse)");
            System.out.println("9. Update Movie Rating");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 10) break;

            String title, director;
            int year, position;
            double rating;

            switch (choice) {

                case 1:
                    System.out.print("Title: ");
                    title = sc.nextLine();
                    System.out.print("Director: ");
                    director = sc.nextLine();
                    System.out.print("Year: ");
                    year = sc.nextInt();
                    System.out.print("Rating: ");
                    rating = sc.nextDouble();
                    list.addAtBeginning(title, director, year, rating);
                    break;

                case 2:
                    System.out.print("Title: ");
                    title = sc.nextLine();
                    System.out.print("Director: ");
                    director = sc.nextLine();
                    System.out.print("Year: ");
                    year = sc.nextInt();
                    System.out.print("Rating: ");
                    rating = sc.nextDouble();
                    list.addAtEnd(title, director, year, rating);
                    break;

                case 3:
                    System.out.print("Title: ");
                    title = sc.nextLine();
                    System.out.print("Director: ");
                    director = sc.nextLine();
                    System.out.print("Year: ");
                    year = sc.nextInt();
                    System.out.print("Rating: ");
                    rating = sc.nextDouble();
                    System.out.print("Position: ");
                    position = sc.nextInt();
                    list.addAtPosition(title, director, year, rating, position);
                    break;

                case 4:
                    System.out.print("Enter title to remove: ");
                    title = sc.nextLine();
                    list.removeByTitle(title);
                    break;

                case 5:
                    System.out.print("Enter director name: ");
                    director = sc.nextLine();
                    list.searchByDirector(director);
                    break;

                case 6:
                    System.out.print("Enter rating: ");
                    rating = sc.nextDouble();
                    list.searchByRating(rating);
                    break;

                case 7:
                    list.displayForward();
                    break;

                case 8:
                    list.displayReverse();
                    break;

                case 9:
                    System.out.print("Enter title: ");
                    title = sc.nextLine();
                    System.out.print("New Rating: ");
                    rating = sc.nextDouble();
                    list.updateRating(title, rating);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
