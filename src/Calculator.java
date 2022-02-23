public class Calculator {

	private CalcFrame calcFrame;
	private String text;
	
	public Calculator() {
		text = "0";
		
		calcFrame = new CalcFrame(this);
		calcFrame.setText(text);
	}
	
	public void setText(String text) {
		int decimalIndex = text.indexOf(".");
		if (decimalIndex == -1) {
			// no decimal
			calcFrame.setText(addComma(text));
		} else {
			// decimal
			calcFrame.setText(addComma(text.substring(0, decimalIndex))
					+ text.substring(decimalIndex, text.length()));
		}
	}
	
	private static String addComma(String n) {
		int digitCount = 0;
		String withComma = "";
		for (int i = n.length() - 1; i >= 0; i--) {
			digitCount++;
			if (n.charAt(i) != '-' && (digitCount == 4
					|| digitCount > 4 && digitCount % 3 == 1)) {
				withComma = n.charAt(i) + "," + withComma;
			} else {
				withComma = n.charAt(i) + withComma;
			}
		}
		return withComma;
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
	
	public void decimal() {
		if (!text.contains(".")) {
			text += ".";
			setText(text);
		}
	}
	
	public void clearEntry() {
		text = "0";
		setText(text);
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
