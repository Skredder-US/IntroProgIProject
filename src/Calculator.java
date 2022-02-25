import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.SwingUtilities;

public class Calculator {
	
	public static final String DIV_ZERO = "Cannot divide by zero";
	public static final String OVERFLOW = "Overflow";
	
	private static final MathContext PRECISION = new MathContext(16);
	private static final BigDecimal ONE = new BigDecimal("1");
	
	enum Operation {
		ADD {
			@Override
			public BigDecimal apply(BigDecimal a, BigDecimal b) {
				return a.add(b, PRECISION);
			}
		}, 
		SUBTRACT {
			@Override
			public BigDecimal apply(BigDecimal a, BigDecimal b) {
				return a.subtract(b, PRECISION);
			}
		}, 
		MULTIPLY {
			@Override
			public BigDecimal apply(BigDecimal a, BigDecimal b) {
				return a.multiply(b, PRECISION);
			}
		}, 
		DIVIDE {
			@Override
			public BigDecimal apply(BigDecimal a, BigDecimal b) {
				return a.divide(b, PRECISION);
			}
		};

		public abstract BigDecimal apply(BigDecimal a, BigDecimal b);
	}

	private CalcFrame calcFrame;
	private String entry;
	private String secondEntry;
	private String repeatEntry;
	private Operation op;
	private Operation repeatOp;
	private boolean prevIsNumber;
	
	public Calculator() {
		calcFrame = new CalcFrame(this);
		entry = "0";
		secondEntry = "0";
		repeatEntry = "0";
		op = null;
		repeatOp = null;
		prevIsNumber = false;
		
		calcFrame.setText(entry);
	}
	
	private void setText(String text) {
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
					|| (digitCount > 4 && digitCount % 3 == 1))) {
				withComma = n.charAt(i) + "," + withComma;
			} else {
				withComma = n.charAt(i) + withComma;
			}
		}
		return withComma;
	} 
	
	public void number(String n) {
		prevIsNumber = true;
		
		if (op == null) {
			if (entry.length() < 16) {
				if (entry.equals("0")) {
					entry = n;
				} else {
					entry += n;
				}
				setText(entry);
			}
		} else  {
			if (secondEntry.length() < 16) {
				if (secondEntry.equals("0")) {
					secondEntry = n;
				} else {
					secondEntry += n;
				}
				setText(secondEntry);
			}
		}
	}
	
	public void add() {
		operation(Operation.ADD);
	}
	
	public void subtract() {
		operation(Operation.SUBTRACT);
	}
	
	public void multiply() {
		operation(Operation.MULTIPLY);
	}
	
	public void division() {
		operation(Operation.DIVIDE);
	}
	
	private void operation(Operation op) {
		if (repeatOp != null) {
			equals();
		}
		this.op = op;
		repeatOp = op;
		repeatEntry = entry;
		prevIsNumber = false;
	}
	
	public void equals() {
		if (repeatOp == null || !prevIsNumber) {
			setEntry(op.apply(
					new BigDecimal(entry), new BigDecimal(repeatEntry)));
		} else {
			repeatEntry = secondEntry;
			setEntry(op.apply(
					new BigDecimal(entry), new BigDecimal(secondEntry)));
		}
		repeatOp = null;
	}
	
	private void setEntry(BigDecimal num) {
		entry = "" + num;
		setText(entry);
		secondEntry = "0";
	}
	
	private void setSecondEntry(BigDecimal num) {
		secondEntry = "" + num;
		setText(secondEntry);
	}
	
	public void clear() {
		entry = "0";
		secondEntry = "0";
		repeatEntry = "0";
		op = null;
		repeatOp = null;
		prevIsNumber = false;
		
		setText(entry);
	}
	
	public void clearEntry() {
		if (op == null) {
			entry = "0";
			setText(entry);
		} else {
			secondEntry = "0";
			setText(secondEntry);
		}
	}
	
	public void back() {
		if (op == null) {
			if (entry.length() == 1
					|| (entry.charAt(0) == '-' && entry.length() == 2)) {
				entry = "0";
			} else {
				entry = entry.substring(0, entry.length() - 1);
			}
			setText(entry);
		} else {
			if (secondEntry.length() == 1 
					|| (secondEntry.charAt(0) == '-'
					&& secondEntry.length() == 2)) {
				secondEntry = "0";
			} else {
				secondEntry = 
						secondEntry.substring(0, secondEntry.length() - 1);
			}
			setText(secondEntry);
			
		}
	}
	
	public void decimal() {
		if (op == null) {
			if (!entry.contains(".")) {
				entry += ".";
				setText(entry);
			}
		} else {
			if (!secondEntry.contains(".")) {
				secondEntry += ".";
				setText(secondEntry);
			}
		}
	}
	
	public void negate() {
		if (op == null) {
			if (!entry.equals("0")) {
				if (entry.charAt(0) == '-') {
					entry = entry.substring(1, entry.length());
				} else {
					entry = "-" + entry;
				}
				setText(entry);
			}
		} else {
			if (!secondEntry.equals("0")) {
				if (secondEntry.charAt(0) == '-') {
					secondEntry = secondEntry.substring(1, entry.length());
				} else {
					secondEntry = "-" + secondEntry;
				}
				setText(secondEntry);
			}
		}
	}
	
	public void oneOver() {
		if (secondEntry == "0") {
			if (!entry.equals("0")) {
				setEntry((ONE.divide(new BigDecimal(entry))));
			} else {
				calcFrame.setText(DIV_ZERO);
			}
		} else {
			if (!secondEntry.equals("0")) {
				setEntry((new BigDecimal("1").divide(
						new BigDecimal(secondEntry))));
			} else {
				calcFrame.setText(DIV_ZERO);
			}
		}

	}
	
	public void squared() {
		if (secondEntry == "0") {
			BigDecimal value = new BigDecimal(entry);
			value.multiply(value);
			if (!value.equals(new BigDecimal(0))) {
				setEntry(value);
			} else {
				calcFrame.setText(OVERFLOW);
			}
		} else {
			BigDecimal value = new BigDecimal(secondEntry);
			value.multiply(value);
			if (!value.equals(new BigDecimal(0))) {
				setSecondEntry(value);
			} else {
				calcFrame.setText(OVERFLOW);
			}
		}
	}
	
	public void sqrt() {
		if (secondEntry == "0") {
			setEntry((new BigDecimal(entry)).sqrt(PRECISION));
		} else {
			setSecondEntry( 
					(new BigDecimal(secondEntry)).sqrt(PRECISION));
		}
	}

	public void percent() {
		if (secondEntry != "0") {
			setSecondEntry((new BigDecimal(entry).multiply(
					new BigDecimal(secondEntry))).divide(new BigDecimal(100)));
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
