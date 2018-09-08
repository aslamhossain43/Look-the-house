package com.renu.look.house.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.renu.look.house.models.AddService;

public class VideoFileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return AddService.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AddService addService = (AddService) target;

		if (addService.getvFile() == null || addService.getvFile().getOriginalFilename().equals("")) {
			errors.rejectValue("vFile", null, "Please upload a file");
			return;
		}

		if (!(addService.getvFile().getContentType().toLowerCase().equals("video/mp4")
				|| addService.getvFile().getContentType().toLowerCase().equals("video/avi")
				|| addService.getvFile().getContentType().toLowerCase().equals("video/wmv")
				|| addService.getvFile().getContentType().toLowerCase().equals("video/flv")
				|| addService.getvFile().getContentType().toLowerCase().equals("video/mov"))) {
			errors.rejectValue("vFile", null, "Please upload a video file");
			return;

		}

	}

}
