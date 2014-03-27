import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/** Class CritterController
  * Author: Jessica Aceret
  * Date: June 8, 2013
  * 
  * This is the class that creates the GUI for the CritterWorld and basically
  * brings together everything created in Critter and its subclasses to create
  * an animation simulation of Critters that react to each other according to 
  * the rules they are programmed with. The user can control how many Critters
  * are added, what kind of Critters to add and when to start, stop and clear 
  * the simulation. */ 
public class CritterController extends JApplet implements Runnable
{
  
  /** Some constants that can be used throughout the class */
  private static final int CHASER = 1;
  private static final int RUNNER = 2;
  private static final int RANDOM = 3;
  private static final int CUSTOM = 4;
  
  /** How quickly the simulation runs.  The longer the delay, 
    * the slower the simulation */
  private static final int DELAY = 50;
  
  // Instance varaibles that maintain various parts of the simulation 
  // YOU CAN (AND PROBABLY WILL NEED TO) ADD MORE INSTANCE VARIABLES HERE
  private ArrayList<Critter> critterList;  /** The Critters in the world */
  private Interactor actor; /** The Interactor object that controls which Critters interact */
  private boolean running;  /** Whether or not the simulation is running */
  private CritterPanel world; /** The custom JPanel that displays the Critters */
  private JLabel stateLabel;  /** The label at the top of the panel */
  private JLabel critLabel; /** The label next to the Critter buttons */
  private int x; /** Holds the x value of where the mouse is clicked */
  private int y; /** Holds the y value of where the mouse is clicked */
  private int holder; /** Helper variable for determining which critter to add */
  private int labelChanger; /** Helps determine the status label */
  
  /** init creates the JPanel on which all the other GUI components will be added.
    * It also initializes the critterList and Interactor variables and starts
    * the simulation Thread. */
  public void init() {
    // Initialize the instance variables
    critterList = new ArrayList<Critter>();
    actor = new Interactor();
    running = false;
    
    // The overall layout is a BorderLayout
    setLayout( new BorderLayout() );
    
    // Add the CritterPanel in the middle
    // You will need to add a MouseListener to this panel.
    world = new CritterPanel();
    add(world, BorderLayout.CENTER);
    world.addMouseListener( new ClickListener() );
    
    // Call a helper method which adds the buttons and label at the top.
    // YOU WILL LIKELY NEED TO ADD TO THE HELPER METHOD, so be sure to check it out.
    JPanel topPanel = makeTopPanel();
    add( topPanel, BorderLayout.NORTH );
    
    // Call a helper method which adds the buttons and label at the bottom.
    // YOU WILL LIKELY NEED TO ADD TO THE HELPER METHOD, so be sure to check it out.
    JPanel botPanel = makeBottomPanel();
    add( botPanel, BorderLayout.SOUTH );
    
    // Start the simulation
    new Thread( this ).start();
  }
  
  // A helper method to arrange the buttons and label at the top 
  // (i.e., the ones that control the simulation).  You will probably
  // need to add to this method, e.g. to add listeners on the buttons.
  private JPanel makeTopPanel() {
    JPanel topPanel = new JPanel();
    topPanel.setLayout( new GridLayout( 1, 2 ) );
    JPanel topButPanel = new JPanel();
    
    JButton start = new JButton( "Start" );
    start.addActionListener( new CritterListener() {
      public void actionPerformed( ActionEvent e ) {
        if ( critterList.size() >= 2 ) {
        running = true;
        stateLabel.setText( "Simulation is running.");
        }}});
    
    JButton stop = new JButton( "Stop" );
    stop.addActionListener( new CritterListener() {
      public void actionPerformed( ActionEvent e ) {
        if ( running ) {
        running = false;
        stateLabel.setText( "Simulation is stopped." );
        }}});
    
    JButton clear = new JButton( "Clear" );
    clear.addActionListener( new CritterListener() {
      public void actionPerformed( ActionEvent e ) {
        if ( critterList.size() > 0 ) {
        critterList.clear();
        stateLabel.setText( "Cleared. Please add two or more critters." );
        running = false;
        repaint();
        }}});
    
    stateLabel = new JLabel( "Two more Critters to run simulation" );
    topPanel.add( stateLabel );
    topButPanel.add( start );
    topButPanel.add( stop );
    topButPanel.add( clear );
    topPanel.add( topButPanel );
    
    return topPanel;
  }
  
