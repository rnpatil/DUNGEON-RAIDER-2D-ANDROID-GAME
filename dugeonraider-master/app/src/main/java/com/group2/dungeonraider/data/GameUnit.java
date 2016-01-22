package com.group2.dungeonraider.data;

import android.content.Context;
import android.graphics.Rect;

public class GameUnit extends GameImage
{
	private int id;
	private static int count = 1;

	public GameUnit(Context context, int drawable)
	{
		super(context, drawable);

        id=count;
		count++;
	}


	public Rect getRect()
	{
		Rect rect = new Rect(mX, mY, (mX + this.getWidth()), (mY + this.getHeight()));
		return rect;
	}
	
	public boolean getCollision(int x, int y, int width, int height)
	{
		Rect rect = new Rect(x, y, (x + width), (y + height));
		return (rect.intersects(mX, mY, (mX + getWidth()), (mY + getHeight())));
	}
	
	public boolean getImpact(int x, int y)
	{
		if ((x >= mX) && (x <= (mX + this.getWidth())))
		{
			if ((y >= mY) && (y <= (mY + this.getHeight())))
			{
				return true;
			}
		}

		return false;
	}

	public static int getCount()
	{
		return count;
	}

	public static void resetCount()
	{
		count = 1;
	}

	public int getId()
	{
		return id;
	}
}
