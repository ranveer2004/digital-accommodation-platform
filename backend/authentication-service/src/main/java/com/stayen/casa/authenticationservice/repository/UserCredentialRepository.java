package com.stayen.casa.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stayen.casa.authenticationservice.entity.UserCredential;

public interface UserCredentialRepository extends MongoRepository<UserCredential, String> {
	
	Optional<UserCredential> findByEmail(String email);
	
}
