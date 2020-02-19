package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import com.luv2code.springboot.cruddemo.entity.ImageInsert;

public interface ImageService {

	public List<ImageInsert> findAll();
	
	public ImageInsert findById(int theId);
	
	public void save(ImageInsert theImageInsert);
	
	public void deleteById(int theId);
	
}
