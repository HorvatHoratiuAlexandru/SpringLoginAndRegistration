package com.horatiuhorvat.springLogInRegistrationProject.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.horatiuhorvat.springLogInRegistrationProject.persistence.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
	
	public UserEntity findByEmail(String email);

}
