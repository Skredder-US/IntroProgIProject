public class Calculator {
	
	public static final String DIV_ZERO = "Cannot divide by zero";
	public static final String OVERFLOW = "Overflow";
	
	enum Operation {
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE
	}
	
	private String entry;
	private String nextEntry;
	private boolean isNextEntry;
	private Operation op;
	private CalcFrame calcFrame;
	
	public Calculator() {
		entry = "0";
		nextEntry = "0";
		isNextEntry = false;
		op = null;
		
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
		if (isNextEntry) {
			if (nextEntry.equals("0")) {
				nextEntry = n;
			} else {
				nextEntry += n;
			}
			setText(nextEntry);
		} else if (entry.length() < 16) {
			if (entry.equals("0")) {
				entry = n;
			} else {
				entry += n;
			}
			setText(entry);
		}
	}
	
	public void negate() {
		if (isNextEntry) {
			if (!nextEntry.equals("0")) {
				if (nextEntry.charAt(0) == '-') {
					nextEntry = nextEntry.substring(1, entry.length());
				} else {
					nextEntry = "-" + nextEntry;
				}
				setText(nextEntry);
			}
		} else {
			if (!entry.equals("0")) {
				if (entry.charAt(0) == '-') {
					entry = entry.substring(1, entry.length());
				} else {
					entry = "-" + entry;
				}
				setText(entry);
			}
		}
	}
	
	public void decimal() {
		if (isNextEntry) {
			if (!nextEntry.contains(".")) {
				nextEntry += ".";
				setText(nextEntry);
			}
		} else {
			if (!entry.contains(".")) {
				entry += ".";
				setText(entry);
			}
		}
	}
	
	public void clearEntry() {
		if (isNextEntry) {
			nextEntry = "0";
			setText(nextEntry);
		} else {
			entry = "0";
			setText(entry);
		}
	}
	
	public void clear() {
		entry = "0";
		nextEntry = "0";
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
	
	public void add() {
		isNextEntry = true;
		op = Operation.ADD;
	}
	
	public void equals() {
		isNextEntry = false;
		if (op == Operation.ADD) {
			double value = Double.valueOf(nextEntry) + Double.valueOf(entry);
			setEntry(value);
			setText(entry);
		}
	}
	
	public void oneOver() {
		if (!entry.equals("0")) {
			double value = Double.valueOf(entry);
			value = 1 / value;
			setEntry(value);
			setText(entry);
		} else {
			calcFrame.setText(DIV_ZERO);
		}
	}
	
	public void squared() {
		double value = Double.valueOf(entry);
		value *= value;
		if (value != 0) {
			setEntry(value);
			setText(entry);
		} else {
			calcFrame.setText(OVERFLOW);
		}
	}
	
	public void sqrt() {
		double value = Double.valueOf(entry);
		value = Math.sqrt(value);
		setEntry(value);
		setText(entry);
	}
	
	private void setEntry(double value) {
		int intValue = (int) value;
		if (intValue == value) {
			entry = "" + intValue;
		} else {
			entry = "" + value;
		}
	}
	
	public static void main(String[] args) {
		new Calculator();
	}
}
