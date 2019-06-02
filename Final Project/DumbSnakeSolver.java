/*
things to fix:
3.apple spawning on snake

*/
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
   
public class DumbSnakeSolver extends JPanel implements Runnable//, KeyListener
{
   public static final int WIDTH = 200, HEIGHT = 200;
   private Thread thread;
   private boolean running;
   private boolean right = true, left = false, up = false, down = false;
   private SnakeBodyPart b;
   private ArrayList <SnakeBodyPart> snake;
   private SnakeApple apple;
   private ArrayList <SnakeApple> apples;
   private Random r;
   private int xCoor = 0, yCoor = 17, size = 5;
   private int ticks = 0;
   
   public DumbSnakeSolver()
   {
      setFocusable(true);
      
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      
      
      snake = new ArrayList <SnakeBodyPart> ();
      apples = new ArrayList <SnakeApple> ();
      r = new Random (); 
      start();
   }
   
   public void start()
   {
      running = true;
      thread = new Thread(this);
      thread.start();
   }  
   
   public void stop()  
   {
      int score = size- 5;
      System.out.println("Score: " + score);
      running = false;
      try{
         thread.join();
      }
      catch(InterruptedException e){
         e.printStackTrace();
      }
      
      
   }
   public void tick()
   {
      route();
      if(snake.size() ==0)
      {
         b = new SnakeBodyPart(xCoor, yCoor, 10);
         snake.add(b);
      }
      ticks++;
      if(ticks> 250000)
      {
         if(right) xCoor++;
         if(left) xCoor--;
         if(up) yCoor++;
         if(down) yCoor--;
         
         ticks = 0;
         
         b= new SnakeBodyPart(xCoor, yCoor, 10);
         snake.add(b);
         
         if(snake.size() > size)
         {
            snake.remove(0);
         }
      }
      
      if(apples.size()==0) ///come here to fix apple spwaning on snake issue
      {
      
         int xCoor = r.nextInt(19);
         int yCoor = r.nextInt(19);
         
         apple = new SnakeApple(xCoor, yCoor, 10);
         apples.add(apple);
      }
      
      for(int i = 0; i < apples.size(); i++) //if gets apple
      {
         if(xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor())
         {
            size = size+5;
            apples.remove(i);
            i++;
         }
      }
      
      for(int i = 0; i < snake.size(); i++)
      {
         if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor())
         {
            if(i!=snake.size()-1)
            {
               System.out.println("Hit yourself. Game Over!");
               stop();
            }
         }
      }
      
      if(xCoor<0 || xCoor >29 || yCoor <0 || yCoor>29) //if hits outside
      {
         System.out.println("Out of bounds. Game Over!");
         stop();
      }      
   }
   
   public void paint(Graphics g)
   {
      g.clearRect(0,0,WIDTH,HEIGHT);
   
      g.setColor(Color.BLACK);
      g.fillRect(0,0,WIDTH,HEIGHT);
   
      for(int i = 0; i < WIDTH/10; i++)
         g.drawLine(i*10,0,i*10,HEIGHT);
      for(int i = 0; i < HEIGHT/10; i++)
         g.drawLine(0,i*10,HEIGHT,i*10);
      for(int i = 0; i < snake.size(); i++)
         snake.get(i).draw(g);
      for(int i = 0; i < apples.size(); i++)
         apples.get(i).draw(g);
   }
   
   @Override
   public void run()
   {
      while(running)
      {
         tick();
         repaint();
      }
   }
   
   //@Override
   public void route()
   {  
   
       
      
      if(xCoor==0 && yCoor==0 )//top left
         right();
   
      eFormation(); 
      
    
      if(xCoor == 19)
      {
         down();
         eFormation();
      }
      if(xCoor==1 && yCoor==19)
      {
         left();
      }
      if(xCoor == 0 && yCoor == 19){
         up();
      }
   
   }
   
   public void eFormation()
   {  
      if(xCoor+1>19 && yCoor ==0)//turn if top right
         down();
      if(xCoor == 19 && (yCoor%2==1))
         left();
      if(xCoor ==1 && (yCoor%2==1) && yCoor!=0)
         down();   
      if(xCoor ==1 && (yCoor%2==0) && yCoor!=0)
         right();      
   }
   
   public void down()
   {
      up = true;
      down = false;
      right = false;
      left = false;
   }
   public void left()
   { 
      left = true;
      down = false;
      right = false;
      up = false;   
   }
   public void up()
   {
      down = true;
      up = false;
      right = false;
      left = false;
   }
   public void right()
   {
      right = true;
      up = false;
      down = false;
      left = false;
   }
}