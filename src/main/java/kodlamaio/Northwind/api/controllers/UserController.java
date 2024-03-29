package kodlamaio.Northwind.api.controllers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.Northwind.business.abstracts.UserService;
import kodlamaio.Northwind.core.entities.User;
import kodlamaio.Northwind.core.utilities.results.ErrorDataResult;
import kodlamaio.Northwind.core.utilities.results.Result;

@RestController
@RequestMapping(value="/api/users")
public class UserController {
	
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<?> Add(@Valid @RequestBody User user)
	{
		return ResponseEntity.ok(this.userService.add(user));
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(MethodArgumentNotValidException exceptions){
		Map<String,String> validationErrors = new HashMap<String, String>();
		for (FieldError fieldError :exceptions.getBindingResult().getFieldErrors())
		{
			validationErrors.put(fieldError.getField() ,fieldError.getDefaultMessage());
			
		}
		ErrorDataResult<Object> errors= new ErrorDataResult<Object>();
			return new ErrorDataResult<Object>(validationErrors, "Doğrulama Hataları");
		
	}
}
