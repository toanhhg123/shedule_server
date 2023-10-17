package com.example.schedule.organizationalUnit;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrganizationalRepository extends MongoRepository<OrganizationalUnitModel, String> {
	@Query("{name:'?0'}")
	Optional<OrganizationalUnitModel> findItemByName(String name);
}
