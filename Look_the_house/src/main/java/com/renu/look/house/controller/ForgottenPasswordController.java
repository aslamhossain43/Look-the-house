package com.renu.look.house.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.renu.look.house.models.User;
import com.renu.look.house.repository.User_Repository;


@Controller
public class ForgottenPasswordController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForgottenPasswordController.class);
	@Autowired
	private User_Repository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "/show-email-word")
	public String showEmailWord(Model model) {
		LOGGER.info("From class : ForgottenPasswordController, method : showEmailWord()");

		return "forgotten-password-email-word";

	}

	@RequestMapping(value = "/send-email-word")
	public String sendEmailWord(@RequestParam("username") String username, @RequestParam("word") String word,
			Model model) {
		LOGGER.info("From class : ForgottenPasswordController, method : sendEmailWord()");
		LOGGER.info("Getting email : " + username);
		LOGGER.info("Getting word : " + word);
		User user = userRepository.getByUsername(username);
		if (user == null) {
			model.addAttribute("message", "Your email is wrong !!!");
			return "forgotten-password-email-word";

		}
		String dataBaseWord = user.getWord();
		if (word.equals(dataBaseWord)) {
			model.addAttribute("email", username);
			return "forgotten-password";
		} else {
			model.addAttribute("message",
					"Your word is wrong ! Please enter a word which you added during registration .");
			return "forgotten-password-email-word";

		}

	}

	@RequestMapping(value = "/reset-password", method = RequestMethod.POST)
	public String resetPassword(@RequestParam("username") String username, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, Model model, String[] roles) {
		LOGGER.info("From class : ForgottenPasswordController, method : resetPassword()");
		LOGGER.info("Getting email : " + username);
		LOGGER.info("Getting password : " + password);

		LOGGER.info("Getting confirm password : " + confirmPassword);

		if (password.equals(confirmPassword)) {
			String pwd = bCryptPasswordEncoder.encode(password);
			userRepository.updatePassword(pwd, username);
			model.addAttribute("message", "Your password has been completed reset successfully !!!");

			return "sec-login";
		} else {
			model.addAttribute("message", "Not matching password and confirm password !!!");
			return "forgotten-password";
		}

	}

}
