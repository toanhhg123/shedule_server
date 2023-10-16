package com.example.schedule.role;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<RoleModel, String> {
	@Query("{name: '?0'}")
	Optional<RoleModel> findByName(ERole name);
}
