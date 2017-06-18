package mancala;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;

/**
 * Icon that will display Stones in the pockets as well
 * as the Mancala pools at the ends
 * @author Alan Chin, Mike Royal
 */
public class StoneIcon implements Icon{
	
	private int height;
	private int width;
	private int numStones; //number to display
	private int across;  //how many across you want the stones
	private Color color;
	/**
	 * Default Constructor
	 * @param width - width of the Icon
	 * @param height - height of the Icon
	 * @param wide - how wide you want each subShape to be
	 */
	public StoneIcon(int width, int height, int across){
		this.width = width;
		this.height = height;
		this.across = across;
		color = Color.BLACK;
	}

	@Override
	public int getIconHeight() {
		return height;
	}

	@Override
	public int getIconWidth() {
		return width;
	}
	
	/**
	 * How many stones to draw in each Icon
	 * @param x - number of stones
	 */
	public void setNumStones(int x){
		this.numStones = x;
	}
	
	public void setAcross(int x){
		this.across = x;
	}

	@Override
	public void paintIcon(Component arg0, Graphics g, int arg2, int arg3) {
		Graphics2D g2 = (Graphics2D)g; 
		//NUMBER OF STONES TO PAINT
		int temp = numStones;
		//INITIAL STARTING POINT
		int startX = 5;   
		int startY = 15; 
		//HOW MANY ACROSS YOU WANT THE STONES
		int widthOfShape = width /across; 
		
		//WHILE THERE ARE STILL STONES TO BE PAINTED
		while (temp != 0){
			//IF THE PAINTING IS ABOUT TO RUN OUT OF ROOM ON RIGHT SIDE
			//START PAINTING AGAIN ONE ROW BELOW
			if (startX >= width -2){
				startY += widthOfShape+2;
				startX = 5;
			}
			StoneShape shape = new StoneShape(startX, startY, widthOfShape, color);
			shape.draw(g2);
			startX += widthOfShape+2;
			temp--;	
		}
		
	}
	
	public void setStoneColor(Color color){
		this.color = color;
	}
	/**
	 * Subclass to draw each of the subShapes inside the Icon
	 * @author Alan Chin
	 *
	 */
	public class StoneShape {
		
		private int x, y ,width;
		private Color stoneColor = Color.BLACK;
		
		public StoneShape(int x, int y, int width, Color color){
	
	     this.x = x;
	     this.y = y;
	     this.width = width;
	     this.stoneColor = color;
	   }

	   public void draw(Graphics2D g2)
	   {
		   Ellipse2D.Double circle
	         = new Ellipse2D.Double(x, y, width,width);
		   
		   g2.setColor(stoneColor);
		   g2.fill(circle);
		   g2.draw(circle);
	     
	   }
	}
}
