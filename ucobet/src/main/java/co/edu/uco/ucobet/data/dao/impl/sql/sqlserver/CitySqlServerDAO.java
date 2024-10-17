package co.edu.uco.ucobet.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import co.edu.uco.ucobet.crosscutting.exceptions.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.CityDAO;
import co.edu.uco.ucobet.data.dao.impl.sql.SqlDAO;
import co.edu.uco.ucobet.entity.CityEntity;


public final class CitySqlServerDAO extends SqlDAO implements CityDAO{

	protected CitySqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public CityEntity findById(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityEntity> findByFilter(CityEntity filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(final CityEntity data) {
		
		final StringBuilder statement = new StringBuilder();
		statement.append("INSERT INTO City(id, name) VALUES (?, ?, ?) ");
		
		try(final var preparedStatement = getConnection().prepareStatement(statement.toString())){
			
			preparedStatement.setObject(1, data.getId());
			preparedStatement.setString(2, data.getName());
			preparedStatement.setObject(3, data.getState().getId());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage ="Se ha presentado un problema tratando de llevar a cabo el registro de la informacion del nueva ciudad. Por favor intente de nuevo, y si el problema persiste, reporte la novedad...";
			var technicalMessage="Se ha presentado un problema al tratar de registrar la informacion de la nueva ciudad en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";
			
			throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
		}
		}
		

	@Override
	public void delete(UUID data) {
		final StringBuilder statement = new StringBuilder();
		statement.append("DELETE FROM City WHERE id = ? ");
		
		try(final var preparedStatement = getConnection().prepareStatement(statement.toString())){

			preparedStatement.setObject(1, data);
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage ="Se ha presentado un problema tratando de llevar a cabo la eliminacion del registro de la ciudad. Por favor intente de nuevo, y si el problema persiste, reporte la novedad...";
			var technicalMessage="Se ha presentado un problema al tratar de eliminar la informacion del pais en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";
			
			throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
		}
		
	}

	@Override
	public void update(CityEntity data) {
		
		final StringBuilder statement = new StringBuilder();
		statement.append("UPDATE City SET name = ?, state = ? WHERE id = ? ");
		
		try(final var preparedStatement = getConnection().prepareStatement(statement.toString())){
			
			preparedStatement.setString(1, data.getName());
			preparedStatement.setObject(2, data.getState().getId());
			preparedStatement.setObject(3, data.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (final SQLException exception) {
			var userMessage ="Se ha presentado un problema tratando de llevar a cabo al modificacion del registro ciudad. Por favor intente de nuevo, y si el problema persiste, reporte la novedad...";
			var technicalMessage="Se ha presentado un problema al tratar de modificar la informacion del ciudad en la base de datos SQL Server. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";
			
			throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
		}
		
	}



}
