import java.util.*;
import java.awt.*;

/** Class Interactor
  * Author: Jessica Aceret
  * Date: June 8, 2013
  * 
  * This class is designed to determine which Critter will interact with the Critter that
  * this method is called upon. 
 */
public class Interactor 
{
  private double distance;
  private double closestDist;
  /** This method takes a Critter and a list of Critters.  It finds the 
    * Critter from the list of Critters that c will interact with.  Usually
    * this is the closest Critter in the list, but you might vary this slightly
    * (See the PSA instructions).  Then it asks the Critter c to reactTo
    * that Critter.  The Rectangle r is the bounds in which 
    * the Critters must stay, and should be passed to the reactTo method.
    */  
  public void interact(Critter c, ArrayList<Critter> cList, Rectangle r) 
  {
    Critter closestCritter = null;
    closestDist = Double.MAX_VALUE;
    if ( cList.size() == 1 )
      return;
    for ( int i = 0; i < cList.size() ; i++ ) {
      int x1 = c.getX(), y1 = c.getY();
      if ( cList.get(i) != c ) {
        int x2 = cList.get(i).getX(), y2 = cList.get(i).getY();
        distance = c.distanceFormula( x1,x2,y1,y2 );        
        if ( distance < closestDist ) {
          closestCritter = cList.get(i);
          closestDist = distance;
        }
      }
    }
    c.reactTo( closestCritter, r );
    
  }
  
}