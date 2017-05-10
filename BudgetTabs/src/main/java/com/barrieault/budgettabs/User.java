package com.barrieault.budgettabs;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
@Entity
@Table(name="user")
public class User {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	@Email
	@NotEmpty
	private String email;
	
	@Id @GeneratedValue
	private int ID;
	@NotNull
	private int spendingMax;
	private int currentSpent;
	private int newPurchase;
	
	
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
	public int getNewPurchase() {
		return newPurchase;
	}
	public void setNewPurchase(int newPurchase) {
		this.newPurchase = newPurchase;
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {

		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isItAffordable(){
		if(this.getSpendingMax() - this.getCurrentSpent() - this.getNewPurchase() >= 0){
			return true;			
		}
		return false;
	}


}