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
 * Class named CustomerInfo containing private variables, items and time, constructor method, getter setter methods and ToString method
 * to print the object in clear way possible.
 */
public class CustomerInfo {

    /**
     * Created a private Integer named as items to store the count of number of items.
     */
    private int items;
    /**
     * Created a private Integer named as time to store the count of time taken by the customer to exit the lane.
     */
    private int time;

    /**
     * Construtor CustomerInfo which has a parameter Integer named items
     * @param items
     */
    public CustomerInfo(int items)
    {
        this.items = items;
        this.time = 45+5*items;
    }

    /**
     * Getter method to return value for the Items count the customer has.
     * @return items count for the customer.
     */
    public int getItems() {
        return items;
    }

    /**
     * Getter method to return value for the Time taken by the customer.
     * @return Time taken value for the customer.
     */
    public int getTime() {
        return time;
    }

    /**
     * Setter method to set the time taken by the customer, passing the value in the parameter replacing the previous value if assigned already.
     * @param time taken value passed through as parameter.
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * ToString method used to print each of the strings of the objects
     * @return string representing the class, items and time.
     */
    @Override
    public String toString() {
        return "[" + items + " (" + time + " s)]";
    }
}
