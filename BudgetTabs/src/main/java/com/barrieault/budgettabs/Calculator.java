package com.barrieault.budgettabs;

import javax.servlet.http.Cookie;

public class Calculator {
	int newPurchase;

	public int getNewPurchase() {
		return newPurchase;
	}

	public void setNewPurchase(int newPurchase) {
		this.newPurchase = newPurchase;
	}
	
	public int calculateBudget(Cookie maxBudget, Cookie currentSpent, int newPurchase){
		int newPurchaseValue = newPurchase;
		int maxValue = Integer.parseInt(maxBudget.getValue());
		int spentValue = Integer.parseInt(currentSpent.getValue());
		int calculatedAmount = maxValue - spentValue - newPurchaseValue;
		return calculatedAmount;
	}
}
