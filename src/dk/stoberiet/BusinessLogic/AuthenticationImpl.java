package dk.stoberiet.BusinessLogic;

import dk.stoberiet.Data.UserDAO;
import dk.stoberiet.Data.UserDAOImpl;
import dk.stoberiet.Models.UserModel;

public class AuthenticationImpl implements Authentication {
	private static UserModel loggedInUser;
	private UserDAO userDAO;

	public AuthenticationImpl() {
		this(new UserDAOImpl());
	}

	public AuthenticationImpl(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	@Override
	public UserModel login(String username, String password) {

		UserModel userModel = this.userDAO.getUserByUsername(username);

		if (userModel != null)
		{
			// Check the psasword is correct
			String userPassword = userModel.getPassword();
			if (password != null && userPassword.equals(password))
			{
				loggedInUser = userModel;
				return userModel;
			}
		}

		return null;
	}

	@Override
	public void logout() {
		loggedInUser = null;
	}

	@Override
	public UserModel getLoggedInUser(){
		return loggedInUser;
	}
}
