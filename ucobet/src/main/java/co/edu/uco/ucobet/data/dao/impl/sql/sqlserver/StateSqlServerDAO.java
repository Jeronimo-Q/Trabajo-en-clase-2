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
import co.edu.uco.ucobet.data.dao.StateDAO;
import co.edu.uco.ucobet.data.dao.impl.sql.SqlDAO;
import co.edu.uco.ucobet.entity.CountryEntity;
import co.edu.uco.ucobet.entity.StateEntity;

public class StateSqlServerDAO extends SqlDAO implements StateDAO{

	protected StateSqlServerDAO(Connection connection) {
		super(connection);
	}

	@Override
	public StateEntity findById(UUID id) {
		var stateEntityFilter = new StateEntity();
		stateEntityFilter.setId(id);
		
		var result = findByFilter(stateEntityFilter);
			
		return (result.isEmpty()) ? new StateEntity() : result.get(0);
	}

	@Override
	public List<StateEntity> findAll() {
		return findByFilter(new StateEntity());
	}

	@Override
	public List<StateEntity> findByFilter(StateEntity filter) {
		final var statement = new StringBuilder();
		final var parameters = new ArrayList<>();
		final var resultSelect = new ArrayList<StateEntity>();
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
				var countryEntityTmp = new CountryEntity();
				var stateEntityTmp = new StateEntity();
				
				countryEntityTmp.setName(result.getString("name"));
				
				stateEntityTmp.setId(UUIDHelper.convertToUUID(result.getString("id")));
				stateEntityTmp.setName(result.getString("name"));
				stateEntityTmp.setCountry(countryEntityTmp);
				resultSelect.add(stateEntityTmp);
			}
			
			} catch ( final SQLException exception) {
				var userMessage ="Se ha presentado un problema tratando de llevar a cabo la consulta de los departamentos por el filtro deseado.Por favor intente de nuevo, y si el problema persiste, reporte la novedad...";
				var technicalMessage="Se ha presentado un problema al tratar de consultar la información de los departamentos por el filtro deseado en la base de datos SQL Server tratando de preparar la sentencia SQL que se iba a ejecutar. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";

				if(statementWasPrepared) {
					technicalMessage="Se ha presentado un problema al tratar de consultar la información de los departamentos por el filtro deseado en la base de datos SQL Server tratando de ejecutar la sentencia SQL definida. Por favor valide el log de errores para encontrar mayores detalles del problema presentado...";		
				}
				
				throw DataUcoBetException.crear(userMessage, technicalMessage, exception);
			}
		
		return resultSelect;
	}
	
	
	private void createSelect(final StringBuilder statement) {
		statement.append("SELECT id, name, country ");
	} 

	private void createFrom(final StringBuilder statement) {
		statement.append("FROM State ");
	} 
	
	private void createWhere(StringBuilder statemet, StateEntity filter, ArrayList<Object> parameters) {
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