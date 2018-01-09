package pongPac2;

import javax.swing.JFrame;

public class PongMain
{

  /*   Version 1 */
  public static void main(String[] args)
  {
    
      int fWidth = 1200;
      int fHeight = 500;
      JFrame f = new JFrame(" Welcome to STM Air Hockey ");
      f.setSize(fWidth, fHeight+25);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      PongAirHockey pah = new PongAirHockey(fWidth, fHeight);
      f.add(pah);
      
      
      //pong stuff goes here...
      f.setVisible(true);

  }

}
