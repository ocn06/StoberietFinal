package dk.stoberiet.BusinessLogic;

import dk.stoberiet.Models.CredentialModel;


/**
 * Created by fede0004@stud.kea.dk on 22-10-2016.
 */
public interface Login {
	public CredentialModel fetchUser(String username, String password);


}
