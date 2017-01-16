package tableTennis;

import java.awt.Color;
import java.awt.Graphics;

public class Ball 
{
	double x_vel, y_vel, x_pos, y_pos;
	
	public Ball()
	{
		x_pos = 350;
		y_pos = 250;
		x_vel = getRandomSpeed() * getRandomDirection();
		y_vel = getRandomSpeed() * getRandomDirection();
	}
	
	public double getRandomSpeed()
	{
		return (Math.random()*3 + 2);
	}
	
	public double getRandomDirection()
	{
		int rand = (int)(Math.random()*2);
		
		return (rand == 1)? 1: -1;
	}
	
	public void move()
	{
		x_pos += x_vel;
		y_pos += y_vel;
		
		if (y_pos < 10) y_vel = -y_vel;
		if (y_pos > 490) y_vel = -y_vel;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillOval((int)x_pos - 10, (int)y_pos - 10, 20, 20);
	}
	
	public void checkPaddleCollision(Paddle player_1, Paddle player_2)
	{
		if (this.x_pos <= 50)
		{
			if (this.y_pos >= player_1.getY() && this.y_pos <= player_1.getY()+80)
					this.x_vel = -this.x_vel;
		}
		
		if (this.x_pos >= 650)
		{
			if (this.y_pos >= player_2.getY() && this.y_pos <= player_2.getY()+80)
					this.x_vel = -this.x_vel;
		}
	}
	
	public int getX()
	{
		return (int) x_pos;
	}
	
	public int getY()
	{
		return (int) y_pos;
	}

}
