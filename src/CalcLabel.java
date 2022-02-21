import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class CalcLabel extends JLabel {
	
	public CalcLabel() {
		super("0", SwingConstants.RIGHT);

		setPreferredSize(new Dimension(450, 90));
		setBackground(CalcPanel.GRAY_240);
		setOpaque(true); // necessary to set Color
		setBorder(new EmptyBorder(0, 0, 0, 10));
		setFont(new Font(CalcPanel.FONT_BOLD, Font.PLAIN, 40));
	}
	
}
