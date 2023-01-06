package quadystar.employee.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import quadystar.employee.DTO.CreateNewUserDTORequest01;
import quadystar.employee.DTO.CreateNewUserDTOforResponse02;
import quadystar.employee.DTO.User01DTO;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.config.AppConfig;
import quadystar.employee.service.UserService;
import quadystar.employee.supportClass.CreateNewUserResponse03;
import quadystar.employee.supportClass.User02Response;

@Service
public class UserServiceImpl implements UserService {

	private AppConfig appConfig;
	/*
	 * private RestTemplate restTemplate; private ObjectMapper objectMapper;
	 */

	public UserServiceImpl(@Autowired AppConfig appConfig) {
		this.appConfig = appConfig;
	}

	/*
	 * public UserServiceImpl(@Autowired RestTemplate restTemplate, @Autowired
	 * ObjectMapper objectMapper) { this.restTemplate = restTemplate;
	 * this.objectMapper = objectMapper; }
	 */

	@Override
	public List<User01DTO> getAllJsData() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	@Override
	public User01DTO findUserByEmail(String emailId) throws MenuException, JsonProcessingException {
		if (emailId.equals("")) {
			throw new MenuException(HttpStatus.BAD_REQUEST, "Email not found.");
		}
		User01DTO userDTO = null;
		//https://reqres.in/api/users?page=1
		String response = appConfig.restTemplate().getForObject("https://reqres.in/api/users?page=1", String.class);
		User02Response userResponse = appConfig.objectMapper().readValue(response, User02Response.class);
		
		for (int totalPage = 1; totalPage <= userResponse.getPage(); totalPage++) {

			response = appConfig.restTemplate().getForObject("https://reqres.in/api/users?page=" + totalPage,
					String.class);
			userResponse = appConfig.objectMapper().readValue(response, User02Response.class);

			for (User01DTO tempUser : userResponse.getData()) {
				if (tempUser.getEmail().equals(emailId)) {
					//return tempUser;
					userDTO = tempUser;
				}
			}
		}
		
		//throw new MenuException(HttpStatus.BAD_REQUEST, "User Can not found");
		return userDTO;
	}
	
	
	

	@Override
	public CreateNewUserDTOforResponse02 createUser(CreateNewUserDTORequest01 createNewUserDTORequest)
			throws MenuException, JsonProcessingException {
		if (createNewUserDTORequest.getName().equals("") || createNewUserDTORequest.getAge().equals("")
				|| createNewUserDTORequest.getSalary().equals("") || createNewUserDTORequest.getName().equals(null)
				|| createNewUserDTORequest.getAge().equals(null) || createNewUserDTORequest.getSalary().equals(null)) {

			if (createNewUserDTORequest.getName().length() <= 0) {
				throw new MenuException(HttpStatus.BAD_REQUEST, "Name can not be empity");
			} else if (createNewUserDTORequest.getAge().length() <= 0) {
				throw new MenuException(HttpStatus.BAD_REQUEST, "Age can not be empity");
			} else if (createNewUserDTORequest.getSalary().length() <= 0) {
				throw new MenuException(HttpStatus.BAD_REQUEST, "Salary can not be empity");
			} else {
				throw new MenuException(HttpStatus.BAD_REQUEST, "Null not applicable");
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		// HttpEntity<String> entity = new HttpEntity<String>("parameters",headers);
		HttpEntity<String> entity = new HttpEntity<String>(
				appConfig.objectMapper().writeValueAsString(createNewUserDTORequest), headers);
		
		ResponseEntity<CreateNewUserResponse03> resonse = appConfig.restTemplate().exchange(
				"https://dummy.restapiexample.com/api/v1/create", HttpMethod.POST, entity, CreateNewUserResponse03.class);

		return resonse.getBody().getCreateNewUserDTOforResponse();
	}

}
