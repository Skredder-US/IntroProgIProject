import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Calculator {
	
	public static final int FRAME_WIDTH = 516;
	public static final int FRAME_HEIGHT = 509;
	public static final String FONT_NAME_PLAIN = "Segoe UI";
	public static final String FONT_NAME_BOLD = "Segoe UI Semibold";
	public static final String[] BUTTON_TEXT = 
			{"%", "CE", "C", "◄",
			 "1/x", "x²", "√x", "÷",
			 "7", "8", "9", "×",
			 "4", "5", "6", "-",
			 "1", "2", "3", "+",
			 "±", "0", ".", "="};
	public static final Color LIGHT_BLUEISH = new Color(145, 205, 220);
	public static final Color DARKER_GRAY = new Color(240, 240, 240);
	public static final Color LIGHTER_GRAY = new Color(252, 252, 252);
	public static final Color MIDDLE_GRAY = new Color(247, 247, 247);
	public static final Font BUTTON_BOLD =
			new Font(FONT_NAME_BOLD, Font.PLAIN, 18);
	public static final Font BUTTON_PLAIN =
			new Font(FONT_NAME_PLAIN, Font.PLAIN, 16);
	public static final Font BUTTON_LARGE =
			new Font(FONT_NAME_PLAIN, Font.PLAIN, 28);
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null); // centers frame in screen
		
		JPanel mainPanel = new JPanel(new BorderLayout()); 
		
		// Add result display
		JLabel result =  /* Temporary fill text */
				new JLabel("1,000,000,000,000,000", SwingConstants.RIGHT);
		result.setFont(new Font(FONT_NAME_BOLD, Font.PLAIN, 40));
		result.setPreferredSize(new Dimension(450, 90));
		result.setBackground(DARKER_GRAY);
		result.setOpaque(true); // necessary to set Color
		result.setBorder(new EmptyBorder(0, 0, 0, 10));
		mainPanel.add(result, BorderLayout.NORTH);
		
		// Add buttons
		JPanel buttons = new JPanel(new GridLayout(0, 4, 2, 2));
		buttons.setBackground(DARKER_GRAY);
		buttons.setBorder(new LineBorder(DARKER_GRAY, 5, true));
		for (int i = 0; i < 24; i++) {
			JButton button = new JButton(BUTTON_TEXT[i]);
			if (i % 4 == 3) {
				button.setFont(BUTTON_LARGE);
				if (i != 23) {
					button.setBackground(MIDDLE_GRAY);
					button.setBorder(
							new LineBorder(MIDDLE_GRAY, 1, true));
				} else { // i == 23
					button.setBackground(LIGHT_BLUEISH);
					button.setBorder(
							new LineBorder(LIGHT_BLUEISH, 1, true));
				}
			} else if (i > 7) {
				button.setFont(BUTTON_BOLD);
				button.setBackground(LIGHTER_GRAY);
				button.setBorder(
						new LineBorder(LIGHTER_GRAY, 1, true));
			} else {
				button.setFont(BUTTON_PLAIN);
				button.setBackground(MIDDLE_GRAY);
				button.setBorder(
						new LineBorder(MIDDLE_GRAY, 1, true));
			}
			buttons.add(button);
		}
		mainPanel.add(buttons, BorderLayout.CENTER);
		
		frame.add(mainPanel);
		frame.setVisible(true);
	}

}
