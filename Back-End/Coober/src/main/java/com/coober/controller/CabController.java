package com.coober.controller;

import com.coober.modal.Cab;
import com.coober.service.CabService;
import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/cabs")
public class CabController {

	private Logger logger = LoggerFactory.getLogger(CabController.class);

	@Autowired
	private CabService cabService;

	// services;
	@PostMapping("/add")
	public ResponseEntity<Cab> insertCabHandler(@Valid @RequestBody Cab cab){
		logger.info("Class: CabController, method: insertCabHandler started");
		Cab insertedCab= cabService.insertCab(cab);
		logger.info("Class: CabController, method: insertCabHandler returned "+insertedCab);
		return new ResponseEntity<>(insertedCab, HttpStatus.CREATED);
	}
	@PutMapping("/update")
	public ResponseEntity<Cab> updateCabHandler(@Valid @RequestBody Cab cab){
		logger.info("Class: CabController, method: updateCabHandler started");
		Cab updatedCab= cabService.updateCab(cab);
		logger.info("Class: CabController, method: updateCabHandler returned "+updatedCab);
		return new ResponseEntity<>(updatedCab, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<Cab> deleteCabHandler( Integer cabId){
		logger.info("Class: CabController, method: deleteCabHandler started");
		Cab deletedCab= cabService.deleteCab(cabId);
		logger.info("Class: CabController, method: deleteCabHandler returned "+deletedCab);
		return new ResponseEntity<>(deletedCab, HttpStatus.OK);
	}
	@GetMapping("/viewCabs")
	public ResponseEntity<List<Cab>> viewCabsOfTypeHandler( String carType){
		logger.info("Class: CabController, method: viewCabsOfTypeHandler started");
		List<Cab> cabs= cabService.viewCabsOfType(carType);
		logger.info("Class: CabController, method: viewCabsOfTypeHandler returned "+cabs);
		return new ResponseEntity<>(cabs, HttpStatus.FOUND);
	}
	@GetMapping("/countCabs")
	public ResponseEntity<Integer> countCabsOfTypeHandler( String carType){
		logger.info("Class: CabController, method: countCabsOfTypeHandler started");
		Integer count= cabService.countCabsOfType(carType);
		logger.info("Class: CabController, method: countCabsOfTypeHandler returned "+count);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

}
