/*Problem Statement: Implement a round-robin CPU scheduling algorithm using a circular linked list. Each node will represent a process and contain Process ID, Burst Time, and Priority. Implement the following functionalities:
Add a new process at the end of the circular list.
Remove a process by Process ID after its execution.
Simulate the scheduling of processes in a round-robin manner with a fixed time quantum.
Display the list of processes in the circular queue after each round.
Calculate and display the average waiting time and turn-around time for all processes.
*/

import java.util.Scanner;
class ProcessNode {
    int pid;
    int burstTime;
    int remainingTime;
    int priority;
    ProcessNode next;

    ProcessNode(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class CircularProcessList {
    ProcessNode head = null;
    void addProcess(int pid, int burstTime, int priority) {
        ProcessNode newNode = new ProcessNode(pid, burstTime, priority);

        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            ProcessNode temp = head;
            while (temp.next != head)
                temp = temp.next;

            temp.next = newNode;
            newNode.next = head;
        }

        System.out.println("Process added.");
    }

    void removeProcess(int pid) {
        if (head == null) return;

        ProcessNode temp = head, prev = null;
        if (head.pid == pid) {
            ProcessNode last = head;
            while (last.next != head)
                last = last.next;

            if (head == head.next) {  
                head = null;
                return;
            }

            head = head.next;
            last.next = head;
            return;
        }

        do {
            prev = temp;
            temp = temp.next;

            if (temp.pid == pid) {
                prev.next = temp.next;
                return;
            }
        } while (temp != head);
    }

    void displayQueue() {
        if (head == null) {
            System.out.println("Queue is empty.");
            return;
        }

        ProcessNode temp = head;
        System.out.println("\nProcesses in Queue:");
        do {
            System.out.println("PID: " + temp.pid +
                             " | BT: " + temp.burstTime +
                             " | Remaining: " + temp.remainingTime +
                             " | Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
    }

    void roundRobin(int quantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        ProcessNode current = head;
        int time = 0;

        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;
        int processCount = countProcesses();

        int[] finishTime = new int[processCount + 1];
        int[] waitingTime = new int[processCount + 1];

        while (head != null) {

            if (current.remainingTime > 0) {
                System.out.println("\nExecuting Process P" + current.pid);

                if (current.remainingTime > quantum) {
                    current.remainingTime -= quantum;
                    time += quantum;
                } else {
                    time += current.remainingTime;
                    current.remainingTime = 0;

                    finishTime[current.pid] = time;
                    waitingTime[current.pid] = finishTime[current.pid] - current.burstTime;

                    System.out.println("Process P" + current.pid + " completed.");
                    removeProcess(current.pid);
                }

                displayQueue();
            }

            current = (current.next != null) ? current.next : head;

            if (head != null && current == head && allDone()) 
                break;
        }

        for (int i = 1; i <= processCount; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += finishTime[i];
        }

        System.out.println("\n=== Scheduling Complete ===");
        System.out.println("Average Waiting Time: " + (double) totalWaitingTime / processCount);
        System.out.println("Average Turnaround Time: " + (double) totalTurnaroundTime / processCount);
    }

    boolean allDone() {
        ProcessNode temp = head;
        do {
            if (temp.remainingTime > 0)
                return false;
            temp = temp.next;
        } while (temp != head);
        return true;
    }

    int countProcesses() {
        if (head == null) return 0;
        int count = 0;
        ProcessNode temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }
}
public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularProcessList list = new CircularProcessList();

        int choice;

        while (true) {
            System.out.println("\n--- Round Robin Scheduling Menu ---");
            System.out.println("1. Add Process");
            System.out.println("2. Display Process Queue");
            System.out.println("3. Run Round Robin Scheduling");
            System.out.println("4. Exit");

            System.out.print("Choice: ");
            choice = sc.nextInt();

            if (choice == 4) break;

            int pid, bt, pr, quantum;

            switch (choice) {
                case 1:
                    System.out.print("Process ID: ");
                    pid = sc.nextInt();
                    System.out.print("Burst Time: ");
                    bt = sc.nextInt();
                    System.out.print("Priority: ");
                    pr = sc.nextInt();

                    list.addProcess(pid, bt, pr);
                    break;

                case 2:
                    list.displayQueue();
                    break;

                case 3:
                    System.out.print("Enter Time Quantum: ");
                    quantum = sc.nextInt();
                    list.roundRobin(quantum);
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
