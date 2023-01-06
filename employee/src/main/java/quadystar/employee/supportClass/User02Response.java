package quadystar.employee.supportClass;

import java.util.List;

import lombok.Data;
import quadystar.employee.DTO.User01DTO;
@Data
public class User02Response {
	int page, per_page, total, total_pages;
	List<User01DTO> data;
	UserSupport support;
}