  // A helper method to arrange the buttons and label at the bottom 
  // (i.e., the ones that control adding critters).  You will probably
  // need to add to this method, e.g. to add listeners on the buttons.
  private JPanel makeBottomPanel()
  {
    JPanel botPanel = new JPanel();
    JPanel botButPanel = new JPanel();
    botButPanel.setLayout( new GridLayout( 1, 4 ) );
    botPanel.setLayout( new GridLayout( 2, 1 ) );
    
    // You will need to create and add action listeners to these buttons
    JButton chase = new JButton( "Chaser" );
    chase.addActionListener( new CritterListener() {
      public void actionPerformed( ActionEvent e ) { 
        holder = CHASER;
        labelChanger = 1;
        critLabel.setText("Click in the world to place Critter");
      }});
    
    JButton runner = new JButton( "Runner" );
    runner.addActionListener( new CritterListener() {
      public void actionPerformed( ActionEvent e ) { 
        holder = RUNNER; 
        labelChanger = 1;
        critLabel.setText("Click in the world to place Critter");
      }});                             
    
    JButton rand = new JButton( "Random" );
    rand.addActionListener( new CritterListener() {
      public void actionPerformed( ActionEvent e ) { 
        holder = RANDOM;
        labelChanger = 1;      
        critLabel.setText("Click in the world to place Critter");
      }}); 
    
    JButton custom = new JButton( "Custom" );
    custom.addActionListener( new CritterListener() {
      public void actionPerformed( ActionEvent e ) { 
        holder = CUSTOM;
        labelChanger = 1;      
        critLabel.setText("Click in the world to place Critter");
      }}); 
    
    botButPanel.add( chase );
    botButPanel.add( runner );
    botButPanel.add( rand );
    botButPanel.add( custom );
    
    critLabel = new JLabel( "Select which Critter to place" );
    botPanel.add( critLabel );
    botPanel.add( botButPanel );

    return botPanel;
  }
  
  /**
   * Implemented from the Runnable class -- runs when a thread is started.
   */
  // Note: This method won't do anything until you implement functionality in 
  // the Interactor.
  public void run() {
    try {
      while( true ) {
        if ( running ) {
          Rectangle r = new Rectangle(world.getSize().width,world.getSize().height);
          for ( Critter crit : critterList ) {
            actor.interact( crit, critterList, r );
          }
          repaint();
        } Thread.sleep( DELAY );
      }} catch (InterruptedException ex) {}
  }
  
  /**
   * Panel that knows how to paint Critters (if they can paint themselves)
   */
  class CritterPanel extends JPanel {
    /**  This method will paint all the critters. */
    protected void paintComponent( Graphics g ) {
      super.paintComponent( g );
      for ( Critter critter : critterList ) {
        critter.paint( g );
      }}
  }
  
  /** CritterListener listens for which button is clicked */
  class CritterListener implements ActionListener {
    public void actionPerformed( ActionEvent e ) {} }
  
  /** ClickListener listens for when the CritterPanel is clicked and adds 
    * the correct critter to the CritterPanel */
  class ClickListener implements MouseListener {
    public void mouseClicked( MouseEvent e ) {
      e.getSource();
      x = e.getX(); 
      y = e.getY();
      if ( holder == RUNNER ) 
        critterList.add( new Runner( x, y ) );
      else if ( holder == CHASER )
        critterList.add( new Chaser( x, y ) );
      else if ( holder == RANDOM )
        critterList.add( new Random( x, y ) );
      else if ( holder == CUSTOM )
        critterList.add( new Custom( x, y ) );
      holder = -1;
      if ( critterList.size() == 1 )
        stateLabel.setText( "One more Critter to run simulation" );
      if ( critterList.size() >= 2 )
        stateLabel.setText( "Simulation is ready to run!" );
           
      labelChanger = 0;
      if ( labelChanger == 0 )
        critLabel.setText("Select which Critter to place");

      repaint();
    }  
    public void mousePressed( MouseEvent e ) {}
    public void mouseReleased( MouseEvent e ) {}
    public void mouseEntered( MouseEvent e ) {}
    public void mouseExited( MouseEvent e ) {}
  }
  
  
  
  
}