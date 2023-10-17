package com.example.schedule.organizationalUnit;

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
import com.example.schedule.companionUnit.CompanionUnitDto;
import com.example.schedule.companionUnit.CompanionUnitModel;
import com.example.schedule.core.ResponseObject;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/organizational")
public class OrganizationalController {
	@Autowired
	OrganizationalRepository organizationalRepository;

	@IsAdmin
	@GetMapping("")
	ResponseEntity<?> getAll() {
		var organizationals = organizationalRepository.findAll();

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", organizationals));
	}

	@IsAdmin
	@PostMapping("")
	ResponseEntity<?> create(@RequestBody OrganizationalUnitDto organizationalUnitDto) {

		OrganizationalUnitModel organizational = OrganizationalUnitModel
				.builder()
				.name(organizationalUnitDto.getName())
				.build();

		var companionUnits = organizationalRepository.insert(organizational);

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", companionUnits));
	}

	@IsAdmin
	@PatchMapping("{id}")
	ResponseEntity<?> update(@PathVariable("id") String id,
			@Valid @RequestBody OrganizationalUnitDto organizationalUnitDto) {

		var organizational = organizationalRepository.findById(id);

		if (!organizational.isPresent()) {
			throw new Error("not found organizational with id: " + id);
		}

		OrganizationalUnitModel organizationalUnitModel = organizational.get();

		organizationalUnitModel.setName(organizationalUnitDto.getName());

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", organizationalRepository.save(organizationalUnitModel)));
	}

	@IsAdmin
	@DeleteMapping("{id}")
	ResponseEntity<?> delete(@PathVariable("id") String id) {
		organizationalRepository.deleteById(id);

		return ResponseEntity.ok(
				new ResponseObject("success", "query success", id));
	}
}
