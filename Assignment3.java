/*#################################
 * Isaac Israel                   #
 *7/8/16                          #
 *Assignment 3                    #
 *Simulation of Prod./consum. prob#
 #################################*/
//Used Queue because it's FIFO
import java.util.*;
import edu.princeton.cs.algs4.*;


public class Assignment3{
    static int maxcust;               
    static int maxcash;                       
    static int maxItemAmount;                         
    static int expressQueues;                        

    static int customersThrough = 0;               

    static Queue[] Cashier;                 
    static int[] TimeLeftAtCashier;                
    static Customer[] CustomerStats;               

 
    public static void SetParameters(){  //set param
    
        maxcash = 9;
        maxcust = 1000;  //change to 1000*31 if monthly
        maxItemAmount = 100;
        expressQueues = 1;
    }

   
    public static void StartUpCashiers(){ //starts Cashier sim
    
        Cashier = new Queue[maxcash + expressQueues];
        TimeLeftAtCashier = new int[maxcash + expressQueues];

        for (int i=0; i<maxcash + expressQueues; i++){
            Cashier[i] = new Queue();
        }
    }

    /*
     * Simulation complete once 1000 done
     */   
    public static boolean AllCustomersServed(){
        boolean done = false;

        if (customersThrough == 1000){   //change if month
            done = true;
        }
        return done;
    }

    //Find Average checkout time customers w 12 or less
    public static double AvgU12 (){
        double total = 0;
        int count = 0;
        double avg;

        for (int i=0; i<1000; i++){
            if(CustomerStats[i].getItems() <= 12) {
                total += (double)CustomerStats[i].getTime();
                count++;
            }
        }
        avg = (total/(double)count);
        return avg;
    }

    //Find Fastest checkout 12 Items or less
    public static double FU12 (){
        double fastest = 1000000000; //setting abritrarily high

        for (int i=0; i<1000; i++){
            if(CustomerStats[i].getItems() <= 12){
                if((double)CustomerStats[i].getTime() < fastest){
                    fastest = (double)CustomerStats[i].getTime();
                }
            }
        }
        return fastest;
    }

    //Find Slowest time 12 Items or less  
    public static double SU12 (){
        double slowest = 0;

        for (int i=0; i<1000; i++){
            if(CustomerStats[i].getItems() <= 12){
                if((double)CustomerStats[i].getTime() > slowest){
                    slowest = (double)CustomerStats[i].getTime();
                }
            }
        }
        return slowest;
    }

    //Find Average checkout w/ 13 or more Items
    public static double Avg12 (){
        double total = 0;
        int count = 0;
        double avg;

        for (int i=0; i<1000; i++){ //monthly change
       
            if(CustomerStats[i].getItems() > 12){
                total += (double)CustomerStats[i].getTime();
                count++;
            }
        }
        avg = (total/(double)count);
        return avg;
    }

    //Find Fastest checkout time for customers w/ 13 or more Items
    public static double F12 (){
        double fastest = 1000000;
        

        for (int i = 0 ; i<1000; i++){
            if(CustomerStats[i].getItems() > 12){
                if((double)CustomerStats[i].getTime() < fastest){
                    fastest = (double)CustomerStats[i].getTime();
                }
            }
        }
        return fastest;
    }

    //Find Slowest checkout 13 or more Items  
    public static double S12 (){
        double slowest = 0;

        for (int i=0; i<1000; i++){
            if(CustomerStats[i].getItems() > 12){
                if((double)CustomerStats[i].getTime() > slowest){
                    slowest = (double)CustomerStats[i].getTime();
                }
            }
        }
        return slowest;
    }
    public static double median(){
        double [] array = new double[1000];
        
        for(int i = 0; i < 1000; i++){
            
           array[i] = CustomerStats[i].getTime();
            
        }
        Arrays.sort(array);
        
                
            double median = array[500];  //also change here
            
        return median;
    }
    public static void calculateMode(){
        double [] array = new double[1000]; //also change
            
        for(int i = 0; i < 1000; i ++){//days in month change
      
            array[i] = CustomerStats[i].getTime();
        }
        Arrays.sort(array);
        
        int counterA = 0, counterB = 0;
        double modeNum1 = 0, modeNum2 = 0;
        
        for(int i = 0; i < 100; i++){
            modeNum1 = array[i];
            counterA = 1;
            
            for(int j = i + 1; j < 100; j++){
                if(modeNum1 == array[j]){
                    counterA++;
                }
            }
            
            if(counterA > counterB){
                modeNum2 = modeNum1;
                counterB = counterA;
            }
            
            else if(counterA == counterB){
                modeNum2 = Math.min(modeNum2, modeNum1);
            }
        }
        if(counterA > counterB){
            StdOut.println("Mode: " + modeNum1);
        }
        else if(counterB > counterA){
            StdOut.println("Mode: " + modeNum2);
        }
        else if(counterA == counterB){
            StdOut.println("Mode: " + modeNum1 + " and " + modeNum2);
        }
    }
     //tried using bag for mode and median but couldn't figure it out

    
    public static void OutputCustomerStats(){
        System.out.printf("Customer Records after Completion\n");
        System.out.printf("==============================================================\n");
        System.out.printf("Average checkout time for customers w/ 12 items or less: %.2f ticks(secs)\n", AvgU12());
        System.out.printf("Fastest checkout time for customers w/ 12 items or less: %.2f ticks(secs)\n", FU12());
        System.out.printf("Slowest checkout time for customers w/ 12 items or less: %.2f ticks(secs)\n", SU12());
        System.out.printf("==============================================================\n");
        System.out.printf("Average checkout time for customers w/ 13 or more items: %.2f ticks(secs)\n", Avg12());
        System.out.printf("Fastest checkout time for customers w/ 13 or more items: %.2f ticks(secs)\n", F12());
        System.out.printf("Slowest checkout time for customers w/ 13 or more items: %.2f ticks(secs)\n", S12());
        System.out.printf("==============================================================\n");
        System.out.printf("Overall average checkout time for customers: %.2f ticks(secs)\n", (AvgU12()+Avg12()/2));

        if(FU12() < F12()){
            System.out.printf("Overall fastest checkout time for customers: %.2f ticks(secs)\n", FU12());
        } else
            System.out.printf("Overall fastest checkout time for customers: %.2f ticks(secs)\n", F12());

        if(SU12() > S12()){
            System.out.printf("Overall slowest checkout time for customers: %.2f ticks(secs)\n", SU12());
        } else
            System.out.printf("Overall slowest checkout time for customers: %.2f ticks(secs)\n", S12());
        System.out.printf("============================================================\n");
        System.out.printf("The median value of the array %.2f\n", median());
        calculateMode();
    }

    
     // Chooses A Queue (For Customers with more than 13 items)
     
