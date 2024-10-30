package co.edu.uco.ucobet.businesslogic.adapter.entity;

import java.util.List;

import co.edu.uco.ucobet.businesslogic.adapter.Adapter;
import co.edu.uco.ucobet.entity.StateEntity;

public class StateEntityAdapter implements Adapter<StateEntity, StateEntity>{
	
	private static final Adapter<StateEntity, StateEntity> instance = new StateEntityAdapter();	
	
	private StateEntityAdapter() {
		
	}
	
	public static Adapter<StateEntity, StateEntity> getStateEntityAdapter(){
		return instance;
	}

	@Override
	public StateEntity adaptSource(StateEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StateEntity adaptTarget(StateEntity data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StateEntity> adaptTarget(List<StateEntity> data) {
		// TODO Auto-generated method stub
		return null;
	}


}
