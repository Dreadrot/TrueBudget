package com.barrieault.budgettabs;


import java.net.HttpCookie;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.barrieault.budgettabs.DAO;
import com.barrieault.budgettabs.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "home";
		
	}
	//front page of site (home.jsp)
	@RequestMapping(value = "/home", method = RequestMethod.GET)

	public String welcomeagain() {
		return "home";
	}
	//sends to login.jsp when login button on home page is clicked, 
	//returns back to login page and posts user information
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loggingin(Model model, @CookieValue(value="username", required=false) Cookie username, @CookieValue(value="userid", required=false) Cookie userid) {
		model.addAttribute("loginForm", new User());
		if (username == null || userid == null)
			return "login";
		return "alreadylogged";
	}
	//gets login information typed by user
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userlogincheck(HttpSession session, 
			Model model,
			@ModelAttribute("loginForm") User user, 
			HttpServletResponse response) {
	    //checking to see if username/password match in database
		
		User checkedUser = DAO.userAndPassValidator(user);
		if(checkedUser != null){
			//making cookies for username + userID
			Cookie username = new Cookie("username", checkedUser.getUsername());
			Cookie userID = new Cookie("userid", ("" + checkedUser.getID()));
			Cookie spendingmax = new Cookie("spendingmax", String.valueOf(checkedUser.getSpendingMax()));
    			Cookie currentspent = new Cookie("currentspent", String.valueOf(checkedUser.getCurrentSpent()));
			//adding cookies for both username and ID 
			response.addCookie(username);
			response.addCookie(userID);
			response.addCookie(currentspent);
			response.addCookie(spendingmax);
			
			//adding values to model 
			model.addAttribute("userid", checkedUser.getID());
			model.addAttribute("username", checkedUser.getUsername());
			model.addAttribute("spendingmax", checkedUser.getSpendingMax());
    			model.addAttribute("currentspent", checkedUser.getCurrentSpent());
			
			//sends to checklogin.jsp
			return "loginsuccess";
		}else{
			//sends to loginfailed.jsp
			return "loginfailed";
		}
	}
	@RequestMapping(value = "/createaccount", method = RequestMethod.GET)
	public String createAccount(Map<String, Object> model) {
		User user = new User();
		//adding new user to model
		model.put("addUser", user);
		//DAO.isUsernameTaken(user);
		//sending to create account form page 
		return "createaccount";
	}
	//mapping for retrieving user input into 
	@RequestMapping(value = "/adduser", method=RequestMethod.POST)    
    public String checkPersonInfo(HttpSession session, Model model, @ModelAttribute("addUser") User addUser,HttpServletResponse response) {
        //testing to see if username taken
        if(!DAO.isUsernameTaken(addUser)){
        	DAO.addUser(addUser);
        	//creating new cookie with username + user id
        	Cookie username = new Cookie ("username", addUser.getUsername());
    		Cookie userid = new Cookie("userid", ("" + addUser.getID()));
    		Cookie spendingmax = new Cookie("spendingmax", String.valueOf(addUser.getSpendingMax()));
    		Cookie currentspent = new Cookie("currentspent", String.valueOf(addUser.getCurrentSpent()));

    		response.addCookie(username);
    		response.addCookie(userid);
    		response.addCookie(spendingmax);
    		response.addCookie(currentspent);
    		//adding values to model with ID/Username
    		model.addAttribute("userid", addUser.getID());
    		model.addAttribute("username", addUser.getUsername());
    		model.addAttribute("spendingmax", addUser.getSpendingMax());
    		model.addAttribute("currentspent", addUser.getCurrentSpent());
        }else{
        	//please try again re-route to create account (loginfailed.jsp)
        	return "login";
        }
        //return a success page(userprofile.jsp)
        return "redirect:/home";
    }
	@RequestMapping("/logout")
	public ModelAndView accessLogout( @CookieValue("spendingmax") Cookie spendingmax, @CookieValue("currentspent") Cookie currentspent, @CookieValue("username") Cookie username,@CookieValue("userid") Cookie userid, HttpServletResponse response){
		//as long as username destroys made cookie
		if(!(username.getValue().equals("null"))){
			username.setMaxAge(0);
			response.addCookie(username);
	}
		if(!(spendingmax.getValue().equals("null"))){
			username.setMaxAge(0);
			response.addCookie(spendingmax);
	}
		if(!(currentspent.getValue().equals("null"))){
			username.setMaxAge(0);
			response.addCookie(currentspent);
	}  
		//as long as id exists destroys made cookie
		if(!(userid.getValue().equals("null"))){
			userid.setMaxAge(0);
			response.addCookie(userid);
	}
		//sends to logout page 
		return new ModelAndView("logout");
	}
	@RequestMapping(value = "/checkAffordability", method = RequestMethod.GET)
	public String getCalculate(Model model) {
		Calculator calc = new Calculator();
		model.addAttribute("purchaseForm", calc);
		return "checkAffordability";
	}
	@RequestMapping(value = "/checkAffordability", method = RequestMethod.POST)
	public String postCalculate(HttpSession session, 
			Model model,
			@ModelAttribute("purchaseForm") Calculator calc, 
			HttpServletResponse response, @CookieValue(value="spendingmax", required=false) Cookie spendingmax, 
			@CookieValue(value="currentspent", required=false) Cookie currentspent) {
		
		
		/*int newPurchaseValue = calc.getNewPurchase();
		int maxValue = Integer.parseInt(spendingmax.getValue());
		int spentValue = Integer.parseInt(currentspent.getValue());
		int calculatedAmount = maxValue - spentValue - newPurchaseValue;
		
		System.out.println(maxValue + "   " + spentValue + "    " + calculatedAmount);
		*/
        if(calc.calculateBudget(spendingmax, currentspent, calc.getNewPurchase()) >= 0){
    			return "calcSuccess";
        }
        return "calcFailure";
	}
	/*@RequestMapping(value = "/formAction", method = RequestMethod.POST)
	public String postCalculate(@CookieValue("maxspent") Cookie maxspent, @CookieValue("currentspent") Cookie currentspent, HttpServletRequest request) {
        int newPurchaseValue = Integer.parseInt(request.getParameter("path1"));
        int maxValue = Integer.parseInt(maxspent.toString());
        int spentValue = Integer.parseInt(currentspent.toString());
        int calculatedAmount = maxValue - spentValue - newPurchaseValue;
        String youCanAfford = "You can afford it! You will have " + calculatedAmount + " left over!";
        String youCannotAfford = "You can't buy this and stay in your budget. You'd be over by " + calculatedAmount + "!";
        if(calculatedAmount >= 0){
        		return "calcSuccess";
        }
		return "calcFailure";
	} */
	
	@RequestMapping(value = "/calcFailure", method = RequestMethod.GET)
	public String calcFailed() {
		
		return "calcFailure";
	}
	
	@RequestMapping(value = "/calcSuccess", method = RequestMethod.GET)

	public String calcSuccess() {
		return "calcSuccess";
	}

}
