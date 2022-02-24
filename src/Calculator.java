public class Calculator {
	
	public static final String DIV_ZERO = "Cannot divide by zero";
	public static final String OVERFLOW = "Overflow";
	
	private String entry;
	private CalcFrame calcFrame;
	
	public Calculator() {
		entry = "0";
		
		calcFrame = new CalcFrame(this);
		calcFrame.setText(entry);
	}
	
	public void setText(String text) {
		if (!text.equals("Infinity")) {
			int decimalIndex = text.indexOf(".");
			if (decimalIndex == -1) {
				// no decimal
				calcFrame.setText(addComma(text));
			} else {
				// decimal
				calcFrame.setText(addComma(text.substring(0, decimalIndex))
						+ text.substring(decimalIndex, text.length()));
			}
		} else {
			calcFrame.setText(OVERFLOW);
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
		if (entry.length() < 16) {
			if (entry.equals("0")) {
				entry = n;
			} else {
				entry += n;
			}
			setText(entry);
		}
	}
	
	public void negate() {
		if (!entry.equals("0")) {
			if (entry.charAt(0) == '-') {
				entry = entry.substring(1, entry.length());
			} else {
				entry = "-" + entry;
			}
			setText(entry);
		}
	}
	
	public void decimal() {
		if (!entry.contains(".")) {
			entry += ".";
			setText(entry);
		}
	}
	
	public void clearEntry() {
		entry = "0";
		setText(entry);
	}
	
	public void clear() {
		entry = "0";
		setText(entry);
	}
	
	public void back() {
		if (entry.length() == 1
				|| (entry.charAt(0) == '-' && entry.length() == 2)) {
			entry = "0";
		} else {
			entry = entry.substring(0, entry.length() - 1);
		}
		setText(entry);
	}
	
	public void oneOver() {
		if (!entry.equals("0")) {
			double value = Double.valueOf(entry);
			value = 1 / value;
			int intValue = (int) value;
			if (intValue == value) {
				entry = "" + intValue;
			} else {
				entry = "" + value;
			}
			setText(entry);
		} else {
			calcFrame.setText(DIV_ZERO);
		}
	}
	
	public void squared() {
		double value = Double.valueOf(entry);
		value *= value;
		if (value != 0) {
			int intValue = (int) value;
			if (intValue == value) {
				entry = "" + intValue;
			} else {
				entry = "" + value;
			}
			setText(entry);
		} else {
			calcFrame.setText(OVERFLOW);
		}

	}
	
	public static void main(String[] args) {
		new Calculator();
	}
}
