package com.wsguardian.orchestrator.logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wsguardian.orchestrator.entities.SoapRequestEntity;

@Component
public class OrchestratorLogic {
	/*
	 * Elementos
	 */
	private Logger logger = LoggerFactory.getLogger(OrchestratorLogic.class);
	private final Map<String, String> mapPeticionesBackend = new HashMap<>();
	private final String backendInvocadorServicios=System.getenv().getOrDefault("INVOCADOR_SERVICIOS", "localhost:8100/invocador_servicios").toString();;
	
	public OrchestratorLogic() {
		this.mapPeticionesBackend.put("politica_calendario", System.getenv().getOrDefault("POLITICA_CALENDARIO", "localhost:8091/politica_calendario").toString());
		this.mapPeticionesBackend.put("politica_firmas_digitales", System.getenv().getOrDefault("POLITICA_FIRMAS", "localhost:8092/politica_firmas_digitales").toString());
	}
	
	//procesar peticion
	public ResponseEntity<Object> procesarPeticion(String requestBase64){
		
		byte[] base64Decoded = DatatypeConverter.parseBase64Binary(requestBase64);
		String jsonString = new String(base64Decoded); 
		
		ObjectMapper mapper = new ObjectMapper();
		SoapRequestEntity requestEntity = new SoapRequestEntity();
		
		try {
			requestEntity=mapper.readValue(jsonString, SoapRequestEntity.class);
			System.out.println("desserializada= \n"+requestEntity.toString()+"\n");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResponseEntity<Object>responsePoliticasIda = this.realizarPeticionesPoliticas(requestEntity);
		
		return null;
	}
	
	//realizar las peticiones a cada una de las politicas para validacion 
	public ResponseEntity<Object> realizarPeticionesPoliticas(SoapRequestEntity requestEntity){
		
		
		return null;
	}
}
