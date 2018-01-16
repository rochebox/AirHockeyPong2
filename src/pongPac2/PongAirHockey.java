package pongPac2;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/*   Version 1 */
public class PongAirHockey extends JPanel implements ActionListener, KeyListener
{
    //
    //Data  -- practicing "Data Hiding"
    private int pWidth;
    private int pHeight;
    private Color bColor;
    
    //String imageFileName = "johnwarren.png";
    // Image johnWarrenImage;
    
    private String iFileStringName = "SMHockeyLogoblue2.png";
    private Image appleImage;
    private int scaleFactorW = 45;
    private int scaleFactorH = 100;
    
    private int goalWidth;
    private int goalMouth;
    private int goalYTop;
    private int goalYBottom;
    
    // puck related variables 
    private int puckDiameter;
    private Puck thePuck;
    
    // NOW MAKE PADDLES
    private int paddleDiameter;
    private Paddle rightPaddle;
    private Paddle leftPaddle;
    
    // set up a timer...
    private Timer t;
    
    
  
    
    
    
    
    
    
    BufferedImage bJohnWarrenImage;
  
  
    //Constructors(0---or many)
    
   public PongAirHockey(int w, int h)
   {
         pWidth = w;
         pHeight = h;
         bColor = new Color(222, 246, 247);
         this.setSize(pWidth, pHeight);
         this.setBackground(bColor);
         
         goalWidth =(int) (pWidth/10);
         goalMouth = (int)(pHeight/2.5);
         goalYTop =  (int)( (pHeight/2) - (pHeight/5) );
         goalYBottom = goalYTop + goalMouth;
     
         try{
           File iAppleFile = new File(iFileStringName);
           appleImage = ImageIO.read(iAppleFile);
           
         } catch (IOException e){
           System.out.println("AAHHHHHHHHH -- Terrible Error");
           System.out.println(e.toString());
           
         }
     
     
         // set up Puck stuff
         puckDiameter = (int)(pWidth/12);
         thePuck = new Puck(puckDiameter, this);
         //default speeds are 0
         //thePuck.setXSpeed(100);
         //thePuck.setYSpeed(-100);
         
         
     
         //instantiate the paddles
         paddleDiameter = (int)(pWidth/15);
         rightPaddle = new Paddle(paddleDiameter, Paddle.RIGHT_PADDLE, this );
         leftPaddle =  new Paddle(paddleDiameter, Paddle.LEFT_PADDLE, this );
     
         setFocusable(true);
         addKeyListener(this);
         
         // more timer set up....
         t = new Timer(60, this);
         t.start();
     
   }
  
  
  // Methods
   //Overriding Method
   @Override
   public void paintComponent(Graphics g)
   {
     
     g.setColor(bColor);
     g.fillRect( 0, 0, pWidth,  pHeight );
     
     //double alpha = 0.6; 
     //g2.setComposite(AlphaComposite.getInstance(
      //   AlphaComposite.SRC_IN, (float) alpha));
  
   
     g.drawImage(
         appleImage, 
         (int)(pWidth/2-pHeight/4) + (scaleFactorW/2), 
         (int)(pHeight/4) + (scaleFactorH/2),
         (int)(pHeight/2) - scaleFactorW, 
         (int)(pHeight/2) - scaleFactorH,
         this);
         
   Graphics2D g2 = (Graphics2D) g;
       //draw lines...
      g2.setColor(Color.RED);
       g2.fillRect((int)(pWidth/2)-3, 0, 6, pHeight);
       
     //center circle
   
     g2.setStroke(new BasicStroke(4));
         g2.drawOval(
         (int)(pWidth/2-pHeight/4),  /* x */
         (int)(pHeight/4),  /* y */
         (int)(pHeight/2), 
         (int)(pHeight/2)
         );
     
      // make the left goal...
        
         
         
         g2.setColor(Color.WHITE);
         g2.fillRect(
             0,                                                     // xLoc of goal
             (int)( (pHeight/2) - (pHeight/5) ),    //yLoc of goal...
             goalWidth,
             (int)(pHeight/2.5));
         g2.setColor(Color.RED);
         g2.drawRect(
             0, 
             (int)( (pHeight/2) - (pHeight/5) ), 
             goalWidth, 
             (int)(pHeight/2.5)
             );
         
         g2.fillRect( (int)(goalWidth), 0, 4, pHeight );
         
         //make the right side goal
         
         
         g2.setColor(Color.WHITE);
         g2.fillRect(
             pWidth - goalWidth,                         // xLoc of goal
             (int)( (pHeight/2) - (pHeight/5) ),    //yLoc of goal...
             goalWidth,
             (int)(pHeight/2.5));
         g2.setColor(Color.RED);
         g2.drawRect(
             pWidth - goalWidth, 
             (int)( (pHeight/2) - (pHeight/5) ), 
             goalWidth, 
             (int)(pHeight/2.5)
             );
         
         g2.fillRect( pWidth - goalWidth, 0, 4, pHeight );
         
         
         thePuck.drawPuck(g);
         rightPaddle.drawPaddle(g);
         leftPaddle.drawPaddle(g);
         
         
   }
   
   // Accessor Methods
   public int getWidth() 
   {
     return pWidth;
   }
   
