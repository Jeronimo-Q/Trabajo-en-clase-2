package co.edu.uco.ucobet.businesslogic.usecase.city.impl;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.ucobet.businesslogic.adapter.entity.CityEntityAdapter;
import co.edu.uco.ucobet.businesslogic.usecase.city.UpdateCity;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;

public final class UpdateCityImpl implements UpdateCity{

	private DAOFactory daoFactory;
	
	public UpdateCityImpl(DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}
	
	@Override
	public void execute(final CityDomain data) {
		//Validate policies
		
		var cityEntity = CityEntityAdapter.getCityEntityAdapter().adaptSource(data);
		daoFactory.getCityDAO().update(cityEntity);
		
	}

	private void setDaoFactory(DAOFactory daoFactory) {
		if(ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo la modificacion de la ciudad deseada.Por favor intente de nuevo y si el problema persiste, comuniquese...";
			var technicalMessage = "EL dao factory requerido para crear la clase que actualiza la ciudad llego nula ";
			throw BusinessLogicUcoBetException.crear(userMessage, technicalMessage);
			
		}
		
		this.daoFactory = daoFactory;
	}

}
