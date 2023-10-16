package com.example.schedule.companionUnit;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CompanionUnitRepository extends MongoRepository<CompanionUnitModel, String> {

	@Query("{name:'?0'}")
	CompanionUnitModel findItemByName(String name);

}