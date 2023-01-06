package quadystar.employee.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User01DTO {
	private int id;
	private String email;
	private String first_name;
	private String last_name;
	private String avatar;
}
