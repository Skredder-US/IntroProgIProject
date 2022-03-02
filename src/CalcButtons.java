import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class CalcButtons extends JPanel {

	public static final int NUM_BUTTONS = 24;
	
	public CalcButtons(Calculator calc, CalcLabel calcLabel) {
		super(new GridLayout(0, 4, 2, 2));
		
		setPreferredSize(new Dimension(502, 384));
		setBackground(CalcPanel.BACKGROUND);
		setBorder(new LineBorder(CalcPanel.BACKGROUND, 4, false));
		
		for (int i = 0; i < NUM_BUTTONS; i++) {
			add(new CalcButton(calc, i));
		}
	}
	
}
