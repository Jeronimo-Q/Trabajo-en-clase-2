package co.edu.uco.ucobet.businesslogic.adapter.dto;

import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.domain.StateDomain;
import co.edu.uco.ucobet.dto.StateDTO;

public final class StateDTOAdapter implements Adapter<StateDomain, StateDTO> {
	
	private static final Adapter<StateDomain, StateDTO> instance = new StateDTOAdapter();	
	
	private StateDTOAdapter() {
		
	}
	
	public static Adapter<StateDomain, StateDTO> getStateDTOAdapter(){
		return instance;
	}

	@Override
	public StateDomain adaptSource(StateDTO data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateDTO adaptTarget(StateDomain data) {
		// TODO Auto-generated method stub
		return null;
	}

}
