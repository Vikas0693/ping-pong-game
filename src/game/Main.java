package game;

import java.awt.Color;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Main  {
	public static void main(String[] ara)
	{
		Pong p=new Pong();
		
		
	}

}
class Pong extends JFrame implements KeyListener,MouseListener,ActionListener//,Runnable
{
	int bx=300,by=350,nums=3,counting=2;
	Image i;
	Graphics dg;
	Ball b;
	public Rectangle boundary=new Rectangle(10,30,580,560);
	boolean spacePressed=false;
	//Rectangle start;
	//Rectangle quit;
	Font inGame=new Font("Chiller",Font.BOLD,20);	
	//Font s=new Font("Forte",Font.BOLD,32);	
	Font space=new Font("AR DARLING",Font.ITALIC,32);
	//JLabel space1;
	Dimension d;
	//Thread t=new Thread(this);
//	Thread blinking=new Thread(this);
	Timer t;
	int width,height;
	Pong()
	{
		setBackground(Color.red);
		setSize(600,600);
		
		setResizable(false);
		d=Toolkit.getDefaultToolkit().getScreenSize();
		width=d.width;
		height=d.height;
		b=new Ball(bx,by,600,600);
		//System.out.println("width="+d.getWidth());
		//System.out.println("height="+d.getHeight());
		setLocation((d.width/2)-300,(d.height/2)-300);
	
		setTitle("PING PONG");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		addKeyListener(this);
		
		//FontMetrics fm=space.getFontMetrics();
		//addMouseListener(this);
		//start=new Rectangle(225,100,70,30);
		
		//quit = new Rectangle(225,150,70,30);
		t=new Timer(550,this);
		t.start();
	}
	
	/*public void run()
	{
		while(startgame)
		{
			setStart();
			
		}
	}
	void setStart()
	{
		setStart=true;
	}*/
	public void paint(Graphics g)
	{
		i=createImage(600,600);
		dg=i.getGraphics();
		drawG(dg);
		g.drawImage(i,0,0,this);
	}
	public void drawG(Graphics g)
	{
		//this is our board
		g.drawRect(boundary.x,boundary.y,boundary.width,boundary.height);
		b.draw(g);
		
		if(counting==0)
		{
			
			//b.p1.draw(g);
			//b.p2.draw(g);
			g.setFont(space);
			g.setColor(new Color(0,2,34));
		
			drawCenteredString("Press SPACE to Start",600,600, g);
			
			//add(space1);
			repaint();
	
		}
		if(counting==2)
		{
			
			
			
			repaint();
		}
		
	
		if(counting==3)
		{
			
			repaint();
		}
		
			
			
			//repaint();
			
			
		
		
	}
	public void drawCenteredString(String s,int width,int height,Graphics graph)
	{
		FontMetrics fm=graph.getFontMetrics();
		int x=(width/2-fm.stringWidth(s)/2);
		//int y=(fm.getAscent()+(height-(fm.getAscent()+fm.getDescent()))/2);
		int q=height/2;
		graph.drawString(s, x, q);
		//graph.drawLine(x,y ,x+50, y);
		
		
	}
	//@SuppressWarnings("deprecation")
	public void keyPressed(KeyEvent e)
	{
		int k=e.getKeyCode();
		if(k==KeyEvent.VK_W)
		{
			
			b.p1.keyPressed(e);
			
		}
		if(e.getKeyCode()==e.VK_S)
		{
			b.p1.keyPressed(e);
		}
		if(e.getKeyCode()==e.VK_UP)
		{
			System.out.println("up");
			b.p2.keyPressed(e);
		}
		if(e.getKeyCode()==e.VK_DOWN)
		{
			b.p2.keyPressed(e);
		}
		if(e.getKeyCode()==e.VK_SPACE)
		{
			
			b.start();
			
			counting=3;
		}
		if(e.getKeyCode()==e.VK_ESCAPE)
		{
			counting=0;
			
			b.stop();
			
			
		}
		
		
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()==e.VK_W)
		{
			b.p1.keyReleased(e);
		}
		if(e.getKeyCode()==e.VK_S)
		{
			b.p1.keyReleased(e);
		}
		if(e.getKeyCode()==e.VK_UP)
		{
			b.p2.keyReleased(e);
		}
		if(e.getKeyCode()==e.VK_DOWN)
		{
			b.p2.keyReleased(e);
		}
	}
	public void keyTyped(KeyEvent e)
	{
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
	/*	// TODO Auto-generated method stub
		int x=e.getX();
		int y=e.getY();
		if(x>start.x && x<start.x+70 && y>start.y && y<start.y+30)
		{
			counting=3;
			
			
			
		}
		*/
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		if(counting==2)
			counting=0;
		else if(counting==0)
			counting=2;
	}
}
