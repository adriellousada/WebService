package com.aulapds.code.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.aulapds.code.dto.UserDTO;
import com.aulapds.code.entities.User;
import com.aulapds.code.repositories.UserRepository;
import com.aulapds.code.resources.exceptions.FieldMessage;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private UserRepository userRepository;


	@Override
	public void initialize(UserUpdateValid ann) {
	}

	@Override
	public boolean isValid(UserDTO dto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		User user = userRepository.findByEmail(dto.getEmail());
		if(user !=null && user.getId() != uriId) {
			list.add(new FieldMessage("email", "Email already taken"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}