    public static int ChooseQueue ( Queue[] CheckOuts ){
        int shortest = 1;

        for (int i=1; i<maxcash; i++){
            if (CheckOuts[i].GetWaiting() < CheckOuts[shortest].GetWaiting()){
                shortest = i;
            }
        }
        return shortest;
    }

    
     //Chooses An Express Queue 12 or less items  
   
    public static int ChooseExpressQueue ( Queue[] CheckOuts ){
        int shortest = maxcash;

        for (int i=maxcash; i<10; i++){
            if (CheckOuts[i].GetWaiting() < CheckOuts[shortest].GetWaiting()){
                shortest = i;
            }
        }
        return shortest;
    }

    // The number of items bought by a new customer is randomly chosen to be a value between 1 and MAX_ITEMS.                              
    public static int NumberofItems(){
        return (1+(int)Math.rint((maxItemAmount-1)*Math.random()));
    }

    public static void main(String[] args){
        int put_on_queue;                 // To index CheckOutQueues[]
        int firstinQ;                     // Used in updating Queue/Customer
        int nextinQ;
        int time = 0;
        int interval = (int)Math.rint((10*Math.random()));
        Integer CustomerIndex;   // Need Integer to store int index on Queue

        SetParameters();        
        StartUpCashiers();                              // Initialize Cashiers
        CustomerStats = new Customer[maxcust];  // Initialize customer stats.

        //goes through 1000 customers
        while  (!AllCustomersServed()){
            // Update CheckOut Queue
            for (int cashier = 0; cashier < maxcash; cashier++){
               
                if (!Cashier[cashier].QIsEmpty()){
                    firstinQ = Integer.valueOf(Cashier[cashier].GetFirstInQ().toString()).intValue();
                    TimeLeftAtCashier[cashier]--;

                    // See if this customer served if so update the customer stats and queue        
                    if (TimeLeftAtCashier[cashier] < 1){
                      
                        if(time == 0)
                            CustomerStats[firstinQ].setDepart(time+CustomerStats[firstinQ].getItems());
                        else
                            CustomerStats[firstinQ].setDepart(time);

                        Cashier[cashier].TakeOffQ();             

                        // If any customers are left, then serve the next in this Queue 
                        if (!Cashier[cashier].QIsEmpty()){
                            nextinQ = Integer.valueOf((Cashier[cashier].GetFirstInQ()).toString()).intValue();
                            TimeLeftAtCashier[cashier]=CustomerStats[nextinQ].getItems();
                        }
                    }
                }
            }

            // Based on the random interval, check if time for new customer then initiate a new Customer and choose a CheckOutQueue based on items
            if ( interval == 0 ){
                CustomerStats[customersThrough] = new Customer();
                CustomerStats[customersThrough].setArrival(time);
                CustomerStats[customersThrough].setItems(NumberofItems());
                CustomerStats[customersThrough].setId(customersThrough);

                if(expressQueues != 0){
                    if(CustomerStats[customersThrough].getItems() < 12){
                        put_on_queue = ChooseExpressQueue(Cashier);
                    } else
                        put_on_queue = ChooseQueue(Cashier);
                } else
                    put_on_queue = ChooseQueue(Cashier);

                // If the selected Cashier is not serving another customer, newly added customer will immediately be served

                if ( Cashier[put_on_queue].QIsEmpty() )
                    TimeLeftAtCashier[put_on_queue] = CustomerStats[customersThrough].getItems();

                CustomerStats[customersThrough].setCashier(put_on_queue);
                CustomerIndex = new Integer(customersThrough);
                Cashier[put_on_queue].AddToQ(CustomerIndex);

                customersThrough++;            // One more customer has been processed/served
                //Set new interval
                interval = (int)Math.rint((10*Math.random()));
            } 
            else 
                interval--;
            time++;    
        }
       
        
        for(int i=0;i<1000;i++){ //do NOT multiply by 31 for months
        System.out.println(CustomerStats[i].toString());
        }
        OutputCustomerStats();
    }
}
