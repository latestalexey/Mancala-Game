package mancala;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Model for the Mancala game that holds all the information
 * @author Jordan Jennings, Alan Chin
 */
public class Model extends Observable{
	
	private Player players[] = new Player[2];
	private Player saveState[];
	private ArrayList<String> information;
	private boolean currentTurn;
	private int stones;
	/**
	 * Default constructor for the model
	 * @param stones - the number of starting stones for the game board
	 */
	public Model(int stones){
		//POPULATE THE ARRAY OF PLAYERS
		players[0] = new Player();
		players[1] = new Player();
		
		this.stones = stones;
		currentTurn = true;
		
		//SET THE NUMBER OF STARTING STONES FOR ALL PLAYERS
		for (Player p : players){
			ArrayList<Integer> temp = p.getMyPockets();
			for (int i = 0 ; i < 6; i ++)
				temp.set(i, stones);
		}
		//INSTANCE THE INFORMATION ARRAYLIST
		information = new ArrayList<String>();
		saveState = new Player[2];
		saveState[0] = new Player();
		saveState[1] = new Player();
	}
    
	/**
	 * Saves the previous state of the board so player can undo their actions
	 */
    public void saveState(){ 
    	for (int i=0; i < 2; i++){
    		saveState[i].setMancala(players[i].getMancala()); 
    		for (int j=0; j < 6; j++)
    			saveState[i].getMyPockets().set(j, players[i].getMyPockets().get(j)); 
    	}	
    }
    
    /**
     * Resets the Array that is in play to the previous state
     * by copying over all the previous information
     */
    public void undoMove(){
    	for (int i=0; i < 2; i++){
    		players[i].setMancala(saveState[i].getMancala()); 
    		for (int j=0; j < 6; j++)
    			players[i].getMyPockets().set(j, saveState[i].getMyPockets().get(j)); 
    	}	
    	refresh();
    }
    
    /**
     * Gets the number of stones in a player's pocket
     * @param player 
     * @param pocket
     * @return - the value in the pocket
     */
    public int getValue(int player, int pocket){
    	return players[player-1].getMyPockets().get(pocket);
    }
    
    /**
     * Sets the number of stones in the pocket
     * Used mostly to empty the pocket
     * @param player
     * @param pocket
     * @param value
     */
    public void setValue(int player, int pocket, int value){
    	players[player-1].getMyPockets().set(pocket, value);
    	refresh();
    }
    
    /**
     * Increases a player's mancala pool count by the value plus whatever
     * is inside the pool already
     * @param player
     * @param value - amount to increase the pool to
     */
    public void increaseMancala(int player, int value){
    	players[player-1].setMancala(players[player-1].getMancala() + value);
    	refresh();
    }
    /**
     * Adds a single stone in a pocket
     * @param player
     * @param position
     */
    public void addStone(int player, int position){
    	players[player-1].addStone(position);
    	refresh();
    }
    
    /**
     * Gets the value for a players mancala pool
     * @param player
     * @return
     */
    public int getMancala(int player){
    	return players[player-1].getMancala();
    }
    
    /**
     * Adds a string to the infomation box
     * @param s
     */
    public void addInfo(String s){
    	information.add(s);
    }
    
    /**
     * Returns a Arraylist of all the infomation logged
     * throughout the game
     * @return
     */
    public ArrayList<String> getInfo(){
    	return information;
    }
    
    /**
     * Method to determine whether a player can undo an action
     * @param player
     * @return
     */
    public boolean canUndo(int player){
    	return players[player-1].canUndo();
    }
    
    /**
     * Sets the value of players undo
     * @param player
     * @param b
     */
    public void setUndo(int player, boolean b){
    	players[player -1].setUndo(b);
    }
    
    /**
     * Who's turn is it.
     * @return
     */
    public boolean getTurn(){
    	return currentTurn;
    }
    
    /**
     * Sets the turn
     * @param b
     */
    public void setTurn(boolean b){
    	currentTurn = b;
    }
    
    /**
     * Resets the game.
     */
    public void resetGame(){
    	currentTurn = true;
		
		//SET THE NUMBER OF STARTING STONES FOR ALL PLAYERS
		for (Player p : players){
			ArrayList<Integer> temp = p.getMyPockets();
			for (int i = 0 ; i < 6; i ++)
				temp.set(i, stones);
		}
		
		players[0].setMancala(0);
		players[1].setMancala(0);
		
		information.add("STARTING A NEW GAME....");
		
		refresh();
    }
    
    /**
	 * Refreshes the Game board.
	 */
	public void refresh(){
		setChanged();
    	notifyObservers();	
	}
	
	/**
	 * Clears the board when the game is done
	 */
	public void emptyPockets(){
		for (Player p : players){
			ArrayList<Integer> temp = p.getMyPockets();
			for (int i = 0 ; i < 6; i ++)
				temp.set(i, 0);
		}
		refresh();
	}
    /**
     * Inner Class Player 
     * Holds all information for each player
     * @author Jordan Jennings
     *
     */
    public class Player {
    	
    	private int myMancala;
        private ArrayList<Integer> myPockets;
        private boolean undo;
        
        public Player(){
        	myPockets = new ArrayList<Integer>();
        	for (int i = 0; i < 6; i++)
        		myPockets.add(0); //default empty
        	myMancala = 0;
        }
    	
        public void setUndo(boolean b) {
			undo = b;	
		}

		public ArrayList<Integer> getMyPockets() {
            return myPockets;
        }
        
        public int getMancala() {
            return myMancala;
        }
        
        public void setMancala(int newMancala) {
            myMancala = newMancala;
        }
        
        public void addStone(int position){
        	myPockets.set(position, myPockets.get(position)+1);
        }
        
        public boolean canUndo(){
        	return undo;
        }
    }
    
}
