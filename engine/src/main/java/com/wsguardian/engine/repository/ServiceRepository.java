package com.wsguardian.engine.repository;

import javax.transaction.Transactional;
import com.wsguardian.engine.entities.Wsg_service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
@Transactional
public interface ServiceRepository extends CrudRepository<Wsg_service, Integer>{
	
}
