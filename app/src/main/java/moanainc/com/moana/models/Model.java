package moanainc.com.moana.models;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by josh baldwin on 2/13/2017.
 */

public class Model {
    private static final Model _instance = new Model();
    private Account currentAccount;
    public static Model getInstance() { return _instance; }

    private Map<String, Account> _users;

    /**
     * make a new model
     */
    public Model() {
        _users = new HashMap<>();

        testUser();
    }

    /**
     * return the list of users
     */
    public Map<String, Account> getUsers() { return _users; };

    /**
     * Add a test user
     */
    public void testUser() {
        Account datBoi = new Account("George P. Burdell", "jackets7");
        _users.put("Georgia P. Burdell", datBoi);
    }

    /**
     * Add a new user to the list.....is this needed??? since arraylist has add method
     */
    public void addUser(Account u) {
        _users.put(u.getUsername(), u);
    }

    /**
     * Find the cause of a failed registration
     */
    public void registrationFailed(Account u) {
        if (_users.containsValue(u)) {
            //user already exists
        } else if (!_users.containsValue(u)) {
            //user input an unacceptable password
        }
    }

    public Account findUserByUsername(String username) {
        return _users.get(username);
    }

    public boolean userExists(String username) {
        return _users.containsKey(username);
    }

    public Account getCurrentAccount() { return currentAccount; }
    public void setCurrentAccount(Account account) { currentAccount = account; }
}
