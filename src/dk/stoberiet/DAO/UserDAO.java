package dk.stoberiet.DAO;

import dk.stoberiet.Models.UserModel;

public interface UserDAO {
    UserModel getUserByUsername(String username);
}
