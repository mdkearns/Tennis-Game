package tableTennis;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerPaddle implements Paddle
{
	
	private static final double GRAVITY = 0.94;
	boolean up_accel, down_accel;
	double y_pos, y_vel;
	int player, x_pos;
	
	public PlayerPaddle(int player)
	{
		up_accel = false;
		down_accel = false;
		
		y_pos = 210;
		y_vel = 0;
		
		x_pos = (player == 1)? 20: 660;
	}
	
	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(x_pos, (int)y_pos, 20, 80);
	}

	public void move() 
	{
		if (up_accel)
		{
			if (y_vel > -5) y_vel -= 1;
		}
		if (down_accel)
		{
			if (y_vel < 5) y_vel += 1;
		}
		if (!up_accel && !down_accel)
		{
			y_vel *= GRAVITY;
		}
		
		y_pos += y_vel;
		
		if (y_pos < 0) y_pos = 0;
		if (y_pos > 420) y_pos = 420;
	}

	public int getY() 
	{
		return (int)y_pos;
	}
	
	public void setUpAccel(boolean input)
	{
		up_accel = input;
	}
	
	public void setDownAccel(boolean input)
	{
		down_accel = input;
	}

}
