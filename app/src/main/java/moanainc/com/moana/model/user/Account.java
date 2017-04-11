package moanainc.com.moana.model.user;

/**
 * Created by josh baldwin on 2/13/2017.
 */

class Account {
    //the user's username
    private String _username;
    //the user's password
    private String _password;
    //the user's real name
    private String _name = "";
    //the user's access level
    private AccountType _accountType;

    //user details
    private String _emailAddress = "";
    private String _homeAddress = "";

    Account() {

    }

    /**
     * Makes a new user
     * @param username the username
     * @param password the password
     * @param name the name
     * @param accountType the account type
     */

    Account(String username, String password, String name, AccountType accountType) {

        _username = username;
        _password = password;
        _name = name;
        _accountType = accountType;
    }

    /**
     * Makes a new user
     * @param username the username
     * @param password the password
     */

    Account(String username, String password) {

        _username = username;
        _password = password;
    }

    /**
     * Getters and Setters
     */
    public String getUsername() { return _username; }

    public String getPassword() { return _password; } //if we want to implement "Forgot password"
    public void setPassword(String password) { _password = password; } //if we want to implement "Change password"

    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public AccountType getAccountType() { return _accountType; }

    public String getEmailAddress() { return _emailAddress; }
    public void setEmailAddress(String email) { _emailAddress = email; }

    public String getHomeAddress() { return _homeAddress; }
    public void setHomeAddress(String home) { _homeAddress = home; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Account)) {
            return false;
        }

        Account u = (Account) o;
        return (u.getUsername().equals(_username) && u.getPassword().equals(_password));
    }

    @Override
    public String toString() {
        return _name + " " + _username + " " + _password + " " + _accountType.toString();
    }
}
