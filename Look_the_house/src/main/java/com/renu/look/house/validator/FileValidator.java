package com.renu.look.house.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.renu.look.house.models.AddService;

public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		return AddService.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	AddService addService=(AddService) target;
		
	if (addService.getiFile()==null || addService.getiFile().getOriginalFilename().equals("")) {
		errors.rejectValue("iFile", null, "Please upload a file");
		return;
	}
	
	
	if (!(addService.getiFile().getContentType().equals("image/jpeg")||
			addService.getiFile().getContentType().equals("image/png"))||
					addService.getiFile().getContentType().equals("image/jpg")||
					addService.getiFile().getContentType().equals("image/gif")) {
						errors.rejectValue("iFile", null, "Please upload an image");
						return;		
		
	}
	}

}
