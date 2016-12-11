package dk.stoberiet.BusinessLogic;

import dk.stoberiet.Models.UserModel;

public interface Authentication {
	UserModel login(String username, String password);
	void logout();
	UserModel getLoggedInUser();
}
