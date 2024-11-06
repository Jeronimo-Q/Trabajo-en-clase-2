package co.edu.uco.ucobet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.crosscuting.exceptions.UcoApplicationException;
import co.edu.uco.ucobet.businesslogic.facade.city.impl.RegisterNewCityFacadeImpl;
import co.edu.uco.ucobet.controller.response.GeneratedResponse;
import co.edu.uco.ucobet.controller.response.concrete.CityResponse;
import co.edu.uco.ucobet.controller.response.concrete.GenericResponse;
import co.edu.uco.ucobet.crosscutting.exceptions.UcoBetException;
import co.edu.uco.ucobet.dto.CityDTO;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/v1/cities")
public final class CityController {
	
	@GetMapping("/dummy")
	public CityDTO getDummy() {
		return CityDTO.create();
	}
	
	@PostMapping
	public ResponseEntity<GenericResponse> create(@RequestBody CityDTO city) {
		var message = new ArrayList<String>();
		
		try {
			var registerNewCityFacade = new RegisterNewCityFacadeImpl();
			registerNewCityFacade.execute(city);
			message.add("La ciudad se registro de forma satisfactoria");
			
			return GeneratedResponse.generateSuccessResponse(message);
		} catch (final UcoBetException exception) {
			message.add(exception.getUserMessage());
			exception.printStackTrace();
			
			return GeneratedResponse.generateSuccessResponse(message);
		}catch (final UcoApplicationException exception) {
			message.add(exception.getUserMessage());
			exception.printStackTrace();
			
			return GeneratedResponse.generateFailedResponse(message);
		}catch (final Exception exception) {
			message.add(
					"Se ha presentado un problema inesperado tratando de llevar a cabo el registro de la ciudad de forma satisfactoria");
		}
		
		message.add("La ciudad se agrego de forma satisfactoria");
		
		return GeneratedResponse.generateFailedResponse(message);
		
		
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<GenericResponse> update(@PathVariable String id,@RequestBody CityDTO city) {
		var message = new ArrayList<String>();
		
		message.add("La ciudad se actualizo de forma satisfactoria");
		return GeneratedResponse.generateFailedResponse(message);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<GenericResponse> delete(@PathVariable String id) {
		var message = new ArrayList<String>();
		
		message.add("La ciudad se elimino de forma satisfactoria");
		return GeneratedResponse.generateSuccessResponse(message);
	}
	
	@GetMapping
	public ResponseEntity<CityResponse> retrieveAll() {
		CityResponse responseWithData = new CityResponse();
		
		var message = new ArrayList<String>();
		message.add("La ciudades se consultaron de forma satisfactoria");
		
		var list = new ArrayList<CityDTO>();
		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());
		list.add(getDummy());

		
		responseWithData.setData(list);
		responseWithData.setMessages(message);
		
		return ((new GeneratedResponse<CityResponse>()).generateSuccessResponseWithData(responseWithData));


	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CityResponse>  retrieveById(@PathVariable String id) {
		CityResponse responseWithData = new CityResponse();
		
		var messages = new ArrayList<String>();
		messages.add("La ciudad se consultó de forma satisfactoria");
		
		var list = new ArrayList<CityDTO>();
		list.add(getDummy());
		
		responseWithData.setData(list);
		responseWithData.setMessages(messages);
		return ((new GeneratedResponse<CityResponse>()).generateSuccessResponseWithData(responseWithData));

	}
	
}
