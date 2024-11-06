package co.edu.uco.ucobet.businesslogic.usecase.city.impl;

import java.util.UUID;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.ucobet.businesslogic.usecase.city.RegisterNewCity;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.CityNameConsistencyIsValid;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.CityNameDoesNotExistForState;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.impl.CityNameConsistencyIsValidImpl;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.rules.impl.CityNameDoesNotExistsForStateImpl;
import co.edu.uco.ucobet.businesslogic.usecase.state.rules.StateExists;
import co.edu.uco.ucobet.businesslogic.usecase.state.rules.impl.StateExistsImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;

public final class RegisterNewCityImpl implements RegisterNewCity{
	
	private DAOFactory daoFactory;
	private CityNameDoesNotExistForState cityNameDoesNotExistForState = new CityNameDoesNotExistsForStateImpl();
	private StateExists stateExists = new StateExistsImpl();
	private CityNameConsistencyIsValid cityNameConsistencyIsValid = new CityNameConsistencyIsValidImpl();
	

	public RegisterNewCityImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}
	
	private void setDaoFactory(DAOFactory daoFactory) {
		
		if(ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo el registro de la ciudad deseada.Por favor intente de nuevo y si el problema persiste, comuniquese...";
			var technicalMessage = "EL dao factory requerido para crear la clase que registra la ciudad llego nula ";
			throw BusinessLogicUcoBetException.crear(userMessage, technicalMessage);
			
		}
	}

	
	private UUID generatedId() {
		var id = UUIDHelper.generate();
		var cityEntity = daoFactory.getCityDAO().findById(id);
		
		if(UUIDHelper.isEqual(cityEntity.getId(), id)) {
			id = generatedId();
		}
		
		return id;
	}
	
	@Override
	public void execute(final CityDomain data) {

		cityNameDoesNotExistForState.execute(data, daoFactory);
		stateExists.execute(data.getState().getId(), daoFactory);
		cityNameConsistencyIsValid.execute(data.getName());
		
		var cityDomainToMap = CityDomain.create(generatedId(), data.getName(), data.getState());
		var cityEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(cityDomainToMap);
		daoFactory.getCityDAO().create(cityEntity);
		
	}

}
