import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class CalcButton extends JButton {

	private static final Color BLUE_PRESSED = new Color(0, 153, 188);
	private static final Color BLUE_HOVER = new Color(75, 180, 205);
	private static final Color BLUE = new Color(145, 205, 220);
	private static final Color GRAY_PRESSED = new Color(203, 203, 203);
	private static final Color GRAY_HOVER = new Color(224, 224, 224);
	private static final Color GRAY_247 = new Color(247, 247, 247);
	private static final Color GRAY_252 = new Color(252, 252, 252);
	private static final String[] BUTTON_TEXT = 
		{"%", "CE", "C", "◄",
		 "1/x", "x²", "√x", "÷",
		 "7", "8", "9", "×",
		 "4", "5", "6", "-",
		 "1", "2", "3", "+",
		 "±", "0", ".", "="};
	
	private int index; 
	
	public CalcButton(int index) {
		super(BUTTON_TEXT[index]);
		
		this.index = index;
		
		setFont();
		setBorder(BorderFactory.createEmptyBorder());		
		setContentAreaFilled(false); // Necessary for hover and click actions
		setFocusPainted(false); // Removes button selecting's text highlight
	}
	
	private void setFont() {
		if (index % 4 == 3) {
			// Rightmost column
			super.setFont(CalcPanel.LARGE);
		} else if (index < 8) {
			// top left 3x2 (WxH)
			super.setFont(CalcPanel.PLAIN);
		} else { // index >= 8
			// The rest (bottom left 3x4)
			super.setFont(CalcPanel.BOLD);
		}
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        // Set the color
		if (getModel().isPressed()) {
			if (index < 23) {
				// all but bottom right
	            g.setColor(GRAY_PRESSED);
			} else {
				// bottom right
	            g.setColor(BLUE_PRESSED);
			}
        } else if (getModel().isRollover()) {
			if (index < 23) {
				// all but bottom right
	            g.setColor(GRAY_HOVER);
			} else {
				// bottom right
	            g.setColor(BLUE_HOVER);
			}
        } else {
    		if (index == 23) {
        		// bottom left
    			g.setColor(BLUE);
        	} else if (index < 8 || index % 4 == 3) {
        		// top 2 rows and rightmost column except the bottom one 
        		g.setColor(GRAY_247);
     		} else { // index >= 8 && index % 4 != 3
     			// bottom left 3x4 (WxH) 
     			g.setColor(GRAY_252);
     		}
        }
        
	 	// Draw the button
        g.fillRect(0, 0, getWidth(), getHeight());
        
        // The rest
        super.paintComponent(g);
    }
	 
}
