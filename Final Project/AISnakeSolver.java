
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
   
public class AISnakeSolver extends JPanel implements Runnable//, KeyListener
{
   public static final int WIDTH = 500, HEIGHT = 500;
   private Thread thread;
   private boolean running;
   private boolean right = true, left = false, up = false, down = false;
   private SnakeBodyPart b;
   private ArrayList <SnakeBodyPart> snake;
   private SnakeApple apple;
   private ArrayList <SnakeApple> apples;
   private Random r;
   private int xCoor = 5, yCoor = 17, size = 5;
   private int ticks = 0;
   
   public AISnakeSolver()
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
      
         int xCoor = r.nextInt(49);
         int yCoor = r.nextInt(49);
         
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
      
      
      //if(snake.contains())
      
      if(xCoor<0 || xCoor >49 || yCoor <0 || yCoor>49) //if hits outside
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
      //shortest path algorithm
      //apple coordinate = apples.get(0).getxCoor() || apples.get(0).getyCoor()
     
      postFoodSave();
 
      for(int i = 0; i < apples.size(); i++)
      {
         if(apples.get(i).getxCoor() <= xCoor && apples.get(i).getyCoor() <= yCoor)
            up();
      }
      for(int i = 0; i < apples.size(); i++) 
      {
         if(apples.get(i).getxCoor() >= xCoor && apples.get(i).getyCoor() >= yCoor)
            down();
      }
      for(int i = 0; i < apples.size(); i++) 
      {
         if(apples.get(i).getxCoor() >= xCoor && apples.get(i).getyCoor() <= yCoor)
            right();
      }
      for(int i = 0; i < apples.size(); i++) 
      {
         if(apples.get(i).getxCoor() <= xCoor && apples.get(i).getyCoor() >= yCoor)
            left();
      }
      
      
   }
   
   public void postFoodSave()
   {
      tweaks();
     
      if(xCoor+1<0 && yCoor+1 <0) //TOP LEFT C
         down();
      
      if(xCoor+1<0 && yCoor+1 >49)//BOTTOM LEFT C
         up();
         
      if(xCoor+1>49 && yCoor+1 <0)//TOP RIGHT C
         down();
      
      if(xCoor+1>49 && yCoor+1 >49)//BOTTOM RIGHT C
         up();
   }
   
   public void tweaks()
   {
      //to negate ex: when going to the left and direction says to go right?
     
   }
   
   public boolean down()
   {
      up = true;
      down = false;
      right = false;
      left = false;
      return true;
   }
   public boolean left()
   { 
      left = true;
      down = false;
      right = false;
      up = false;   
      return true;
   }
   public boolean up()
   {
      down = true;
      up = false;
      right = false;
      left = false;
      return true;
   }
   public boolean right()
   {
      right = true;
      up = false;
      down = false;
      left = false;
      return true;
   }
}