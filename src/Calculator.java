public class Calculator {

	private CalcFrame calcFrame;
	private String text;
	
	public Calculator() {
		text = "0";
		
		calcFrame = new CalcFrame(this);
		calcFrame.setText(text);
	}
	
	public void setText(String text) {
		String[] split = text.split(".");
		
		if (split.length == 0) {
			// no decimal
			int digitCount = 0;
			String textWithComma = "";
			for (int i = text.length() - 1; i >= 0; i--) {
				digitCount++;
				if (text.charAt(i) != '-' && (digitCount == 4
						|| digitCount > 4 && digitCount % 3 == 1)) {
					textWithComma = text.charAt(i) + "," + textWithComma;
				} else {
					textWithComma = text.charAt(i) + textWithComma;
				}
			}
			calcFrame.setText(textWithComma);
		} else {
			// decimal
		}
	}

	public void number(String n) {
		if (text.length() < 16) {
			if (text.equals("0")) {
				text = n;
			} else {
				text += n;
			}
			setText(text);
		}
	}
	
	public void negate() {
		if (!text.equals("0")) {
			if (text.charAt(0) == '-') {
				text = text.substring(1, text.length());
			} else {
				text = "-" + text;
			}
			setText(text);
		}
	}
	
	public void clear() {
		text = "0";
		setText(text);
	}
	
	public void back() {
		if (text.length() == 1
				|| (text.charAt(0) == '-' && text.length() == 2)) {
			text = "0";
		} else {
			text = text.substring(0, text.length() - 1);
		}
		setText(text);
	}
	
	public static void main(String[] args) {
		new Calculator();
	}

}
