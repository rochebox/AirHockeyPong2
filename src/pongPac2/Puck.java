package pongPac2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Puck
{
      //Data We practice Data Hiding
      
      private int puckD;
      private int xLoc, yLoc;
      private  int xSpeed, ySpeed;
      //private Color outerColor;
      //private Color innerColor;
      //private int frictionFactor = 3;
      
      private double PDFrictionFactor =  0.90;
      private PongAirHockey  myTable;
      // Image???
      
      private String puckFileName = "penguins.png";
      //private String puckFileName = "puck1.png";
      //private String puckFileName = "canada150.png";
      //private String puckFileName = "peterPuck.gif";
      private Image puckImage;
  
      // Constructors
      public Puck( int size, PongAirHockey t)
      {
          puckD = size;
          myTable = t;
          
          xSpeed = -70;
          ySpeed = -40;
          xLoc= (int)( myTable.getWidth()/2 ) - (int)(puckD/2);
          yLoc= (int)( myTable.getHeight()/2 ) - (int)(puckD/2);
          //yLoc =  puckD/2;
         //yLoc = myTable.getHeight() - puckD;
          //outerColor = Color.BLACK;
          //innerColor = new Color(232, 145, 16);
          
          
          try {
            
             File pFile = new File(puckFileName);
             puckImage = ImageIO.read(pFile);
            
          } catch ( IOException e) {
            System.out.println("CATASTROPIC ERROR WITH PUCK READ -- RUN AWAY!!");
            System.out.println(e);
            
          }
        
        
      }
  
  
  //Methods
  
      public void drawPuck(Graphics g){
      
           g.setColor(Color.GRAY);
           g.fillOval(xLoc+5, yLoc+5, puckD, puckD);
           g.drawImage(puckImage, xLoc, yLoc, puckD, puckD, myTable);
        
      }
  
  
  
      public void movePuck()
      {
        
        checkLeftPaddle();
        checkRightPaddle();
        xLoc += xSpeed;
        yLoc += ySpeed;
        
      
 
        dealWithFrictionInAPDWay();
        //You may have your bounce check here or it could be in a method like mine.
        checkForBounces();
        
      }
      
        private void checkLeftPaddle()
        {
          
          // Step 1 find out if puck is near left paddle
          // Find the center of the puck
          int puckCX = xLoc + (int)(puckD/2);
          int puckCY = yLoc + (int)(puckD/2);
          // Find the center of the paddle
          int paddleCX =myTable.getLeftPaddle().getXLoc() + (int)(myTable.getLeftPaddle().getPDiameter()/2);
          int paddleCY =myTable.getLeftPaddle().getYLoc() + (int)(myTable.getLeftPaddle().getPDiameter()/2);
          
          //Now Get Ready for P theorem
          
          double A = paddleCY - puckCY;
          double B = paddleCX - puckCX;
          
          // for homework find C
          double C = Math.sqrt((A*A) + (B*B));
          
          if(C < (puckD/2) + (myTable.getLeftPaddle().getPDiameter()/2))
          {
            System.out.println("There is a left paddle collision");
          } else {
           // System.out.println("There is no left  collision");
          }
             
          
        }
        
        private void checkRightPaddle()
        {
          
        }
      
      
      
      
      
      
      public void dealWithFrictionInAPDWay(){
            xSpeed = (int) (xSpeed * PDFrictionFactor);
            ySpeed = (int) (ySpeed * PDFrictionFactor);
   
      }
      
      
      //A couple of new accessor methods
      public void setXSpeed(int newSpeed)
      {
          xSpeed = newSpeed;
      }
      
      public void setYSpeed(int newSpeed)
      {
          ySpeed = newSpeed;
      }
      
      
      
      
      public void dealWithFriction()
      {
        //let's deal tomorrow
        
        
        
      }
      
      public void checkForBounces()
      {
        
        // Left Side Bounce  -- test 1
          if( xSpeed < 0 )   // if the puck is moving left...
          {
                  if(
                      yLoc < myTable.getGoalYTop() - (int)(puckD/1.5) ||
                      yLoc > myTable.getGoalYBottom()  - (int)(puckD/1.5)
                      )
                  {
                             if(xLoc < myTable.getGoalWidth())
                             {
                               xLoc = myTable.getGoalWidth();
                               xSpeed *= -1;
                             }
                     // if you get here it means the puck is going in the goal
                  } else {
                            if( xLoc < 0)
                            {
                              xLoc = 0;
                              xSpeed *= -1;
                            } 
                  } 
                  //RIGHT SIDE
          } else if (xSpeed > 0) {      // meaning that its moving right
              
                //check the ylocs to see if puck is going into the goal or hitting the side walls
                    if(
                        yLoc < myTable.getGoalYTop() - (int)(puckD/1.5) ||
                        yLoc > myTable.getGoalYBottom()  - (int)(puckD/1.5)
                        )
                    {
                              //check for bounce on sides of the goal
                              if(xLoc > myTable.getWidth()- myTable.getGoalWidth() - puckD)
                              {
                                xLoc = myTable.getWidth()- myTable.getGoalWidth() - puckD;
                                xSpeed *= -1;
                               
                              } 
                            
                    } else {
                                //check for bounce inside the goal
                                if(xLoc > myTable.getWidth() - puckD )
                                {
                                  xLoc = myTable.getWidth() - puckD;
                                  xSpeed *= -1;
                                }
                    }
              }
          
          // check for top and bottom
          
          //TOP CHECK
          if(ySpeed < 0){
            
            if(yLoc < 0)
            {
              yLoc = 0;
              ySpeed *= -1;
            }
            
            
           // Bottom CHECK!!
          } else if (ySpeed > 0){
             if(yLoc > myTable.getHeight() - puckD)
             {
               yLoc = myTable.getHeight() - puckD;
               ySpeed *= -1;
             }
            
          }
          
          
          
      }
      

}
