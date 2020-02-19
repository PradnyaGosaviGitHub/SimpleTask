package com.spring.boot.demo.rest;

import java.util.Base64;
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

import com.spring.boot.demo.entity.ImageInsert;
import com.spring.boot.demo.service.ImageService;

@RestController
@RequestMapping("/api")
public class ImageRestController {

	private ImageService imageService;

	@Autowired
	public ImageRestController(ImageService theimageService) {
		imageService = theimageService;
	}

	/*
	 * READ ALL the images
	 */
	
	@GetMapping("/images")
	public List<ImageInsert> findAll() {
		return imageService.findAll();
	}
	
	/*
	 *	READ IMAGE by image id
	 */

	@GetMapping("/images/id/{imageId}")
	public ImageInsert getImage(@PathVariable int imageId) {
		

		ImageInsert theImage = imageService.findById(imageId);
		System.out.println("image path by id: " + theImage);
		
		if (theImage == null) {
			throw new RuntimeException("Image id not found - " + imageId);
		}

		return theImage;
	}

	/*
	 * READ IMAGE BY image path
	 */
	@GetMapping("/read/{imagePath}")
	public String getImage(@PathVariable String imagePath) {

		byte[] actualByte = Base64.getDecoder().decode(imagePath);
		String actualString = new String(actualByte);
		System.out.println(actualString);

		ImageInsert theImage = imageService.findByimagePath(imagePath);
		System.out.println("image path: " + imagePath);

		if (theImage == null) {
			throw new RuntimeException("Image id not found - " + imagePath);
		}

		return actualString;
	}


	@PostMapping("/upload")
	public ImageInsert addImage(@RequestBody ImageInsert theImageInsert) {


		theImageInsert.setId(0);

		String toEncode = theImageInsert.toString();

		// Encode into Base64 format
		String BasicBase64format = Base64.getEncoder().encodeToString(toEncode.getBytes());
		theImageInsert.setImagePath(BasicBase64format);
//        System.out.println(BasicBase64format);

		imageService.save(theImageInsert);

		return theImageInsert;
	}



	 


	@DeleteMapping("/delete/{imageId}")
	public String deleteImage(@PathVariable int imageId) {

		ImageInsert tempImage = imageService.findById(imageId);

		// throw exception if null

		if (tempImage == null) {
			throw new RuntimeException("Image id not found - " + imageId);
		}

		imageService.deleteById(imageId);

		return "Deleted image id - " + imageId;
	}

}
