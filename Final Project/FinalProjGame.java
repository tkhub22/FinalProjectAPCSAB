/*
Purpose:
   to create a game that uploads highscore to a server.

LOG:
winter break - learned SQL, and want to create a project that incorporates it.

2/8 - with help of omkar made a sqlite connection + downloaded sqlite jar file
    - created a table called test and drops names into it using add method.

2/9  - hopefully make a scanner to get user input names and insert into text file

2/13 - created basic game gui + instructions page
    - accessed text file to read values without using the website thing
  
   
2/14  - made highscore page and start game button
      - connected highscore page to this sql table

2/16 - made solo snake game
      - connected snake to game and added score once played
      - made back ground colors

2/17 - fixed colors + tweaks
     - added gif to home page
     - added 2 new buttons 
 
2/18 - made brute force algorithm to play snake + with some bugs
     - tweaks
      
2/19 - made username set frame + tweaks

2/21 - made set username frame and added the name to sql database
     - made a go incognito button that doesn't add name to database

2/24 - fixed brute force snake bug

3/2 - completed SQL, but still need to integrate w game

3/3 - INTEGRATED SQL PERFECTLY WITH GAME (just need to format the highscores page to make it look nice)

(future) - set username action listener  
(future)  
      
*/
//imported java sqlite jar file
import javax.swing.JFrame;
import java.awt.*;
import java.util.*;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.*;

public class FinalProjGame extends JFrame
{
   
   public static void main (String [] args)
   {   
      JFrame frame = new JFrame("Snake 2.0!");
      frame.setSize(600,525);
      frame.setLocation(200,100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setContentPane(new PanelGame());
      frame.setVisible(true);
      
      
      
   }
}