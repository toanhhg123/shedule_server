package com.example.schedule.plan;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanRepositiry extends MongoRepository<PlanModel, String> {

}
