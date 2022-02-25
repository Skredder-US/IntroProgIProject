import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CalcButtons extends JPanel {

	public static final int COLUMNS = 4;
	public static final int PADDING = 2; // px
	public static final int BORDER_THICKNESS = 4; // px
	public static final int NUM_BUTTONS = 24;
	
	public CalcButtons(Calculator calc, CalcLabel calcLabel) {
		super(new GridLayout(0, COLUMNS, PADDING, PADDING)); // 0 is auto
		
		setBackground(CalcPanel.BACKGROUND);
		setBorder(new LineBorder(CalcPanel.BACKGROUND, BORDER_THICKNESS, false));
		
		for (int i = 0; i < NUM_BUTTONS; i++) {
			add(new CalcButton(calc, i));
		}
	}
	
}
