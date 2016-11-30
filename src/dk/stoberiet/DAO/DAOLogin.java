package dk.stoberiet.DAO;

import dk.stoberiet.Models.CredentialModel;
//import Models.ContentModel;


/**
 * Created by fede0004@stud.kea.dk on 22-10-2016.
 */
public interface DAOLogin {
	public CredentialModel fetchUser(String username, String password);
//	public List<ContentModel> fetchContent();
//	public boolean addContent(ContentModel content);
}
