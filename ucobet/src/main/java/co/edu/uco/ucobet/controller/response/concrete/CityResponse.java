package co.edu.uco.ucobet.controller.response.concrete;

import java.util.List;

import co.edu.uco.ucobet.controller.response.Response;
import co.edu.uco.ucobet.controller.response.ResponseWithData;
import co.edu.uco.ucobet.dto.CityDTO;

public class CityResponse extends ResponseWithData<CityDTO>{
	
	
	public static final Response build(final List<String> messages,  final List<CityDTO> data) {
		var response = new CityResponse();
		response.setMessages(messages);
		response.setData(data);
		return response;
	}

}
