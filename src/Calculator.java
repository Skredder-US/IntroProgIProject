import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.SwingUtilities;

public class Calculator {
	
	public static final String DIV_ZERO = "Cannot divide by zero";
	public static final String OVERFLOW = "Overflow";
	
	private static final MathContext PRECISION = new MathContext(16);
	private static final BigDecimal ONE = new BigDecimal(1);
	private static final BigDecimal ZERO = new BigDecimal(0);
	
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
	private String prevEntry;
	private Operation op;
	private boolean isNext;
	private boolean isResult;
	
	public Calculator() {
		entry = "0";
		prevEntry = "0";
		op = null;
		isNext = false;
		isResult = false;
		
		calcFrame = new CalcFrame(this);
		calcFrame.setText(entry);
	}
	
	private void setText(String text) {
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
					|| (digitCount > 4 && digitCount % 3 == 1))) {
				withComma = n.charAt(i) + "," + withComma;
			} else {
				withComma = n.charAt(i) + withComma;
			}
		}
		return withComma;
	} 
	
	public void number(String num) {
		if (isNext) {
			prevEntry = entry;
			setEntry(num);
			isNext = false;
		} else if (isResult) {
			prevEntry = "0";
			op = null;
			setEntry(num);
			isResult = false;
		} else if (entry.length() < 16) {
			if (entry.equals("0")) {
				setEntry(num);
			} else {
				setEntry(entry + num);
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
		if (this.op == op) {
			equals();
			isResult = false;
		}
		this.op = op;
		isNext = true;
	}
	
	public void equals() {
		setEntry(op.apply(new BigDecimal(entry), new BigDecimal(prevEntry)));
		isResult = true;
	}
	
	private void setEntry(String newEntry) {
		entry = newEntry;
		setText(entry);
	}
	
	private void setEntry(BigDecimal bd) {
		setEntry("" + bd);
	}
	
	public void clear() {
		entry = "0";
		op = null;
		
		setText(entry);
	}
	
	public void clearEntry() {
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
	
	public void decimal() {
		if (!entry.contains(".")) {
			entry += ".";
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
	
	public void oneOver() {
		if (!entry.equals("0")) {
			setEntry("" + ONE.divide(new BigDecimal(entry), PRECISION));
		} else {
			calcFrame.setText(DIV_ZERO);
		}

	}
	
	public void squared() {
		BigDecimal value = new BigDecimal(entry);
		value = value.multiply(value, PRECISION);
		if (!value.equals(ZERO)) {
			setEntry("" + value);
		} else {
			calcFrame.setText(OVERFLOW);
		}
	}
	
	public void sqrt() {
			setEntry("" + (new BigDecimal(entry)).sqrt(PRECISION));
	}

	public void percent() {
		setEntry("" + (new BigDecimal(entry)
				.multiply(new BigDecimal(prevEntry)))
				.divide(new BigDecimal(100)));
		
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
