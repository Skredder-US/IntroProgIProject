import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.KeyStroke;

public class CalcButton extends JButton implements ActionListener {

	private static final Color BLUE_PRESSED = new Color(0, 153, 188);
	private static final Color BLUE_HOVER = new Color(75, 180, 205);
	private static final Color BLUE = new Color(145, 205, 220);
	private static final Color GRAY_PRESSED = new Color(203, 203, 203);
	private static final Color GRAY_HOVER = new Color(224, 224, 224);
	private static final Color GRAY_247 = new Color(247, 247, 247);
	private static final Color GRAY_252 = new Color(252, 252, 252);
	// *, +, @ are broke
	// %, - are missing
	// same outer indices as BUTTON_TEXT indices
	private static final Integer[][] HOTKEYS =
			{{}, {KeyEvent.VK_DELETE}, {KeyEvent.VK_ESCAPE},
					{KeyEvent.VK_BACK_SPACE},
			 
			 {KeyEvent.VK_R}, {}, {KeyEvent.VK_AT}, {KeyEvent.VK_SLASH,
				 	KeyEvent.VK_DIVIDE},
			 
			 {KeyEvent.VK_7, KeyEvent.VK_NUMPAD7}, 
			 		{KeyEvent.VK_8, KeyEvent.VK_NUMPAD8}, 
			 		{KeyEvent.VK_9, KeyEvent.VK_NUMPAD9},
			 		{KeyEvent.VK_MULTIPLY, KeyEvent.VK_ASTERISK},
			 
			 {KeyEvent.VK_4, KeyEvent.VK_NUMPAD4},
			 		{KeyEvent.VK_5, KeyEvent.VK_NUMPAD5}, 
			 		{KeyEvent.VK_6, KeyEvent.VK_NUMPAD6},
			 		{KeyEvent.VK_SUBTRACT},
			 
			 {KeyEvent.VK_1, KeyEvent.VK_NUMPAD1},
			 		{KeyEvent.VK_2, KeyEvent.VK_NUMPAD2},
			 		{KeyEvent.VK_3, KeyEvent.VK_NUMPAD3},
			 		{KeyEvent.VK_ADD, KeyEvent.VK_PLUS},
			 
			 {KeyEvent.VK_F9}, {KeyEvent.VK_0, KeyEvent.VK_NUMPAD0},
			 		{KeyEvent.VK_PERIOD , KeyEvent.VK_DECIMAL},
			 		{KeyEvent.VK_ENTER, KeyEvent.VK_EQUALS}};
	private static final String[] BUTTON_TEXT = 
			{"%", "CE", "C", "◄",
			 "1/x", "x²", "√x", "÷",
			 "7", "8", "9", "×",
			 "4", "5", "6", "-",
			 "1", "2", "3", "+",
			 "±", "0", ".", "="};
	
	
	private int index; 
	private Calculator calc;
	
	public CalcButton(Calculator calc, int index) {
		super(BUTTON_TEXT[index]);
		
		this.calc = calc;
		this.index = index;

		setFont();
		setHotkey();
		addActionListener(this);
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
	
	private void setHotkey() {
		for (Integer hotkey : HOTKEYS[index]) {
			getInputMap(WHEN_IN_FOCUSED_WINDOW).put(
					KeyStroke.getKeyStroke(hotkey, 0), "doClick");
		}
		// TODO Needs a new AbstractAction for each hotkey (like this)?
		getActionMap().put("doClick", new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			  doClick();
			}
			
		});
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

	@Override
	public void actionPerformed(ActionEvent ae) {
		String text = BUTTON_TEXT[index];
		if (text.matches("[0-9]")) {
			calc.number(text);
		} else if (text.equals("±")) {
			calc.negate();
		} else if (text.equals(".")) {
			calc.decimal();
		} else if (text.equals("CE")) {
			calc.clearEntry();
		} else if (text.equals("C")) {
			calc.clear();
		} else if (text.equals("◄")) {
			calc.back();
		} else if (text.equals("+")) {
			calc.add();
		} else if (text.equals("=")) {
			calc.equals();
		} else if (text.equals("1/x")) {
			calc.oneOver();
		} else if (text.equals("x²")) {
			calc.squared();
		} else if (text.equals("√x")) {
			calc.sqrt();
		}
	}
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
