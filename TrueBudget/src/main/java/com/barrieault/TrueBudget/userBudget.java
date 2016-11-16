package com.barrieault.TrueBudget;

public class userBudget {
	private int spendingMax = 100;
	private int currentSpent = 50;
	public int getSpendingMax() {
		return spendingMax;
	}
	public void setSpendingMax(int spendingMax) {
		this.spendingMax = spendingMax;
	}
	public int getCurrentSpent() {
		return currentSpent;
	}
	public void setCurrentSpent(int currentSpent) {
		this.currentSpent = currentSpent;
	}
}
