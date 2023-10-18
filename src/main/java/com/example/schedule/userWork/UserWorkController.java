package com.example.schedule.userWork;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.schedule.annotation.IsAdmin;
import com.example.schedule.core.ResponseObject;
import com.example.schedule.plan.PlanDto;
import com.example.schedule.plan.PlanModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/apt/userWork")
public class UserWorkController {
	@Autowired
	UserWorkRepository userWorkRepository;

	@GetMapping()
	@IsAdmin
	ResponseEntity<?> getAll() {
		List<?> planModels = userWorkRepository.findAll();

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", planModels));
	}

	@PostMapping("")
	@IsAdmin
	ResponseEntity<?> create(@Valid @RequestBody UserWorkDto userWorkDto) {
		var data = UserWorkModel.builder()
				.userId(userWorkDto.userId)
				.confirm(userWorkDto.confirm)
				.status(userWorkDto.status)
				.planId(userWorkDto.planId)

				.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseObject("success", "query success", userWorkRepository.insert(data)));
	}

	@IsAdmin
	@DeleteMapping("{id}")
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		userWorkRepository.deleteById(id);

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", id));
	}

}
