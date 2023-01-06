package quadystar.employee.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import quadystar.employee.DTO.CreateNewUserDTORequest01;
import quadystar.employee.DTO.User01DTO;
import quadystar.employee.Exception.MenuException;
import quadystar.employee.service.UserService;

@RestController
@RequestMapping("user/api")
public class UserController {

	private UserService userService;

	public UserController(@Autowired UserService userService) {

		this.userService = userService;
	}

	// it mean "defaultValue = "",required = false" it is not require defaultValue
	// is "" value
	@GetMapping(value = "getByEm")
	ResponseEntity<?> findUserByEmail(@RequestParam(defaultValue = "", required = false) String emailId)
			throws MenuException, JsonProcessingException {

		try {
			return ResponseEntity.ok(userService.findUserByEmail(emailId));
		} catch (MenuException e) {
			return ResponseEntity.ok(e.getMessage());
		} catch (JsonProcessingException e) {
			return ResponseEntity.ok("Invalid Email " + e.getMessage());
		}

	}

	@PostMapping(value = "postByAny")
	ResponseEntity<?> createNewUser(@RequestBody CreateNewUserDTORequest01 createNewUserDTORequest)
			throws MenuException, JsonProcessingException {
		try {
			return ResponseEntity.ok(userService.createUser(createNewUserDTORequest));
		} catch (MenuException e) {
			return ResponseEntity.ok(e.getMessage());
		} catch (JsonProcessingException e) {
			return ResponseEntity.ok("Invalide " + e.getMessage());
		}

	}

}
