import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.SwingUtilities;

public class Calculator {
	private static final MathContext PRECISION = new MathContext(16);
	private static final BigDecimal ONE_HUNDRED
			= new BigDecimal(100, PRECISION);
	
	enum Operation {
		ADD {
			@Override
			public BigDecimal apply(BigDecimal addend, BigDecimal augend) {
				return addend.add(augend, PRECISION).stripTrailingZeros();
			}
		}, 
		SUBTRACT {
			@Override
			public BigDecimal apply(BigDecimal minuend, BigDecimal subtrahend) {
				return minuend.subtract(subtrahend, PRECISION)
						.stripTrailingZeros();
			}
		}, 
		MULTIPLY {
			@Override
			public BigDecimal apply(
					BigDecimal multiplier, BigDecimal multiplicand) {
				return multiplier.multiply(multiplicand, PRECISION)
						.stripTrailingZeros();
			}
		}, 
		DIVIDE {
			@Override
			public BigDecimal apply(BigDecimal dividend, BigDecimal divisor) {
				return dividend.divide(divisor, PRECISION).stripTrailingZeros();
			}
		};

		public abstract BigDecimal apply(BigDecimal a, BigDecimal b);
	}

	private CalcFrame calcFrame;
	private String entry;
	private String prevEntry;
	private String repeatEntry;
	private Operation op;
	private boolean isNext;
	private boolean isResult;
	
	public Calculator() {
		calcFrame = new CalcFrame(this);
		clear();
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
		String withCommas = "";
		for (int i = n.length() - 1; i >= 0; i--) {
			digitCount++;
			if (n.charAt(i) != '-' && (digitCount == 4
					|| (digitCount > 4 && digitCount % 3 == 1))) {
				withCommas = n.charAt(i) + "," + withCommas;
			} else {
				withCommas = n.charAt(i) + withCommas;
			}
		}
		return withCommas;
	} 
	
	public void number(String num) {
		repeatEntry = "0";
		if (isNext) {
			prevEntry = entry;
			setEntry(num);
			isNext = false;
		} else if (isResult) {
			prevEntry = "0";
			op = null;
			setEntry(num);
		} else if (entry.length() < 16) {
			if (entry.equals("0")) {
				setEntry(num);
			} else {
				setEntry(entry + num);
			}
		}
		isResult = false;
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
		if (isResult) {
			repeatEntry = entry;
		}
		
		if (this.op == op && !isResult) {
			equals();
			isResult = false;
		}
		
		this.op = op;
		isNext = true;
	}
	
	public void equals() {
		try {
			if (op != null) {
				if (isResult) {
					setEntry(op.apply(new BigDecimal(entry, PRECISION),
							new BigDecimal(repeatEntry, PRECISION)));
				} else {
					repeatEntry = entry;
					setEntry(op.apply(new BigDecimal(prevEntry, PRECISION),
							new BigDecimal(entry, PRECISION)));
				}
				isResult = true;
			}
		} catch (ArithmeticException ae) {
			calcFrame.setText(ae.getMessage());
		}
		
	}
	
	private void setEntry(String newEntry) {
		entry = newEntry;
		setText(entry);
	}
	
	private void setEntry(BigDecimal bd) {
		String newEntry = bd.toPlainString();
		if (newEntry.length() > 16) {
			newEntry = bd.toString();
		}
		setEntry(newEntry);
	}
	
	public void clear() {
		entry = "0";
		prevEntry = "0";
		repeatEntry = "0";
		op = null;
		isNext = false;
		isResult = false;
		
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
		try {
			setEntry(BigDecimal.ONE.divide(
					new BigDecimal(entry, PRECISION), PRECISION)
					.stripTrailingZeros());
		} catch (ArithmeticException ae) {
			calcFrame.setText(ae.getMessage());
		}
	}
	
	public void squared() {
		BigDecimal value = new BigDecimal(entry, PRECISION);
		try {
			value = value.multiply(value, PRECISION).stripTrailingZeros();
			setEntry(value);
		} catch (ArithmeticException ae) {
			calcFrame.setText(ae.getMessage());
		}
	}
	
	public void sqrt() {
		try {
			setEntry((new BigDecimal(entry, PRECISION)).sqrt(PRECISION)
					.stripTrailingZeros());
		} catch (ArithmeticException ae) {
			calcFrame.setText(ae.getMessage());
		}
	}

	public void percent() {
		try {
			setEntry((new BigDecimal(entry, PRECISION)
					.multiply(new BigDecimal(prevEntry, PRECISION)))
					.divide(ONE_HUNDRED).stripTrailingZeros());
		} catch (ArithmeticException ae) {
			calcFrame.setText(ae.getMessage());
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
