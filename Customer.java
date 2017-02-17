//Customer Class
// Isaac Israel
//6/20/16


public class Customer
{
    private int id;                 // Customer number
    private int numItems;           // Number of Items
    private int arrivalTime;        // Time of arrival
    private int departTime;         // Time of departure
    private int cashierNum;         // Line Chosen/Assigned

    
    public void setId(int num)
    {
        id = num;
    } 
    public void setArrival(int time)
    {
        arrivalTime = time;
    }
    public void setDepart(int time){ 
        departTime = time;
    }
  

    public void setCashier(int num){
        cashierNum = num;
    }
        public void setItems(int items){
        numItems = items;
    }  

      
    public int getCashier(){ 
        return cashierNum;
    }  
    public int getId(){ 
        return id;
    }  
    public int getItems(){ 
        return numItems;
    }

      
    public int getTime(){ 
        if(departTime == 0)
            return (numItems);

        return (departTime - arrivalTime);
    }
    public String toString(){
        String client = "";
        client ="Customer has "+ numItems + " Items";
        return client;
    }
}
