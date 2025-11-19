/*Problem Statement: Implement a movie management system using a doubly linked list. Each node will represent a movie and contain Movie Title, Director, Year of Release, and Rating. Implement the following functionalities:
Add a movie record at the beginning, end, or at a specific position.
Remove a movie record by Movie Title.
Search for a movie record by Director or Rating.
Display all movie records in both forward and reverse order.
Update a movie's Rating based on the Movie Title.
*/

import java.util.Scanner;
class Task {
    int taskID;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskID, String taskName, int priority, String dueDate) {
        this.taskID = taskID;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class CircularTaskList {
    Task head = null;
    Task current = null;
    void addAtBeginning(int taskID, String taskName, int priority, String dueDate) {
        Task newNode = new Task(taskID, taskName, priority, dueDate);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            current = head;
        } else {
            Task temp = head;

            while (temp.next != head)
                temp = temp.next;

            newNode.next = head;
            temp.next = newNode;
            head = newNode;
        }

        System.out.println("Task added at beginning.");
    }

    void addAtEnd(int taskID, String taskName, int priority, String dueDate) {
        if (head == null) {
            addAtBeginning(taskID, taskName, priority, dueDate);
            return;
        }

        Task newNode = new Task(taskID, taskName, priority, dueDate);
        Task temp = head;

        while (temp.next != head)
            temp = temp.next;

        temp.next = newNode;
        newNode.next = head;

        System.out.println("Task added at end.");
    }
    void addAtPosition(int taskID, String taskName, int priority, String dueDate, int position) {
        if (position == 1) {
            addAtBeginning(taskID, taskName, priority, dueDate);
            return;
        }

        Task newNode = new Task(taskID, taskName, priority, dueDate);
        Task temp = head;

        for (int i = 1; i < position - 1; i++) {
            if (temp.next == head) {
                System.out.println("Invalid position.");
                return;
            }
            temp = temp.next;
        }

        newNode.next = temp.next;
        temp.next = newNode;

        System.out.println("Task added at position " + position);
    }

    void removeByID(int taskID) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Task temp = head;
        Task prev = null;
        if (head.taskID == taskID) {
            Task last = head;
            while (last.next != head)
                last = last.next;

            if (head == head.next) { 
                head = null;
                current = null;
            } else {
                last.next = head.next;
                head = head.next;
            }

            System.out.println("Task removed.");
            return;
        }

        do {
            prev = temp;
            temp = temp.next;

            if (temp.taskID == taskID) {
                prev.next = temp.next;

                if (current == temp)
                    current = current.next;

                System.out.println("Task removed.");
                return;
            }
        } while (temp != head);

        System.out.println("Task not found.");
    }
    void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\nCurrent Task:");
        System.out.println("Task ID: " + current.taskID);
        System.out.println("Name: " + current.taskName);
        System.out.println("Priority: " + current.priority);
        System.out.println("Due Date: " + current.dueDate);
    }
    void moveToNext() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        current = current.next;
        System.out.println("Moved to next task.");
        viewCurrentTask();
    }
    void displayAll() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }

        Task temp = head;
        System.out.println("\n--- All Tasks ---");

        do {
            System.out.println("Task ID: " + temp.taskID +
                    " | Name: " + temp.taskName +
                    " | Priority: " + temp.priority +
                    " | Due Date: " + temp.dueDate);
            temp = temp.next;

        } while (temp != head);
    }
    void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Task temp = head;
        boolean found = false;

        System.out.println("\nTasks with Priority " + priority + ":");

        do {
            if (temp.priority == priority) {
                System.out.println("Task ID: " + temp.taskID +
                        " | Name: " + temp.taskName +
                        " | Due Date: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;

        } while (temp != head);

        if (!found)
            System.out.println("No matching tasks found.");
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularTaskList list = new CircularTaskList();
        int choice;

        while (true) {
            System.out.println("\n--- Task Scheduler Menu ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task");
            System.out.println("6. Move to Next Task");
            System.out.println("7. Display All Tasks");
            System.out.println("8. Search Task by Priority");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 9) break;

            int id, priority, pos;
            String name, dueDate;

            switch (choice) {
                case 1:
                    System.out.print("Task ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Priority: ");
                    priority = sc.nextInt(); sc.nextLine();
                    System.out.print("Due Date: ");
                    dueDate = sc.nextLine();
                    list.addAtBeginning(id, name, priority, dueDate);
                    break;

                case 2:
                    System.out.print("Task ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Priority: ");
                    priority = sc.nextInt(); sc.nextLine();
                    System.out.print("Due Date: ");
                    dueDate = sc.nextLine();
                    list.addAtEnd(id, name, priority, dueDate);
                    break;

                case 3:
                    System.out.print("Task ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Priority: ");
                    priority = sc.nextInt(); sc.nextLine();
                    System.out.print("Due Date: ");
                    dueDate = sc.nextLine();
                    System.out.print("Position: ");
                    pos = sc.nextInt();
                    list.addAtPosition(id, name, priority, dueDate, pos);
                    break;

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    id = sc.nextInt();
                    list.removeByID(id);
                    break;

                case 5:
                    list.viewCurrentTask();
                    break;

                case 6:
                    list.moveToNext();
                    break;

                case 7:
                    list.displayAll();
                    break;

                case 8:
                    System.out.print("Enter Priority: ");
                    priority = sc.nextInt();
                    list.searchByPriority(priority);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
