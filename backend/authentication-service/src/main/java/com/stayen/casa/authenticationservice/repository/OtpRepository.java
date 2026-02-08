package com.stayen.casa.authenticationservice.repository;

import com.stayen.casa.authenticationservice.entity.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepository extends MongoRepository<Otp, String> {

}
