import java.awt.*;

/** Class Runner
  * Author: Jessica Aceret
  * June 8, 2013
  * 
  * This class extends from the abstract class Critter and overwrites its 
  * abstract method to create an instance of Critter with the functionality
  * of "running" from the closest Critter on the CritterPanel. 
  */
public class Runner extends Critter
{
  public int farX = this.getX();
  public int farY = this.getY();
  
   /** This is the constructor of the Runner. It calls on the Critter superclass
    * and uses its x and y coordinates.
    * @param xInit Passed in x coordinate from Critter superclass
    * @param yInit Passed in y coordinate from Critter superclass */
  public Runner( int xInit, int yInit ) {
    super( xInit, yInit );
  }
  
    /** This method paints the Chaser, using the passed in x and y coordinates 
    * as the center of the Chaser for the GUI */
  public void paint( Graphics g ) {
    g.setColor( new Color(0,255,0) );
    g.fillRect( farX-7, farY-7, 15, 15 );
  }
  
   /** This method uses the coordinates of the passed in Critter and calculates 
    * the shortest distance from the passed in Critter to the Runner by looping 
    * through the surrounding coordinates of the Runner. It then checks to see 
    * if that move would be valid and then resets the x and y coordinates of 
    * the Runner to be the coordinates that would have the longest distance 
    * from the passed in Critter. 
    * @param critter The critter whose x and y coordinates will be used to 
    *                calculate the farthest distance.
    * @param r Specifies the geometric context and bounds of the CritterPanel */
public void reactTo( Critter critter, Rectangle r ) {
double farDist = Double.MIN_VALUE;
    for ( int possibleX = -1; possibleX <= 1; possibleX++ ) {
      for ( int possibleY = -1; possibleY <= 1; possibleY++ ) {
        if ( r.contains( farX+possibleX, farY+possibleY ) ) {
          double distance = this.distanceFormula( farX+possibleX,critter.getX(),
                                          farY+possibleY,critter.getY() );
          if ( distance > farDist ) { 
            farDist = distance;
            farX = farX+possibleX;
            farY = farY+possibleY;
          }
        }
      }
    }
    this.setX( farX );
    this.setY( farY );
  }
  
}