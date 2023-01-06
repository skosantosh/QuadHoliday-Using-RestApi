package quadystar.employee.supportClass;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;
import quadystar.employee.service.EmployeeService;

@Data
@NoArgsConstructor
public class Result {
	private String error_code;
	private HttpStatus error_message;
	private EmployeeService employeeService;
}
