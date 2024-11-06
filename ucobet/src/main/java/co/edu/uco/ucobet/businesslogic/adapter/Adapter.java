package co.edu.uco.ucobet.businesslogic.adapter;

import java.util.List;

public interface Adapter <T,D>{
	
	T adaptSource(D data);
	
	D adaptTarget(T data);
	
	List<T> adaptSource(List<D> data);
	
	List<D> adaptTarjet(List<T> data);

}
