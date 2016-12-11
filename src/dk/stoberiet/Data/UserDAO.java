package dk.stoberiet.Data;

import dk.stoberiet.Models.UserModel;

public interface UserDAO {
    UserModel getUserByUsername(String username);
}
