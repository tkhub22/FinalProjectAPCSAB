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
   
public class SnakeGamePanel extends JPanel implements Runnable, KeyListener
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
   private int xCoor = 10, yCoor = 10, size = 5;
   private int ticks = 0;
   
   
   public static int score;
     
   public SnakeGamePanel()
   {
      setFocusable(true);
      setPreferredSize(new Dimension(WIDTH, HEIGHT));
      addKeyListener(this);
      
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
   
   public int getScore()
   {
      score = size - 5;
      return score;
   }
   
   public int stop()  //come here to input scores
   {
      System.out.println("iteratiomnnnnnnn");
   
   
      
      score = size-5;
      System.out.println("Your score: " + score);
      
      //sql.add(""+ score);
      
      running = false;
      
      
      try{
         JFrame temp = new JFrame("temp");
         temp.setContentPane(new UsernameClass());
         //temp.setVisible(true);
      
      }
      catch(Exception e)
      {
         e.printStackTrace();
         System.out.println("Record scores...");
      }
   
   
   try{
      thread.join();
      }
      catch(Exception e){
         System.out.println(e);
      }
      
      return score;
      
   }
   
   public void tick()
   {
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
      
      if(apples.size()==0)
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
            size++;
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
               System.out.println("You hit yourself. Game Over!");
                  stop();
            }
         }
      }
      
      
      if(xCoor<0 || xCoor >49 || yCoor <0 || yCoor>49) //if hits outside
      {
         System.out.println("You went out of bounds. Game Over!");
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
   
   @Override
   public void keyPressed(KeyEvent e) //change up to match actual snake controls
   {
      int key = e.getKeyCode();
      if(key == KeyEvent.VK_RIGHT && !left)
      {
         right = true;
         up = false;
         down = false;
         left = false;
      }
      if(key == KeyEvent.VK_LEFT && !right)
      {
         left = true;
         up = false;
         down = false;
         right = false;
      }
      if(key == KeyEvent.VK_UP && !down)
      {
         up = false;
         left = false;
         right = false;
         down = true;
      }
      if(key == KeyEvent.VK_DOWN && !up)
      {
         down = false;
         left = false;
         right = false;
         up = true;
      }
   }
   
   @Override
   public void keyReleased(KeyEvent arg0)
   {
   
   }
   
   @Override
   public void keyTyped(KeyEvent arg0)
   {
   
   }
}



