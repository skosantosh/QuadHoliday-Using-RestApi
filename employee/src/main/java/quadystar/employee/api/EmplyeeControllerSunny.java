package quadystar.employee.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import quadystar.employee.DTO.ConstantsSantosh;
import quadystar.employee.DTO.EmployeeDTORequest;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.service.EmployeeServiceBySunnyWay;

@RestController
@RequestMapping("emp/apis")
public class EmplyeeControllerSunny {

	private EmployeeServiceBySunnyWay employeeService;

	public EmplyeeControllerSunny(@Autowired EmployeeServiceBySunnyWay employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping(value = "/postemp")
	public ResponseEntity<?> createNewEmployee(@RequestBody EmployeeDTORequest employeeDTORequest) throws JsonProcessingException {
		return ResponseEntity.ok(employeeService.createEmplyee(employeeDTORequest));

	}
	
	@GetMapping(value = "getemp")
	public ResponseEntity<?> getAllEmployee() throws JsonProcessingException{
		return ResponseEntity.ok(employeeService.getEmployee());		
	}
	
	@GetMapping(value = "getpage")	
	public ResponseEntity<?> getAllByPage(@RequestParam int pageNumber, @RequestParam int pageSize){
		try {
			return ResponseEntity.ok(employeeService.getEmployeeByPage(pageNumber, pageSize));
		} catch (MenuException e) {
			return ResponseEntity.ok(e.getMessage());
		}
				
	}
	//http://localhost:1000/emp/apis/getpagesort?pageNumber=0&pageSize=4&sortBy=asc&sortField=employerName
	@GetMapping(value = "getpagesort")	
	public ResponseEntity<?> getAllByPage(@RequestParam int pageNumber, @RequestParam int pageSize, String sortBy, String sortField){
		try {
			return ResponseEntity.ok(employeeService.getEmployeeByPage(pageNumber, pageSize,sortBy,sortField));
		} catch (MenuException e) {
			return ResponseEntity.ok(e.getMessage());
		}
				
	}
	
	

	

}
