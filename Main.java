/**
 * Name: Taran Preet Singh
 * Student Number: 000824301
 * 
 * I, Taran Preet Singh, confirm that this work of Assignment 5 belongs to me, It's my original work
 * and has not been copied from anyone.
 *
 * It includes managing aisles inside a shopping store where we have fast and normal lanes
 * whose data is retrieved from a text file named CustomerData.txt, where all info regarding lanes and customers are given.
 * Using which the following code has been designed by me. It contains Two parts- A & B.
 * Part A contains managing the customers into the aisles and Part B gives the count of customers inside the store after
 * an interval of 30 secs, until the there are no customers.
 */

import java.io.File;
import java.util.Scanner;

public class Main {

    // Assignment5 Main Method- Answers to Both Parts A and B.
    public static void main(String[] args) {

        // Creating a file of Datatype File to store location of the CustomerData text file from which data will be retrieved.
        File file = new File("src/CustomerData.txt");

        //Created customers LinkedQueue of DataType CustomerInfo.
        LinkedQueue<CustomerInfo> customers = new LinkedQueue<>();

        // Created variables f,normalNum, maxItems, totCus to store the count of fast lanes, normal lanes, maximum item to go for fast lane,
        // and total customer count inside the store respectively.
        int f = 0;
        int normalNum = 0;
        int maxItem = 0;
        int totCus = 0;

        //try catch method to read through the file and storing them in the above defined variables
        // and catching if console throws an exception.
        try {
            //Created Scanner named read to read all the contents of file being the parameter.
            Scanner read = new Scanner(file);
            // f stores the count for fast lanes
            f = read.nextInt();
            // normalNum stores the count for Normal lanes
            normalNum = read.nextInt();
            // maxItems stores the count for MaxItems allowed for Fast LANE.
            maxItem = read.nextInt();
            // totCus stores the count for total Customers
            totCus = read.nextInt();

            //for loop to add new customer details to customers LinkedQueue
            for (int i = 0; i < totCus; i++) {
                customers.enqueue(new CustomerInfo(read.nextInt()));
            }

        }
        //catch method to catch if the above try method throws an Exception and print the message it gives.
        catch (Exception o) {
            System.out.println("Exception thrown: "+o.getMessage());
        }

        //Created LinkedQueue Array named 'name' to store values of different names and initialize sum value for the LinkedQueue List later.
        // With items inside it being equal to summation of fast lanes and normal lanes.
        LinkedQueue[] name = new LinkedQueue[f + normalNum];

        // Adding names and initialized sum for all the lanes one by one using for loop
        for (int i = 0; i < name.length; i++) {
            name[i] = new LinkedQueue("lane" + i, 0);

        }

        //Using for loop, adding new LinkedQueue list for DataType CustomerInfo,
        //for registering all fast lanes till count for fast lanes.
        for (int i = 0; i < f; i++) {
            name[i] = new LinkedQueue<CustomerInfo>();

        }
        //Using for loop, adding new LinkedQueue list for DataType CustomerInfo,
        //for registering all normal lanes till count for fast lanes plus normal lanes from first normal lane.
        for (int i = f; i < f + normalNum; i++) {
            name[i] = new LinkedQueue<CustomerInfo>();

        }
        //Part A of the assignment to manage all the customers inside the Store with different count of items with fast way possible.
        System.out.println("PART A - Checkout lines and time estimates for each line\n");
        //Creating a boolean named pass initialized as true, to continue the loop until the last customer is sent to one of
        // the lanes.
        boolean pass = true;
        //While loop as pass being the deciding factor being false when no more customers exists in the waiting area.
        while (pass) {
            // If statement for ending the loop once no more customer remains in the customers LinkedQueue Data Structure.
            if (!customers.isEmpty()) {

                //Created an Integer named fSum inialized to store Sum of all fast lanes' Time Count
                int fSum = 0;
                //Created an Integer named nSum inialized to store Sum of all Normal lanes' Time Count
                int nSum = 0;

                //For Loop, Summing up all fast lanes' Time Count adding each customer Time count in each fast lane.
                for (int i = 0; i < f; i++) {
                    //Initialised an Integer named smallVal to store Time count of each lane and storing using name[i].setSum()
                    int smallVal = 0;
                    //Removing to add the sum and adding it back to the LinkedQueue as FIFO.
                    //And storing it in fSum.
                    for (int j = 0; j < name[i].size(); j++) {
                        CustomerInfo v = (CustomerInfo) name[i].dequeue();
                        fSum += v.getTime();
                        smallVal += v.getTime();
                        name[i].enqueue(v);
                        name[i].setSum(smallVal);
                    }
                }
                //For Loop, Summing up all normal lanes' Time Count adding each customer Time count in each normal lane.
                for (int i = f; i < f + normalNum; i++) {
                    //Initialised an Integer named smallVal to store Time count of each lane and storing using name[i].setSum()
                    int smallVal = 0;
                    //Removing to add the sum and adding it back to the LinkedQueue as FIFO.
                    //And storing it in fSum.
                    for (int j = 0; j < name[i].size(); j++) {
                        CustomerInfo v = (CustomerInfo) name[i].dequeue();
                        nSum += v.getTime();
                        smallVal += v.getTime();
                        name[i].enqueue(v);
                        name[i].setSum(smallVal);
                    }
                }

                // Creating a boolean named passs initialized as true, to enter the loop if all the lanes contains
                // non zero elements.
                boolean passs = true;

                //If statement to allow the customers who has items count less than or equal to the maxItems value.
                if (customers.peek().getItems() <= maxItem) {

                    //For loop to check if any lane has zero customer count, so to add them there first
                    // and breaking through the loop once that customer is added to that empty lane.
                    for (int i = 0; i < f + normalNum; i++) {
                        if (name[i].getSum() == 0) {
                            CustomerInfo v = customers.dequeue();
                            name[i].enqueue(v);
                            passs = false;
                            break;
                        }
                    }
                    // IF statement that comes in use once all lanes become non zero time count.
                    if (passs) {

                        //If statement to decide if the total time count of fast lanes is less than or equal to the Normal lanes.
                        // so that the less consumed lane can be filled first than the over allocated lane.
                        if (fSum <= nSum) {
                            //Initialised a LinkedQueue variable named min.
                            LinkedQueue min = name[0];
                            //For loop to count till the fast lanes count to count all the fast lanes.
                            for (int i = 0; i < f; i++) {
                                // If statement to find the least time value lane and change the min value to that value.
                                if (min.getSum() > name[i].getSum()) {
                                    min = name[i];
                                }
                                //If statement that runs once the loop reaches the end, found the lowest num list as min
                                // LinkedQueue, that customer can be added to that lane.
                                if (i == f - 1) {
                                    CustomerInfo v = customers.dequeue();
                                    min.enqueue(v);
                                    passs = false;
                                    break;
                                }
                            }
                        } else {
                            //Initialised a LinkedQueue variable named min.
                            LinkedQueue min = name[f];
                            //For loop to count till the fast lanes plus normal lane count to count all the normal lanes.
                            for (int i = f; i < f + normalNum; i++) {
                                // If statement to find the least time value lane and change the min value to that value.
                                if (min.getSum() > name[i].getSum()) {
                                    min = name[i];
                                }
                                //If statement that runs once the loop reaches the end, found the lowest num list as min
                                // LinkedQueue, that customer can be added to that lane.
                                if (i == f + normalNum - 1) {
                                    CustomerInfo v = customers.dequeue();
                                    min.enqueue(v);
                                    passs = false;
                                    break;
                                }
                            }
                        }
                    }
                }
                //If statement to allow the customers who has items count more than the maxItems value.
                // This loop will only iterate through the normal lanes as these customers have more than the maxItems
                //count and can't go to the fast lane.
                else {

                    //For loop to check if any normal lane has zero customer count, so to add them there first
                    // and breaking through the loop once that customer is added to that empty lane.
                    for (int i = f; i < f + normalNum; i++) {
                        if (name[i].getSum() == 0) {
                            CustomerInfo v = customers.dequeue();
                            name[i].enqueue(v);
                            passs = false;
                            break;
                        }
                    }

                    // IF statement that comes in use once all normal lanes become non zero time count.
                    if (passs) {
                        //Initialised a LinkedQueue variable named min.
                        LinkedQueue min = name[f];
                        //For loop to count till the fast lanes plus normal lane count to count all the normal lanes.
                        for (int i = f; i < f + normalNum; i++) {
                            // If statement to find the least time value lane and change the min value to that value.
                            if (min.getSum() > name[i].getSum()) {
                                min = name[i];
                            }
                            //If statement that runs once the loop reaches the end, found the lowest num list as min
                            // LinkedQueue, that customer can be added to that normal lane.
                            if (i == f + normalNum - 1) {
                                CustomerInfo v = customers.dequeue();
                                min.enqueue(v);
                                passs = false;
                                break;
                            }
                        }
                    }
                }
            }
            // Else Statement once there are no more customers inside the customers LinkedQueue<>.
            // and passes 'pass' as false to exit the loop as its empty.
            else {
                pass = false;
            }
        }

        //Created an Integer named fSum inialized to store Sum of all fast lanes' Time Count outside the above loop.
        int fSum = 0;
        //Created an Integer named nSum inialized to store Sum of all Normal lanes' Time Count outside the above loop.
        int nSum = 0;
        //For Loop, Summing up all fast lanes' Time Count adding each customer Time count in each fast lane.
        for (int i = 0; i < f; i++) {
            //Initialised an Integer named smallVal to store Time count of each lane and storing using name[i].setSum()
            int smallVal = 0;
            //Removing to add the sum and adding it back to the LinkedQueue as FIFO.
            //And storing it in fSum.
            for (int j = 0; j < name[i].size(); j++) {
                CustomerInfo v = (CustomerInfo) name[i].dequeue();
                fSum += v.getTime();
                smallVal += v.getTime();
                name[i].enqueue(v);
                name[i].setSum(smallVal);
            }
        }
        //For Loop, Summing up all normal lanes' Time Count adding each customer Time count in each normal lane.
        for (int i = f; i < f + normalNum; i++) {
            //Initialised an Integer named smallVal to store Time count of each lane and storing using name[i].setSum()
            int smallVal = 0;
            //Removing to add the sum and adding it back to the LinkedQueue as FIFO.
            //And storing it in nSum.
            for (int j = 0; j < name[i].size(); j++) {
                CustomerInfo v = (CustomerInfo) name[i].dequeue();
                nSum += v.getTime();
                smallVal += v.getTime();
                name[i].enqueue(v);
                name[i].setSum(smallVal);
            }
        }

        //For loop, printing all the fast lanes with the time they take and number of items that they have.
        for (int i = 0; i < f; i++) {
            System.out.println("CheckOut(Express) #" + (i + 1) + " (Est Time ="+ name[i].getSum()+"  s) = " + name[i]);
        }
        //For loop, printing all the normal lanes with the time they take and number of items that they have.
        for (int i = f; i < f + normalNum; i++) {
            System.out.println("CheckOut(Normal ) #" + (i + 1) + " (Est Time ="+name[i].getSum() +"  s) = " + name[i]);
        }


        //Part B
        System.out.println("");
        // Part B contains customers count inside the store after interval each of 30 sec until the whole store is empty.
        System.out.println("PART B - Number of customers in line after each 1/2 minute (30s) \n");

        System.out.print("t(s)     ");
        // For loop to print the line number on the top of the count for every lane
        for (int i = 0; i < f + normalNum; i++) {
            System.out.print("line " + (i + 1) + "    ");
        }

        // Creating a boolean named Epass initialized as true, to enter the loop if lane contains any customer, until there are
        // no customers in any of the lane.
        boolean Epass = true;
        // Initialised an Integer named tame with value 30.
        // for the time interval of 30 sec.
        int tame = 30;
        // Initialised an Integer named end with value 0.
        // To store the time interval after 30 sec each until the store empties.
        int end=0;
        // Initialised an Integer named count with value 0.
        // To count the number of times the loop is iterating.
        int count=0;

        while (Epass){
            //Incrementing the count by 1
            count+=1;
            System.out.println("");
            // Product of tame and count to give the current time after intervals.
            end = tame*count;
            System.out.print(end+"     ");
            //For loop to iterate through the name LinkedQueue<> containing many LinkedQueue inside it.
            for (int i = 0; i < f + normalNum; i++) {
                System.out.print("     ");
                // Try method to get the top element of that particular LinkedQueue<> and reducing 30 sec in that
                // for 30 sec time interval.
                try{
                 // Initialised an object named a of datatype CustomerInfo class, peeking on the LinkedQueue<>.
                CustomerInfo a = (CustomerInfo) name[i].peek();
                //setting time as reducing it by 30 sec for the time taken.
                a.setTime(a.getTime() - tame);

                // If statement to check if that after reduced getTime is equal or less than zero.
                // If its less than or equal to 0, then the absolute of that value is subtracted from next element in the LinkedQueue<>.
                if (a.getTime() <= 0) {
                    //try method to avoid exception for the end of the LinkedQueue<>.
                    try {
                            //Initialising an Integer v with Absolute value of that LinkedQueue<> Peek's Time.
                            int v = Math.abs(a.getTime());
                            //Then removing that customer from the list
                            name[i].dequeue();
                            //Peeking for the next customer in name[] LinkedQueue<>
                        // and initialising it in Object CustomerInfo named b.
                            CustomerInfo b = (CustomerInfo) name[i].peek();
                            // Then reducing the time taken by the prev absolute.
                            b.setTime(b.getTime() - v);

                    }
                    //Catch method to catch the exception
                    catch (Exception io) {
                        name[i].size() ;
                    }
                }
                // Printing the Particular LinkedQueue<> Size showing as a result of 30 sec interval.
                System.out.print(name[i].size() + "    ");

            }
                //catch method if the above try method peeking of the next element throws an exception
                // Printing the Particular LinkedQueue<> Size showing as a result of 30 sec interval.
                catch (Exception o){System.out.print(name[i].size() + "    ");}
            }

            // Setting the value for fSum to 0.
            fSum = 0;
            // Setting the value for nSum to 0.
            nSum = 0;
            //For Loop, Summing up all fast lanes' Time Count adding each customer Time count in each fast lane.
            for (int i = 0; i < f; i++) {
                //Initialised an Integer named smallVal to store Time count of each lane and storing using name[i].setSum()
                int smallVal = 0;
                //Removing to add the sum and adding it back to the LinkedQueue as FIFO.
                //And storing it in fSum.
                for (int j = 0; j < name[i].size(); j++) {
                    CustomerInfo v = (CustomerInfo) name[i].dequeue();
                    fSum += v.getTime();
                    smallVal += v.getTime();
                    name[i].enqueue(v);
                    name[i].setSum(smallVal);
                }
            }

            //For Loop, Summing up all normal lanes' Time Count adding each customer Time count in each normal lane.
            for (int i = f; i < f + normalNum; i++) {
                //Initialised an Integer named smallVal to store Time count of each lane and storing using name[i].setSum()
                int smallVal = 0;
                //Removing to add the sum and adding it back to the LinkedQueue as FIFO.
                //And storing it in nSum.
                for (int j = 0; j < name[i].size(); j++) {
                    CustomerInfo v = (CustomerInfo) name[i].dequeue();
                    nSum += v.getTime();
                    smallVal += v.getTime();
                    name[i].enqueue(v);
                    name[i].setSum(smallVal);
                }
            }

            // If statement that checks if no customer remains inside the lanes.
            // If no one waiting, then it passes Epass as false which breaks the above loop.
            if(fSum==0 && nSum==0)
                Epass=false;
        }
        //Print statement to print the Time taken to Clear the Store of all Customers.
        System.out.println(" ");
        System.out.println("\nTime to clear store of all customers = "+end+" s");
    }
}
