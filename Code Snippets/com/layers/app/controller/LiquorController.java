package com.layers.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.layers.app.business.LiquorBusinessInterface;
import com.layers.app.business.ScaleBusinessInterface;
import com.layers.app.exceptions.LiquorFoundException;
import com.layers.app.exceptions.LiquorNotFoundException;
import com.layers.app.exceptions.ScaleNotFoundException;
import com.layers.app.model.Liquor;
import com.layers.app.model.Scale;
import com.layers.app.model.User;
import com.layers.app.model.alerts.Alert;
import com.layers.app.model.alerts.Error;
import com.layers.app.model.alerts.Info;
import com.layers.app.model.alerts.Success;
import com.layers.app.model.alerts.Warn;
import com.layers.app.util.ILogger;

/**
 * <p>Provides all the {@link Liquor} associated request handlers. 
 * 		Uses user session data to execute requests based on location. 
 * 		Controller method path URI values overlap to prevent Status 405 "Method Not Allowed" errors.</p>
 * <p>-- example of request mapping:</p>
 * <pre>@GetMapping("/edit")<br>@PostMapping("/edit")</pre>
 * <p>Implements context dependency injected interfaces to execute the request. Request handlers catch business logic thrown exceptions 
 * 		to assemble a proper response. Each function is documented by logging the result of each request.</p>
 * 
 * @see LiquorBusinessInterface
 * @see ScaleBusinessInterface
 * @see ILogger
 */
@Controller
@RequestMapping("/liquor")
@SessionAttributes({"userToken", "locationToken", "requestToken"})
public class LiquorController 
{
	@Autowired
	private LiquorBusinessInterface liquorService;
	
	@Autowired
	private ScaleBusinessInterface scaleService;
	
	@Autowired
	private ILogger logger;

	/**
	 * Navigates user to the liquorList
	 * 
	 * @param liquor Liquor Model
	 * @param model Session variable 
	 * @return ModelAndView 
	 */
	@GetMapping({"/list","/"})
	public ModelAndView listLiquor(ModelMap model, @ModelAttribute("alert") Alert alert)
	{
		try
		{
			List<Liquor> liquors =  liquorService.findAll(((User)model.get("userToken")).getLocationId());
			
			logger.info((User) model.get("userToken"), "Liquor Service - Get Inventory List", 
					"Liquor list was assembled and user navigated to view.", false);
			
			// Forward user to the liquor View of new class.
			ModelAndView mv = new ModelAndView("inventory/list");
			mv.addObject("liquors", liquors);
			mv.addObject("alert", alert);
			return mv;
		}
		catch(LiquorNotFoundException e)
		{	
			logger.debug((User) model.get("userToken"), "Liquor Service - Get Inventory List", 
					"No liquors were assembled into a list.", false);
			
			ModelAndView mv = new ModelAndView("inventory/list");
			mv.addObject("alert", new Info("No liquor registered to the inventory. Add a new one."));
			return mv;
		}
	}
	
	/**
	 * <p> asdjfoaisdfoiasd </p>
	 * <pre>
	 * Submits the modified liquor form and saves it
	 * </pre>
	 * Cracy times
	 * 
	 * @param liquor The form defined model attribute
	 * @param validate Used to verify the model's field rules are being met.
	 * @param model Session model map
	 * @param redir Reports errors to the main controller method
	 * @see #listLiquor(ModelMap, Alert)
	 * @return
	 */
	@PostMapping("/edit")
	public ModelAndView editLiquor(@Valid @ModelAttribute("liquor") Liquor liquor, 
			BindingResult validate, ModelMap model, RedirectAttributes redir) 
	{
		try
		{
			// Validate the form, if failed, return previous view
			if(validate.hasErrors())
			{
				ModelAndView mv = new ModelAndView("inventory/edit", "liquor", liquor);
				mv.addObject("alert", new Error("Error editting liquor."));
				return mv;
			}
			
			// Call liquor service to update the liquor by the user's location
			liquorService.editLiquor(liquor, ((User) model.get("userToken")).getLocationId());
			
			logger.info((User)model.get("userToken"), "Liquor Service - Submit Liquor Update", 
					"Liquor info Updated: " + liquor.toString(), true);
			
			redir.addFlashAttribute("alert", new Success(liquor.quickDescription() + " was updated."));
			return new ModelAndView("redirect:/liquor/list");
		}
		// catches if liquor being edited is not found
		catch(LiquorNotFoundException e)
		{
			logger.warn((User)model.get("userToken"), "Liquor Service - Submit Liquor Update",
					"Liquor Not Found: " + liquor.toString(), true);
			
			redir.addFlashAttribute("alert", new Error("Liquor Code does not reflect any existing liquors"));
			return new ModelAndView("redirect:/liquor/list");
		}
	}
	
