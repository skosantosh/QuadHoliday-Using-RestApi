package quadystar.employee.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import quadystar.employee.DTO.EmployeeDTORequest;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.service.EmployeeService;
import quadystar.employee.supportClass.Result;

@RestController
@RequestMapping("emp/api")
public class EmplyeeController {

	private EmployeeService employeeService;

	public EmplyeeController(@Autowired EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/postemp")
	public String createNewEmployee(@RequestBody EmployeeDTORequest employeeDTORequest) {
		String message = employeeService.createNewEmployee(employeeDTORequest);
		return message;
	}

	@GetMapping("/getemp")
	public List<EmployeeDTORequest> getAllEmployee() {
		return employeeService.getEmployeeList();
	}

	/*
	 * @GetMapping(value = "/getpage") public List<EmployeeDTORequest>
	 * getAllEmpByPage(@RequestParam int pageNumber, @RequestParam int pageSize) {
	 * try { return employeeService.getEmployeeList(pageNumber, pageSize); } catch
	 * (MenuException e) { return null; }
	 * 
	 * }
	 */

	@GetMapping(value = "/getpage")
	public Result getAllEmpByPage01(@RequestParam int pageNumber, @RequestParam int pageSize) throws MenuException {
		Result res = new Result();
		try {
			return res.getEmployeeService().getEmployeeList(pageNumber, pageSize);
		} catch (MenuException e) {
			//return res.getError_code();
			return null;
		}
		

	}

}
