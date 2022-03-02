import java.awt.Toolkit;

import javax.swing.JFrame;

public class CalcFrame extends JFrame {
	
	private CalcPanel calcPanel;
	
	public CalcFrame(Calculator calc) {
		super("Calculator");

		setSize(518, 510);
		setResizable(false);
		setIconImage(
				Toolkit.getDefaultToolkit().getImage("src/Calculator-Icon.png"));
		setLocationRelativeTo(null); // centers frame in screen
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		calcPanel = new CalcPanel(calc);
		
		add(calcPanel);
		setVisible(true);
	}
	
	public void setText(String text) {
		calcPanel.setText(text);
	}
	
}
