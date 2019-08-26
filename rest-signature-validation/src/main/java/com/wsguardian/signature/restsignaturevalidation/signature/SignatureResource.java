package com.wsguardian.signature.restsignaturevalidation.signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignatureResource {
	
	@Autowired
	private SignatureService service;
	
	@PostMapping("/signature")
	public boolean validateSignature(@RequestBody String envelope) {
		
		System.out.println("Envelope recibido:: "+envelope);
		return service.validateSignature(envelope);
	}
	
}
