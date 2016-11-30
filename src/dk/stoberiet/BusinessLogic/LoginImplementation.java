package dk.stoberiet.BusinessLogic;

import dk.stoberiet.DAO.DAOLoginImpl;
import dk.stoberiet.Models.CredentialModel;


/**
 * Created by fede0004@stud.kea.dk on 22-10-2016.
 */
public class LoginImplementation implements Login {

	public CredentialModel fetchUser(String username, String password) {
		DAOLoginImpl loginDAO = new DAOLoginImpl();
		return loginDAO.fetchUser(username, password);
	}
}
