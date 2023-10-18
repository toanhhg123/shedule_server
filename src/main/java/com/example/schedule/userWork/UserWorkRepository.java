package com.example.schedule.userWork;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserWorkRepository extends MongoRepository<UserWorkModel, String> {
	
}
