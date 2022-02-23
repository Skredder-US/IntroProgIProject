import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CalcButtons extends JPanel {

	public static final int BORDER_THICKNESS = 5; // px
	public static final int COLUMNS = 4;
	public static final int PADDING = 2; // px
	
	public CalcButtons(Calculator calc, CalcLabel calcLabel) {
		super(new GridLayout(0, COLUMNS, PADDING, PADDING)); // 0 is auto
		
		setBackground(CalcPanel.GRAY_240);
		setBorder(new LineBorder(CalcPanel.GRAY_240, BORDER_THICKNESS, false));
		
		for (int i = 0; i < 24; i++) {
			add(new CalcButton(calc, i));
		}
	}
	
}
