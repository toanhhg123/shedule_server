package com.example.schedule.plan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.annotation.IsAdmin;
import com.example.schedule.core.ResponseObject;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/plan")
public class PlanController {
	@Autowired
	PlanRepositiry planRepositiry;

	@GetMapping()
	@IsAdmin
	ResponseEntity<?> getAll() {
		List<PlanModel> planModels = planRepositiry.findAll();

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", planModels));
	}

	@PostMapping("")
	@IsAdmin
	ResponseEntity<?> create(@Valid @RequestBody PlanDto planDto) {
		var planModel = PlanModel.builder()
				.title(planDto.title)
				.content(planDto.content)
				.organizationalId(planDto.organizationalId)
				.companionUnitId(planDto.companionUnitId)
				.timeStart(planDto.timeStart)
				.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseObject("success", "query success", planRepositiry.insert(planModel)));
	}

	@IsAdmin
	@PatchMapping("{id}")
	ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody PlanDto planDto) {

		var plan = planRepositiry.findById(id);

		if (!plan.isPresent()) {
			throw new Error("not found plan with id: " + id);
		}

		PlanModel planUpdate = plan.get();

		planUpdate.setCompanionUnitId(planDto.getCompanionUnitId());
		planUpdate.setContent(planDto.getContent());
		planUpdate.setTitle(planDto.getTitle());
		planUpdate.setOrganizationalId(planDto.getOrganizationalId());
		planUpdate.setTimeStart(planDto.getTimeStart());

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", planRepositiry.save(planUpdate)));
	}

	@IsAdmin
	@DeleteMapping("{id}")
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		planRepositiry.deleteById(id);

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", id));
	}

}
