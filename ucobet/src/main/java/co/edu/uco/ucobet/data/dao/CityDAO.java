package co.edu.uco.ucobet.data.dao;

import java.util.UUID;

import co.edu.uco.ucobet.entity.CityEntity;


public interface CityDAO extends RetrieveDAO<CityEntity,UUID>, CreateDAO<CityEntity>,DeleteDAO<UUID>,UpdateDAO<CityEntity>{

}
