package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {
    private final ScholarshipCalculator calculator = new ScholarshipCalculator();
    @Test void gradeA() { assertEquals("A", calculator.letterGrade(95)); }
    @Test void gradeB() { assertEquals("B", calculator.letterGrade(85)); }
    @Test void gradeC() { assertEquals("C", calculator.letterGrade(75)); }
    @Test void gradeD() { assertEquals("D", calculator.letterGrade(65)); }
    @Test void gradeF() { assertEquals("F", calculator.letterGrade(55)); }
    @Test void invalidScoreBelowZero() { assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(-1)); }
    @Test void invalidScoreAboveHundred() { assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(101)); }
    @Test void averageGpa() { assertEquals(3.5, calculator.gpa(new double[]{95, 85})); }
    @Test void gpaNullArray() { assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null)); }
    @Test void gpaEmptyArray() { assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[]{})); }
    @Test void gpaGradeC() { assertEquals(2.0, calculator.gpa(new double[]{75})); }
    @Test void gpaGradeD() { assertEquals(1.0, calculator.gpa(new double[]{65})); }
    @Test void gpaSwitchDefaultF() { assertEquals(0.0, calculator.gpa(new double[]{55})); }
    @Test void scholarshipHundred() { assertEquals(100, calculator.scholarship(3.9, 16, false)); }
    @Test void scholarshipHundredCreditConditionFalse() { assertEquals(70, calculator.scholarship(3.9, 14, false)); }
    @Test void scholarshipSeventy() { assertEquals(70, calculator.scholarship(3.6, 12, false)); }
    @Test void scholarshipSeventyCreditConditionFalse() { assertEquals(50, calculator.scholarship(3.6, 11, false)); }
    @Test void scholarshipFifty() { assertEquals(50, calculator.scholarship(3.2, 9, false)); }
    @Test void scholarshipZeroBelowThreshold() { assertEquals(0, calculator.scholarship(2.9, 8, false)); }
    @Test void scholarshipViolation() { assertEquals(0, calculator.scholarship(3.9, 16, true)); }
    @Test void invalidGpaBelowZero() { assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(-0.1, 16, false)); }
    @Test void invalidGpaAboveFour() { assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(4.1, 16, false)); }
    @Test void invalidCreditsBelowZero() { assertThrows(IllegalArgumentException.class, () -> calculator.scholarship(3.9, -1, false)); }
}
