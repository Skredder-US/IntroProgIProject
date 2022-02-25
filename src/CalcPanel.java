import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class CalcPanel extends JPanel {
	public static final Color BACKGROUND = new Color(230, 230, 230);
	public static final String FONTNAME_PLAIN = "Segoe UI";
	public static final String FONTNAME_BOLD = "Segoe UI Semibold";
	public static final Font PLAIN = new Font(FONTNAME_PLAIN, Font.PLAIN, 16);
	public static final Font BOLD = new Font(FONTNAME_BOLD, Font.PLAIN, 18);
	
	private CalcLabel calcLabel;
	
	public CalcPanel(Calculator calc) {
		super(new BorderLayout());
		
		calcLabel = new CalcLabel(calc);
		
		add(calcLabel, BorderLayout.NORTH);
		add(new CalcButtons(calc, calcLabel), BorderLayout.CENTER);
	}
	
	public void setText(String text) {
		calcLabel.setText(text);
	}
	
}
