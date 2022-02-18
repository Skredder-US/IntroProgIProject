import javax.swing.JFrame;

public class Calculator {

	public static final int FRAME_WIDTH = 300;
	public static final int FRAME_HEIGHT = 500;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		frame.setLocationRelativeTo(null); // centers frame on screen
		frame.setVisible(true);
	}

}
