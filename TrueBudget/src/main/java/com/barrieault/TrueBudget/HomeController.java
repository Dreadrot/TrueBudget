package com.barrieault.TrueBudget;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
	
		return "home";
	}
	@RequestMapping(value = "cani", method = RequestMethod.GET)
	public String cani(Model model) {
		//get user info. Currently hardcoded.
		userBudget Tommy = new userBudget();
		Tommy.setCurrentSpent(0);
		Tommy.setSpendingMax(200);
		//I'll end up putting this logic in a method before I'm done.
		//get the pieces to do the math
		int budgetCap = Tommy.getSpendingMax();
		int budgetSpent = Tommy.getCurrentSpent();
		int budgetLeft = budgetCap - budgetSpent;
		//newPurchase will be a user inputted variable. Currently hardcoded
		int newPurchase = 85;
		//create a string to store a yes or no answer for the user to read
		String budgetResult;
		//doing the math logic. 
		//Can't afford a new item if you already spent too much
		if(budgetLeft<0){
			budgetResult = "You can't afford this. You're already over budget!";
		}else{
			//Is the new item too much?
			if(budgetLeft - newPurchase<0){
				//calculate how much I can't afford it by exactly
				int budgetDifference = (budgetLeft - newPurchase) * -1;
				budgetResult = "You can't afford this! your are over by: $" + budgetDifference;
			}else{
				//calculate how much I'll have left afterwards
				int budgetDifference = (budgetLeft - newPurchase);
				budgetResult = "Yeah! You can go for it! You'll have $" + budgetDifference + " when you're done.";
			}
		}
		//assign to model for use in the jsp
		model.addAttribute("budgetSpent", budgetSpent);
		model.addAttribute("budgetCap", budgetCap);
		model.addAttribute("newCost", newPurchase);
		model.addAttribute("budgetResult", budgetResult);
		return "cani";
	}
}
