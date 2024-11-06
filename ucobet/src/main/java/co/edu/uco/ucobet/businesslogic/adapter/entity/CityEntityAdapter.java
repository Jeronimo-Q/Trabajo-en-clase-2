package co.edu.uco.ucobet.businesslogic.adapter.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CityDomain;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.entity.CityEntity;
import co.edu.uco.ucobet.entity.StateEntity;


public final class CityEntityAdapter implements Adapter<CityEntity,CityDomain>{
	
	private static final Adapter<CityEntity,CityDomain> instance = new CityEntityAdapter();	
	
	private CityEntityAdapter() {
		
	}
	
	public static Adapter<CityEntity,CityDomain> getCityEntityAdapter(){
		return instance;
	}

	@Override
	public CityEntity adaptSource(CityDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data, CityDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY,
				StateDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY,
						CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY))));
		
		StateEntity stateEntity = StateEntityAdapter.getStateEntityAdapter().adaptSource(domainToAdapt.getState());
		
		var entityAdapted = new CityEntity();
		entityAdapted.setId(domainToAdapt.getId());
		entityAdapted.setName(domainToAdapt.getName());
		entityAdapted.setState(stateEntity);
		
		return entityAdapted;
	}

	@Override
	public CityDomain adaptTarget(CityEntity data) {
		var entityToAdapt = ObjectHelper.getDefault(data, new CityEntity());
		
		StateDomain stateDomain = StateEntityAdapter.getStateEntityAdapter().adaptTarget(entityToAdapt.getState());
		
		return CityDomain.create( entityToAdapt.getId(), data.getName(),stateDomain);
	}

	@Override
	public List<CityEntity> adaptSource(List<CityDomain> data) {
		var results = new ArrayList<CityEntity>();
		
		for (CityDomain domain:data) {
			results.add(adaptSource(domain));
		}
		
		return results;
	}

	@Override
	public List<CityDomain> adaptTarjet(List<CityEntity> data) {
		var results = new ArrayList<CityDomain>();
		
		for (CityEntity entity:data) {
			results.add(adaptTarget(entity));
		}
		
		return results;
	}



}
