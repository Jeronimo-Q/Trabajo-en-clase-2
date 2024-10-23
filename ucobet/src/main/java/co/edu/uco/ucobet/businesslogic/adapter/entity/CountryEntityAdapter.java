package co.edu.uco.ucobet.businesslogic.adapter.entity;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.entity.CountryEntity;

public class CountryEntityAdapter implements Adapter<CountryDomain, CountryEntity>{
	
	private static final Adapter<CountryDomain, CountryEntity> instance = new CountryEntityAdapter();	
	
	private CountryEntityAdapter() {
		
	}
	
	public static Adapter<CountryDomain, CountryEntity> getCountryEntityAdapter(){
		return instance;
	}

	@Override
	public CountryDomain adaptSource(CountryEntity data) {
		var entityToAdapt = ObjectHelper.getDefault(data, new CountryEntity());
		return CountryDomain.create( entityToAdapt.getId(), data.getName());
	}

	@Override
	public CountryEntity adaptTarget(CountryDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data, CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY));
			
		var entityAdapted = new CountryEntity();
		entityAdapted.setId(domainToAdapt.getId());
		entityAdapted.setName(domainToAdapt.getName());
		
		return entityAdapted;
	}

}
