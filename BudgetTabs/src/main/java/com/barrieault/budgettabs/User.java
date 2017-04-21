package com.barrieault.budgettabs;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
@Entity
@Table(name="user")

public class User {
	@NotEmpty
	   @Column(name = "username")
	private String username;
	@NotEmpty
	   @Column(name = "password")
	private String password;
	@Email
	@NotEmpty
	   @Column(name = "email")
	private String email;
	
	@Id @GeneratedValue
	   @Column(name = "ID")
	private int ID;
	   @Column(name = "spendingMax")
	private int spendingMax;
	   @Column(name = "currentSpent")
	private int currentSpent;
	   @Column(name = "newPurchase")
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


}