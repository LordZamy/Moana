package moanainc.com.moana.models;

import android.content.Intent;

import moanainc.com.moana.controllers.HomeActivity;
import moanainc.com.moana.models.Account;

/**
 * Created by darrion on 2/28/17.
 * << information holder >>
 */

public class User {
    Account _account;
    ReportManager _reportManager;

    public User(String username, String password, String name, AccountType accountType) {
        _account = new Account(username, password, name, accountType);
    }

    public User(String username, String password) {
        _account = new Account(username, password);
    }

    public User() {
        _account = new Account();
    }

    public Report createAvailReport() {
        return new AvailReport();
    }

    public Account getAccount() {
        return _account;
    }
    public void setAccount(Account newAccount) {
        _account = newAccount;
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) {
            return false;
        }

        User u = (User) o;
        return (u.getAccount().getUsername().equals(_account.getUsername()) && u.getAccount().getPassword().equals(_account.getPassword()));
    }

    @Override
    public String toString() {
        return _account.getName() + " " + _account.getUsername() + " " + _account.getPassword() + " " + _account.getAccountType().toString();
    }

    // TODO: a method to pull a report submitted by this user from the report manager.
}
