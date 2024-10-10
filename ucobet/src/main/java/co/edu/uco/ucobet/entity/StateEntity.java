package co.edu.uco.ucobet.entity;

import java.util.UUID;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;

public class StateEntity extends DomainEntity{
	
	private String name;
	private CountryEntity country;
	
	public StateEntity() {
		super(UUIDHelper.getDefault());
		setName(TextHelper.EMPTY);
	}
	
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	@Override
	public void setId(final UUID id) {
		super.setId(id);
	}
	
	@Override
	public  UUID getId() {
		return super.getId();
	}
	
	public CountryEntity getCountry() {
		return country;
	}
	
	public void setCountry(final CountryEntity country) {
		this.country = ObjectHelper.getDefault(country, new CountryEntity());
	}	

}
