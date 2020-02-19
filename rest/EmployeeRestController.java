package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entity.ImageInsert;

import com.luv2code.springboot.cruddemo.service.ImageService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private ImageService imageService;
	
	@Autowired
	public EmployeeRestController(ImageService theimageService) {
		imageService = theimageService;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/images")
	public List<ImageInsert> findAll() {
		return imageService.findAll();
	}

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/images/{imageId}")
	public ImageInsert getEmployee(@PathVariable int imageId) {
		
		ImageInsert theEmployee = imageService.findById(imageId);
		
		if (theEmployee == null) {
			throw new RuntimeException("Employee id not found - " + imageId);
		}
		
		return theEmployee;
	}
	
	// add mapping for POST /employees - add new employee
	
	@PostMapping("/images")
	public ImageInsert addEmployee(@RequestBody ImageInsert theImageInsert) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theImageInsert.setId(0);
		
		imageService.save(theImageInsert);
		
		return theImageInsert;
	}
	
	// add mapping for PUT /employees - update existing employee
	
	@PutMapping("/images")
	public ImageInsert updateEmployee(@RequestBody ImageInsert theImageInsert) {
		
		imageService.save(theImageInsert);
		
		return theImageInsert;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	
	@DeleteMapping("/images/{imaheId}")
	public String deleteEmployee(@PathVariable int imageId) {
		
		ImageInsert tempEmployee = imageService.findById(imageId);
		
		// throw exception if null
		
		if (tempEmployee == null) {
			throw new RuntimeException("Employee id not found - " + imageId);
		}
		
		imageService.deleteById(imageId);
		
		return "Deleted employee id - " + imageId;
	}
	
}










