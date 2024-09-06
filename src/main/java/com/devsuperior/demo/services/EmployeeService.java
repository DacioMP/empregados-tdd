package com.devsuperior.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EmployeeDTO;
import com.devsuperior.demo.entities.Department;
import com.devsuperior.demo.entities.Employee;
import com.devsuperior.demo.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	public EmployeeRepository repository;
		
	@Transactional(readOnly = true)
	public Page<EmployeeDTO> findAllPaged(Pageable pageable) {
		Page<Employee> page = repository.findAll(pageable);
		return page.map(EmployeeDTO::new);
	}
	
	@Transactional
	public EmployeeDTO insert(EmployeeDTO dto) {
		Employee entity = new Employee();
		fromDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new EmployeeDTO(entity);
	}
	
	private void fromDtoToEntity(EmployeeDTO dto, Employee entity) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setDepartment(new Department(dto.getDepartmentId(), null));
	}
}
