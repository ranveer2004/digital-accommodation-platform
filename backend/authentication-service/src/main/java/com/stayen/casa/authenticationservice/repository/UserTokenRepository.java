package com.stayen.casa.authenticationservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.stayen.casa.authenticationservice.entity.DeviceToken;
import com.stayen.casa.authenticationservice.entity.UserToken;

public interface UserTokenRepository extends MongoRepository<UserToken, String> {

	Optional<UserToken> findByUid(String uid);
	
}
