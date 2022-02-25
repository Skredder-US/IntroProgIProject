import javax.swing.SwingUtilities;

public class Calculator {
	
	public static final String DIV_ZERO = "Cannot divide by zero";
	public static final String OVERFLOW = "Overflow";
	
	enum Operation {
		ADD,
		SUBTRACT,
		MULTIPLY,
		DIVIDE,
		NONE
	}

	private CalcFrame calcFrame;
	private String entry;
	private String secondEntry;
	private String repeatEntry;
	private Operation op;
	private Operation repeatOp;
	private boolean prevIsNumber;
	
	public Calculator() {
		entry = "0";
		secondEntry = "0";
		op = Operation.NONE;
		
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
	
	private void setEntry(double value) {
		int intValue = (int) value;
		if (intValue == value) {
			entry = "" + intValue;
		} else {
			entry = "" + value;
		}
		setText(entry);
		secondEntry = "0";
	}
	
	private void setSecondEntry(double value) {
		int intValue = (int) value;
		if (intValue == value) {
			secondEntry = "" + intValue;
		} else {
			secondEntry = "" + value;
		}
		setText(secondEntry);
	}
	
	public void number(String n) {
		prevIsNumber = true;
		if (op != Operation.NONE) {
			if (secondEntry.equals("0")) {
				secondEntry = n;
			} else {
				secondEntry += n;
			}
			setText(secondEntry);
		} else if (entry.length() < 16) {
			if (entry.equals("0")) {
				entry = n;
			} else {
				entry += n;
			}
			setText(entry);
		}
	}
	
	public void add() {
		if (repeatOp != Operation.NONE) {
			equals();
		}
		op = Operation.ADD;
		repeatOp = Operation.ADD;
		repeatEntry = entry;
		prevIsNumber = false;
	}
	
	public void equals() {
		if (op == Operation.ADD) {
			if (repeatOp == Operation.NONE || !prevIsNumber) {
				setEntry(Double.valueOf(entry) + Double.valueOf(repeatEntry));
			} else {
				repeatEntry = secondEntry;
				setEntry(Double.valueOf(entry) + Double.valueOf(secondEntry));
			}
		}
		repeatOp = Operation.NONE;
	}
	
	public void clear() {
		entry = "0";
		secondEntry = "0";
		op = Operation.NONE;
		setText(entry);
	}
	
	public void clearEntry() {
		if (op != Operation.NONE) {
			secondEntry = "0";
			setText(secondEntry);
		} else {
			entry = "0";
			setText(entry);
		}
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
	
	public void decimal() {
		if (op != Operation.NONE) {
			if (!secondEntry.contains(".")) {
				secondEntry += ".";
				setText(secondEntry);
			}
		} else {
			if (!entry.contains(".")) {
				entry += ".";
				setText(entry);
			}
		}
	}
	
	public void negate() {
		if (op != Operation.NONE) {
			if (!secondEntry.equals("0")) {
				if (secondEntry.charAt(0) == '-') {
					secondEntry = secondEntry.substring(1, entry.length());
				} else {
					secondEntry = "-" + secondEntry;
				}
				setText(secondEntry);
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
	
	public void oneOver() {
		if (secondEntry == "0") {
			if (!entry.equals("0")) {
				setEntry(1 / Double.valueOf(entry));
			} else {
				calcFrame.setText(DIV_ZERO);
			}
		} else {
			if (!secondEntry.equals("0")) {
				setEntry(1 / Double.valueOf(secondEntry));
			} else {
				calcFrame.setText(DIV_ZERO);
			}
		}

	}
	
	public void squared() {
		if (secondEntry == "0") {
			double value = Double.valueOf(entry);
			value *= value;
			if (value != 0) {
				setEntry(value);
			} else {
				calcFrame.setText(OVERFLOW);
			}
		} else {
			double value = Double.valueOf(secondEntry);
			value *= value;
			if (value != 0) {
				setSecondEntry(value);
			} else {
				calcFrame.setText(OVERFLOW);
			}
		}
	}
	
	public void sqrt() {
		if (secondEntry == "0") {
			setEntry(Math.sqrt(Double.valueOf(secondEntry)));
		} else {
			setSecondEntry(Math.sqrt(Double.valueOf(secondEntry)));
		}
	}

	public void percent() {
		if (secondEntry != "0") {
			setSecondEntry(
					Double.valueOf(entry) * Double.valueOf(secondEntry) / 100);
		} else {
			entry = "0";
		}
		
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator();
            }
        });
    }
}
