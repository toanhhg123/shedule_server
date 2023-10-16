package com.example.schedule.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, String> {
	@Query("{email: '?0'}")
	Optional<UserModel> findByEmail(String email);
}
