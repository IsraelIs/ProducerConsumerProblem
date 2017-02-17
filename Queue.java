//Queue
//Isaac Israel
//6/20/16
public class Queue
{

  private class QCell
  {

    private Object QItem;
    private QCell  QLink;

    private QCell(Object head, QCell NextInQ)
    {
      QItem = head; QLink=NextInQ; 
    }
  }
  
  private QCell First;   
  private QCell Rest;  
  private QCell Last;
  
  private int Waiting; 
 
  public Queue()
  {
    First = null;
    Rest = null; 
    Last = null;
    Waiting=0;
  }
 
  public boolean QIsEmpty(){
    return (Waiting == 0);       
  }
  public int GetWaiting(){
    return Waiting;
  }
  public void AddToQ ( Object EndofQ )
  {

    if (QIsEmpty())            
    {
      Last = new QCell(EndofQ,Last);   
      First = Last;
      Rest = First.QLink;;                      
      Waiting=1;                     
    }
    else                              
    {
      Last.QLink = new QCell(EndofQ,Last.QLink);  
      Last = Last.QLink;
      Waiting++;                                 
    }
  }
 
  /*
   * Remove the head of the Queue   
   */
  public void TakeOffQ()
  {
    if (Waiting<=1)                                
    {                                            
      First = null; Last=null; Rest=null;
      Waiting=0;
    }
    else
    {
      First = First.QLink;                         // Find the 'new' head of the Queue
      Waiting--;                                   
      if (Waiting==1)                              // Deal with the case that there's
      {                                            // exactly one item left in this Queue
        Last = First;
        Rest = null;      
      }
      else
        Rest = First.QLink;                        
    }                                          
  }
  
  /*
   * Returns the head of the queue                        
  */
  public Object GetFirstInQ()
    {
    if (!(Waiting==0))                
      return First.QItem;             
    else
      return null;                    // If the Queue is empty then return a null reference
    }
  
  /*
   * Return a reference to the REST (All but the head) of the Queue
   */
  public Queue GetRestOfQ()
  {
    Queue temporary= new Queue();
    temporary.First=First; temporary.Rest=Rest;
    temporary.Last=Last;
    temporary.Waiting = Waiting;
    temporary.TakeOffQ();
    return temporary;
  }

  public String toString()
  {
    String res = new String();
    Queue temporary = new Queue();
    temporary.First = First; 
    temporary.Rest = Rest; 
    temporary.Last = Last;
    temporary.Waiting=Waiting;
    while (!temporary.QIsEmpty())
    {
      res = res+(temporary.GetFirstInQ()).toString()+"\n";
      temporary = temporary.GetRestOfQ();
    }
    return res;
  }     
}