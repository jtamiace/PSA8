import java.awt.*;

/** Class Chaser
  * Author: Jessica Aceret
  * June 8, 2013
  * 
  * This class extends from the abstract class Critter and overwrites its 
  * abstract method to create an instance of Critter with the functionality
  * of "chasing" the closest Critter on the CritterPanel. 
  */
public class Chaser extends Critter {

  public int closestX = this.getX(); /** sets the x-coordinate of the Chaser */
  public int closestY = this.getY(); /** sets the y-coordinate of the Chaser */
  
  /** This is the constructor of the Chaser. It calls on the Critter superclass
    * and uses its x and y coordinates.
    * @param xInit Passed in x coordinate from Critter superclass
    * @param yInit Passed in y coordinate from Critter superclass */
  public Chaser( int xInit, int yInit ) {
    super( xInit, yInit );
  }
  
  /** This method paints the Chaser, using the passed in x and y coordinates 
    * as the center of the Chaser for the GUI */
  public void paint( Graphics g ){
    g.setColor( new Color(255,0,0) );
    g.fillOval( closestX-7, closestY-7, 15, 15 );
  }
  
  /** This method uses the coordinates of the passed in Critter and calculates 
    * the shortest distance from the passed in Critter to the Chaser by looping 
    * through the surrounding coordinates of the Chaser. It then checks to see 
    * if that move would be valid and then resets the x and y coordinates of 
    * the chaser to be the coordinates that would have the shortest distance 
    * from the passed in Critter. 
    * @param critter The critter whose x and y coordinates will be used to 
    *                calculate the shortest distance.
    * @param r Specifies the geometric context and bounds of the CritterPanel */
public void reactTo( Critter critter, Rectangle r ) {
double closestDist = Double.MAX_VALUE;
    for ( int possibleX = -1; possibleX <= 1; possibleX++ ) {
      for ( int possibleY = -1; possibleY <= 1; possibleY++ ) {
        if ( r.contains( closestX+possibleX, closestY+possibleY ) ) {
          double distance = this.distanceFormula( closestX+possibleX,critter.getX(),
                                          closestY+possibleY,critter.getY() );
          if ( distance < closestDist ) { 
            closestDist = distance;
            closestX = closestX+possibleX;
            closestY = closestY+possibleY;
          }
        }
      }
    }
    this.setX( closestX );
    this.setY( closestY );
  }

}