import java.awt.*;

/** Class Critter 
 *  Author: Jessica Aceret 
 *  Date: June 8, 2013
 * 
 *  This is the abstract class that provides instance variables and methods for the various Critter
 *  subclasses to overwrite and use. 
 * 
 **/ 
public abstract class Critter
{
  /** The x location of the MIDDLE of the Critter */
  protected int x;
  /** The y location of the MIDDLE of the Critter */
  protected int y;
  
  protected double distance;
  
  /** Create a new Critter at position xInit, yInit 
    * @param xInit The starting x location
    * @param yInit The starting y location
    */
  public Critter( int xInit, int yInit ) {
    x = xInit;
    y = yInit;
  }
  
  /** Return the x location of the Critter. */
  public int getX() { return x; }
  
  public int setX( int newX ) { 
    this.x = newX;
    return newX;
  }
  
  /** Return the y location of the Critter */
  public int getY() { return y; }
  
  public int setY( int newY ) { 
    this.y = newY;
    return newY;
  }
    
  /** Paint the Critter appropriately using the graphic object g */
  public abstract void paint( Graphics g );
  
  /** React appropriately to the Critter c, staying within the bounds
    * of the Rectangle r */
  public abstract void reactTo( Critter c, Rectangle r );

  // You will probably want to add another method (or more than one) 
  // here.  For example, a very useful method calculates the distance
  // from this Critter to an x, y location (and/or to another Critter).
    public double distanceFormula( int x1, int x2, int y1, int y2 ){
    double distance = Math.sqrt( (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2) );
    return distance;
  }

}