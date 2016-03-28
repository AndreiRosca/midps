package md.utm.labs.midps;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class Controller {

	@FXML private TextArea outputTextArea;
	@FXML private RadioButton angleInDegrees;
	@FXML private RadioButton dangleInRadians;
	
	private ScientificCalculator calculator = ScientificCalculator.newInstance();
	private Map<String, ArithmeticOperationTemplate> operations = new HashMap<>(50);

	public Controller() {
		operations.put("squareRootButton", new SquareRootOperation());
		operations.put("squareButton", new SquareOperation());
		operations.put("cubeButton", new CubeOperation());
		operations.put("factorialButton", new FactorialOperation());
		operations.put("cubeRootButton", new CubeRootOperation());
		operations.put("inverseNumberButton", new InverseNumberOperation());
		operations.put("expButton", new ExponentiateNumberOperation());
		operations.put("negateButton", new NegateNumberOperation());
		operations.put("piButton", new DisplayPiNumberOperation());
		operations.put("baseTenLogButton", new BaseTenLogOperation());
		operations.put("naturalLogButton", new NaturalLogOperation());
		operations.put("tenToTheXButton", new BaseTenExponentiationOperation());
		operations.put("nthPowerButton", new NthPowerOperation());
		operations.put("backspaceButton", new BackspaceOperation());
		operations.put("equalsButton", new EqualsOperation());
		operations.put("plusButton", new PlusOperation());
		operations.put("decimalPointButton", new DicimalPointOperation());
		operations.put("minusButton", new MinusOperation());
		operations.put("multiplyButton", new MultiplyOperation());
		operations.put("divisionButton", new DivisionOperation());
		operations.put("clearButton", new CleanOperation());
		operations.put("sineButton", new SineOperation());
		operations.put("cosButton", new CosineOperation());
		operations.put("tanButton", new TangentOperation());
		operations.put("sinhButton", new HyperbolicSineOperation());
		operations.put("coshButton", new HyperbolicCosineOperation());
		operations.put("tanhButton", new HyperbolicTangentOperation());
		operations.put("quitFileMenuItem", new ExitOperation());
		addNumberStorageHandlers();
		addDigitHandlers();
	}

	private void addNumberStorageHandlers() {
		operations.put("storeInNumberStorageButton", new StoreInNumberStorageOperation());
		operations.put("retrieveFromNumberStorageButton", new RetrieveFromNumberStorageStorageOperation());
		operations.put("clearNumberStorageButton", new ClearNumberStorageOperation());
		operations.put("addToNumberStorageButton", new AddToNumberStorageOperation());
		operations.put("subtractFromNumberStorageButton", new SubtractFromNumberStorageOperation());
	}

	private void addDigitHandlers() {
		operations.put("zeroButton", new DigitButtonOperation("0"));
		operations.put("oneButton", new DigitButtonOperation("1"));
		operations.put("twoButton", new DigitButtonOperation("2"));
		operations.put("threeButton", new DigitButtonOperation("3"));
		operations.put("fourButton", new DigitButtonOperation("4"));
		operations.put("fiveButton", new DigitButtonOperation("5"));
		operations.put("sixButton", new DigitButtonOperation("6"));
		operations.put("sevenButton", new DigitButtonOperation("7"));
		operations.put("eightButton", new DigitButtonOperation("8"));
		operations.put("nineButton", new DigitButtonOperation("9"));
	}

	public void controlPressed(ActionEvent event) {
		String controlId = ((Node) event.getSource()).getId();
		ArithmeticOperationTemplate operation = operations.get(controlId);
		if (operation != null)
			operation.execute();
		else
			throw new RuntimeException("NPE in controlPressed");
	}

	private abstract class ArithmeticOperationTemplate {
		protected String expression = "";

		public final void execute() {
			if (operationNeedsInput())
				expression = outputTextArea.getText();
			if (operationNeedsDigits() && !containsDigits(expression))
				return;
			if (shouldEvaluateIfPossible() && calculator.canEvaluate(expression)) {
				operations.get("equalsButton").execute();
				appendToOutputArea(" " + getOperationSymbol() + " ");
				expression = outputTextArea.getText();
			}
			if (angleInDegrees != null && angleInDegrees.isSelected())
				calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_DEGREES);
			else
				calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_RADIANS);
			calculate();
			if (operationNeedsOutput())
				outputResult();
		}

		protected boolean shouldEvaluateIfPossible() {
			return true;
		}

		protected String getOperationSymbol() {
			return "";
		}

		protected boolean operationNeedsInput() {
			return true;
		}

		protected boolean operationNeedsDigits() {
			return true;
		}

		protected boolean operationNeedsOutput() {
			return true;
		}

		protected abstract void calculate();

		private void outputResult() {
			outputTextArea.setText(Double.toString(calculator.getPreviousResult()));
		}
	}

	private class SquareRootOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.sqrt(expression);
		}
	}

	private class SquareOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.square(expression);
		}
	}

	private class CubeOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.cube(expression);
		}
	}

	private class FactorialOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.factorial(expression);
		}
	}

	private class CubeRootOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.cbrt(expression);
		}
	}

	private class InverseNumberOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.inverse(expression);
		}
	}

	private class ExponentiateNumberOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.exp(expression);
		}
	}

	private class StoreInNumberStorageOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.save(expression);
		}

		protected boolean operationNeedsOutput() {
			return false;
		}
	}

	private class DigitButtonOperation extends ArithmeticOperationTemplate {
		private String digit = "";

		public DigitButtonOperation(String digit) {
			this.digit = digit;
		}

		protected boolean operationNeedsOutput() {
			return false;
		}

		protected boolean operationNeedsDigits() {
			return false;
		}

		protected boolean operationNeedsInput() {
			return false;
		}

		public void calculate() {
			appendToOutputArea(digit);
		}
	}

	private class RetrieveFromNumberStorageStorageOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			Double numberStorage = calculator.retrieve();
			if (null != numberStorage)
				outputTextArea.setText(Double.toString(numberStorage));
		}

		protected boolean operationNeedsOutput() {
			return false;
		}

		protected boolean operationNeedsDigits() {
			return false;
		}

		protected boolean operationNeedsInput() {
			return false;
		}
	}

	private class NegateNumberOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.negate(expression);
		}
	}

	private class ClearNumberStorageOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.clear();
		}

		protected boolean operationNeedsOutput() {
			return false;
		}

		protected boolean operationNeedsDigits() {
			return false;
		}

		protected boolean operationNeedsInput() {
			return false;
		}
	}

	private class AddToNumberStorageOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.addTo(expression);
		}

		public boolean operationNeedsOutput() {
			return false;
		}
	}

	private class SubtractFromNumberStorageOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.subtractFrom(expression);
		}

		public boolean operationNeedsOutput() {
			return false;
		}
	}

	private class DisplayPiNumberOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.setPreviousResult(Math.PI);
			appendToOutputArea(calculator.getPreviousResult() + "");
		}

		protected boolean operationNeedsOutput() {
			return false;
		}

		protected boolean operationNeedsDigits() {
			return false;
		}

		protected boolean operationNeedsInput() {
			return false;
		}
	}

	private class BaseTenLogOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.log(expression);
		}
	}

	private class NaturalLogOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.ln(expression);
		}
	}

	private class BaseTenExponentiationOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.evaluate("10 ^ " + expression);
		}
	}

	private class NthPowerOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			if (expression.indexOf("^") < 0 && containsDigits(expression))
				appendToOutputArea(" ^ ");
		}

		protected String getOperationSymbol() {
			return "^";
		}

		protected boolean shouldEvaluateIfPossible() {
			return true;
		}

		protected boolean operationNeedsOutput() {
			return false;
		}
	}

	private class BackspaceOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			if (expression.length() > 0) {
				expression = expression.trim();
				expression = expression.substring(0, expression.length() - 1);
				outputTextArea.setText(expression);
			}
		}

		protected boolean operationNeedsOutput() {
			return false;
		}
	}

	private class EqualsOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			calculator.evaluate(expression);
		}
		
		protected boolean shouldEvaluateIfPossible() {
			return false;
		}
	}

	private class PlusOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			if (expression.indexOf("+") < 0 && containsDigits(expression))
				appendToOutputArea(" + ");
		}

		protected String getOperationSymbol() {
			return "+";
		}

		protected boolean shouldEvaluateIfPossible() {
			return true;
		}

		public boolean operationNeedsOutput() {
			return false;
		}
	}

	private class DicimalPointOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			String[] tokens = expression.split(" ");
			if (tokens[0] == null || (tokens.length < 2 && tokens[0].indexOf(".") < 0))
				appendToOutputArea(".");
			else if (tokens.length == 2 || (tokens.length == 3 && tokens[2].indexOf(".") < 0))
				appendToOutputArea(".");
		}

		public boolean operationNeedsOutput() {
			return false;
		}
		
		public boolean operationNeedsDigits() {
			return false;
		}
	}

	private class MinusOperation extends ArithmeticOperationTemplate {
		public void calculate() {
			String[] tokens = expression.split(" ");
			String lastToken = tokens[tokens.length - 1];
				if (tokens.length < 3 && lastToken.length() > 0 && containsDigits(lastToken))
					appendToOutputArea(" - ");
				else if (tokens.length == 1 && lastToken.indexOf("-") < 0)
					appendToOutputArea("-");
				else if (tokens.length == 2 && numberOfOccurences(lastToken, "-") <= 2)
					appendToOutputArea("-");
		}

		private int numberOfOccurences(String string, String token) {
			int count = 0;
			for (int i = 0; i < string.length(); ++i)
				if (string.substring(i).startsWith(token))
					++count;
			return count;
		}

		protected String getOperationSymbol() {
			return "-";
		}

		protected boolean shouldEvaluateIfPossible() {
			return true;
		}

		public boolean operationNeedsOutput() {
			return false;
		}
		
		public boolean operationNeedsDigits() {
			return false;
		}
	}

	private class MultiplyOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			if (expression.indexOf("*") < 0 && containsDigits(expression))
				appendToOutputArea(" * ");
		}

		protected String getOperationSymbol() {
			return "*";
		}

		protected boolean shouldEvaluateIfPossible() {
			return true;
		}

		protected boolean operationNeedsOutput() {
			return false;
		}
	}

	private class DivisionOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			if (expression.indexOf("/") < 0 && containsDigits(expression))
				appendToOutputArea(" / ");
		}

		protected String getOperationSymbol() {
			return "/";
		}

		protected boolean shouldEvaluateIfPossible() {
			return true;
		}

		protected boolean operationNeedsOutput() {
			return false;
		}
	}

	private class SineOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			calculator.sine(expression);
		}
	}

	private class CosineOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			calculator.cosine(expression);
		}
	}
	
	private class TangentOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			calculator.tangent(expression);
		}
	}
	
	private class HyperbolicSineOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			calculator.sineh(expression);
		}
	}
	
	private class HyperbolicCosineOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			calculator.cosineh(expression);
		}
	}
	
	private class HyperbolicTangentOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			calculator.tangenth(expression);
		}
	}

	private class CleanOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			outputTextArea.setText("");
		}

		protected boolean operationNeedsInput() {
			return false;
		}

		protected boolean operationNeedsOutput() {
			return false;
		}

		protected boolean operationNeedsDigits() {
			return false;
		}
	}

	private class ExitOperation extends ArithmeticOperationTemplate {
		protected void calculate() {
			System.exit(0);
		}

		protected boolean operationNeedsInput() {
			return false;
		}

		protected boolean operationNeedsOutput() {
			return false;
		}

		protected boolean operationNeedsDigits() {
			return false;
		}
	}

	private boolean containsDigits(String expression) {
		return expression.matches("([+-])?\\d*?(\\.\\d*)?([eE][+-]?\\d+)?.*") && expression.length() > 0 
				&& !isOperator(expression);
	}

	private boolean isOperator(String expression) {
		return expression.equals("+") || expression.equals("-") || expression.equals("*") || 
				expression.equals("/") || expression.equals("^");
	}

	private void appendToOutputArea(String appendedString) {
		outputTextArea.setText(outputTextArea.getText() + appendedString);
	}
}
