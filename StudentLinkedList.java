/*Problem Statement: Create a program to manage student records using a singly linked list. Each node will store information about a student, including their Roll Number, Name, Age, and Grade. Implement the following operations:
Add a new student record at the beginning, end, or at a specific position.
Delete a student record by Roll Number.
Search for a student record by Roll Number.
Display all student records.
Update a student's grade based on their Roll Number.
*/

import java.util.Scanner;
class Student {
    int roll;
    String name;
    int age;
    char grade;
    Student next;

    Student(int roll, String name, int age, char grade) {
        this.roll = roll;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentList {
    Student head = null;
    void addAtBeginning(int roll, String name, int age, char grade) {
        Student newNode = new Student(roll, name, age, grade);
        newNode.next = head;
        head = newNode;
        System.out.println("Record added at beginning.");
    }

    void addAtEnd(int roll, String name, int age, char grade) {
        Student newNode = new Student(roll, name, age, grade);

        if (head == null) {
            head = newNode;
            System.out.println("Record added at end.");
            return;
        }

        Student temp = head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = newNode;
        System.out.println("Record added at end.");
    }

    void addAtPosition(int roll, String name, int age, char grade, int pos) {
        Student newNode = new Student(roll, name, age, grade);

        if (pos == 1) {
            newNode.next = head;
            head = newNode;
            System.out.println("Record added at position " + pos);
            return;
        }

        Student temp = head;
        for (int i = 1; temp != null && i < pos - 1; i++)
            temp = temp.next;

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        System.out.println("Record added at position " + pos);
    }

    void deleteByRoll(int roll) {
        Student temp = head, prev = null;

        if (temp != null && temp.roll == roll) {
            head = temp.next;
            System.out.println("Record deleted.");
            return;
        }

        while (temp != null && temp.roll != roll) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Record not found.");
            return;
        }

        prev.next = temp.next;
        System.out.println("Record deleted.");
    }

    void searchByRoll(int roll) {
        Student temp = head;

        while (temp != null) {
            if (temp.roll == roll) {
                System.out.println("\nRecord Found:");
                System.out.println("Roll: " + temp.roll);
                System.out.println("Name: " + temp.name);
                System.out.println("Age: " + temp.age);
                System.out.println("Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }

        System.out.println("Record not found.");
    }

    void updateGrade(int roll, char newGrade) {
        Student temp = head;

        while (temp != null) {
            if (temp.roll == roll) {
                temp.grade = newGrade;
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Record not found.");
    }

    void display() {
        if (head == null) {
            System.out.println("No records to display.");
            return;
        }

        Student temp = head;
        System.out.println("\n--- Student Records ---");
        while (temp != null) {
            System.out.println("Roll: " + temp.roll + 
                               " | Name: " + temp.name +
                               " | Age: " + temp.age + 
                               " | Grade: " + temp.grade);
            temp = temp.next;
        }
    }
}

public class StudentLinkedList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList list = new StudentList();
        int choice;

        while (true) {
            System.out.println("\n--- Student Record Management ---");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Position");
            System.out.println("4. Delete by Roll Number");
            System.out.println("5. Search by Roll Number");
            System.out.println("6. Display All Records");
            System.out.println("7. Update Grade");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 8) break;

            int roll, age, pos;
            char grade;
            String name;

            switch (choice) {
                case 1:
                    System.out.print("Roll: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    name = sc.nextLine();
                    System.out.print("Age: ");
                    age = sc.nextInt();
                    System.out.print("Grade: ");
                    grade = sc.next().charAt(0);
                    list.addAtBeginning(roll, name, age, grade);
                    break;

                case 2:
                    System.out.print("Roll: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    name = sc.nextLine();
                    System.out.print("Age: ");
                    age = sc.nextInt();
                    System.out.print("Grade: ");
                    grade = sc.next().charAt(0);
                    list.addAtEnd(roll, name, age, grade);
                    break;

                case 3:
                    System.out.print("Roll: ");
                    roll = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Name: ");
                    name = sc.nextLine();
                    System.out.print("Age: ");
                    age = sc.nextInt();
                    System.out.print("Grade: ");
                    grade = sc.next().charAt(0);
                    System.out.print("Position: ");
                    pos = sc.nextInt();
                    list.addAtPosition(roll, name, age, grade, pos);
                    break;

                case 4:
                    System.out.print("Enter Roll to delete: ");
                    roll = sc.nextInt();
                    list.deleteByRoll(roll);
                    break;

                case 5:
                    System.out.print("Enter Roll to search: ");
                    roll = sc.nextInt();
                    list.searchByRoll(roll);
                    break;

                case 6:
                    list.display();
                    break;

                case 7:
                    System.out.print("Enter Roll to update grade: ");
                    roll = sc.nextInt();
                    System.out.print("New Grade: ");
                    grade = sc.next().charAt(0);
                    list.updateGrade(roll, grade);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
