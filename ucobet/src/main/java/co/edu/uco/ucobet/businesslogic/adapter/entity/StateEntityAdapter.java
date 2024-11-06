package co.edu.uco.ucobet.businesslogic.adapter.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.entity.CountryEntity;
import co.edu.uco.ucobet.entity.StateEntity;

public class StateEntityAdapter implements Adapter<StateEntity, StateDomain>{
	
	private static final Adapter<StateEntity, StateDomain> instance = new StateEntityAdapter();	
	
	private StateEntityAdapter() {
		
	}
	
	public static Adapter<StateEntity, StateDomain> getStateEntityAdapter(){
		return instance;
	}

	@Override
	public StateEntity adaptSource(final StateDomain data) {
		
		var domainToAdapt = ObjectHelper.getDefault(data, StateDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY,
				CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY)));
		
		CountryEntity countryEntity = CountryEntityAdapter.getCountryEntityAdapter().adaptSource(domainToAdapt.getCountry());
		
		var entityAdapted = new StateEntity();
		entityAdapted.setId(domainToAdapt.getId());
		entityAdapted.setName(domainToAdapt.getName());
		entityAdapted.setCountry(countryEntity);
		
		return entityAdapted;
	}

	@Override
	public StateDomain adaptTarget(final StateEntity data) {
		var entityToAdapt = ObjectHelper.getDefault(data, new StateEntity());
		
		CountryDomain countryDomain = CountryEntityAdapter.getCountryEntityAdapter().adaptTarget(entityToAdapt.getCountry());
		
		return StateDomain.create( entityToAdapt.getId(), data.getName(),countryDomain);
	}

	@Override
	public List<StateEntity> adaptSource(final List<StateDomain> data) {
		var results = new ArrayList<StateEntity>();
		
		for (StateDomain domain:data) {
			results.add(adaptSource(domain));
		}
		
		return results;
	}

	@Override
	public List<StateDomain> adaptTarjet(List<StateEntity> data) {
		// TODO Auto-generated method stub
		return null;
	}

}
