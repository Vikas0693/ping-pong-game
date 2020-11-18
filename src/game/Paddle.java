package game;


import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Paddle  implements Runnable{
	int x,y,id;
	public Thread t;
	Rectangle paddle;			//we use Rectangle class to make paddles bcoz we can use intersect function with it
	int yDir=0;
	//Pong p;
	Paddle(int x,int y,int id)
	{
		this.x=x;
		this.y=y;
		this.id=id;
		paddle=new Rectangle(this.x,this.y,15,70);
		t=new Thread(this);
		//t.start();
	}
	public void start()
	{
		t.start();
	}
	public void stop()
	{
		t.stop();
		t=new Thread(this);
	}
	public void run()
	{
		while(true)
		{
			
			move();
			try
			{
				Thread.sleep(1);
			}
			catch(Exception e){}
		}
	}
	public void keyPressed(KeyEvent e)
	{
		if(id==1)
		{
			if(e.getKeyCode()==KeyEvent.VK_W)
			{
				
				yDir=-1;
			}
			if(e.getKeyCode()==e.VK_S)
			{
				yDir=1;
			}
		}
		if(id==2)
		{
			if(e.getKeyCode()==e.VK_UP)
			{
				
				yDir=-1;
			}
			if(e.getKeyCode()==e.VK_DOWN)
			{
				yDir=1;
			}
		}

	}
	public void keyReleased(KeyEvent e)
	{
		if(id==1)
		{
			if(e.getKeyCode()==e.VK_W)
			{
				yDir=0;
			}
			if(e.getKeyCode()==e.VK_S)
			{
				yDir=0;
			}
		}
		if(id==2)
		{
			if(e.getKeyCode()==e.VK_UP)
			{
				yDir=0;
			}
			if(e.getKeyCode()==e.VK_DOWN)
			{
				yDir=0;
			}
		}
	}
	void move()
	{
		
		paddle.y=paddle.y+yDir;
		if(paddle.y<=30)
		{
			paddle.y=30;
		}
		if(paddle.y>=590-70)		//(30+460)-70=420;where 30 is y for upper boundary and 460 is for lower boundary
		{
			paddle.y=520;
		}
		
	}
	void draw(Graphics g)
	{
		
		if(id==1)
		{
			
			g.setColor(Color.white);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
		}
		if(id==2)
		{
			g.setColor(Color.blue);
			g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
		}
	}
}
