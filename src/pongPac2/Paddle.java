package pongPac2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paddle
{
 
    //Data  --- Fields
    public static final boolean RIGHT_PADDLE = true;
    public static final boolean LEFT_PADDLE = false;
    
    private int xLoc, yLoc;
    private int xSpeed, ySpeed;
    private int pDiameter;
    
    private String paddleFileName = "AirPaddle.png";
    private Image pImage;
    private PongAirHockey myTable;
    private boolean mySide;
   
    
  
    
    //Constructor(s)
    public Paddle ( int d, boolean whichPaddle, PongAirHockey t)
    {
      pDiameter = d;
      mySide = whichPaddle;
      myTable = t;
      xSpeed = 0;
      ySpeed = 0;
      
      //This puts paddle in middle of table in either side...
      yLoc = (int)(myTable.getHeight()/2) - (int)(pDiameter/2);
      
      //This locates x for the different paddles
          if(mySide == this.RIGHT_PADDLE)
          {
            xLoc = (int) (myTable.getWidth() * 0.75) - (int)(pDiameter/2);
          } else {
            xLoc = (int) (myTable.getWidth() * 0.25) -  (int)(pDiameter/2);
          }
      
          try{
            
            File paddlePicFile = new File(paddleFileName);
            pImage = ImageIO.read(paddlePicFile);
            
          } catch ( IOException e) {
            System.out.println("ERROR  --- CAN'T OPEN PADDLE FILE");
          }
      
    }
    
    
    public void drawPaddle(Graphics g)
    {
      
        g.setColor(Color.GRAY);
        g.fillOval(xLoc+10, yLoc+10, pDiameter, pDiameter);
        g.drawImage(pImage, xLoc, yLoc, pDiameter, pDiameter, myTable);
    }
    
    public void movePaddle()
    {
        yLoc += ySpeed;
        xLoc += xSpeed;
        
       // System.out.println(" hi from movePaddle yLoc is " + yLoc);
        
        if( ySpeed < 0 )   // if this is true then paddle is moving up!!!
        {
               if(yLoc < 2 )
               {
                    yLoc = 2;   // this puts paddle on screen...
                    ySpeed = 0;
               }
        } 
        
        if( ySpeed > 0)   // i'm heading to the bottom wall..
        {
               if(yLoc > myTable.getRinkHeight() - pDiameter-2 )
               {
                  yLoc = myTable.getRinkHeight() - pDiameter-2;
                  ySpeed = 0;
               }
        }
        
        // Handle Right and Left Movement....
        
        if(mySide == LEFT_PADDLE)
        {
          //This is for Left Side Stuff  part 1
          if(xSpeed < 0 )   //moving towards the goal
          {
            if(xLoc < myTable.getGoalWidth() + 2)
            {
              xLoc = myTable.getGoalWidth() + 2;
              xSpeed = 0;
            }
          }
          
          //This is Left Side Stuff Part 2
          if(xSpeed > 0 )
          {
              if( xLoc > (int)(myTable.getRinkWidth()/2) - (pDiameter+ 2))
              {
                xLoc = (int)(myTable.getRinkWidth()/2) - (pDiameter +2);
                xSpeed = 0;
              }
          }
          
          
        } else {
          
          //This is for Right Side Stuff
          
          // For homework write stuff here...
          
        //This is for Left Side Stuff  part 1
          if(xSpeed < 0 )   //moving towards the GOAL LINE
          {
            
                if( xLoc < (int)(myTable.getRinkWidth()/2) + 2)
                {
                  xLoc = (int)(myTable.getRinkWidth()/2) + 2;
                  xSpeed = 0;
                }
          }
          
          if(xSpeed > 0)
          {
              if(  xLoc > (int)(myTable.getRinkWidth()) - (myTable.getGoalWidth() + 2) - pDiameter )
              {
                xLoc = (int)(myTable.getRinkWidth()) - (myTable.getGoalWidth() + 2) - pDiameter;
                xSpeed = 0;
            }
            
          }
            
        }
            
    }
    
    
    public void setSpeedY(int newSpeed)
    {
        ySpeed = newSpeed;
        
        System.out.println("Hi  in paddle setSpeed is " + ySpeed);
    }
    
    public int getSpeedY()
    {
      return ySpeed;
    }

  
    public void setSpeedX(int newSpeed)
    {
      xSpeed = newSpeed;
    }
    
    public int getSpeedX()
    {
      return xSpeed;
    }
  
    //New to allow puck to determine distance...
    public int getXLoc()
    {
      return xLoc;
    }
    //New Friday
    public int getYLoc()
    {
      return yLoc;
    }
    //Also New Friday
    public int getPDiameter()
    {
      return pDiameter;
    }
  
  
  
}
