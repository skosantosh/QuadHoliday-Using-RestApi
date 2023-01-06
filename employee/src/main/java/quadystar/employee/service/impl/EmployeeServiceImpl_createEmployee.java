package quadystar.employee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import quadystar.employee.DAO.EmployeeDAO;
//import quadystar.employee.DTO.EmployeeDTO;
import quadystar.employee.DTO.EmployeeDTORequest;
import quadystar.employee.Entity.EmployeeEntity;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.service.EmployeeService;
import quadystar.employee.supportClass.Result;

@Service
@Transactional
public class EmployeeServiceImpl_createEmployee implements EmployeeService {

	private EmployeeDAO employeeDAO;

	public EmployeeServiceImpl_createEmployee(@Autowired EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	@Override
	public String createNewEmployee(EmployeeDTORequest employeeDTORequest) {
		EmployeeEntity employeeEntity = EmployeeEntity.builder().uniqueEmpID(UUID.randomUUID().toString())
				.employerName(employeeDTORequest.getEmployerName())
				.employeeImageURL(employeeDTORequest.getEmployeeImageURL()).build();

		employeeDAO.save(employeeEntity);

		/*
		 * return EmployeeDTO.builder() .employerName(employeeEntity.getEmployerName())
		 * .uniqueEmpID(employeeEntity.getUniqueEmpID())
		 * .employeeImageURL(employeeEntity.getEmployeeImageURL()) .build();
		 */

		return "Employee Saved";
	}

	@Override
	public String createNewEmployeeBeanUnits(EmployeeDTORequest employeeDTORequest) {
		EmployeeEntity employeeEntity = new EmployeeEntity();

		employeeEntity.setUniqueEmpID(UUID.randomUUID().toString());
		BeanUtils.copyProperties(employeeDTORequest, employeeEntity);
		employeeDAO.save(employeeEntity);

		return "Employee Saved";
	}

	@Override
	public Result getEmployeeList(int pageNumber, int pageSize) throws MenuException {
		if (pageNumber < 0) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Page nuner must be greater than zero");
		}
		if (pageSize <= 0) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Page size must be greater than one");
		}
		
		Page<EmployeeEntity> employeeEntity = employeeDAO.findAll(PageRequest.of(pageNumber, pageSize));
		List<EmployeeDTORequest> employeeDTORequests = new ArrayList<>();

		for (EmployeeEntity tempEmployees : employeeEntity.getContent()) {
			employeeDTORequests.add(EmployeeDTORequest.builder().employerName(tempEmployees.getEmployerName())
					.employeeImageURL(tempEmployees.getEmployeeImageURL()).uniqueEmpID(tempEmployees.getUniqueEmpID())
					.build());
		}

		return (Result) employeeDTORequests;

	}

	@Override
	public List<EmployeeDTORequest> getEmployeeList() {

		List<EmployeeEntity> employeeEntity = employeeDAO.findAll();
		List<EmployeeDTORequest> employeeDTORequests = new ArrayList<>();

		for (EmployeeEntity tempEmployees : employeeEntity) {
			employeeDTORequests.add(EmployeeDTORequest.builder().employerName(tempEmployees.getEmployerName())
					.employeeImageURL(tempEmployees.getEmployeeImageURL()).uniqueEmpID(tempEmployees.getUniqueEmpID())
					.build());
		}

		return employeeDTORequests;
	}

}
