package com.prodapt.bicyclespring.business;

import com.prodapt.bicyclespring.entity.User;

import lombok.Data;

@Data
public class LoggedInUser {
    private User loggedInUser;

	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}


}
