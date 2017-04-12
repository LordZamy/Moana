package moanainc.com.moana.model.user;

import java.util.Date;

import moanainc.com.moana.firebase.FirebaseInterface;
import moanainc.com.moana.model.ReportManager;
import moanainc.com.moana.model.report.AvailReport;

/**
 * Created by darrion on 2/28/17.
 * << information holder >>
 */

public class User {
    private Account _account;

    public User(String username, String password, String name, AccountType accountType) {
        _account = new Account(username, password, name, accountType);
    }

    public User(String username, String password) {
        _account = new Account(username, password);
    }

    public User() {
        _account = new Account();
    }

    public void createAvailReport(String reportName, Date dateCreated, double lat, double lng, String statusOfReport) {
        FirebaseInterface.addAvailabilityReport(new AvailReport(reportName, dateCreated, this, lat, lng, statusOfReport));
    }

    public Account getAccount() {
        return _account;
    }
    public void setAccount(Account newAccount) {
        _account = newAccount;
    }

    public ReportManager getReportManager() {
        return null;
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

}
