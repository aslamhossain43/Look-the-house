package com.renu.look.house.utility;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class FileUploadUtility {

	public static final String VABS_PATH = "H:\\STS_Github\\To_Let\\To-Let\\src\\main\\resources\\static\\videos\\";
	public static final String IABS_PATH = "H:\\STS_Github\\To_Let\\To-Let\\src\\main\\resources\\static\\images\\";

	 
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	public static boolean videoUploadFile(HttpServletRequest request, MultipartFile file, String code) 
	{				
		
		logger.info("VideoUploadFile method");	
		
		
		// create the directories if it does not exist
		
	
		if(!new File(VABS_PATH).exists()) {
			new File(VABS_PATH).mkdirs();
		}
		
		try {
			
			file.transferTo(new File(VABS_PATH + code + ".mp4"));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	public static boolean imageUploadFile(HttpServletRequest request, MultipartFile file, String code) 
	{				
		
		logger.info("FROM imageUploadFile method");	
		
		
		
		// create the directories if it does not exist
		
	
		if(!new File(IABS_PATH).exists()) {
			new File(IABS_PATH).mkdirs();
		}
		
		try {
			
			file.transferTo(new File(IABS_PATH + code + ".jpg"));
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
		
	
}
