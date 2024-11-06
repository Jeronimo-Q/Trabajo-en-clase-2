package co.edu.uco.ucobet.data.dao.impl.sql.sqlserver;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.crosscutting.exceptions.DataUcoBetException;
import co.edu.uco.ucobet.data.dao.CityDAO;
import co.edu.uco.ucobet.data.dao.impl.sql.SqlDAO;
import co.edu.uco.ucobet.entity.CityEntity;
import co.edu.uco.ucobet.entity.StateEntity;


public final class CitySqlServerDAO extends SqlDAO implements CityDAO{

	protected CitySqlServerDAO(final Connection connection) {
		super(connection);
	}

	@Override
	public CityEntity findById(UUID id) {
		var cityEntityFilter = new CityEntity();
		cityEntityFilter.setId(id);
		
		var result = findByFilter(cityEntityFilter);
			
		return (result.isEmpty()) ? new CityEntity() : result.get(0);
	}

	@Override
	public List<CityEntity> findAll() {
		return findByFilter(new CityEntity());
	}

	@Override
	public List<CityEntity> findByFilter(CityEntity filter) {
		final var statement = new StringBuilder();
		final var parameters = new ArrayList<>();
		final var resultSelect = new ArrayList<CityEntity>();
		var statementWasPrepared = false;
		
		createSelect(statement);
		createFrom(statement);
		createWhere(statement, filter, parameters);
		createOrderBy(statement);
		
		try (final var  preparedStatement = getConnection().prepareStatement(statement.toString())){
			
			for (var arrayIndex = 0; arrayIndex < parameters.size(); arrayIndex++) {
				var statementIndex = arrayIndex + 1;
				preparedStatement.setObject(statementIndex, parameters.get(arrayIndex));
			}
			statementWasPrepared = true;
			
			final var result = preparedStatement.executeQuery();
			
			while(result.next()) {
				var stateEntityTmp = new StateEntity();
				var cityEntityTmp = new CityEntity();
				
				stateEntityTmp.setName(result.getString("name"));
				
				cityEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
				cityEntityTmp.setName(result.getString("name"));
				cityEntityTmp.setState(stateEntityTmp);
				resultSelect.add(cityEntityTmp);
			}
			
			} catch ( final SQLException exception) {
				var userMessage ="Se ha presentado un problema tratando de llevar a cabo la consulta de las ciudades por el filtro deseado.Por favor intente de nuevo, y si el problema persiste, reporte la novedad...";
				var technicalMessage="Se ha presentado un problema al tratar de consultar la información de las ciudades por el filtro deseado en la base de datos SQL Server tratando de preparar la sentencia SQL que se iba a ejecutar. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";

				if(statementWasPrepared) {
					technicalMessage="Se ha presentado un problema al tratar de consultar la información de las ciudades por el filtro deseado en la base de datos SQL Server tratando de ejecutar la sentencia SQL definida. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";		
				}
				
				throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
			}
		
		return resultSelect;
	}

	@Override
	public void create(final CityEntity data) {
		
		final StringBuilder statement = new StringBuilder();
		statement.append("INSERT INTO City(id, name, state) VALUES (?, ?, ?) ");
		
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

	
	private void createSelect(final StringBuilder statement) {
		statement.append("SELECT id, name, state ");
	} 

	private void createFrom(final StringBuilder statement) {
		statement.append("FROM City ");
	} 
	
	private void createWhere(StringBuilder statemet, CityEntity filter, ArrayList<Object> parameters) {
		if(!ObjectHelper.isNull(filter)) {
			if(UUIDHelper.isDefault(filter.getId())) {
				statemet.append("WHERE id = ? ");
				parameters.add(filter.getId());
			}
			if(!TextHelper.isEmptyAppplyingTrim(filter.getName())) {
				statemet.append((parameters.isEmpty()) ?"WHERE "  : "AND ");
				statemet.append("name = ? ");
				parameters.add(filter.getId());
			}
		}
		
	}
	
	private void createOrderBy(final StringBuilder statement) {
		statement.append("ORDER BY name ASC ");
	} 



}
