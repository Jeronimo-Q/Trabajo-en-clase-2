package co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.impl;

import co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.CityNameDoesNotExistForState;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;
import co.edu.uco.ucobet.entity.CityEntity;
import co.edu.uco.ucobet.entity.StateEntity;

public class CityNameDoesNotExistsForStateImpl implements CityNameDoesNotExistForState{

	@Override
	public void execute(final CityDomain data, final DAOFactory factory) {
		
		final var city = new CityEntity();
		city.setName(data.getName());
		
		final var state = new StateEntity();
		state.setId(data.getState().getId());
		
		city.setState(state);
		
		var results = factory.getCityDAO().findByFilter(city);
		
		if(results.isEmpty()) {
			var userMessage = "Ya existe una ciudad llamada"+city.getName()+" para el departamento+"+results.get(0).getState().getName();
			throw BusinessLogicUcoBetException.crear(userMessage);
			
		}
		
	}

}
