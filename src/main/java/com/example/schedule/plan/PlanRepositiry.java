package com.example.schedule.plan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PlanRepositiry extends MongoRepository<PlanModel, String> {
	@Query("{organizationalId:'?0'}")
	List<PlanModel> findByOrganizationalId(String organizationalId);

	@Query("{title: '?0'}")
	List<PlanModel> findByTitle(String title);

	@Query("{companionUnitId:'?0'}")
	List<PlanModel> findByCompanionUnitId(String companionUnitId);
}
