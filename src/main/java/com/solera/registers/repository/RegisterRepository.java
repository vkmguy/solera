package com.solera.registers.repository;

import com.solera.registers.entity.Register;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RegisterRepository extends CrudRepository<Register, Integer> {
   Register findByName(String name);
}
