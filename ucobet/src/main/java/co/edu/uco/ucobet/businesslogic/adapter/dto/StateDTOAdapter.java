package co.edu.uco.ucobet.businesslogic.adapter.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.dto.CountryDTO;
import co.edu.uco.ucobet.dto.StateDTO;

public final class StateDTOAdapter implements Adapter<StateDTO,StateDomain> {
	
	private static final Adapter<StateDTO,StateDomain> instance = new StateDTOAdapter();	
	
	private StateDTOAdapter() {
		
	}
	
	public static Adapter<StateDTO,StateDomain> getStateDTOAdapter(){
		return instance;
	}

	@Override
	public List<StateDTO> adaptSource(final List<StateDomain> data) {
		
		var results = new ArrayList<StateDTO>();
		
		for (StateDomain domain:data) {
			results.add(adaptSource(domain));
		}
		
		return results;
	}

	@Override
	public StateDTO adaptSource(final StateDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data,  StateDomain.create(UUIDHelper.getDefault(),TextHelper.EMPTY,
				CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY)));
		
		CountryDTO countryDTO = CountryDTOAdapter.getCountryDTOAdapter().adaptSource(domainToAdapt.getCountry());
		
		return StateDTO.create().setId("").setName(domainToAdapt.getName()).setCountry(countryDTO);
	}

	@Override
	public StateDomain adaptTarget(final StateDTO data) {
		var dtoToAdapt = ObjectHelper.getDefault(data,  StateDTO.create());
		
		CountryDomain countryDomain = CountryDTOAdapter.getCountryDTOAdapter().adaptTarget(dtoToAdapt.getCountry());
		
		return StateDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()), data.getName(), countryDomain);
	}

	@Override
	public List<StateDomain> adaptTarjet(List<StateDTO> data) {
		// TODO Auto-generated method stub
		return null;
	}

}
