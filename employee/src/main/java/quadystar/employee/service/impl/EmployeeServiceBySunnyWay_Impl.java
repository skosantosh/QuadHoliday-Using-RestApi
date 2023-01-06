package quadystar.employee.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import quadystar.employee.DAO.EmployeeDAO;
import quadystar.employee.DTO.ConstantsSantosh;
import quadystar.employee.DTO.EmployeeDTO;
import quadystar.employee.DTO.EmployeeDTORequest;
import quadystar.employee.Entity.EmployeeEntity;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.service.EmployeeServiceBySunnyWay;

@Service
@Transactional
public class EmployeeServiceBySunnyWay_Impl implements EmployeeServiceBySunnyWay {
	private EmployeeDAO employeeDAO;

	public EmployeeServiceBySunnyWay_Impl(@Autowired EmployeeDAO employeeDAO) {
		super();
		this.employeeDAO = employeeDAO;
	}

	@Override
	public EmployeeDTO createEmplyee(EmployeeDTORequest employeeDTORequest) throws MenuException {
		EmployeeEntity employeeEntity = EmployeeEntity.builder().uniqueEmpID(UUID.randomUUID().toString())
				.employerName(employeeDTORequest.getEmployerName())
				.employeeImageURL(employeeDTORequest.getEmployeeImageURL()).build();
		employeeDAO.save(employeeEntity);

		EmployeeDTO employeeDTO = EmployeeDTO.builder().uniqueEmpID(employeeEntity.getUniqueEmpID())
				.employerName(employeeEntity.getEmployerName()).employeeImageURL(employeeEntity.getEmployeeImageURL())
				.build();

		return employeeDTO;
	}

	@Override
	public List<EmployeeDTORequest> getEmployee() throws JsonProcessingException {
		List<EmployeeEntity> employeeEntities = employeeDAO.findAll();
		List<EmployeeDTORequest> employeeDTORequests = new ArrayList<>();

		for (EmployeeEntity employeeEntity : employeeEntities) {
			employeeDTORequests.add(EmployeeDTORequest.builder().uniqueEmpID(employeeEntity.getUniqueEmpID())
					.employerName(employeeEntity.getEmployerName())
					.employeeImageURL(employeeEntity.getEmployeeImageURL()).build());

		}
		return employeeDTORequests;

	}

	// list by pagination
	@Override
	public List<EmployeeDTORequest> getEmployeeByPage(int pageNumber, int pageSize, String sortBy, String sortField)
			throws MenuException {
		// SQL upset Limit
		// EG: select * from <table> LIMIT 5 OFFSET 2 ; it means will get rows 3 through
		// 8. OFFSET 2 mean skip 2 rows.
		// spring boot JPA pagination

		// PageRequest pageWithTwoElements = PageRequest.of(pageNumber, pageSize);

		if (pageNumber < 0) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Page number must be greater than zero");
		}
		if (pageSize <= 0) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Page size must be greater than one");
		}

		
		if (!(sortBy.equalsIgnoreCase("asc") || sortBy.equalsIgnoreCase("ASC") || sortBy.equalsIgnoreCase("desc") ||
				sortBy.equalsIgnoreCase("DESC")	|| sortBy.isEmpty())) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Sort can be ASC OR DESC");
		}

		if (!(sortField.equals("employerName") || sortField.equals("uniqueEmpID")
				|| sortField.equals("employeeImageURL") || sortField.isEmpty())) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Field not found");
		}
		
		Page<EmployeeEntity> pageEmployeeEntity;
		if (sortBy.equals("desc") || sortBy.equals("DESC")) {
			pageEmployeeEntity = employeeDAO.findAll(PageRequest.of(pageNumber, pageSize,
					Sort.by(sortField).descending()));
		}else {
			pageEmployeeEntity = employeeDAO.findAll(PageRequest.of(pageNumber, pageSize,
					Sort.by(sortField).ascending()));
		}

		List<EmployeeDTORequest> employeeDTORequests = new ArrayList<>();
		for (EmployeeEntity tempEmployeeEntity : pageEmployeeEntity) {
			employeeDTORequests.add(EmployeeDTORequest.builder().uniqueEmpID(tempEmployeeEntity.getUniqueEmpID())
					.employerName(tempEmployeeEntity.getEmployerName())
					.employeeImageURL(tempEmployeeEntity.getEmployeeImageURL()).build());
		}
		return employeeDTORequests;
	}
	
	
	

	@Override
	public List<EmployeeDTORequest> getEmployeeByPage(int pageNumber, int pageSize) throws MenuException {
		// SQL upset Limit
		// EG: select * from <table> LIMIT 5 OFFSET 2 ; it means will get rows 3 through
		// 8. OFFSET 2 mean skip 2 rows.
		// spring boot JPA pagination

		// PageRequest pageWithTwoElements = PageRequest.of(pageNumber, pageSize);

		if (pageNumber < 0) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Page s number must be greater than zero");
		}
		if (pageSize <= 0) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Page s size must be greater than one");
		}

		Page<EmployeeEntity> pageEmployeeEntity = employeeDAO.findAll(PageRequest.of(pageNumber, pageSize));
		// or
		// Page<EmployeeEntity> pageEmployeeEntity =
		// employeeDAO.findAll(pageWithTwoElements);

		List<EmployeeDTORequest> employeeDTORequests = new ArrayList<>();
		for (EmployeeEntity tempEmployeeEntity : pageEmployeeEntity) {
			employeeDTORequests.add(EmployeeDTORequest.builder().uniqueEmpID(tempEmployeeEntity.getUniqueEmpID())
					.employerName(tempEmployeeEntity.getEmployerName())
					.employeeImageURL(tempEmployeeEntity.getEmployeeImageURL()).build());
		}
		return employeeDTORequests;
	}

}
