package co.edu.uco.ucobet.data.dao;

import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.ucobet.crosscutting.exceptions.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.enums.DAOSource;
import co.edu.uco.ucobet.data.dao.impl.sql.sqlserver.SqlServerDAOFactory;

public abstract class DAOFactory {
	
	public static final DAOFactory getFactory(final DAOSource source) {
		
		switch (source) {
		case SQLSERVER: {
			return new SqlServerDAOFactory();
		}
		default:
			var userMessage = "Se ha prensentando un problema tratando de llevar a cabo la operacion deseada...";
			var technicalMessage = TextHelper.concat("No existe una factoria implementada para",source.toString());
			throw DataUcoBetException.crear(userMessage,technicalMessage);
		}
	}
	
	protected abstract void openConnection();
	
	public abstract void initTransaction();

	public abstract void commitTransaction();
	
	public abstract void rollbackTransaction();
	
	public abstract void closeConnection();
	
	public abstract CityDAO getCityDAO();
	
	public abstract CountryDAO getCountryDAO();

	public abstract StateDAO getStateDAO();
}
