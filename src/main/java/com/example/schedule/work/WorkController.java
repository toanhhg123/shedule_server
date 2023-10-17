package com.example.schedule.work;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/work")
public class WorkController {
	@Autowired
	WorkRepository workRepository;

	@GetMapping()
	@IsAdmin
	ResponseEntity<?> getAll() {
		List<WorkModel> planModels = workRepository.findAll();
		return ResponseEntity.ok(
				new ResponseObject("success", "query success", planModels));
	}

	@PostMapping("")
	@IsAdmin
	ResponseEntity<?> create(@Valid @RequestBody WorkDto workDto) {
		var work = WorkModel.builder()
				.planId(workDto.planId)
				.workingItem(workDto.wokingItem)
				.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(
				new ResponseObject("success", "query success", workRepository.insert(work)));
	}

	@IsAdmin
	@DeleteMapping("{id}")
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		workRepository.deleteById(id);

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", id));
	}
}
