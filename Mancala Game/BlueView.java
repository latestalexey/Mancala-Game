package mancala;

import java.awt.Color;

/**
 * Blue color view of the game board
 * @author Alan Chin
 *
 */
public class BlueView implements View{
	
	private Color color;
	private int stoneWidth;
	private Color stoneColor;
	
	public BlueView (){
		color = new Color(102, 153, 255);
		stoneColor = Color.GRAY;
		stoneWidth = 7;
	}

	@Override
	public void setStoneWidth(int width) {
		stoneWidth = width;
	}

	@Override
	public void setBackgroundColor(Color c) {
		this.color = c;
	}
	
	public int getStoneWidth(){
		return stoneWidth;
	}
	
	public Color getColor(){
		return color;
	}

	@Override
	public void setStoneColor(Color stoneColor) {
		this.stoneColor = stoneColor;
	}

	@Override
	public Color getStoneColor() {
		return this.stoneColor;
	}

}
