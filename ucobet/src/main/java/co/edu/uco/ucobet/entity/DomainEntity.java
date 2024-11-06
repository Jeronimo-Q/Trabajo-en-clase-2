package co.edu.uco.ucobet.entity;

import java.util.UUID;

import co.edu.uco.crosscuting.helpers.UUIDHelper;

public class DomainEntity {
	
	private UUID id;
	
	protected DomainEntity(final UUID id){
		setId(id);
	}

	protected UUID getId() {
		return id;
	}

	protected void setId(final UUID id) {
		this.id = UUIDHelper.getDefault(id, UUIDHelper.getDefault());
	}
}
