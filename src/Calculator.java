import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Calculator {

	public static final int FRAME_SIZE = 500;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_SIZE, FRAME_SIZE);
		frame.setLocationRelativeTo(null); // centers frame in screen
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JLabel result =
				new JLabel("1,000,000,000,000,000", SwingConstants.RIGHT);
		result.setFont(new Font("Courier", Font.BOLD, 36));
		result.setPreferredSize(new Dimension(450, 100));
		mainPanel.add(result, BorderLayout.NORTH);
		
		JPanel buttons = new JPanel(new GridLayout(0, 4, 2, 2));
		for (int i = 0; i < 24; i++) {
			buttons.add(new JButton("" + i));
		}
		mainPanel.add(buttons, BorderLayout.CENTER);
		
		frame.add(mainPanel);
		
		frame.setVisible(true);
	}

}
