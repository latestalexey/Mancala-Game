package mancala;

import java.awt.Color;

/**
 * Default view of the game board
 * Game board will use this if any error happen with selection
 * @author Alan Chin
 * @author Mike Royal
 *
 */
public class DefaultView implements View{

	private Color color;
	private int stoneWidth;
	private Color stoneColor;
	
	public DefaultView (){
		color = Color.WHITE;
		stoneColor = Color.BLACK;
		stoneWidth = 4;
	}
	

	@Override
	public void setStoneWidth(int width) {
		stoneWidth = width;
	}

	@Override
	public void setBackgroundColor(Color color) {
		this.color = color;
	}

	public int getStoneWidth(){
		return stoneWidth;
	}
	
	public Color getColor(){
		return color;
	}

	@Override
	public void setStoneColor(Color color) {
		this.stoneColor = color;
	}

	@Override
	public Color getStoneColor() {
		return this.stoneColor;
	}

}


