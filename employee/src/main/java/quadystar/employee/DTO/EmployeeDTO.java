package quadystar.employee.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDTO {	
	private String employerName;
	private String uniqueEmpID;
	private String employeeImageURL;
}
