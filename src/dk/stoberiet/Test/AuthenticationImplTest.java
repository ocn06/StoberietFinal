package dk.stoberiet.Test;

import dk.stoberiet.BusinessLogic.Authentication;
import dk.stoberiet.BusinessLogic.AuthenticationImpl;
import org.junit.Test;
import static org.junit.Assert.*;
import org.hamcrest.*;

public class AuthenticationImplTest {
    @Test
    public void handleLogin() throws Exception {
        Authentication tester = new AuthenticationImpl();
        assertNull(tester.login("000", "000"));
    }


}
