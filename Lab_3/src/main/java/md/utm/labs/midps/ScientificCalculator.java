package md.utm.labs.midps;

import java.util.HashMap;
import java.util.Map;

public class ScientificCalculator {

	public enum AngleMode {
		ANGLES_IN_DEGREES, ANGLES_IN_RADIANS
	}

	private Map<String, BinaryOperation> binaryOperators = new HashMap<>();
	private double previousResult;
	private Double numberStorage;
	private AngleMode angleMode = AngleMode.ANGLES_IN_RADIANS;

	protected ScientificCalculator() {
		binaryOperators.put("+", new BinaryAddition());
		binaryOperators.put("-", new BinarySubtraction());
		binaryOperators.put("*", new BinaryMultiplication());
		binaryOperators.put("/", new BinaryDivision());
		binaryOperators.put("^", new NthPower());
	}

	public static ScientificCalculator newInstance() {
		return new ScientificCalculator();
	}

	public double getPreviousResult() {
		return previousResult;
	}

	public void setPreviousResult(double previousResult) {
		this.previousResult = previousResult;
	}

	public double evaluate(String expression) {
		String[] tokens = expression.split(" ");
		if (tokens.length < 3)
			return Double.NaN;
		double firstNumber = Double.parseDouble(tokens[0]);
		String operator = tokens[1];
		double secondNumber = Double.parseDouble(tokens[2]);
		BinaryOperation operation = binaryOperators.get(operator);
		setPreviousResult(operation.evaluate(firstNumber, secondNumber));
		return getPreviousResult();
	}

	private interface BinaryOperation {
		public double evaluate(double firstNumber, double secondNumber);
	}

	private static class BinaryAddition implements BinaryOperation {
		public double evaluate(double firstNumber, double secondNumber) {
			return firstNumber + secondNumber;
		}
	}

	private static class BinarySubtraction implements BinaryOperation {
		public double evaluate(double firstNumber, double secondNumber) {
			return firstNumber - secondNumber;
		}
	}

	private static class BinaryMultiplication implements BinaryOperation {
		public double evaluate(double firstNumber, double secondNumber) {
			return firstNumber * secondNumber;
		}
	}

	private static class BinaryDivision implements BinaryOperation {
		public double evaluate(double firstNumber, double secondNumber) throws ArithmeticException {
			return firstNumber / secondNumber;
		}
	}

	private static class NthPower implements BinaryOperation {
		public double evaluate(double base, double exponent) {
			return Math.pow(base, exponent);
		}
	}

	public double negate() {
		setPreviousResult(-getPreviousResult());
		return getPreviousResult();
	}

	public double sqrt(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(number);
		return sqrt();
	}

	public double sqrt() {
		setPreviousResult(Math.sqrt(getPreviousResult()));
		return getPreviousResult();
	}

	public double square(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(Math.pow(number, 2));
		return getPreviousResult();
	}

	public double cube(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(Math.pow(number, 3));
		return getPreviousResult();
	}

	public double factorial(String expression) {
		int number = (int) Double.parseDouble(expression);
		setPreviousResult(factorial(number));
		return getPreviousResult();
	}

	private double factorial(int n) {
		if (n <= 1)
			return 1;
		return n * factorial(n - 1);
	}

	public double cbrt(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(Math.cbrt(number));
		return getPreviousResult();
	}

	public double inverse(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(1.0 / number);
		return getPreviousResult();
	}

	public double exp(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(Math.exp(number));
		return getPreviousResult();
	}

	public void save(String expression) {
		double number = Double.parseDouble(expression);
		this.numberStorage = number;
	}

	public Double retrieve() {
		return numberStorage;
	}

	public void clear() {
		numberStorage = null;
	}

	public void addTo(String expression) {
		double number = Double.parseDouble(expression);
		if (numberStorage == null)
			numberStorage = number;
		else
			numberStorage += number;
	}

	public void subtractFrom(String expression) {
		double number = Double.parseDouble(expression);
		if (numberStorage == null)
			numberStorage = number;
		else
			numberStorage -= number;
	}

	public double log(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(Math.log10(number));
		return getPreviousResult();
	}

	public double ln(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(Math.log(number));
		return getPreviousResult();
	}

	public void setAngleMode(AngleMode angleMode) {
		this.angleMode = angleMode;
	}

	public double sine(String expression) {
		double angle = Double.parseDouble(expression);
		if (angleMode == AngleMode.ANGLES_IN_DEGREES)
			angle = angle * Math.PI / 180.0;
		setPreviousResult(Math.sin(angle));
		return getPreviousResult();
	}

	public double cosine(String expression) {
		double angle = Double.parseDouble(expression);
		if (angleMode == AngleMode.ANGLES_IN_DEGREES)
			angle = angle * Math.PI / 180.0;
		setPreviousResult(Math.cos(angle));
		return getPreviousResult();
	}

	public double tangent(String expression) {
		double angle = Double.parseDouble(expression);
		if (angleMode == AngleMode.ANGLES_IN_DEGREES)
			angle = angle * Math.PI / 180.0;
		setPreviousResult(Math.tan(angle));
		return getPreviousResult();
	}

	public boolean canEvaluate(String expression) {
		String[] tokens = expression.split(" ");
		return tokens != null && tokens.length == 3 && isNumber(tokens[0]) && isNumber(tokens[2]);
	}

	private boolean isNumber(String expression) {
		return expression.matches("\\d*?(\\.\\d*)?([eE][+-]?\\d+)?") && expression.length() > 0;
	}

	public double negate(String expression) {
		double number = Double.parseDouble(expression);
		setPreviousResult(number);
		return negate();
	}

	public double sineh(String expression) {
		double angle = Double.parseDouble(expression);
		if (angleMode == AngleMode.ANGLES_IN_DEGREES)
			angle = angle * Math.PI / 180.0;
		setPreviousResult(Math.sinh(angle));
		return getPreviousResult();
	}

	public double cosineh(String expression) {
		double angle = Double.parseDouble(expression);
		if (angleMode == AngleMode.ANGLES_IN_DEGREES)
			angle = angle * Math.PI / 180.0;
		setPreviousResult(Math.cosh(angle));
		return getPreviousResult();
	}

	public double tangenth(String expression) {
		double angle = Double.parseDouble(expression);
		if (angleMode == AngleMode.ANGLES_IN_DEGREES)
			angle = angle * Math.PI / 180.0;
		setPreviousResult(Math.tanh(angle));
		return getPreviousResult();
	}
}
