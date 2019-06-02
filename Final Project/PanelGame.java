import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.*;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

public class PanelGame extends JPanel
{
   private JLabel label;
   private JButton instructions, showHighScores, startGame, settings, ai, dumbSolution;
   private JPanel top ,center, bottom;
   
   private SQLiteJDBCDriverConnection sql;
   
      
   public PanelGame()
   {
            
      
      //layout
      setLayout(new BorderLayout());
      
      //top panel
      top = new JPanel();
      top.setBackground(new Color(107,142,35));
      add(top, BorderLayout.NORTH);
      
      
      //instructions button
      instructions = new JButton("Click to see how to play!");
      instructions.setSize(new Dimension(180,180));
      instructions.addActionListener(new instructionsButton()); //do something on click
      instructions.setForeground(Color.RED);
      top.add(instructions);
   
      
      //label/title of game
      label = new JLabel("Welcome to Snake");
      top.add(label);
      
      
      //highscores button
      showHighScores = new JButton ("Click to see HighScores");
      showHighScores.setSize(new Dimension(180,180));
      showHighScores.setForeground(Color.RED);
      showHighScores.addActionListener(new highScoresButton());
      top.add(showHighScores);   
      
      
      //center thing for main + board?
      center = new JPanel();
      add(center, BorderLayout.CENTER);
      center.setBackground(new Color(34,139,34));
      
      
      //kaa from jungle book image
      try{
         ImageIcon image = new ImageIcon("/Users/Batman/Desktop/APCS AB/Final Project/snake4.gif",null); 
         JLabel labelImage = new JLabel (image, JLabel.CENTER);
         center.add(labelImage);
      }
      catch(Exception e)
      {
         System.out.println("GIF of snake in center");
      }
      
      
      
      // button panel
      bottom = new JPanel();
      add(bottom, BorderLayout.SOUTH);
      bottom.setBackground(new Color(152,251,152));
      
      //settings button
      settings = new JButton ("Settings");
      settings.setSize(new Dimension(180,180));
      settings.setForeground(new Color(115,0,230));
      settings.addActionListener(new settingsButton());
      //bottom.add(settings);
      
      //start game button
      startGame = new JButton("Solo Mode");
      startGame.setSize(new Dimension(180,180));
      startGame.addActionListener(new playGameButton());
      startGame.setForeground(new Color(226,29,200));
      bottom.add(startGame);
      
      //dumb solution button
      dumbSolution = new JButton("Brute Solver");
      dumbSolution.addActionListener(new dumbSolutionButton());
      dumbSolution.setForeground(new Color(26,83,255));
      bottom.add(dumbSolution);
      
      //ai plays snake button
      ai = new JButton("AI Mode");
      ai.setSize(new Dimension(180,180));
      ai.setForeground(new Color(26,83,255));
      ai.addActionListener(new aiButton());
      bottom.add(ai);
     
     //sql 
      sql = new SQLiteJDBCDriverConnection();
   }
   
   
   
   private class instructionsButton implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
            //instructions window
         JFrame temp = new JFrame("temp");
         temp.setContentPane(new InstructionsClass());
      }
   } 
   
   private class highScoresButton implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
            //highscores window
         JFrame temp = new JFrame("temp");
         temp.setContentPane(new HighscoresClass());
      }
   }
   
   private class playGameButton implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  
         JFrame frame = new JFrame();
         SnakeGamePanel snakeGamePanel = new SnakeGamePanel();
      
         frame.add(snakeGamePanel);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.setTitle("Snake!");
         frame.setResizable(false);
         frame.pack();
         frame.setVisible(true);
         frame.setLocationRelativeTo(null);
      }
   }
   
   private class dumbSolutionButton implements ActionListener
   {
      public void actionPerformed (ActionEvent e)
      {
         JFrame frame = new JFrame();
         DumbSnakeSolver dumbSnakeSolver = new DumbSnakeSolver();
      
         frame.add(dumbSnakeSolver);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.setTitle("Brute Force");
      
         frame.pack();
         frame.setVisible(true);
         frame.setLocationRelativeTo(null);
         frame.setResizable(false);
      }
   }
   
   private class settingsButton implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.out.println("add settings such as: \n1. changing board sizes\n2.resetting high scores\n3. color themes of snake/apple whatnot");
      }
   }
   
   private class aiButton implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         System.out.println("new frame that ai plays snake");
         JFrame frame = new JFrame();
         AISnakeSolver aiSnakeSolver = new AISnakeSolver();
      
         frame.add(aiSnakeSolver);
         frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         frame.setTitle("AI Solver");
      
         frame.pack();
         frame.setVisible(true);
         frame.setLocationRelativeTo(null);
         frame.setResizable(false);
      }
   }
   
}


//instructions class
class InstructionsClass extends JPanel
{
   //make a label for instructions 
   private JFrame instructionsFrame;
   private JTextArea textArea;
   
   public InstructionsClass()
   {
      //frame for instructions
      instructionsFrame = new JFrame("Instructions!");
      instructionsFrame.setSize(500,500);
      instructionsFrame.setLocation(200,200);
      instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      instructionsFrame.setVisible(true);
      
      //instructions in textArea
      textArea = new JTextArea(17,22);
      textArea.setSize(100,100);
      textArea.setText("Instructions:\n\n\nIf you want to play solo mode you can by clicking the bottom left button\n  - your score (if you choose) can be saved to the\n     scoreboard if it is high enough!\n\nYou can also watch a snake follow a set path and beating the game with\n the Brute Force button\n\nYou can watch AI play snake with the AI button\n\nHAVE FUN :)");    
      instructionsFrame.add(textArea);
   }
}



//highscores class
class HighscoresClass extends JPanel
{
   private JFrame highscoresFrame;
   private JPanel north;
   private JLabel northLabel;
   private JTextArea textArea;
   private SQLiteJDBCDriverConnection sql;
   //make a refresh button
   
   public HighscoresClass() 
   {
      setLayout(new BorderLayout());
      
      highscoresFrame = new JFrame("High Scores!");
      highscoresFrame.setSize(500,500);
      highscoresFrame.setLocation(600,600);
      highscoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      highscoresFrame.setVisible(true);
      highscoresFrame.setBackground(Color.BLUE);
      
      //panel north
      north = new JPanel();
      highscoresFrame.add(north, BorderLayout.NORTH);
      
      //label north
      northLabel = new JLabel("Top 5 High Scores");
      northLabel.setBackground(Color.BLUE);
      north.add(northLabel);
      
      
      sql = new SQLiteJDBCDriverConnection();
      
      textArea = new JTextArea(17,22);
      textArea.setSize(100,100);
      textArea.setText(sql.filter());
      //textArea.setAlignment(Component.CENTER);  //how to align it??    
      highscoresFrame.add(textArea);
      
   }
   
}


//play game class
class PlayGameClass extends JPanel
{
   private JFrame game;
   private JButton goBack;
   
   public PlayGameClass()
   {
      //frame 
      game = new JFrame("Let's Play!!");
      game.setSize(700,700);
      game.setLocation(200,100);
      game.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      game.setVisible(true);
      
      //go back button
      goBack = new JButton ("Go Back");
      goBack.addActionListener(new goBackButton());
      game.add(goBack);
      
   }
   
   private class goBackButton implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         game.dispose();
      }
   }
}