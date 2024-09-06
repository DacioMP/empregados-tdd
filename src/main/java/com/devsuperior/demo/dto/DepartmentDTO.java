package com.devsuperior.demo.dto;

import com.devsuperior.demo.entities.Department;

public class DepartmentDTO {
	
	private Long id;
	private String name;
	
	public DepartmentDTO() {}
	
	public DepartmentDTO(Department entity) {
		this.id = entity.getId();
		this.name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
