package com.devsuperior.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.services.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	public EmployeeService service;
	
	@GetMapping
	public ResponseEntity<Page<EmployeeDTO>> findAll(
			@PageableDefault(sort = {"name"}, direction = Sort.Direction.ASC) Pageable pageable
		) {
		Page<EmployeeDTO> employeesDTO = service.findAllPaged(pageable);
		return ResponseEntity.ok().body(employeesDTO);
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> insert(@RequestBody EmployeeDTO dto) {
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequestUri()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}
}
