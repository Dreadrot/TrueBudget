package com.barrieault.budgettabs;


import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			Cookie username = new Cookie ("username", checkedUser.getUsername());
			Cookie userID = new Cookie("userid", ("" + checkedUser.getID()));
			//adding cookies for both username and ID 
			response.addCookie(username);
			response.addCookie(userID);
			//adding values to model 
			model.addAttribute("userid", checkedUser.getID());
			model.addAttribute("username", checkedUser.getUsername());
			
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
    public ModelAndView checkPersonInfo(HttpSession session, Model model, @ModelAttribute("addUser") User addUser,HttpServletResponse response) {
        //testing to see if username taken
        if(!DAO.isUsernameTaken(addUser)){
        	DAO.addUser(addUser);
        	//creating new cookie with username + user id
        	Cookie username = new Cookie ("username", addUser.getUsername());
    		Cookie userid = new Cookie("userid", ("" + addUser.getID()));
    		//response.addCookie(username);
    		//response.addCookie(userid);
    		//adding values to model with ID/Username
    		//model.addAttribute("userid", addUser.getID());
    		//model.addAttribute("username", addUser.getUsername());
        }else{
        	//please try again re-route to create account (loginfailed.jsp)
        	return new ModelAndView ("login");
        }
        //return a success page(userprofile.jsp)
        return new ModelAndView ("home");
    }
	@RequestMapping("/logout")
	public ModelAndView accessLogout(@CookieValue("username") Cookie username,@CookieValue("userid") Cookie userid, HttpServletResponse response){
		//as long as username destroys made cookie
		if(!(username.getValue().equals("null"))){
			username.setMaxAge(0);
			response.addCookie(username);
	}
		//as long as id exists destroys made cookie
		if(!(userid.getValue().equals("null"))){
			userid.setMaxAge(0);
			response.addCookie(userid);
	}
		//sends to logout page 
		return new ModelAndView("logout");
	}
}
