import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

public class CalcPanel extends JPanel {
	public static final Color GRAY_240 = new Color(240, 240, 240);
	public static final String FONT_BOLD = "Segoe UI Semibold";
	public static final String FONT_PLAIN = "Segoe UI";
	public static final Font BOLD = new Font(FONT_BOLD, Font.PLAIN, 18);
	public static final Font LARGE = new Font(FONT_PLAIN, Font.PLAIN, 28);
	public static final Font PLAIN = new Font(FONT_PLAIN, Font.PLAIN, 16);
	
	public CalcPanel() {
		super(new BorderLayout());
		
		add(new CalcLabel(), BorderLayout.NORTH);
		add(new CalcButtons(), BorderLayout.CENTER);
	}
	
}
