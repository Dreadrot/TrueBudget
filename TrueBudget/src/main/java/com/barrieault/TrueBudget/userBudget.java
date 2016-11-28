package com.barrieault.TrueBudget;

public class userBudget {
	//All these ints will be replaced with BigIntegers before going live
	private int spendingMax = 100;
	private int currentSpent = 50;
	private int newPurchase = 0;
	public int getNewPurchase() {
		return newPurchase;
	}
	public void setNewPurchase(int newPurchase) {
		this.newPurchase = newPurchase;
	}
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
