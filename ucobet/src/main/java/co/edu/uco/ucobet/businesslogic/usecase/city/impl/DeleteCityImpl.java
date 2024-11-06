package co.edu.uco.ucobet.businesslogic.usecase.city.impl;

import java.util.UUID;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.ucobet.businesslogic.usecase.city.DeleteCity;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;

public final class DeleteCityImpl implements DeleteCity{
	
	private DAOFactory daoFactory;

	public DeleteCityImpl(final DAOFactory daoFactory) {
		setDaoFactory(daoFactory);
	}
	
	@Override
	public void execute(final UUID data) {
		
		daoFactory.getCityDAO().delete(data);
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
