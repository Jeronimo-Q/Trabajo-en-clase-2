package co.edu.uco.ucobet.businesslogic.adapter.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;
import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CityDomain;
import co.edu.uco.ucobet.domain.CountryDomain;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.dto.CityDTO;
import co.edu.uco.ucobet.dto.StateDTO;


public final class CityDTOAdapter implements Adapter<CityDTO, CityDomain> {
	
	private static final Adapter<CityDTO, CityDomain> instance = new CityDTOAdapter();	
	
	private CityDTOAdapter() {
		
	}
	
	public static Adapter<CityDTO, CityDomain> getCityDTOAdapter(){
		return instance;
	}

	@Override
	public CityDTO adaptSource(final CityDomain data) {
		var domainToAdapt = ObjectHelper.getDefault(data,  CityDomain.create(UUIDHelper.getDefault(),TextHelper.EMPTY,
				StateDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY,
						CountryDomain.create(UUIDHelper.getDefault(), TextHelper.EMPTY))));
		StateDTO stateDTO = StateDTOAdapter.getStateDTOAdapter().adaptSource(domainToAdapt.getState());
		
		return CityDTO.create().setId("").setName(domainToAdapt.getName()).setState(stateDTO);
	}

	@Override
	public CityDomain adaptTarget(final CityDTO data) {
		var dtoToAdapt = ObjectHelper.getDefault(data,  CityDTO.create());
		
		StateDomain stateDomain = StateDTOAdapter.getStateDTOAdapter().adaptTarget(dtoToAdapt.getState());
		
		return CityDomain.create(UUIDHelper.convertToUUID(dtoToAdapt.getId()), data.getName(), stateDomain);
	}
	
	@Override
	public List<CityDTO> adaptSource(final List<CityDomain> data) {
		
		var results = new ArrayList<CityDTO>();
		
		for (CityDomain domain:data) {
			results.add(adaptSource(domain));
		}
		
		return results;
	}

	@Override
	public List<CityDomain> adaptTarjet(List<CityDTO> data) {
		// TODO Auto-generated method stub
		return null;
	}


}
