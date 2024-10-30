package co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.impl;

import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.CityNameConsistencyIsValid;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;

public class CityNameConsistencyIsValidImpl implements CityNameConsistencyIsValid{

	@Override
	public void execute(final String data) {
		validateNotNull(null);
		validateLen(null);
		validateFormat(null);
	}
	
	private void validateLen(final String data) {
		if (TextHelper.maxLenIsValid(data, 50)) {
			var userMessage = "El nombre de la ciudad puede contener maximo 50 caracteres... ";
			throw BusinessLogicUcoBetException.crear(userMessage);
		}
		
	}
	
	private void validateNotNull(final String data) {
		if (TextHelper.containOnlyLettersAndSpace(data)) {
			var userMessage = "El nombre de la ciudad solo puede contener letras y espacios... ";
			throw BusinessLogicUcoBetException.crear(userMessage);
		}
		
	}
	
	private void validateFormat(final String data) {
		if (TextHelper.isEmpty(data)) {
			var userMessage = "El nombre de la ciudad no puede estar vacio... ";
			throw BusinessLogicUcoBetException.crear(userMessage);
		}
		
	}

}
