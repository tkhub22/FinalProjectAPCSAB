//uaername prompt class after
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.*;

public class UsernameClass extends JPanel
{
   private JDialog usernameFrame;
   private JTextArea username;
   private int myScore;
   private SQLiteJDBCDriverConnection sql;
   
   public UsernameClass()
   {
   
         //username window
      usernameFrame = new JDialog();
      usernameFrame.setSize(300,300);
      usernameFrame.setLocation(300,300);
      usernameFrame.setAlwaysOnTop(true);
      
      usernameFrame.setLayout(new BorderLayout());
      
      
      JPanel usernameTopPanel = new JPanel();
      usernameTopPanel.setBackground(new Color(175,238,238));
      
      usernameFrame.add(usernameTopPanel, BorderLayout.NORTH);
      
      JLabel usernameLabel = new JLabel("Enter a Username     ex (Batman) : "); //add this to panel 
      usernameTopPanel.add(usernameLabel);
      
      //center panel
      JPanel usernameCenterPanel = new JPanel();
      //usernameCenterPanel.setSize(10,10);
      usernameCenterPanel.setBackground(new Color(64,224,208));
      usernameFrame.add(usernameCenterPanel,BorderLayout.CENTER);
      
      //make a text box and add it to sql server (center)
      username = new JTextArea(2,10);
      usernameCenterPanel.add(username);
      
      
      //bottom panel + 'set username' button
      JPanel usernameBottomPanel = new JPanel();
      usernameBottomPanel.setSize(300,300);
      usernameBottomPanel.setBackground(new Color(50,205,50));
      usernameFrame.add(usernameBottomPanel, BorderLayout.SOUTH);
      
      JButton setUsernameButton = new JButton("Set Username");
      setUsernameButton.setSize(new Dimension(180,180));
      setUsernameButton.addActionListener(new SetUsernameButton()); // actionlistener that  adds and closes window
      setUsernameButton.setForeground(Color.RED);
      usernameBottomPanel.add(setUsernameButton);
      
      JButton incognito = new JButton("Incognito");
      incognito.setSize(new Dimension(180,180));
      incognito.addActionListener(new IncognitoButton());
      incognito.setForeground(new Color(75,0,130));
      usernameBottomPanel.add(incognito);
      
      usernameFrame.pack();
      usernameTopPanel.setSize(300,300);
      usernameFrame.setVisible(true);
      
      
   }
   /*
   public UsernameClass(JFrame frame)
   {
      frame.dispose();
   }
   */
   
   private class SetUsernameButton implements ActionListener
   {
      public void actionPerformed (ActionEvent e)
      {
         
         int sco = SnakeGamePanel.score;
         
         sql = new SQLiteJDBCDriverConnection();
         
         sql.add("" +  username.getText(), sco);
         
         usernameFrame.dispose();
      }
      
     
   }
   
   private class IncognitoButton implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.out.println("your score will not be saved...");
         usernameFrame.dispose();
      }
   }
}