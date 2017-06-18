package mancala;

import java.awt.Color;

/**
 * Interface for Views
 * @author Alan Chin, Mike Royal
 *
 */
public interface View {
	
	/**
	 * Sets how many stones would like to see across each pocket
	 * @param width
	 */
	public void setStoneWidth(int width);
	/**
	 * Set what color background you would like
	 * @param color
	 */
	public void setBackgroundColor(Color color);
	/**
	 * Sets what color you would like the stones to be
	 * @param color
	 */
	public void setStoneColor(Color color);
	/**
	 * Getter for how many you want across
	 * @return
	 */
	public int getStoneWidth();
	/**
	 * Getter for background color
	 * @return
	 */
	public Color getColor();
	/**
	 * Getter for stone color
	 * @return
	 */
	public Color getStoneColor();
	
}
