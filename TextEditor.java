/*Problem Statement: Design an undo/redo functionality for a text editor using a doubly linked list. Each node represents a state of the text content (e.g., after typing a word or performing a command). Implement the following:
Add a new text state at the end of the list every time the user types or performs an action.
Implement the undo functionality (revert to the previous state).
Implement the redo functionality (revert back to the next state after undo).
Display the current state of the text.
Limit the undo/redo history to a fixed size (e.g., last 10 states).
*/

import java.util.*;

class State {
    String text;
    State prev, next;

    State(String text) {
        this.text = text;
        this.prev = this.next = null;
    }
}

class TextEditorLL {
    State head = null, current = null;
    int size = 0, limit = 10; 

    
    void addState(String newText) {
        State newState = new State(newText);

       
        if (current != null && current.next != null)
            current.next = null;

        if (head == null)
            head = newState;
        else {
            current.next = newState;
            newState.prev = current;
        }
        current = newState;
        size++;

        
        if (size > limit) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    
    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo done!");
        } else
            System.out.println("No more undo available!");
    }

    
    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo done!");
        } else
            System.out.println("No more redo available!");
    }

   
    void showCurrent() {
        if (current == null)
            System.out.println("Editor is empty.");
        else
            System.out.println("Current Text: " + current.text);
    }
}

public class TextEditor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditorLL te = new TextEditorLL();

        while (true) {
            System.out.println("\n1.Add Text 2.Undo 3.Redo 4.Show Current 5.Exit");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter text: ");
                    String text = sc.nextLine();
                    te.addState(text);
                }
                case 2 -> te.undo();
                case 3 -> te.redo();
                case 4 -> te.showCurrent();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}