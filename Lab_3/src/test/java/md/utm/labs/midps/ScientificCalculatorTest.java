package md.utm.labs.midps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ScientificCalculatorTest {

	ScientificCalculator calculator = ScientificCalculator.newInstance();
	private double PRECISION = 0.0000001;

	@Test
	public void afterInstantiationThePreviousResultIsZero() {
		assertEquals(0.0, calculator.getPreviousResult(), 0.00001);
	}

	@Test
	public void canEvaluateBinaryAddition() {
		assertEquals(5, calculator.evaluate("2 + 3"), PRECISION);
	}

	@Test
	public void canEvaluateBinarySubtraction() {
		assertEquals(-1, calculator.evaluate("2 - 3"), PRECISION);
	}

	@Test
	public void canEvaluateBinaryMultiplication() {
		assertEquals(6, calculator.evaluate("2 * 3"), PRECISION);
	}

	@Test
	public void canEvaluateBinaryDivision() {
		assertEquals(2, calculator.evaluate("8 / 4"), PRECISION);
	}

	@Test
	public void afterEvaluatingTheExpressionPreviousResultIsSetProperty() {
		calculator.evaluate("2 * 3");
		assertEquals(6, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canNegateThePreviousResultNumber() {
		calculator.setPreviousResult(4);
		assertEquals(-4, calculator.negate(), PRECISION);
	}

	@Test
	public void canCalculateSquareRootOfANumber() {
		assertEquals(2, calculator.sqrt("4"), PRECISION);
		assertEquals(2, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void calCalculateTheSquareRootOfThePreviousResult() {
		calculator.setPreviousResult(9);
		assertEquals(3, calculator.sqrt(), PRECISION);
		assertEquals(3, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheSquareOfANumber() {
		assertEquals(9, calculator.square("3"), PRECISION);
		assertEquals(9, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheCubeOfANumber() {
		assertEquals(27, calculator.cube("3"), PRECISION);
		assertEquals(27, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheFactorialOfANumber() {
		assertEquals(120, calculator.factorial("5"), PRECISION);
		assertEquals(120, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheNthPowerOfANumber() {
		assertEquals(125, calculator.evaluate("5 ^ 3"), PRECISION);
		assertEquals(125, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheCubeRootOfANumber() {
		assertEquals(3, calculator.cbrt("27"), PRECISION);
		assertEquals(3, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheInverseOfANumber() {
		assertEquals(0.5, calculator.inverse("2"), PRECISION);
		assertEquals(0.5, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheExponentialOfANumber() {
		assertEquals(2.718281828459045, calculator.exp("1"), PRECISION);
		assertEquals(2.718281828459045, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canSaveAndRetrieveANumberInTheStorage() {
		calculator.save("29");
		assertEquals(29, calculator.retrieve(), PRECISION);
	}

	@Test
	public void canClearTheNumberStorage() {
		calculator.clear();
		assertNull(calculator.retrieve());
	}

	@Test
	public void canAddANumberToTheNumberFromTheStorage() {
		calculator.addTo("3");
		assertEquals(3, calculator.retrieve(), PRECISION);
	}

	@Test
	public void canSubtractANumberFromTheStorage() {
		calculator.subtractFrom("-3");
		assertEquals(-3, calculator.retrieve(), PRECISION);
	}

	@Test
	public void canCalculateTheBaseTenLogOfANumber() {
		assertEquals(2, calculator.log("100"), PRECISION);
		assertEquals(2, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canCalculateTheNaturalLogOfANumber() {
		assertEquals(1, calculator.ln("2.718281828459045"), PRECISION);
		assertEquals(1, calculator.getPreviousResult(), PRECISION);
	}

	@Test
	public void canEvaluateSineInDegrees() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_DEGREES);
		assertEquals(1, calculator.sine("90"), PRECISION);
		assertEquals(0, calculator.sine("0"), PRECISION);
	}

	@Test
	public void canEvaluateSineInRadians() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_RADIANS);
		assertEquals(1, calculator.sine("1.570796"), PRECISION);
		assertEquals(0, calculator.sine("3.1415926535"), PRECISION);
	}

	@Test
	public void canEvaluateCosineInDegrees() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_DEGREES);
		assertEquals(0, calculator.cosine("90"), PRECISION);
		assertEquals(1, calculator.cosine("0"), PRECISION);
	}

	@Test
	public void canEvaluateCosineInRadians() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_RADIANS);
		assertEquals(0, calculator.cosine("1.57079632679"), PRECISION);
		assertEquals(-1, calculator.cosine("3.1415926535"), PRECISION);
	}

	@Test
	public void canEvaluateTangetInDegrees() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_DEGREES);
		assertEquals(0, calculator.tangent("0"), PRECISION);
		assertEquals(1, calculator.tangent("45"), PRECISION);
	}

	@Test
	public void canEvaluateTangentInRadians() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_RADIANS);
		assertEquals(0, calculator.tangent("6.2831853071795"), PRECISION);
		assertEquals(1, calculator.tangent("0.7853981633974"), PRECISION);
	}
	
	@Test
	public void canEvaluateHyberbolicSineInDegrees() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_DEGREES);
		assertEquals(0, calculator.sineh("0"), PRECISION);
		assertEquals(1.17520119, calculator.sineh("57.295779513"), PRECISION);
	}

	@Test
	public void canEvaluateHyberbolicSineInRadians() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_RADIANS);
		assertEquals(0, calculator.sineh("0"), PRECISION);
		assertEquals(1.17520119, calculator.sineh("1"), PRECISION);
	}
	
	@Test
	public void canEvaluateHyberbolicCosineInDegrees() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_DEGREES);
		assertEquals(1, calculator.cosineh("0"), PRECISION);
		assertEquals(1.5430806348, calculator.cosineh("57.295779513"), PRECISION);
	}

	@Test
	public void canEvaluateHyberbolicCosineInRadians() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_RADIANS);
		assertEquals(1, calculator.cosineh("0"), PRECISION);
		assertEquals(1.5430806348, calculator.cosineh("1"), PRECISION);
	}
	
	@Test
	public void canEvaluateHyberbolicTangentInDegrees() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_DEGREES);
		assertEquals(0.7615941559, calculator.tangenth("57.295779513"), PRECISION);
	}

	@Test
	public void canEvaluateHyberbolicTangentInRadians() {
		calculator.setAngleMode(ScientificCalculator.AngleMode.ANGLES_IN_RADIANS);
		assertEquals(0.7615941559, calculator.tangenth("1"), PRECISION);
	}

	@Test
	public void canDetermineIfAnExpressionIsEvaluable() {
		assertTrue(calculator.canEvaluate("2 + 2"));
		assertFalse(calculator.canEvaluate("2 + "));
	}

	@Test
	public void canNegateMultipleTimes() {
		assertEquals(-2, calculator.negate("2"), PRECISION);
		assertEquals(2, calculator.negate("-2"), PRECISION);
		assertEquals(-2, calculator.negate(), PRECISION);
		assertEquals(2, calculator.negate(), PRECISION);
	}
}
