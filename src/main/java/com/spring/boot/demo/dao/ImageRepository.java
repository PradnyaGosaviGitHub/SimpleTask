package com.spring.boot.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.demo.entity.ImageInsert;

public interface ImageRepository extends JpaRepository<ImageInsert, Integer> {

	Optional<ImageInsert> findByimagePath(String imagePath);

//	void save(String theImage);

//	Optional<ImageInsert> findByimagePath(String imagePath);

	// that's it ... no need to write any code LOL!
	
}
