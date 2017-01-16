package tableTennis;

import java.awt.Color;
import java.awt.Graphics;

public class ComputerPaddle implements Paddle
{
	
	private Ball game_ball;
	boolean up_accel, down_accel;
	double y_pos, y_vel;
	int player, x_pos;
	
	public ComputerPaddle(int player, Ball game_ball)
	{
		up_accel = false;
		down_accel = false;
		
		this.game_ball = game_ball;
		
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
		y_pos = game_ball.getY() - 40;
		
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
