package quadystar.employee.service;


import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import quadystar.employee.DTO.EmployeeDTORequest;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.supportClass.Result;

public interface EmployeeService {

	public String createNewEmployee(EmployeeDTORequest employeeDTORequest);
	public String createNewEmployeeBeanUnits(EmployeeDTORequest employeeDTORequest);
	
	List<EmployeeDTORequest> getEmployeeList();
	
	Result getEmployeeList(int pageNumber, int pageSize) throws MenuException;	
	
	
	

}
