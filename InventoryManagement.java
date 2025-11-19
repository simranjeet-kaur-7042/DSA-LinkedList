/*Problem Statement: Design an inventory management system using a singly linked list where each node stores information about an item such as Item Name, Item ID, Quantity, and Price. Implement the following functionalities:
Add an item at the beginning, end, or at a specific position.
Remove an item based on Item ID.
Update the quantity of an item by Item ID.
Search for an item based on Item ID or Item Name.
Calculate and display the total value of inventory (Sum of Price * Quantity for each item).
Sort the inventory based on Item Name or Price in ascending or descending order.
*/

import java.util.Scanner;
class ItemNode {
    String itemName;
    int itemID;
    int quantity;
    double price;
    ItemNode next;

    ItemNode(String name, int id, int qty, double price) {
        this.itemName = name;
        this.itemID = id;
        this.quantity = qty;
        this.price = price;
        this.next = null;
    }
}
class InventoryList {
    ItemNode head = null;
    void addAtBeginning(String name, int id, int qty, double price) {
        ItemNode newNode = new ItemNode(name, id, qty, price);
        newNode.next = head;
        head = newNode;
        System.out.println("Item added at the beginning.");
    }
    void addAtEnd(String name, int id, int qty, double price) {
        ItemNode newNode = new ItemNode(name, id, qty, price);

        if (head == null) {
            head = newNode;
            System.out.println("Item added at the end.");
            return;
        }

        ItemNode temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
        System.out.println("Item added at the end.");
    }
    void addAtPosition(String name, int id, int qty, double price, int position) {
        if (position == 1) {
            addAtBeginning(name, id, qty, price);
            return;
        }

        ItemNode newNode = new ItemNode(name, id, qty, price);
        ItemNode temp = head;

        for (int i = 1; temp != null && i < position - 1; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        System.out.println("Item added at position " + position);
    }

    void removeByID(int id) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.itemID == id) {
            head = head.next;
            System.out.println("Item removed.");
            return;
        }

        ItemNode temp = head, prev = null;

        while (temp != null && temp.itemID != id) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Item not found.");
            return;
        }

        prev.next = temp.next;
        System.out.println("Item removed.");
    }
    void updateQuantity(int id, int newQty) {
        ItemNode temp = head;

        while (temp != null) {
            if (temp.itemID == id) {
                temp.quantity = newQty;
                System.out.println("Quantity updated.");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Item not found.");
    }
    void searchByID(int id) {
        ItemNode temp = head;

        while (temp != null) {
            if (temp.itemID == id) {
                System.out.println("\nItem Found:");
                System.out.println("Name: " + temp.itemName);
                System.out.println("ID: " + temp.itemID);
                System.out.println("Quantity: " + temp.quantity);
                System.out.println("Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }
    void searchByName(String name) {
        ItemNode temp = head;
        boolean found = false;

        System.out.println("\nSearch Results:");
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) {
                System.out.println("Name: " + temp.itemName +
                                   " | ID: " + temp.itemID +
                                   " | Qty: " + temp.quantity +
                                   " | Price: " + temp.price);
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No item found with that name.");
    }

    void display() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        ItemNode temp = head;
        System.out.println("\n--- Inventory Items ---");
        while (temp != null) {
            System.out.println("Name: " + temp.itemName +
                               " | ID: " + temp.itemID +
                               " | Qty: " + temp.quantity +
                               " | Price: " + temp.price);
            temp = temp.next;
        }
    }
    void totalValue() {
        double total = 0;
        ItemNode temp = head;

        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }

        System.out.println("\nTotal Inventory Value: $" + total);
    }

    // Sort inventory by name (1) or price (2)
    void sortInventory(int option, boolean ascending) {
        if (head == null || head.next == null) {
            System.out.println("Not enough items to sort.");
            return;
        }

        for (ItemNode i = head; i.next != null; i = i.next) {
            for (ItemNode j = i.next; j != null; j = j.next) {
                boolean condition = false;

                if (option == 1) {
                    condition = ascending ?
                                i.itemName.compareToIgnoreCase(j.itemName) > 0 :
                                i.itemName.compareToIgnoreCase(j.itemName) < 0;
                } else if (option == 2) {
                    condition = ascending ? i.price > j.price : i.price < j.price;
                }

                if (condition) {
                    // Swap node data
                    String tn = i.itemName;
                    int tid = i.itemID;
                    int tq = i.quantity;
                    double tp = i.price;

                    i.itemName = j.itemName;
                    i.itemID = j.itemID;
                    i.quantity = j.quantity;
                    i.price = j.price;

                    j.itemName = tn;
                    j.itemID = tid;
                    j.quantity = tq;
                    j.price = tp;
                }
            }
        }

        System.out.println("Inventory sorted successfully!");
    }
}
public class InventoryManagement{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventoryList list = new InventoryList();
        int choice;

        while (true) {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity");
            System.out.println("6. Search Item by ID");
            System.out.println("7. Search Item by Name");
            System.out.println("8. Display Inventory");
            System.out.println("9. Total Inventory Value");
            System.out.println("10. Sort Inventory");
            System.out.println("11. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 11) break;

            String name;
            int id, qty, pos, sortOption;
            double price;

            switch (choice) {
                case 1:
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    list.addAtBeginning(name, id, qty, price);
                    break;

                case 2:
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    list.addAtEnd(name, id, qty, price);
                    break;

                case 3:
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    System.out.print("Position: ");
                    pos = sc.nextInt();
                    list.addAtPosition(name, id, qty, price, pos);
                    break;

                case 4:
                    System.out.print("Enter Item ID to remove: ");
                    id = sc.nextInt();
                    list.removeByID(id);
                    break;

                case 5:
                    System.out.print("Enter Item ID: ");
                    id = sc.nextInt();
                    System.out.print("New Quantity: ");
                    qty = sc.nextInt();
                    list.updateQuantity(id, qty);
                    break;

                case 6:
                    System.out.print("Enter Item ID: ");
                    id = sc.nextInt();
                    list.searchByID(id);
                    break;

                case 7:
                    System.out.print("Enter Item Name: ");
                    name = sc.nextLine();
                    list.searchByName(name);
                    break;

                case 8:
                    list.display();
                    break;

                case 9:
                    list.totalValue();
                    break;

                case 10:
                    System.out.println("Sort by: 1) Name 2) Price");
                    sortOption = sc.nextInt();
                    System.out.println("Order: 1) Ascending  2) Descending");
                    int order = sc.nextInt();
                    list.sortInventory(sortOption, order == 1);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
