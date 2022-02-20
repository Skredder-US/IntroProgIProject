import javax.swing.JFrame;

public class CalcFrame extends JFrame {
	public static final int HEIGHT = 509;
	public static final int WIDTH  = 516;
	public static final String TITLE = "Calculator";
	
	public CalcFrame() {
		super(TITLE);

		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null); // centers frame in screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
