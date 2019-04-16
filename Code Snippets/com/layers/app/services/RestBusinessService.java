package com.layers.app.services;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.layers.app.business.LiquorBusinessInterface;
import com.layers.app.business.ScaleBusinessInterface;
import com.layers.app.exceptions.ChangeNotFoundException;
import com.layers.app.exceptions.DatabaseException;
import com.layers.app.exceptions.LiquorNotFoundException;
import com.layers.app.exceptions.ScaleNotFoundException;
import com.layers.app.model.Liquor;
import com.layers.app.model.RestResponse;
import com.layers.app.model.Scale;
import com.layers.app.model.User;

/**
 * @author Joey & Matt
 *
 */
@RestController // Bahaves has @ResponseBody, returns Objects in JSON
@RequestMapping("/rest/inventory") // Path for Rest Controller
@SessionAttributes({"userToken", "locationToken", "requestToken"}) 
public class RestBusinessService 
{
	/**
	 * Dependency Injected LiquorBusinessService
	 */
	@Autowired
	private LiquorBusinessInterface liquorService;
	
	@Autowired
	private ScaleBusinessInterface scaleService;

	/**
	 * Saves the liquor information sent from the Arduino to be stored in the persistence layer
	 * 
	 * @param RestResponse rest
	 * @param Scale scale
	 * @return RestResponse
	 */
	@PostMapping("/logScale")
	public ResponseEntity<RestResponse> logScaleAction(@RequestBody Scale log)
	{
		try 
		{
			// Call scale service to return the scale post info in the database
			scaleService.saveScaleLog(log);
			
			return new ResponseEntity<RestResponse>(new RestResponse("GREEN", "Success"), HttpStatus.CREATED);
		}
		// The quantity amount change reported is insignificant and wont be retained
		catch(ChangeNotFoundException e)
		{
			return new ResponseEntity<RestResponse>(new RestResponse("GREEN", "Log quantity insufficient to retain."), HttpStatus.ALREADY_REPORTED);
		}
		// Scale Not Registered in the system to the location
		catch(ScaleNotFoundException e)
		{
			return new ResponseEntity<RestResponse>(new RestResponse("YELLOW", "Scale does not appear to be registered to the location."), HttpStatus.NOT_FOUND);
		}
		// Scale not configured to the reported liquor
		catch(LiquorNotFoundException e)
		{
			return new ResponseEntity<RestResponse>(new RestResponse("YELLOW", "Liquor is not associated to the scale."), HttpStatus.CONFLICT);
		}
		// Database or SQL exception occurance. Server cannot handle request
		catch(DatabaseException e)
		{
			return new ResponseEntity<RestResponse>(new RestResponse("RED", "Database Error: " + e.toString() + " === Message: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
