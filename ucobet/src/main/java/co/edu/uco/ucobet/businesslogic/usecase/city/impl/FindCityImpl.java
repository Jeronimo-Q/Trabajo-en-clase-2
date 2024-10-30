package co.edu.uco.ucobet.businesslogic.usecase.city.impl;

import java.util.List;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.ucobet.businesslogic.usecase.city.FindCity;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.domain.CityDomain;

public final class FindCityImpl implements FindCity{
	
	public FindCityImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}


	@Override
	public List<CityDomain> execute(final CityDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

	private void setDaoFactory(DAOFactory daoFactory) {
		
		if(ObjectHelper.isNull(daoFactory)) {
			var userMessage = "Se ha presentado un problema inesperado, tratando de llevar a cabo ls consulta de la ciudad deseada.Por favor intente de nuevo y si el problema persiste, comuniquese...";
			var technicalMessage = "EL dao factory requerido para crear la clase de consulta de la ciudad llego nula ";
			throw BusinessLogicUcoBetException.crear(userMessage, technicalMessage);
			
		}
	}

}
