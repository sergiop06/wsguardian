package com.wsguardian.orchestrator.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.wsguardian.orchestrator.logic.OrchestratorLogic;

@RestController
public class OrchestratorResource {
	
	@Autowired
	private OrchestratorLogic logic;
	
	@RequestMapping(value = "/orquestador", method = RequestMethod.POST)
	public ResponseEntity<Object> index(@RequestBody String fullRequest) throws JsonProcessingException {
		//this.logger.info("An INFO Message -> Header Params: " + allHeadersParams);
		//this.logger.info("An INFO Message -> Body: " + body);
			
		logic.procesarPeticion(fullRequest);
		
		return ResponseEntity.ok().body(null);
		// return this.realizarPeticiones(allHeadersParams, body);
	}
	
	
	
}
