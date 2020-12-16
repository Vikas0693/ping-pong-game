package game;
import java.awt.*;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


public class Ball implements Runnable{

	int x,y,xDirection,yDirection;
	Rectangle ball;
	AudioInputStream audioInputStream;
	Clip clip;

	int speed =10,score1=0,score2=0;

	Thread t=new Thread(this);
	Font f=new Font("Viner Hand ITC",Font.BOLD,16);
	Paddle p1;
	Paddle p2;
	BufferedImage img;
	
	
	
	Ball(int a,int b,int w,int h)
	{
		x=a;
		y=b;
		Random r=new Random();
		p1=new Paddle(15,50,1);		
		p2=new Paddle(w-10-5-15,50,2);
		int xRan=r.nextInt(2);
		int yRan=r.nextInt(2);
		System.out.println("xRan="+xRan);
		System.out.println("yRan="+yRan);
		if(xRan==0)
			xRan--;
		if(yRan==0)
			yRan--;
		
		setxDirection(xRan);
		setyDirection(yRan);
		try{
			
		
		img=ImageIO.read(new File("F:/Eclipse Programs/Eclipse/PongGame/src/ball.bmp"));
		}
		catch(IOException e)
		{
		}
		//transparent Rectangle
		ball=new Rectangle(x,y,20,20);				//this is our original ball
		
		
	}
	//There is no need to use below two functions as there is only one statements in them
	void setxDirection(int a)
	{
		xDirection=a;
	}
	void setyDirection(int a)
	{
		yDirection=a;
	}
	void start()
	{
		t.start();
		p1.start();
		p2.start();
	}
	void stop()
	{
		t.stop();
		t=new Thread(this);
		
		p1.stop();
		p2.stop();
		
		
		
	}
	
	public void draw(Graphics g)
	{
		
		
		g.setColor(Color.black);
		g.fillRect(ball.x,ball.y,ball.width,ball.height);
		g.drawImage(img, ball.x, ball.y, null);
		
		g.setColor(Color.YELLOW);
		g.setFont(f);
		g.drawString("Player1="+score1, 50, 60);
		g.drawString("Player2="+score2, 450, 60);
		p1.draw(g);
		p2.draw(g);
		
	}
	
	@Override
	public void run(){
		// TODO Auto-generated method stub
		try{
		while(true)
		{
			Thread.sleep(2);
			move();
		}
		}
		catch(Exception e){}
	}
	void move()
	{
		ball.x=ball.x+xDirection;
		ball.y=ball.y+yDirection;
		/*if(ball.x>=10 && ball.x<=p1.x+30 && ball.y>=p1.paddle.y+70)
		{
			if(ball.intersects(p1.paddle))
			{
				yDirection=+1;
				xDirection=+1;
			}
		}
		if(ball.x<480 && ball.x+10>=p1.x && ball.y>=p2.paddle.y+70 )
		{
			if(ball.intersects(p2.paddle))
			{
				yDirection=+1;
				xDirection=-1;
			}
		}*/
		if(ball.x<=10)
		{
			xDirection=1;
			score2+=1;
		}
		if(ball.x>=570)				//470+20=490(x at width of our board=(480+10))
		{
			xDirection=-1;
			score1+=1;
		}
		if(ball.y<=30)
		{
			yDirection=1;
		}
		if(ball.y>=590-20)				//470+20=490(y at height of our board=(30+460))
		{
			yDirection=-1;
		}
		if(ball.intersects(p1.paddle))
		{
			sound();
			xDirection=+1;
		}
		else if(ball.intersects(p2.paddle))
		{
			sound();
			xDirection=-1;
		}
		
	}
	void sound()
	{
		 try {
			 final String fileName ="modified.wav";
			 URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
			 String path=url.getPath();
			 //when running in jar path will contain '!'
			 if(path.contains("!")) {
				 File currentPath= new File(".");
				 //System.out.println("current path = "+currentPath.getCanonicalPath());
				 audioInputStream = AudioSystem.getAudioInputStream(new File(currentPath.getCanonicalFile()+"/modified.wav"));
			 }
			 else {
				 //when running in eclipse
				 //System.out.println("parent = "+this.getClass().getClassLoader().getResource("").getPath());
				 //System.out.println("parent1 = "+this.getClass().getResource("").getPath());
				 path = this.getClass().getClassLoader().getResource(fileName).getPath();
				 System.out.println("path = "+path);
				 audioInputStream = AudioSystem.getAudioInputStream(new File(path));
			 }
			 	clip = AudioSystem.getClip();
		        clip.open(audioInputStream);
		        clip.start();
		    } catch(Exception ex) {
		        System.out.println("Error with playing sound.");
		        ex.printStackTrace();
		    }
	}
	
}
