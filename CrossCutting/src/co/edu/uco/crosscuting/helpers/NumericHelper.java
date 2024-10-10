package co.edu.uco.crosscuting.helpers;

public final class NumericHelper {
	
	private NumericHelper(){
		
	}
	
	public static final boolean RANGEINCLUDED = true;
	public static final boolean RANGENOTINCLUDED = false;
	
	public static boolean isNegative(final Number number) {
		return transformNumberToDouble(number) < 0;
	}
	
	public static boolean isPositive(final Number number) {
		return transformNumberToDouble(number) > 0;
	}

	public static double transformNumberToDouble(final Number number) {
		return number.doubleValue();
	}
	 
	public static boolean isGreaterThan(final Number number1,final Number number2) {
		return transformNumberToDouble(number1) > transformNumberToDouble(number2);
	}
	
	public static boolean isLessThan(final Number number1,final Number number2) {
		return transformNumberToDouble(number1) < transformNumberToDouble(number2);
	}
	
	public static boolean isEqual(final Number number1,final Number number2) {
		return transformNumberToDouble(number1) == (transformNumberToDouble(number2));
	}
	
	public static boolean isGreaterOrEqualTo(final Number number1,final Number number2) {
		return isGreaterThan(number1,number2) || isEqual(number1,number2);
	}
	
	public static boolean isLessOrEqualTo(final Number number1,final Number number2) {
		return isLessThan(number1,number2) || isEqual(number1,number2);
	}
	
	public static boolean isBetween(final Number number,final Number initialNumber,final Number finalNumber, boolean rangeToTheRight, boolean rangeToTheLeft) {
		return ((rangeToTheRight) ? isGreaterOrEqualTo(number,initialNumber): isGreaterThan(number,initialNumber))
				&&
				((rangeToTheLeft) ? isLessOrEqualTo(number,finalNumber):isLessThan(number,finalNumber));
	}
	
	public static boolean isBetweenExclusive(final Number number,final Number initialNumber,final Number finalNumber) {
		return isBetween(number,initialNumber,finalNumber,RANGENOTINCLUDED,RANGENOTINCLUDED);
	}
	
	public static boolean isBetweenInclusive(final Number number,final Number initialNumber,final Number finalNumber) {
		return isBetween(number,initialNumber,finalNumber,RANGEINCLUDED,RANGEINCLUDED);
	}
	
	public static boolean isBetweenInclusiveRight(final Number number,final Number initialNumber,final Number finalNumber) {
		return isBetween(number,initialNumber,finalNumber,RANGENOTINCLUDED,RANGEINCLUDED);
	}
	
	public static boolean isBetweenInclusiveLeft(final Number number,final Number initialNumber,final Number finalNumber) {
		return isBetween(number,initialNumber,finalNumber,RANGEINCLUDED,RANGENOTINCLUDED);
	}
	

}
