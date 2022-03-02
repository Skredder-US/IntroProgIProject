import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JPanel;

public class CalcPanel extends JPanel {
	public static final Color BACKGROUND = new Color(230, 230, 230);
	public static final String FONTNAME_BOLD = "Segoe UI Semibold";
	public static final Font BOLD = new Font(FONTNAME_BOLD, Font.PLAIN, 18);
	public static final Font PLAIN = new Font("Segoe UI", Font.PLAIN, 16);
	
	private CalcLabel calcLabel;
	
	public CalcPanel(Calculator calc) {
		super(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		calcLabel = new CalcLabel(calc);
		
		add(calcLabel);
		add(new CalcButtons(calc, calcLabel));
	}
	
	public void setText(String text) {
		calcLabel.setText(text);
	}
	
}
