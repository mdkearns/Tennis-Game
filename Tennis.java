package tableTennis;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tennis extends Applet implements Runnable, KeyListener
{
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 700, HEIGHT = 500;
	private static boolean gameStarted;
	private static Ball game_ball;
	private static ComputerPaddle player_2;
	private static Graphics gfx;
	private static Image img;
	private static PlayerPaddle player_1;
	private static Thread thread;

	public void init()
	{
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		
		gameStarted = false;
		
		game_ball = new Ball();
		player_1 = new PlayerPaddle(1);
		player_2 = new ComputerPaddle(2, game_ball);
		
		img = createImage(WIDTH, HEIGHT);
		gfx = img.getGraphics();
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void paint(Graphics g) 
	{
		gfx.setColor(Color.black);
		gfx.fillRect(0, 0, WIDTH, HEIGHT);
		
		if (game_ball.getX() < -10 || game_ball.getX() > 710)
		{
			gfx.setColor(Color.red);
			gfx.drawString("Game Over!", WIDTH/2, HEIGHT/2);
		}
		else
		{
			player_1.draw(gfx);
			player_2.draw(gfx);
			game_ball.draw(gfx);
		}
		
		if (!gameStarted)
		{
			gfx.setColor(Color.white);
			gfx.drawString("Tennis", 340, 100);
			gfx.drawString("Press Enter to Begin", 310, 130);
		}
		
		g.drawImage(img, 0, 0, this);
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}

	public void run() 
	{
		while(true)
		{
			if (gameStarted)
			{
				player_1.move();
				player_2.move();
				game_ball.move();
				
				game_ball.checkPaddleCollision(player_1, player_2);
			}
				
			repaint();
				
			try 
			{
				Thread.sleep(10);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void keyPressed(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			player_1.setUpAccel(true);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player_1.setDownAccel(true);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			gameStarted = true;
		}
	}

	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			player_1.setUpAccel(false);
		}
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			player_1.setDownAccel(false);
		}
		
	}

	public void keyTyped(KeyEvent e) {}

}
