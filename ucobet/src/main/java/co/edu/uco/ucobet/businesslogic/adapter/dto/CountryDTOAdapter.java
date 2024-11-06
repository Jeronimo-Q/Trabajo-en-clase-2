package co.edu.uco.ucobet.businesslogic.adapter.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.dto.CountryDTO;

public final class CountryDTOAdapter implements Adapter<CountryDTO,CountryDomain> {
	
	
	private static final Adapter<CountryDTO,CountryDomain> instance = new CountryDTOAdapter();	
	
	private CountryDTOAdapter() {
		
	}
	
	public static Adapter<CountryDTO,CountryDomain> getCountryDTOAdapter(){
		return instance;
	}

	@Override
	public CountryDTO adaptSource(final CountryDomain data) {
		
		var domainToAdapt = ObjectHelper.getDefault(data,  CountryDomain.create(UUIDHelper.getDefault(),TextHelper.EMPTY));
		return CountryDTO.create().setId("").setName(domainToAdapt.getName());
	}

	@Override
	public CountryDomain adaptTarget(final CountryDTO data) {
		var dtoToAdapt = ObjectHelper.getDefault(data,  CountryDTO.create());
		return CountryDomain.create(UUIDHelper.convertToUUID( dtoToAdapt.getId()), data.getName());
	}

	@Override
	public List<CountryDTO> adaptSource(final List<CountryDomain> data) {
		
		var results = new ArrayList<CountryDTO>();
		
		for (CountryDomain domain:data) {
			results.add(adaptSource(domain));
		}
		
		return results;
	}

	@Override
	public List<CountryDomain> adaptTarjet(List<CountryDTO> data) {
		// TODO Auto-generated method stub
		return null;
	}

}
