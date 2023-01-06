package quadystar.employee.supportClass;

import lombok.Data;

import quadystar.employee.DTO.CreateNewUserDTOforResponse02;

@Data
public class CreateNewUserResponse03 {
	String status;
	CreateNewUserDTOforResponse02 createNewUserDTOforResponse;
	UserSupport support;
}
