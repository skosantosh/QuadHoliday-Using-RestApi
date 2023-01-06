package quadystar.employee.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import quadystar.employee.DTO.ConstantsSantosh.SORT_ORDER;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.DTO.EmployeeDTO;
import quadystar.employee.DTO.EmployeeDTORequest;

public interface EmployeeServiceBySunnyWay {
	EmployeeDTO createEmplyee(EmployeeDTORequest employeeDTORequest) throws MenuException;

	List<EmployeeDTORequest> getEmployee() throws JsonProcessingException;
	
	List<EmployeeDTORequest>getEmployeeByPage(int pageNumber, int pageSize) throws MenuException;
	
	List<EmployeeDTORequest>getEmployeeByPage(int pageNumber, int pageSize, String sortBy, String sortField) throws MenuException;
}
