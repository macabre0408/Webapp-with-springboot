package com.openclassrooms.webapp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmployeeProxy {
	
	@Autowired
	private CustomProperties prop;
	
	
	/*employs's list*/
	public Iterable<Employee> getEmployees(){
		String baseApiUrl = prop.getApiUrl();
		
		String getEmployeesUrl = baseApiUrl + "/employees";
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
				getEmployeesUrl,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<Iterable<Employee>>() {
				}
				);
		
		return response.getBody();
 	}
	
	//get an employ
	
	public Employee getEmployee(Long id) {
		String baseApiUrl = prop.getApiUrl();
		String getEmployeeUrl = baseApiUrl + "/employee/"+id;
		
		RestTemplate restTemplate = new RestTemplate();
		 
		HttpEntity<Long> request = new HttpEntity<Long>(id);
		
		ResponseEntity<Employee> response = restTemplate.exchange(
				getEmployeeUrl,
				HttpMethod.POST,
				request,
				Employee.class
				);
		return response.getBody();
	}
	
	//delete an employ
	
	public void DeleteEmploy(Long id) {
		
		String baseApiUrl = prop.getApiUrl();
		
		String deleteEmployeeUrl = baseApiUrl + "/employee/"+id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.exchange(
				deleteEmployeeUrl,
				HttpMethod.DELETE,
				null,
				Void.class
				);
	}
	
	//create an employ
	
	public Employee createEmployee(Employee e) {
		
		String baseApiUrl = prop.getApiUrl();
		String createEmployeeUrl = baseApiUrl+"/create/employee";
		
		HttpEntity<Employee> request = new HttpEntity<Employee>(e);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Employee> response = restTemplate.exchange(
				createEmployeeUrl,
				HttpMethod.POST,
				request,
				Employee.class
				);
		return response.getBody();
	}
	
	//update an employee
	public Employee updateEmployee(Employee e) {
		String baseApiUrl = prop.getApiUrl();
		String updateEmployeeUrl =baseApiUrl + "/employee/"+e.getId();
		
		
		HttpEntity<Employee> request = new HttpEntity<Employee>(e);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Employee> response = restTemplate.exchange(
				updateEmployeeUrl,
				HttpMethod.PUT,
				request,
				Employee.class
				);
		
		return response.getBody();
		
		}
}