	/**
	 * Navigatable API for requesting liquor to edit
	 * 
	 * @param liquorCode
	 * @return
	 */
	@GetMapping("/edit/{liquorCode}")
	public ModelAndView gotoEditLiquor(@PathVariable String liquorCode, ModelMap model, RedirectAttributes redir)
	{
		try
		{		
			Liquor liquor = liquorService.getOneLiquor(liquorCode, ((User) model.get("userToken")).getLocationId());
			
			logger.info((User)model.get("userToken"), "Liquor Service - Get Liquor for Update",
					"Liquor prepped for updating: " + liquor.toString(), false);
			
			// Nav over to the liquor edit page with the requested liquor
			ModelAndView mv = new ModelAndView("inventory/edit", "liquor", liquor);
			return mv;
		}
		//catches if liquor is not found in DB
		catch(LiquorNotFoundException e)
		{
			logger.debug((User)model.get("userToken"), "Liquor Service - Get Liquor for Update",
					"Liquor Not Found: [ID: "+liquorCode+"]", true);
			
			redir.addFlashAttribute("alert", new Error("Liquor Code does not reflect any existing liquors"));
			return new ModelAndView("redirect:/liquor/list");
		}
	}
	
	/**
	 * Adds the Liquor to the DB
	 * 
	 * @param liquor Liquor
	 * @return Model and View
	 * 
	 */
	@PostMapping("/add")
	public ModelAndView addLiquor(@Valid @ModelAttribute("liquor")Liquor liquor, 
			BindingResult validate, ModelMap model, RedirectAttributes redir)
	{		
		try {
			// Validate the form, if failed, return previous view
			if(validate.hasErrors())
			{
				ModelAndView mv = new ModelAndView("inventory/add", "liquor", liquor);
				mv.addObject("alert", new Error("Error creating liquor"));
				return mv;
			}
			// Calls liquorBusinessService.createliquor() to add new liquor
			liquorService.addLiquor(liquor, ((User) model.get("userToken")).getLocationId());
			
			logger.info((User) model.get("userToken"), "Liquor Service - Submit New Liquor", 
					"Liquor successfully added: " + liquor.toString(), true);
			
			redir.addFlashAttribute("alert", new Success(liquor.quickDescription() + " added to inventory."));
			return new ModelAndView("redirect:/liquor/list");
		}
		// liquor being added already exists. Return Mav with error msg
		catch(LiquorFoundException e)
		{
			logger.warn((User) model.get("userToken"), "Liquor Service - Submit New Liquor", 
				"Liquor already exists: " + liquor.toString(), true);
			
			ModelAndView mv = new ModelAndView("inventory/add");
			mv.addObject("liquor", liquor);
			mv.addObject("alert", new Warn("Liquor Code already exists"));
			return mv;
		}
	}
	
	/**
	 * Navigates to the view where to add liquor
	 * 
	 */
	@GetMapping("/add")
	public ModelAndView gotoAddLiquor()
	{		
		// Forward user to the liquor View of new class.
		ModelAndView mv = new ModelAndView("inventory/add","liquor",new Liquor());
		return mv;
	}

