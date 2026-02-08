package com.stayen.casa.userservice.repository;


import com.stayen.casa.userservice.entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.Optional;

public interface UserRepository extends MongoRepository<UserProfile, String> {
}
