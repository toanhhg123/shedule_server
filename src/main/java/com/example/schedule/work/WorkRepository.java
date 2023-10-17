package com.example.schedule.work;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkRepository extends MongoRepository<WorkModel, String> {

}
