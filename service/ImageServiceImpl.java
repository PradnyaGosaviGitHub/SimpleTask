package com.luv2code.springboot.cruddemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.ImageRepository;

import com.luv2code.springboot.cruddemo.entity.ImageInsert;

@Service
public class ImageServiceImpl implements ImageService {

	private ImageRepository imageRepository;
	
	@Autowired
	public ImageServiceImpl(ImageRepository theimageRepository) {
		imageRepository = theimageRepository;
	}
	
	@Override
	public List<ImageInsert> findAll() {
		return imageRepository.findAll();
	}

	@Override
	public ImageInsert findById(int theId) {
		Optional<ImageInsert> result = imageRepository.findById(theId);
		
		ImageInsert theImages = null;
		
		if (result.isPresent()) {
			theImages = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find image id - " + theId);
		}
		
		return theImages;
	}

	@Override
	public void save(ImageInsert theImage) {
		imageRepository.save(theImage);
	}

	@Override
	public void deleteById(int theId) {
		imageRepository.deleteById(theId);
	}

	

	

}