	/**
	 * Navs user to delete confirmation page for liquor and checks if this liquor exists first
	 * 
	 * @param liquor liquor
	 * @return ModelAndView
	 */
	@GetMapping(path="/delete/{liquorCode}")
	public ModelAndView gotoDeleteLiquor(@PathVariable String liquorCode, ModelMap model, RedirectAttributes redir)
	{
		ModelAndView mv = new ModelAndView("inventory/delete");
		try
		{
			// Verify if the liquor exists to the location
			Liquor liquor = liquorService.getOneLiquor(liquorCode, ((User) model.get("userToken")).getLocationId());
			mv.addObject(liquor);
			
			// Call the liquor service and identify if the liquorCode is binded to a scale
			Scale scale = scaleService.verifyLiquorRegistration(liquorCode, ((User) model.get("userToken")).getLocationId());
			mv.addObject("alert", new Warn("Liquor is binded to Scale " + (scale.getScaleId() + 1) + ". "
					+ "Deleting the liquor will unbind it from the scale."));
			
			logger.warn((User) model.get("userToken"), "Liquor Service - Get Liquor for Delete", 
					"Liquor: "+ liquor.toString() +" is binded to Scale " + (scale.getScaleId() + 1) + ". "
					+ "Deleting the liquor will unbind it from the scale.", false);
			
			// Build view w/ liquor and dependencies
			return mv;
		}
		// catch if no liquors are found in the database 
		catch(LiquorNotFoundException e)
		{
			logger.debug((User)model.get("userToken"), "Liquor Service - Get Liquor for Delete",
					"Liquor Not Found: [ID: "+liquorCode+"]", true);
			
			redir.addFlashAttribute("alert", new Warn("Liquor does not reflect any existing liquor."));
			return new ModelAndView("redirect:/liquor/list");
		}
		// Prefered exception where liquor is not binded to scale, report to user
		catch(ScaleNotFoundException scale)
		{
			logger.info((User)model.get("userToken"), "Liquor Service - Get Liquor for Delete",
					"Liquor is safe to remove and not binded to any scales.", false);
			
			mv.addObject("alert", new Info("Liquor is safe to remove and not binded to any scales."));
			return mv;
		}
	}
	
	/**
	 * Navs to the confirmation delete liquor view
	 * 
	 * @param liquor liquor
	 * @return ModelAndView
	 */
	@PostMapping("/delete")
	public ModelAndView deleteLiquor(@Valid @ModelAttribute("liquor") Liquor liquor, 
			BindingResult validate, ModelMap model, RedirectAttributes redir)
	{
		// Validate the form, if failed, return previous view
		if(validate.hasErrors())
		{
			ModelAndView mv = new ModelAndView("inventory/delete", "liquor", liquor);
			mv.addObject("alert", new Error("Error deleting liquor"));
			return mv;
		}
		
		try
		{
			// Delete the liquor from the database, check if it is associated to a scale a removes it
			liquorService.deleteLiquor(liquor.getLiquorCode() ,((User) model.get("userToken")).getLocationId());
			
			logger.info((User)model.get("userToken"), "Liquor Service - Confirm Liquor Delete",
					"Liquor was deleted from inventory and removed from scale: " +liquor.toString(), true);
			
			redir.addFlashAttribute("alert", new Success(liquor.quickDescription() + " was deleted "
							+ "from inventory and removed from scale."));
		}
		// Case where the liquor was not found in the system, return msg
		catch(LiquorNotFoundException e)
		{
			logger.warn((User)model.get("userToken"), "Liquor Service - Confirm Liquor Delete",
					"Liquor Not Found: [ID: "+liquor.getLiquorCode() +"]", true);
			
			redir.addFlashAttribute("alert", new Error("Liquor Code does not reflect any existing liquors"));
		} 
		// Case where no scale was found but the liquor was still successfully deleted, return msg
		catch (ScaleNotFoundException e) 
		{
			logger.info((User)model.get("userToken"), "Liquor Service - Confirm Liquor Delete",
					"Liquor Not Found: [ID: "+liquor.getLiquorCode() +"]", true);
			
			redir.addFlashAttribute("alert", new Success(liquor.quickDescription() + " was deleted "
					+ "from inventory."));
		} 

		// Redirect to the liquor list
		return new ModelAndView("redirect:/liquor/list");
	}
	
	/**
	 * Using Jquery Ajax dataTable to populate the table
	 * 
	 * @return String view
	 */
	@GetMapping("/inventory")
	public String displayInventory()
	{
		return "home";
	}
}
