package co.edu.uco.ucobet.businesslogic.facade.city.impl;

import java.util.List;

import co.edu.uco.ucobet.businesslogic.adapter.dto.CityDTOAdapter;
import co.edu.uco.ucobet.businesslogic.facade.city.FindCityFacade;
import co.edu.uco.ucobet.businesslogic.usecase.city.impl.FindCityImpl;
import co.edu.uco.ucobet.crosscutting.exceptions.BusinessLogicUcoBetException;
import co.edu.uco.ucobet.crosscutting.exceptions.UcoBetException;
import co.edu.uco.ucobet.data.dao.DAOFactory;
import co.edu.uco.ucobet.data.dao.enums.DAOSource;
import co.edu.uco.ucobet.dto.CityDTO;

public class FindCityFacadeImpl implements FindCityFacade{

	@Override
	public List<CityDTO> execute(final CityDTO data) {
		
		var factory = DAOFactory.getFactory(DAOSource.SQLSERVER);
		
		try {
			
			var findCityUseCase = new FindCityImpl(factory);
			var cityDomain = CityDTOAdapter.getCityDTOAdapter().adaptTarget(data);
			
			return CityDTOAdapter.getCityDTOAdapter().adaptSource(findCityUseCase.execute(cityDomain));
			
		} catch (final UcoBetException exception) {
			throw exception;
		} catch (final Exception exception) {
			factory.rollbackTransaction();
			var userMessage = "Se ha presentado un problema tratando de consultar la informacion de las ciudades";
			var technicalMessage = "Se ha presentado un problema inesperado al trartar de consulatar la informacion de las cuidades. Por favor revise el log de errores para tener mas detalles";
			
			throw BusinessLogicUcoBetException.crear(userMessage,technicalMessage,exception);
		}finally {
			factory.closeConnection();
		}
	}

}
