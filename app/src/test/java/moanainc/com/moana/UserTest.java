package moanainc.com.moana;
import org.junit.Test;


import moanainc.com.moana.model.user.AccountType;
import moanainc.com.moana.model.user.User;

import static junit.framework.Assert.assertEquals;

/**
 * Created by reecedantin on 4/7/17.
 */

public class UserTest {

    private User user;
    private User user2;

    @Test
    public void testUserEquals() {
        try {
            user = new User("username", "password", "name", AccountType.USER);
            user2 = new User("username", "password");
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
        assertEquals(true, user.equals(user2));
//        assertEquals("password", user.getAccount().getPassword());
//        assertEquals("name", user.getAccount().getName());
//        assertEquals(AccountType.USER, user.getAccount().getAccountType());

    }

    @Test
    public void testNotUser() {
        try {
            user = new User("username", "password", "name", AccountType.USER);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
        assertEquals(false, user.equals("not a user"));
    }
}
