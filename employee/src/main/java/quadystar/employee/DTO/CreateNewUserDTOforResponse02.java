package quadystar.employee.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateNewUserDTOforResponse02 {
	int id;
	String name;
	String salary;
	String age;

}
