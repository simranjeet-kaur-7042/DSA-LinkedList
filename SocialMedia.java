/*Problem Statement: Create a system to manage social media friend connections using a singly linked list. Each node represents a user with User ID, Name, Age, and List of Friend IDs. Implement the following operations:
Add a friend connection between two users.
Remove a friend connection.
Find mutual friends between two users.
Display all friends of a specific user.
Search for a user by Name or User ID.
Count the number of friends for each user.
*/

import java.util.Scanner;
class FriendNode {
    int friendID;
    FriendNode next;

    FriendNode(int id) {
        friendID = id;
        next = null;
    }
}

class UserNode {
    int userID;
    String name;
    int age;
    FriendNode friends;
    UserNode next;

    UserNode(int id, String n, int a) {
        userID = id;
        name = n;
        age = a;
        friends = null;
        next = null;
    }
}

class UserList {
    UserNode head = null;

    void addUser(int id, String name, int age) {
        UserNode newNode = new UserNode(id, name, age);
        if (head == null) head = newNode;
        else {
            UserNode temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newNode;
        }
    }

    UserNode searchByID(int id) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.userID == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    UserNode searchByName(String n) {
        UserNode temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(n)) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriend(int u1, int u2) {
        UserNode a = searchByID(u1);
        UserNode b = searchByID(u2);
        if (a == null || b == null) return;

        if (!isFriend(a.friends, u2))
            a.friends = addFriendNode(a.friends, u2);

        if (!isFriend(b.friends, u1))
            b.friends = addFriendNode(b.friends, u1);
    }

    FriendNode addFriendNode(FriendNode head, int id) {
        FriendNode newNode = new FriendNode(id);
        if (head == null) return newNode;
        FriendNode temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
        return head;
    }

    boolean isFriend(FriendNode head, int id) {
        FriendNode temp = head;
        while (temp != null) {
            if (temp.friendID == id) return true;
            temp = temp.next;
        }
        return false;
    }

    void removeFriend(int u1, int u2) {
        UserNode a = searchByID(u1);
        UserNode b = searchByID(u2);
        if (a == null || b == null) return;

        a.friends = deleteFriendNode(a.friends, u2);
        b.friends = deleteFriendNode(b.friends, u1);
    }

    FriendNode deleteFriendNode(FriendNode head, int id) {
        if (head == null) return null;
        if (head.friendID == id) return head.next;

        FriendNode temp = head;
        while (temp.next != null) {
            if (temp.next.friendID == id) {
                temp.next = temp.next.next;
                return head;
            }
            temp = temp.next;
        }
        return head;
    }

    void displayFriends(int id) {
        UserNode u = searchByID(id);
        if (u == null) return;
        FriendNode f = u.friends;
        System.out.print("Friends of " + u.name + ": ");
        while (f != null) {
            System.out.print(f.friendID + " ");
            f = f.next;
        }
        System.out.println();
    }

    void mutualFriends(int u1, int u2) {
        UserNode a = searchByID(u1);
        UserNode b = searchByID(u2);
        if (a == null || b == null) return;

        FriendNode f1 = a.friends;
        System.out.print("Mutual Friends: ");
        while (f1 != null) {
            if (isFriend(b.friends, f1.friendID))
                System.out.print(f1.friendID + " ");
            f1 = f1.next;
        }
        System.out.println();
    }

    int countFriends(int id) {
        UserNode u = searchByID(id);
        if (u == null) return 0;

        int count = 0;
        FriendNode f = u.friends;
        while (f != null) {
            count++;
            f = f.next;
        }
        return count;
    }
}

public class SocialMedia{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserList list = new UserList();

        list.addUser(1, "Aarav", 21);
        list.addUser(2, "Priya", 22);
        list.addUser(3, "Rohan", 20);
        list.addUser(4, "Sneha", 23);

        while (true) {
            System.out.println("\n1. Add Friend");
            System.out.println("2. Remove Friend");
            System.out.println("3. Mutual Friends");
            System.out.println("4. Display Friends");
            System.out.println("5. Search by ID");
            System.out.println("6. Search by Name");
            System.out.println("7. Count Friends");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            if (ch == 8) break;

            switch (ch) {
                case 1 -> {
                    System.out.print("User 1 ID: ");
                    int u1 = sc.nextInt();
                    System.out.print("User 2 ID: ");
                    int u2 = sc.nextInt();
                    list.addFriend(u1, u2);
                }
                case 2 -> {
                    System.out.print("User 1 ID: ");
                    int u1 = sc.nextInt();
                    System.out.print("User 2 ID: ");
                    int u2 = sc.nextInt();
                    list.removeFriend(u1, u2);
                }
                case 3 -> {
                    System.out.print("User 1 ID: ");
                    int u1 = sc.nextInt();
                    System.out.print("User 2 ID: ");
                    int u2 = sc.nextInt();
                    list.mutualFriends(u1, u2);
                }
                case 4 -> {
                    System.out.print("User ID: ");
                    list.displayFriends(sc.nextInt());
                }
                case 5 -> {
                    System.out.print("Enter User ID: ");
                    UserNode u = list.searchByID(sc.nextInt());
                    if (u != null) System.out.println(u.userID + " " + u.name + " " + u.age);
                }
                case 6 -> {
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    UserNode u = list.searchByName(sc.nextLine());
                    if (u != null) System.out.println(u.userID + " " + u.name + " " + u.age);
                }
                case 7 -> {
                    System.out.print("User ID: ");
                    System.out.println("Friends Count: " + list.countFriends(sc.nextInt()));
                }
            }
        }
    }
}
