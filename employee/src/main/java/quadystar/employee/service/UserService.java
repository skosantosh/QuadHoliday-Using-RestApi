package quadystar.employee.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import quadystar.employee.DTO.CreateNewUserDTORequest01;
import quadystar.employee.DTO.CreateNewUserDTOforResponse02;
import quadystar.employee.DTO.User01DTO;
import quadystar.employee.Exception.MenuException;

public interface UserService {
	
public List<User01DTO> getAllJsData() throws MenuException, JsonProcessingException;

public User01DTO findUserByEmail(String emailId)throws MenuException, JsonProcessingException;

public CreateNewUserDTOforResponse02 createUser(CreateNewUserDTORequest01 createNewUser) throws MenuException,JsonProcessingException;

}
