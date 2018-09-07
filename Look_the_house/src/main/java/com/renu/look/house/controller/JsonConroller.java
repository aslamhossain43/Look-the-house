package com.renu.look.house.controller;

import java.io.File;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.renu.look.house.models.AddService;
import com.renu.look.house.repository.AddServiceRepository;
import com.renu.look.house.utility.FileUploadUtility;

@Controller
public class JsonConroller {
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonConroller.class);

	@Autowired
	private AddServiceRepository addServiceRepository;

	@RequestMapping(value = "/viewServices")
	@ResponseBody
	public List<AddService> getAllServices() {
		LOGGER.info("From class:JsonController,,method : getAllServices");

		return addServiceRepository.getServicesTable();

	}

	@RequestMapping(value = "/json-category")
	@ResponseBody
	public List<AddService> getByCategory() {
		LOGGER.info("From class:JsonController,,method : getByCategory");

		LOGGER.info("get value : " + ShowController.categories);

		return addServiceRepository.findByCategory(ShowController.categories);

	}

	@RequestMapping(value = "/json-country-category")
	@ResponseBody
	public List<AddService> getByCountryCategory() {
		LOGGER.info("From class:JsonController,,method : getByCountryCategory");
		LOGGER.info("get values : " + ShowController.category + " and " + ShowController.country);
		return addServiceRepository.findByCountryCategory(ShowController.category, ShowController.country);

	}

	@RequestMapping(value = "/delete")
	public String deleteById(@RequestParam("id") long id, Model model) {
		AddService addService=addServiceRepository.getOne(id);
		String vCode=addService.getvCode();
		String iCode=addService.getiCode();
		LOGGER.info("Getting vCode : "+vCode);
		LOGGER.info("Getting iCode : "+iCode);
		File vFile=new File(FileUploadUtility.VABS_PATH+vCode+".mp4");
		File iFile=new File(FileUploadUtility.IABS_PATH+iCode+".jpg");
		if (vFile.exists()) {
			vFile.delete();
		}
		if (iFile.exists()) {
			iFile.delete();
		}
		addServiceRepository.deleteById(id);
		model.addAttribute("message", id + "  Number id has been deleted successfully !!!");
		model.addAttribute("heading", "Available Services");
		model.addAttribute("title", "Viewservices");
		model.addAttribute("jsonurl", "/viewServices");
		return "view-services";
	}

	@RequestMapping(value = "/deleteAll")
	public String deleteAll(Model model) {

		addServiceRepository.deleteAll();
		model.addAttribute("message", "All services had deleted successfully !!!");
		return "view-services";

	}

}
