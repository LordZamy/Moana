package moanainc.com.moana.controllers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by josh baldwin on 2/13/2017.
 */

public class Model {
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private List<User> _users;

    /**
     * make a new model
     */
    public Model() {
        _users = new ArrayList<>();

        testUser();
    }

    /**
     * Add a test user
     */
    public void testUser() {
        User datBoi = new User("George P. Burdell", "jackets7");
        addUser(datBoi);
    }

    /**
     * Add a new user to the list
     */
    public void addUser(User u) {
        if (_users.add(u)) {
            //go to successful registration page
        } else {
            registrationFailed(u);
        }
    }

    /**
     * Find the cause of a failed registration
     */
    public void registrationFailed(User u) {
        if (_users.contains(u)) {
            //user already exists
        } else if (!_users.contains(u)) {
            //user input an unacceptable password
        }
    }

}
