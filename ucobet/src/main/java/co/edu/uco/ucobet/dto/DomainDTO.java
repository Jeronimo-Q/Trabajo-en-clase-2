package co.edu.uco.ucobet.dto;

import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;

public class DomainDTO {
	
	private String id;
	
	protected DomainDTO(final String id){
		setIdentifier(id);
	}

	protected String getId() {
		return id;
	}

	protected void setIdentifier(final String id) {
		this.id = TextHelper.getDefault(id, UUIDHelper.getDefaultAssString());
	}

}
