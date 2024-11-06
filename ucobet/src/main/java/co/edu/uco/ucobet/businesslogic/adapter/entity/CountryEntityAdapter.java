package co.edu.uco.ucobet.businesslogic.adapter.entity;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.entity.CountryEntity;

public class CountryEntityAdapter implements Adapter<CountryEntity, CountryDomain>{
	
	private static final Adapter<CountryEntity, CountryDomain> instance = new CountryEntityAdapter();	
	
	private CountryEntityAdapter() {
		
	}
	
	public static Adapter<CountryEntity, CountryDomain> getCountryEntityAdapter(){
		return instance;
	}

	@Override
	public CountryEntity adaptSource(final CountryDomain data) {
		
		var domainToAdapt = ObjectHelper.getDefault(data, CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY));
		
		var entityAdapted = new CountryEntity();
		entityAdapted.setId(domainToAdapt.getId());
		entityAdapted.setName(domainToAdapt.getName());
		
		return entityAdapted;
	}

	@Override
	public CountryDomain adaptTarget(final CountryEntity data) {
		var entityToAdapt = ObjectHelper.getDefault(data, new CountryEntity());
		return CountryDomain.create( entityToAdapt.getId(), data.getName());
	}

	@Override
	public List<CountryEntity> adaptSource(final List<CountryDomain> data) {
		var results = new ArrayList<CountryEntity>();
		
		for (CountryDomain domain:data) {
			results.add(adaptSource(domain));
		}
		
		return results;
	}

	@Override
	public List<CountryDomain> adaptTarjet(List<CountryEntity> data) {
		// TODO Auto-generated method stub
		return null;
	}


}
