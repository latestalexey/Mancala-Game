package mancala;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 * This class acts as the VIEW and CONTROLLER for the Mancala Game.
 * The inner Class POCKETBUTTON will act as a controller and update the 
 * model accordingly.
 * @author Alan Chin
 *
 */
public class ViewController extends JFrame implements Observer{

	private Model model;
	private ArrayList<PocketButton> p1; //PLAYER ONE BUTTONS
	private ArrayList<PocketButton> p2; //PLAYER TWO BUTTONS
	private JTextArea info;				//INFORMATION PANEL
	private JLabel pitTwo, pitOne;		//PLAYER PITS
	private StoneIcon pitIconOne, pitIconTwo; //ICONS INSIDE THE PITS
	private View view = new DefaultView();
	private JScrollPane scrollPane;
	
	public ViewController(final Model model){
		
		
		this.setTitle("Mancala Game");
		this.model = model;
		
		p1 = new ArrayList<PocketButton>();
		p2 = new ArrayList<PocketButton>();
		
		//ICONS THAT ARE INSIDE THE MANCALA PITS FOR EACH PLAYER
		pitIconOne = new StoneIcon(125, 275, 5);
		pitIconTwo = new StoneIcon(125, 275, 5);
		
		//SCROLLABLE TEXTAREA TO DISPLAY ERROR MSGS DURING PLAY
		info = new JTextArea();
		scrollPane = new JScrollPane(info);
		scrollPane.setBackground(view.getColor());
		scrollPane.setBorder(new TitledBorder(new EtchedBorder(),"Information"));
		
		//TWO JPANELS REPRESENTING THE MANCALA POOLS AT THE ENDS
		pitTwo = new JLabel(pitIconTwo);
		pitTwo.setPreferredSize(new Dimension(150,300));
		pitTwo.setBackground(Color.GRAY);
		pitTwo.setBorder(new TitledBorder(new EtchedBorder(),"Player Two"));
		
		pitOne = new JLabel(pitIconOne);
		pitOne.setPreferredSize(new Dimension(150,300));
		pitOne.setBackground(Color.GRAY);
		pitOne.setBorder(new TitledBorder(new EtchedBorder(),"Player One"));
		
		//CREATE AN UNDO BUTTON THAT RETREIVE THE LAST STATE OF THE BOARD
		JButton undoButton = new JButton("UNDO LAST MOVE");
		
		//THIS ACTIONLISTENER COULD BE CONSIDERED PART OF THE CONTROLLER
		undoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				model.undoMove();
				//REVERT TURN BACK TO OTHER
				if ((!model.getTurn() && model.canUndo(1)) || (model.getTurn() && model.canUndo(2))){
					model.setTurn(!model.getTurn());
					model.setUndo(1, false);
					model.setUndo(2, false);
				}	
			}
		});
		
		//NEW GAME BUTTON AND ACTION LISTENER
		JButton newGame = new JButton("NEW GAME");
		newGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				model.resetGame();	
			}
		});
		
		//EXIT BUTTON TO LEAVE GAME
		JButton exit = new JButton("EXIT GAME");
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}	
		});
		
		//PANELS USED ONLY TO DISPLAY THE PLAYABLE FIELDS PROPERLY
		//NO DATA OR ANYTHING INSIDE THESE
		JPanel center = new JPanel();
		JPanel playerOnePanel = new JPanel();
		JPanel playerTwoPanel = new JPanel();
		
		//GRIDLAYOUT OF SUBJPANELS FOR EACH PLAYER 1 ROW OF 6 COLUMNS
		playerOnePanel.setLayout(new GridLayout(1,6));
		playerTwoPanel.setLayout(new GridLayout(1,6));
		
		//GRIDLAYOUT OF CENTER PANEL 2 ROWS IN SINGLE COLUMN
		center.setPreferredSize(new Dimension(690,300));
		center.setLayout(new GridLayout(3,1));
		
		//POPULATE/INSTANCE THE INITIAL ARRAYLIST OF BUTTONS FOR EACH PLAYER
		for (int a = 0; a < 6; a++){
			p1.add(new PocketButton(1, a, model));
			p2.add(new PocketButton(2, a, model));
		}
		
		//DISPLAY PLAYER TWO POCKETS IN REVERSE ORDER TO MOVE COUNTERCLOCKWISE
		for (int i = 5 ; i >= 0 ; i --)
			playerTwoPanel.add(p2.get(i));
		//DISPLAY PLAYER ONE POCKETS IN REGULAR ORDER	
		for (int x = 0 ; x < 6; x++)
			playerOnePanel.add(p1.get(x));
		
		//ADD PLAYABLE FIELDS AND THE INFO BAR TO THE CENTER PANEL
		center.add(playerTwoPanel);
		center.add(playerOnePanel);
		center.add(scrollPane);
		
		//ADD EVERYTHING TO THE FRAME
		this.setLayout(new FlowLayout());
		this.add(pitTwo);
		this.add(center);
		this.add(pitOne);
		this.add(undoButton);
		this.add(newGame);
		this.add(exit);
		
		this.getContentPane().setBackground(view.getColor());
	
	}
	
	/**Listens to the Model and once a change is detected
	 * Gets the information from the model and updates View
	 */
	public void update(Observable arg0, Object arg1) {
		//UPDATE THE PITS
		pitIconOne.setNumStones(model.getMancala(1));
		pitIconTwo.setNumStones(model.getMancala(2));
		
		//UPDATE THE PLAYABLE FIELDS
		for (int n = 0; n < 6; n++) { 
			p1.get(n).setCount(model.getValue(1,n));
			p2.get(n).setCount(model.getValue(2,n));
		}
		//UPDATE THE INFORMATION BAR
		info.setText("");
		for (String s : model.getInfo())
			info.append(s+"\n");
		
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * Uses strategy pattern to set how the view looks
	 * @param v
	 */
	public void setView(View v){
		
		this.getContentPane().setBackground(v.getColor());
		scrollPane.setBackground(v.getColor());
		pitIconOne.setStoneColor(v.getStoneColor());
		pitIconOne.setAcross(v.getStoneWidth());
		pitIconTwo.setStoneColor(v.getStoneColor());
		pitIconTwo.setAcross(v.getStoneWidth());
		
		for (int n = 0; n < 6; n++) {
			p1.get(n).setBackground(v.getColor());
			p1.get(n).getIcon().setStoneColor(v.getStoneColor());
			p1.get(n).getIcon().setAcross(v.getStoneWidth());
			p2.get(n).setBackground(v.getColor());
			p2.get(n).getIcon().setStoneColor(v.getStoneColor());
			p2.get(n).getIcon().setAcross(v.getStoneWidth());
		}
		
	}
	
public class PocketButton extends JButton{
		
		private static final long serialVersionUID = 1L;
		private int player;
		private int myPosition; //position within the arrayList
		private StoneIcon icon;
		private Model model;
		
		/**
		 * Default Constructor for the PocketButton Class	
		 * @param p - player for whom the button belongs to
		 * @param position - position within the players field
		 * @param m - the model associated with this game
		 */
		public PocketButton(int p, int position, Model m){	
			player = p;
			myPosition = position;
			model = m;
			icon = new StoneIcon(100,100,5);
			this.setIcon(icon);
			this.setBackground(Color.WHITE);
			
			//THIS IS THE CONTROLLER FOR THE BOARD, IT WILL DECIDE PROPER PLAY RULES
			//AND UPDATE THE MODEL INFORMATION AS NEEDED
			ActionListener listen = new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					
					//SAVE THE PREVIOUS STATE OF THE MODEL BEFORE ANYTHING IS DONE
					model.saveState(); 
					boolean turn = model.getTurn();
					
					//PLAYER ONE TURN
					if (turn && player == 1) 
						inPlay(player, turn);
					//PLAYER TWO TURN
					else if (!turn && player == 2)
						inPlay(player, turn);
					//CLICKED ON THE WRONG SIDE, OTHER PLAYER'S TURN
					else{
						if (turn)
							model.addInfo("You cannot move this, it is Player One's turn");
						else
							model.addInfo("You cannot move this, it is Player Two's turn");
					}
					//CHECK FOR GAME COMPLETION AFTER EVERY TURN
					checkEndGame();
					model.refresh();
				}
			};
			this.addActionListener(listen);
		}	
		/**
		 * Sets the number of stones to display in the Icon
		 * @param c - the number of stones
		 */
		public void setCount(int c){
			this.icon.setNumStones(c);
		}
		
		public StoneIcon getIcon(){
			return icon;
		}
		
		/**
		 * Checks to see if any player has emptied their side of the board
		 */
		public void checkEndGame(){
			int endGameOne = 0;
			int endGameTwo = 0;
			
			for (int n = 0 ; n < 6; n++){
				endGameOne += model.getValue(1, n);
				endGameTwo += model.getValue(2, n);
			}
			if (endGameOne == 0){
				model.increaseMancala(2, endGameTwo);
				displayStats();
			}
			else if (endGameTwo == 0){
				model.increaseMancala(1, endGameOne);
				displayStats();
			}
		}
		
		/**
		 * Displays the end game message and the winner as well as the scores.
		 */
		public void displayStats(){
			//CLEAN OUT THE POCKETS AT END GAME
			model.emptyPockets();
			
			if (model.getMancala(1) > model.getMancala(2))		
				model.addInfo("Player One Wins. Score is " + model.getMancala(1) + "/" + model.getMancala(2));
			else if (model.getMancala(1) < model.getMancala(2))	
				model.addInfo("Player Two Wins. Score is " + model.getMancala(2) + "/" + model.getMancala(1));
			else
				model.addInfo("Game is a tie. Score is " + model.getMancala(2) + "/" + model.getMancala(1));
		}
		
		/**
		 * Method to determine where each piece will go and also 
		 * applies and enforces the rules of the game.
		 * @param player - this turn's player
		 * @param t - value of the boolean turn
		 */
		public void inPlay(int player, boolean t ){
			//GET THE NUMBER OF STONES IN THE CLICKED POCKET
			int currentCount = model.getValue(player, myPosition); 
			//EMPTY THE POCKET 
			model.setValue(player, myPosition, 0);	
			int tmpPlayer = player;
			//SET THE STARTING POSITION TO THE POCKET AFTER THE ONE YOU JUST EMPTIED
			int tmpPosition = myPosition+1; 
			
			//IF PLAYER CLICKED ON EMPTY POCKET
			if (currentCount == 0 )
				model.addInfo("Pocket is empty. Please choose another Pocket");
			//WHILE THERE ARE STILL STONES TO BE PLAYED
			while (currentCount != 0){
				//THE LAST STONE IN HAND, SPECIAL RULES APPLIED
				if (currentCount == 1){
					//LAST STONE GOES INTO YOUR OWN MANCALA
					if (tmpPlayer == player && tmpPosition == 6 ){ 
						model.increaseMancala(player, 1);
						model.addInfo("Player " + player + " gets an extra turn");
						model.setTurn(t);
						currentCount--;
						break;
					}
					//LAST STONE GOES INTO YOUR OWN POCKET THAT WAS INITIALLY EMPTY
					else if (tmpPlayer == player && model.getValue(player, tmpPosition) == 0){
						int capture = model.getValue(Math.abs(player-3), Math.abs(tmpPosition -5)) + 1;
						model.setValue(Math.abs(player-3), Math.abs(tmpPosition -5), 0);
						model.increaseMancala(player, capture);
						model.addInfo("Player " + player + " captures the opposite sides stones");
						tmpPosition++;
						currentCount--;
						model.setTurn(!t);
						break;
					}
				}
				//IF THE POSITION REACHED IS THE END OF YOUR SIDE AND HEADING INTO YOUR MANCALA
				if (tmpPosition == 6 && tmpPlayer == player){ 
					model.increaseMancala(player, 1);
					tmpPosition = 0;
					tmpPlayer = (Math.abs(player-3));
					currentCount--;
				}
				//IF THE POSITION REACHED IS THE OTHER PLAYERS MANCALA
				//DO NOT PUT ANYTHING IN THEIR MANCALA
				else if (tmpPosition == 6 && tmpPlayer == Math.abs(player-3)){
					tmpPosition = 0;
					tmpPlayer = Math.abs(player-3);	
				}
				//ADD STONES AS YOU MOVE ALONG 
				else {
					model.addStone(tmpPlayer,tmpPosition);
					tmpPosition++;
					currentCount--;
				}
				//OTHER PLAYERS TURN NOW
				model.setTurn(!t);
				model.setUndo(player, true);
			}
		}
	}
}
