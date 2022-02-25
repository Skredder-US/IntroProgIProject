import java.awt.Toolkit;

import javax.swing.JFrame;

public class CalcFrame extends JFrame {
	public static final int HEIGHT = 509;
	public static final int WIDTH  = 516;
	public static final String TITLE = "Calculator";
	
	private CalcPanel calcPanel;
	
	public CalcFrame(Calculator calc) {
		super(TITLE);

		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); // centers frame in screen
		setIconImage(
				Toolkit.getDefaultToolkit().getImage("src/Calculator-Icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		calcPanel = new CalcPanel(calc);
		add(calcPanel);
		
		setVisible(true);
	}
	
	public void setText(String entry) {
		calcPanel.setText(entry);
	}
	
}
