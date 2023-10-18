package com.example.schedule.companionUnit;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/api/companionUnit")
public class CompanionUnitController {

	@Autowired
	CompanionUnitRepository companionUnitRepository;

	@IsAdmin
	@GetMapping("")
	ResponseEntity<?> getAll() {
		var companionUnits = companionUnitRepository.findAll();

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", companionUnits));
	}

	@IsAdmin
	@PostMapping("")
	ResponseEntity<?> create(@RequestBody CompanionUnitDto companionUnitDto) {

		var compaionUnit = CompanionUnitModel.builder().name(companionUnitDto.getName()).build();

		var companionUnits = companionUnitRepository.insert(compaionUnit);

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", companionUnits));
	}

	@IsAdmin
	@PatchMapping("{id}")
	ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody CompanionUnitDto companionUnitDto) {

		var companionUnits = companionUnitRepository.findById(id);

		if (!companionUnits.isPresent()) {
			throw new Error("not found companionUnits with id: " + id);
		}

		CompanionUnitModel companionUnitModel = companionUnits.get();

		companionUnitModel.setName(companionUnitDto.getName());

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", companionUnitRepository.save(companionUnitModel)));
	}

	@IsAdmin
	@DeleteMapping("{id}")
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		companionUnitRepository.deleteById(id);

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", id));
	}
}
