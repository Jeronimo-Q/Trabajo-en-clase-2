package co.edu.uco.crosscuting.unittesting;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import co.edu.uco.crosscuting.helpers.ObjectHelper;

class HelpersUnitTestCase {

	@Test
	void givenWantValidateIfObjectIsNullWhenObjectIsNullThenResultIsTrue() {
		//Arrage: Initial Context
		String object = null;
		
		//Act
		boolean result = ObjectHelper.isNull(object);

		//Assert
		assertTrue(result);
	}

	@Test
	void givenWantValidateIfObjectIsNotNullWhenObjectIsNotNullThenResultIsFalse() {
		//Arrage: Initial Context
		var object = "";
		
		//Act: Execute action
		boolean result = ObjectHelper.isNull(object);

		//Assert: Validate result
		assertFalse(result);
	}
	
	@Test
	void givenWantGetOriginalWhenObjectIsNotNullThenResultIsTheSameObject() {
		//Arrage: Initial Context
		String object = "Casa";
		var defaultValue = "Default";
		
		//Act
		var result = ObjectHelper.getDefault(object, defaultValue);

		//Assert
		assertEquals(result,result);
	}
	
	
	@Test
	void givenWantGetDefaultValueWhenObjectIsNullThenResultIsDefaultObject() {
		//Arrage: Initial Context
		String object = null;
		var defaultValue = "Default";
		
		//Act
		var result = ObjectHelper.getDefault(object, defaultValue);

		//Assert
		assertEquals(defaultValue,result);
	}
}
