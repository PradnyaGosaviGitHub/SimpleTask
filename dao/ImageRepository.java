package com.luv2code.springboot.cruddemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.cruddemo.entity.ImageInsert;

public interface ImageRepository extends JpaRepository<ImageInsert, Integer> {

	// that's it ... no need to write any code LOL!
	
}
