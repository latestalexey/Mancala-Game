package mancala;

import java.awt.Color;

/**
 * Pink view of the game board
 * @author Alan Chin
 *
 */
public class PinkView implements View{
	
		private Color color;
		private int stoneWidth;
		private Color stoneColor;
		
		public PinkView (){
			color = new Color(255, 204, 204);
			stoneColor = Color.WHITE;
			stoneWidth = 6;
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


