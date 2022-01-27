/**
 * Name: Taran Preet Singh
 * Student Number: 000824301
 * Professor Name: Mark Yendt
 * I, Taran Preet Singh, confirms that this work of Assignment 5 belongs to me, It's my original work
 * and has not been copied by anyone.
 *
 * The assignment 5 is an assignment that includes managing aisles inside a shopping store where we have fast and normal lanes
 * whose data is retrieved from a text file named CustomerData.txt, where all info regarding lanes and customers are given.
 * Using which the following code has been designed by me. It contains Two parts- A & B.
 * Part A contains managing the customers into the aisles and Part B gives the count of customers inside the store after
 * an interval of 30 secs, until the there are no customers.
 */
/**
 * Linked Queue class to be used for Lab#5
 * Used code from COMP-10152 - Data Structures and Algorithms (pg.417-419)
 * Modified by Mark Yendt to be Generic
 */
public class LinkedQueue<E> {

    /**
     * Node class to be used by the LinkedQueue class
     *
     * @param <E>
     */
    private class Node<E> {

        E value;
        Node<E> next;

        Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
    private int count;
    private Node<E> front;
    private Node<E> rear;

    // Created a private Integer named sum, not initialised.
    private int sum;
    // Created a private String named a, not initialised.
    private String a;
    /**
     * Constructor for a LinkedQueue
     */
    public LinkedQueue() {
        front = rear = null;
        count = 0;
    }
    // Created a constructor with Parameter String a and Integer Sum
    //Where stores names of different types of LinkedQueue<> and Sum stores the sum given to it.

    /**
     *  Created a constructor with Parameter String a and Integer Sum
     *  Where stores names of different types of LinkedQueue<> and Sum stores the sum given to it.
     * @param a is given to store names of Different LinkedQueue<>
     * @param sum stores the sum given to it.
     */
    public LinkedQueue(String a, int sum)
    {
        this.a = a;
        this.sum = sum;
    }

    /**
     * Getter method for Sum
     * @return sum as sum value for that Object.
     */
    public int getSum() {
        return sum;
    }

    /**
     * Setter method for Sum, where it sets new value for Sum passed through the parameter.
     * @param sum
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    /**
     * Add an item to the Queue
     *
     * @param value item to be added to the Queue
     */
    public void enqueue(E value) {
        if (rear != null) {
            rear.next = new Node(value, null);
            rear = rear.next;
        } else {
            rear = new Node(value, null);
            front = rear;
        }
        count++;
    }

    /**
     * Remove an item from the Queue - throws exception if queue is empty
     *
     * @return the item at the from of the Queue
     */
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot dequeue - Queue is empty");
        }

        E value = front.value;
        front = front.next;
        count--;

        if (front == null) {
            rear = null;
        }

        return value;
    }

    /**
     * Check is queue is empty
     *
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Shows front of Queue
     * @return the value of the first item in the Queue
     */
    public E peek() {
        return front.value;
    }
    
    /**
     * Obtain the number of elements in the Queue
     * @return 
     */
    
    public int size() {
        return count;
    }
    
    /**
     * ToString method used to print each of the strings of the objects
     * @return string representing the class 
     */
    
    @Override
    public String toString() {
        String result = "[";
        Node<E> current = front;
        while (current != null) {
            result += current.value;
            if (current.next != null) {
                result += ",";
            }
            current = current.next;
        }
        result += "]";
        return result;
    }
}
