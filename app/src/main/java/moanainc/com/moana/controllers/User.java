package moanainc.com.moana.controllers;

/**
 * Created by josh baldwin on 2/13/2017.
 */

public class User {
    //the user's name
    private String _name;
    //the user's password
    private String _password;

    /**
     * Makes a new user
     * @param name the username
     * @param password the password
     */
    public User(String name, String password) {
        boolean bool = false;
        for (int i = 0; i < password.length(); i++) {
            if ((password.charAt(i) < 48) || (password.charAt(i) > 57 && password.charAt(i) < 65)
                    || (password.charAt(i) > 90 && password.charAt(i) < 97)
                    || (password.charAt(i) > 122)) {
                bool = true;
            }
        }
        if (!bool) {
            _name = name;
            _password = password;
        } else {
            //goToRetry();
        }

    }

    /**
     * Getters and Setters
     */
    public String getUsername() { return _name; }
    public void setUsername(String name) { _name = name; }

    public String getPassword() { return _password; } //if we want to implement "Forgot password"
    public void setPassword(String password) { _password = password; } //if we want to implement "Change password"

    public boolean contains(String s) {
        boolean bool = false;
        for (int i = 0; i < s.length(); i++) {
            if ((s.charAt(i) < 48) || (s.charAt(i) > 57 && s.charAt(i) < 65)
                    || (s.charAt(i) > 90 && s.charAt(i) < 97)
                    || (s.charAt(i) > 122)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        User u = (User) o;
        return (u.getUsername().equals(_name) && u.getPassword().equals(_password));
    }

    @Override
    public String toString() {
        return _name + " " + _password;
    }
}
