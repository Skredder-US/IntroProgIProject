import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.KeyStroke;

public class CalcButton extends JButton implements ActionListener {

	public static final Font LARGE = new Font("Segoe UI Light", Font.PLAIN, 30);
	private static final Color DARKER_GRAY = new Color(240, 240, 240);
	private static final Color LIGHTER_GRAY = new Color(250, 250, 250);
	private static final Color GRAY_HOVER = new Color(215, 215, 215);
	private static final Color GRAY_PRESSED = new Color(187, 187, 187);
	private static final Color EQUALS = new Color(138, 199, 213);
	private static final Color EQUALS_HOVER = new Color(69, 176, 200);
	private static final Color EQUALS_PRESSED = new Color(0, 153, 188);
	private static final String[] BUTTON_TEXT = 
		{"%", "CE", "C", "◄",
		 "1/x", "x²", "√x", "÷",
		 "7", "8", "9", "×",
		 "4", "5", "6", "-",
		 "1", "2", "3", "+",
		 "±", "0", ".", "="};
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

	private Calculator calc;
	private int index; 
	
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
			super.setFont(LARGE);
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
	            g.setColor(EQUALS_PRESSED);
			}
        } else if (getModel().isRollover()) {
			if (index < 23) {
				// all but bottom right
	            g.setColor(GRAY_HOVER);
			} else {
				// bottom right
	            g.setColor(EQUALS_HOVER);
			}
        } else {
    		if (index == 23) {
        		// bottom right
    			g.setColor(EQUALS);
        	} else if (index < 8 || index % 4 == 3) {
        		// top 2 rows and rightmost column except the bottom one 
        		g.setColor(DARKER_GRAY);
     		} else { // index >= 8 && index % 4 != 3
     			// bottom left 3x4 (WxH) 
     			g.setColor(LIGHTER_GRAY);
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
		} else if (text.equals("-")) {
			calc.subtract();
		} else if (text.equals("×")) {
			calc.multiply();
		} else if (text.equals("÷")) {
			calc.division();
		} else if (text.equals("=")) {
			calc.equals();
		} else if (text.equals("1/x")) {
			calc.oneOver();
		} else if (text.equals("x²")) {
			calc.squared();
		} else if (text.equals("√x")) {
			calc.sqrt();
		} else if (text.equals("%")) {
			calc.percent();
		} else {
			System.err.println("Error: Unhandled button");
		}
	}
	
}