   public int getHeight()
   {
     return pHeight;
   }
   
   public int getGoalWidth() 
   {
       return goalWidth;
   }
   
   public int getGoalMouth() 
   {
       return goalMouth;
   }
   
   public int getGoalYTop() 
   {
       return goalYTop;
   }
   
   public int getGoalYBottom() 
   {
       return goalYBottom;
   }
   
   
   
   
   
   
   
   
   
   public void drawJohnWarrenImage(Graphics2D g2)
   {
     
     
   }
   
   
   public static BufferedImage makeImageTranslucent(BufferedImage source,
       double alpha) {
     BufferedImage target = new BufferedImage(source.getWidth(),
         source.getHeight(), java.awt.Transparency.TRANSLUCENT);
     // Get the images graphics
     Graphics2D g = target.createGraphics();
     // Set the Graphics composite to Alpha
     g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
         (float) alpha));
     // Draw the image into the prepared reciver image
     g.drawImage(source, null, 0, 0);
     // let go of all system resources in this Graphics
     g.dispose();
     // Return the image
     return target;
   }


  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
    //System.out.println("I want to move!!!");
    
    thePuck.movePuck();
    leftPaddle.movePaddle();
    rightPaddle.movePaddle();
    repaint();
    
  }


  public void keyTyped(KeyEvent e)
  {
    // TODO Auto-generated method stub
    
  }


  public void keyPressed(KeyEvent e)
  {
    // TODO Auto-generated method stub
    //KeyCodes that I'll forget
    /*
     * Space = 32
     * w = 87
     * a = 65
     * s = 83
     * d = 68
     * x = 88
     * UP = 38
     * Down = 40
     * Right = 39
     * Left = 37
     */
    
    System.out.println("Hi the key char is " + e.getKeyChar());
    System.out.println("Hi the key char is " + e.getKeyCode());
    
    // TODO Auto-generated method stub
    
    //LEFT PADDLE CONTROLS ....
    if( e.getKeyCode() == 87) //w   w = 87  * a = 65
    {
        if(leftPaddle.getSpeedY() == 0)  
        {
          leftPaddle.setSpeedY(-12);
        } else {
        leftPaddle.setSpeedY(leftPaddle.getSpeedY() - 3);
        }
    }
    
    
    
    if( e.getKeyCode() == 83) //s
    {
          if(leftPaddle.getSpeedY() == 0)  
          {
            leftPaddle.setSpeedY(12);
          } else {
            leftPaddle.setSpeedY(leftPaddle.getSpeedY() + 3);
          }
    }

    
    if( e.getKeyCode() == 65) // a
    {
      
         if(leftPaddle.getSpeedX() == 0)
         {
           leftPaddle.setSpeedX(-12);
         } else {
           leftPaddle.setSpeedX(leftPaddle.getSpeedX() - 3);
         }
      
      
    }
    
    if( e.getKeyCode() == 68) //d
    {
      
          if(leftPaddle.getSpeedX() == 0)
          {
            leftPaddle.setSpeedX(12);
          } else {
            leftPaddle.setSpeedX(leftPaddle.getSpeedX() + 3);
          }
    }
    
    //WASD -- X
    if( e.getKeyCode() == 88) // x
    {
            leftPaddle.setSpeedX(0);
            leftPaddle.setSpeedY(0);
    }
    
    
    
    
    // RIGHT PADDLE CONTROLS
    if( e.getKeyCode() == 38)  //UP
    {
            if(rightPaddle.getSpeedY() == 0)
            {
              rightPaddle.setSpeedY(-12);
            } else {
              rightPaddle.setSpeedY(rightPaddle.getSpeedY() - 3);
            } 
    }
    
    if( e.getKeyCode() == 40)  //DOWN
    {
      
          if(rightPaddle.getSpeedY() == 0)
          {
            rightPaddle.setSpeedY(12);
          } else {
            rightPaddle.setSpeedY(rightPaddle.getSpeedY() + 3);
          } 
    }
    
    
    if( e.getKeyCode() == 39)  // RIGHT
    {
      
        if(rightPaddle.getSpeedX() == 0)
        {
          rightPaddle.setSpeedX(12);
        } else  {
          rightPaddle.setSpeedX(rightPaddle.getSpeedX() + 3);
        }
       
       
    }
    
    if( e.getKeyCode() == 37)  //LEFT
    {
          if(rightPaddle.getSpeedX() == 0)
          {
            rightPaddle.setSpeedX(-12);
          } else  {
            rightPaddle.setSpeedX(rightPaddle.getSpeedX() - 3);
          }
    }
    
    
    if( e.getKeyCode() == 32) // space
    {
        rightPaddle.setSpeedX(0);
        rightPaddle.setSpeedY(0);
    }
    
    
    
  }


        public void keyReleased(KeyEvent e)
        {
          
        }
        
        
        public int getRinkWidth()
        {
            return pWidth;
        }
        
        public int getRinkHeight()
        {
            return pHeight;
        }
        
        //New to allow puck to access paddles and determine bumping....
        public Paddle getLeftPaddle()
        {
          return leftPaddle;
        }
        
        public Paddle getRightPaddle() 
        {
          return rightPaddle;
        }
        
        
  

} // this very very very last curly brace....
