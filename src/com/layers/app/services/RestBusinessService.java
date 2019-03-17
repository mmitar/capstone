package com.layers.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.layers.app.business.LiquorBusinessInterface;
import com.layers.app.model.Liquor;
import com.layers.app.model.RestResponse;
import com.layers.app.model.Scale;


/**
 * @author Joey & Matt
 *
 */
@RestController
@RequestMapping("/inventory")
public class RestBusinessService {
	
	/**
	 * Dependency Injected LiquorBusinessService
	 */
	@Autowired
	private LiquorBusinessInterface liquorService;
	
	/**
	 * Ajax call the reutns the liquor list
	 * @Param none
	 * @return List<Liquor>
	 */
	@GetMapping("/getAllLiquor")
	public List<Liquor> getAllLiquor()
	{
		// Call the business service to request all stored liquor
		return liquorService.findAll();
	}
	
	/**
	 * Saves the liquor information sent from the Arduino to be stored in the persistence layer
	 * 
	 * @param RestResponse rest
	 * @param Scale scale
	 * @return RestResponse
	 */
	@PostMapping("/logScale")
	public RestResponse logScaleAction(RestResponse rest, Scale scale){
		
		// Call the Business Layer to validate the post
		
		// Factory compiles a proper response for the post
		
		// Return a successful status response
		return rest;
	}

}
