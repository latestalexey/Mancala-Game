package mancala;

/**
 *
 * @author Mike
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import javax.swing.*;


public class MancalaTester extends JPanel implements ActionListener {
  Image myImage = new ImageIcon("texture_wood.jpg").getImage();
  String text = "Mancala";
  Timer timer = new Timer(20, this);
  private float alpha = 1f;
  JButton addUndo;
  int x;
  
  public MancalaTester() {
    timer.start();
  }

    @Override
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;
    
        g2d.drawImage(myImage, 0, 0, null);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.drawImage(myImage, 0, 0, null);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        
        // Draw text centered over image.
        Font font = g2d.getFont().deriveFont(36f);
        g2d.setFont(font);
        FontRenderContext frc = g2d.getFontRenderContext();
        float width = (float)font.getStringBounds(text, frc).getWidth();
        LineMetrics lm = font.getLineMetrics(text, frc);
        float sx = (w - width)/2;
        float sy = (h + lm.getHeight())/2 - lm.getDescent();
        g2d.setPaint(Color.blue);
        g2d.drawString(text, sx, sy);
    }

    @Override
  public void actionPerformed(ActionEvent e) {
    alpha += -0.01f;
    if (alpha <= 0) {
      alpha = 0;
      timer.stop();
      
    }
    repaint();
  }
   
  public static void main(String[] args) {
    JFrame frame = new JFrame("Mancala");
    
    //Number of stones input
    int stones = Integer.parseInt(JOptionPane.showInputDialog(frame, 
            "Enter 3 or 4 stones per pits:"));
    
    while (stones < 3 || stones > 4){
    	JOptionPane.showMessageDialog(null,stones+" is not a valid entry.");
    	stones = Integer.parseInt(JOptionPane.showInputDialog(frame, 
            "Enter 3 or 4 stones per pits:"));
    }
   
    //Selecting style for Board
    JDialog.setDefaultLookAndFeelDecorated(true);
      Object[] options = {"Light Blue", "Pink", "Quit Game!"};
       
    int response = JOptionPane.showOptionDialog(frame, 
            "Select Game Board style?", "Confirm",
    
    JOptionPane.YES_NO_CANCEL_OPTION,
    JOptionPane.QUESTION_MESSAGE,
    null, options, options[2]);
    
    Model model = new Model(stones);
	ViewController viewControl = new ViewController(model);
	
	//STRATEGY LAYOUT HERE
	if (response == 0)
		viewControl.setView(new BlueView());
	else if (response == 1)
		viewControl.setView(new PinkView());
	else 
		System.exit(0);
	

	model.addObserver(viewControl);
	model.refresh();
	
	viewControl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	viewControl.setPreferredSize(new Dimension(1100,400));
	viewControl.pack();
	viewControl.setVisible(true);
    
  }
  
  
}