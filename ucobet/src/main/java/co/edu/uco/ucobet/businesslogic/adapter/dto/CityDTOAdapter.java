package co.edu.uco.ucobet.businesslogic.adapter.dto;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.CityDomain;
import co.edu.uco.ucobet.dto.CityDTO;


public final class CityDTOAdapter implements Adapter<CityDomain, CityDTO> {
	
	private static final Adapter<CityDomain, CityDTO> instance = new CityDTOAdapter();	
	
	private CityDTOAdapter() {
		
	}
	
	public static Adapter<CityDomain, CityDTO> getCityDTOAdapter(){
		return instance;
	}

	@Override
	public CityDomain adaptSource(CityDTO data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CityDTO adaptTarget(CityDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CityDTO> adaptTarget(final List<CityDomain> data) {
		
		var results = new ArrayList<CityDTO>();
		
		for (CityDomain domain:data) {
			results.add(adaptTarget(domain));
		}
		
		return results;
	}


}
