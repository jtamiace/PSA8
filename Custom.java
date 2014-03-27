import java.awt.*;

/** Class Custom
  * Author: Jessica Aceret
  * June 8, 2013
  * 
  * This class extends from the abstract class Critter and overwrites its 
  * abstract method to create an instance of Critter with the functionality
  * of "chasing" the closest Critter on the CritterPanel. While chasing, this
  * Critter also changes colors from red, green, and blue. They're basically
  * fabulous Chasers.
  */
public class Custom extends Critter
{
  public final int RED = 0;
  public final int BLUE = 2;
  public final int GREEN = 4;  
  public final int LIMIT = 6;
  public int colorChanger = 0; /**This int and above moderate the color of Custom*/
  public int closestX = this.getX();
  public int closestY = this.getY();
  
  /** This is the constructor of the Custom. It calls on the Critter superclass
    * and uses its x and y coordinates.
    * @param xInit Passed in x coordinate from Critter superclass
    * @param yInit Passed in y coordinate from Critter superclass */
  public Custom( int xInit, int yInit ) {
    super( xInit, yInit );
  }
  
  /** This method paints the Custom, using the passed in x and y coordinates 
    * as the center of the Custom for the GUI */
  public void paint( Graphics g ){
    if ( colorChanger < BLUE ) {
      g.setColor( new Color(255,0,0) );
      g.fillRect( this.getX()-7, this.getY()-7, 15, 15 );
    }
    else if ( colorChanger >= BLUE && colorChanger < GREEN ) {
      g.setColor( new Color(0,0,255) );
      g.fillRect( this.getX()-7, this.getY()-7, 15, 15 );
    }
    else if ( colorChanger >= GREEN && colorChanger < LIMIT ) {
      g.setColor( new Color(0,255,0) );
      g.fillRect( this.getX()-7, this.getY()-7, 15, 15 );
    }
  }
  
  /** This method uses the coordinates of the passed in Critter and calculates 
    * the shortest distance from the passed in Critter to the Custom Critter by looping 
    * through the surrounding coordinates of the Custom Critter. It then checks to see 
    * if that move would be valid and then resets the x and y coordinates of 
    * the Custom Criter to be the coordinates that would have the shortest distance 
    * from the passed in Critter. 
    * @param critter The critter whose x and y coordinates will be used to 
    *                calculate the shortest distance.
    * @param r Specifies the geometric context and bounds of the CritterPanel */
  public void reactTo( Critter critter, Rectangle r ) {
    if ( colorChanger == LIMIT )
      colorChanger = 0;
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
    colorChanger++;
    this.setX( closestX );
    this.setY( closestY );
  }
 
}