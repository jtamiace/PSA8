import java.awt.*;

/** Class Random
  * Author: Jessica Aceret
  * June 8, 2013
  * 
  * This class extends from the abstract class Critter and overwrites its 
  * abstract method to create an instance of Critter with the functionality
  * of making random movements on the CritterPanel. 
  */
public class Random extends Critter {  
  
  /** This is the constructor of the Random Critter. It calls on the Critter superclass
    * and uses its x and y coordinates.
    * @param xInit Passed in x coordinate from Critter superclass
    * @param yInit Passed in y coordinate from Critter superclass */
  public Random( int xInit, int yInit ) {
    super( xInit, yInit );
  }
  
    /** This method paints the Random Critter, using the passed in x and y coordinates 
    * as the center of the Random Critter for the GUI */
  public void paint( Graphics g ){
    g.setColor( new Color( 0,0,255 ) );
    g.drawLine( this.getX()-7, this.getY()-7, this.getX()+7, this.getY()+7 );
    g.drawLine( this.getX()+7, this.getY()-7, this.getX()-7, this.getY()+7 );
  }
  
  /** reactTo for the Random critter does not actually use the critter parameter,
    * but it uses a Random number generator within a certain bound and within the 
    * bounds of Rectangle r and uses that x and y coordinate as the coordinates of 
    * the Random Critter object.
    * @param critter Not used.
    * @param r Specifies the geometric context and bounds of the CritterPanel */
  public void reactTo( Critter critter, Rectangle r ) {
    java.util.Random rand = new java.util.Random();
    int randomNumX = rand.nextInt( 21 );
    randomNumX -= 10;
    int randomNumY = rand.nextInt( 21 );
    randomNumY -= 10;
    if ( r.contains( this.getX()+randomNumX, this.getY()+randomNumY ) ) {              
    this.setX( this.getX()+randomNumX );                
    this.setY( this.getY()+randomNumY );
    }
  }
  
}