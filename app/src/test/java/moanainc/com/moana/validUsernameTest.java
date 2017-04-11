package moanainc.com.moana;

        import org.junit.Before;
        import org.junit.Test;

        import java.util.ArrayList;

        import moanainc.com.moana.controller.RegisterActivity;
        import moanainc.com.moana.model.user.User;

        import static junit.framework.Assert.assertEquals;

/**
 * Created by davidhan on 4/6/17.
 */

public class validUsernameTest {
    private User user1;
    private User user2;
    private User user3;
    private User user4;
    private User user5;
    private User user6;
    private ArrayList<User> userList;
    private ArrayList<User> validUserList;
    private RegisterActivity register;

    @Before
    public void setup() {
        user1 = new User("Micah1324", "asdfasdf1234");
        user2 = new User("Lord_Zamy", "asdfasdf1234");
        user3 = new User("Darrian", "asdfasdf1234");
        user4 = new User("!@#$%!@#$", "asdfasdf1234");
        user5 = new User("David1995", "asdfasdf1234");
        user6 = new User("Josh", "asdfasdf1234");
        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        validUserList = new ArrayList<>();
        register = new RegisterActivity();
    }

    @Test
    public void testAddUser() {
        for (int i = 0; i < userList.size(); i++) {
            try {
                if (register.validateName(userList.get(i).getAccount().getUsername())) {
                    validUserList.add(userList.get(i));
                }
            } catch (NullPointerException e) {
                System.err.println("NullPointerException: " + e.getMessage());
            }
        }
        assertEquals(4, validUserList.size());
    }
}


