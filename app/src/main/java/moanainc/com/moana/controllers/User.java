package moanainc.com.moana.controllers;

/**
 * Created by josh baldwin on 2/13/2017.
 */

public class User {
    //the user's username
    private String _name;
    //the user's password
    private String _password;
    //the user's real name
    private String _realName;
    //the user's access level
    private AccountType _accountType;

    public User() {

    }

    /**
     * Makes a new user
     * @param name the username
     * @param password the password
     */

    public User(String name, String password) {

        _name = name;
        _password = password;
    }

    /**
     * Getters and Setters
     */
    public String getUsername() { return _name; }
    public void setUsername(String name) { _name = name; }

    public String getPassword() { return _password; } //if we want to implement "Forgot password"
    public void setPassword(String password) { _password = password; } //if we want to implement "Change password"

    public String getRealName() { return _realName; }
    public void setRealName(String realName) { _realName = realName; }

    public AccountType getAccountType() { return _accountType; }
    public void setAccountType(AccountType type) { _accountType = type; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }

        User u = (User) o;
        return (u.getUsername().equals(_name) && u.getPassword().equals(_password)
                && u.getRealName().equals(_realName) && u.getAccountType().equals(_accountType));
    }

    @Override
    public String toString() {
        return _realName + " " + _name + " " + _password + " " + _accountType.toString();
    }
}
