package co.edu.uco.ucobet.dto;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;

public class StateDTO extends DomainDTO{
	
	private String  name;
	private CountryDTO country;
	
	public StateDTO() {
		super(UUIDHelper.getDefaultAssString());
		setName(TextHelper.EMPTY);
		setCountry(CountryDTO.create());
	}
	
	public static final  StateDTO create() {
		return new StateDTO();
	}
	public String getName() {
		return name;
	}

	public StateDTO setName(final String name) {
		this.name = TextHelper.applyTrim(name);
		return this;
	}
	
	public StateDTO setId(final String id) {
		super.setIdentifier(id);
		return this;
	}
	
	@Override
	public  String getId() {
		return super.getId();
	}
	
	public CountryDTO getCountry() {
		return country;
	}
	
	public StateDTO setCountry (final CountryDTO country) {
		this.country = ObjectHelper.getDefault(country, CountryDTO.create());
		return this;
	}

}
