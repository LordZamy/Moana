package moanainc.com.moana.models;

/**
 * Created by darrion on 2/28/17.
 * << information holder >>
 */

public class User {
    private Account _account;
    private ReportManager _reportManager = new ReportManager();

    public User(String username, String password, String name, AccountType accountType) {
        _account = new Account(username, password, name, accountType);
    }

    public User(String username, String password) {
        _account = new Account(username, password);
    }

    public User() {
        _account = new Account();
    }

    public void createAvailReport(String reportName, String dateCreated, double lat, double lng, String statusOfReport) {
        Report newReport = new AvailReport(reportName, dateCreated, this, lat, lng, statusOfReport);
        _reportManager.createReport(newReport);
    }

    public Account getAccount() {
        return _account;
    }
    public void setAccount(Account newAccount) {
        _account = newAccount;
    }

    public ReportManager getReportManager(){ return _reportManager; }

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
