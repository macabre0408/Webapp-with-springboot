package com.openclassrooms.webapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;

import lombok.Data;

@Data
@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeProxy employeeProxy;
	
	public  Iterable<Employee> getEmployees(){
		return employeeProxy.getEmployees();
	}
	
	public Employee getEmployee(final Long id) {
		return employeeProxy.getEmployee(id);
	}
	
	public void deleteEmployee(final Long id) {
		employeeProxy.DeleteEmploy(id);
	}
	
	public Employee saveEmployee(Employee e) {
		e.setLastName(e.getLastName().toUpperCase());
		
		if(e.getId()==null) {
			return employeeProxy.createEmployee(e);
		}
		else {
			return employeeProxy.updateEmployee(e);
		}
	}
}
