package com.barrieault.TrueBudget;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, userBudget Tommy) {
		
		model.addAttribute("newPurchase", Tommy);
		return "home";
	}
	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String homeCostRegister(@ModelAttribute("newPurchase") int newPurchase) {
		//Hardcoding a user for testing. This will be removed when a database is connected.
		userBudget Tommy = new userBudget();
		Tommy.setCurrentSpent(0);
		Tommy.setSpendingMax(200);
		Tommy.setNewPurchase(newPurchase);
		
		return "canIAfford";
	}
	@RequestMapping(value = "/canIAfford", method = RequestMethod.POST)
	public String cani(Model model, userBudget Tommy) {
		//create a string to store a yes or no answer for the user to read
		String budgetResult;
		//grab user data from earlier
		int budgetCap = Tommy.getSpendingMax();
		int budgetSpent = Tommy.getCurrentSpent();
		int budgetLeft = budgetCap - budgetSpent;
		//Can't afford a new item if you already spent too much
		if(budgetLeft<0){
			budgetResult = "You can't afford this. You're already over budget!";
		}else{
			//Is the new item too much?
			if(budgetLeft - Tommy.getNewPurchase()<0){
				//calculate how much I can't afford it by exactly
				int budgetDifference = (budgetLeft - Tommy.getNewPurchase()) * -1;
				budgetResult = "You can't afford this! You'd be over by: $" + budgetDifference;
			}else{
				//calculate how much I'll have left afterwards
				int budgetDifference = (budgetLeft - Tommy.getNewPurchase());
				budgetResult = "Yeah! You can go for it! You'll have $" + budgetDifference + " when you're done.";
			}
		}
		//give user the reply
		model.addAttribute("budgetSpent", Tommy.getCurrentSpent());
		model.addAttribute("budgetCap", Tommy.getSpendingMax());
		model.addAttribute("newCost", Tommy.getNewPurchase());
		model.addAttribute("budgetResult", budgetResult);
		return "canIAfford";
	}
	
}
