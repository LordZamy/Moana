package moanainc.com.moana;
import org.junit.Before;
import org.junit.Test;


import moanainc.com.moana.model.user.AccountType;
import moanainc.com.moana.model.user.User;

import static junit.framework.Assert.assertEquals;

/**
 * Created by reecedantin on 4/7/17.
 */

public class UserTest {

    private User user;

    @Before
    public void setup() {
    }

    @Test
    public void testUserAccount() {
        try {
            user = new User("username", "password", "name", AccountType.USER);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
        assertEquals("username", user.getAccount().getUsername());
        assertEquals("password", user.getAccount().getPassword());
        assertEquals("name", user.getAccount().getName());
        assertEquals(AccountType.USER, user.getAccount().getAccountType());

    }

    @Test
    public void testUserTwoParams() {
        try {
            user = new User("username", "password", "name", AccountType.USER);
        } catch (NullPointerException e) {
            System.err.println("NullPointerException: " + e.getMessage());
        }
        assertEquals("username", user.getAccount().getUsername());
        assertEquals("password", user.getAccount().getPassword());
    }
}
