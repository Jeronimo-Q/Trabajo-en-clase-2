package co.edu.uco.ucobet.domain;

import java.util.UUID;

import co.edu.uco.crosscuting.helpers.ObjectHelper;
import co.edu.uco.crosscuting.helpers.TextHelper;
import co.edu.uco.crosscuting.helpers.UUIDHelper;

public class StateDomain extends Domain{
	
	private String name;
	private CountryDomain country;
	
	private StateDomain(final UUID id, final String name,final CountryDomain country ) {
		super(id);
		setName(name);
		setCountry(country);
	}
	
	public static final StateDomain create (final UUID id , final String name,final CountryDomain country) {
		return new StateDomain(id, name,country);
	}
	
	static final StateDomain create () {
		return new StateDomain(UUIDHelper.getDefault(),TextHelper.EMPTY, CountryDomain.create());
	}
	
	public String getName() {
		return name;
	}

	private void setName(final String name) {
		this.name = TextHelper.applyTrim(name);
	}
	
	@Override
	public  UUID getId() {
		return super.getId();
	}

	private CountryDomain getCountry() {
		return country;
	}

	private void setCountry(final CountryDomain country) {
		this.country = ObjectHelper.getDefault(country, CountryDomain.create());
	}
	

}
