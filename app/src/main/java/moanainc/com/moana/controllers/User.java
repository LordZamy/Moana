package moanainc.com.moana.controllers;

/**
 * Created by josh baldwin on 2/13/2017.
 */

public class User {
    //the user's name
    private String _name;
    //the user's password
    private String _password;

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


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }

        User u = (User) o;
        return (u.getUsername().equals(_name) && u.getPassword().equals(_password));
    }

    @Override
    public String toString() {
        return _name + " " + _password;
    }
}
