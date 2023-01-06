package quadystar.employee.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewUserDTORequest01 {
	String name;
	String salary;
	String age;

}
