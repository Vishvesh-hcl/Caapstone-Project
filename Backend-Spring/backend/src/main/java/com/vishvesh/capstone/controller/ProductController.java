package com.vishvesh.capstone.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vishvesh.capstone.entity.Product;
import com.vishvesh.capstone.repository.ProductRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
//@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductRepository productRepository;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam(required = false) String title) {
		try {
			List<Product> products = new ArrayList<Product>();

			if (title == null)
				productRepository.findAll().forEach(products::add);
			else
				productRepository.findByTitleContaining(title).forEach(products::add);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getTutorialById(@PathVariable("id") long id) {
		Optional<Product> tutorialData = productRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/products")
	public ResponseEntity<Product> createTutorial(@RequestBody Product product) {
		try {
			Product _tutorial = productRepository
					.save(new Product(product.getTitle(), product.getSku(), product.getDescription(), 
							product.getUnitPrice(), product.getImageUrl(), true, product.getUnitsInStock(),
						 	product.getDateCreated(), product.getLastUpdated(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Product> updateTutorial(@PathVariable("id") long id, @RequestBody Product product) {
		Optional<Product> tutorialData = productRepository.findById(id);

		if (tutorialData.isPresent()) {
			Product _tutorial = tutorialData.get();
			_tutorial.setTitle(product.getTitle());
			_tutorial.setDescription(product.getDescription());
			_tutorial.setPublished(product.isPublished());
			return new ResponseEntity<>(productRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			productRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/products")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			productRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Product>> findByPublished() {
		try {
			List<Product> products = productRepository.findByPublished(true);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
