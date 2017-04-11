package moanainc.com.moana.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import moanainc.com.moana.model.report.PurityReport;
import moanainc.com.moana.model.user.User;

/**
 * Created by josh baldwin on 2/13/2017.
 */

public class Model {
    private static final Model _instance = new Model();
    private User currentUser;
    public static Model getInstance() { return _instance; }

    ArrayList<Report> reports = new ArrayList<Report>();
    ArrayList<PurityReport> purityReports = new ArrayList<>();

    private Map<String, User> _users;

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
    public Map<String, User> getUsers() { return _users; }

    /**
     * Add a test user
     */
    public void testUser() {
        User datBoi = new User("George P. Burdell", "jackets7");
        _users.put("Georgia P. Burdell", datBoi);
    }

    /**
     * Add a new user to the list.....is this needed??? since arraylist has add method
     */
    public void addUser(User u) {
        _users.put(u.getAccount().getUsername(), u);
    }

    /**
     * Find the cause of a failed registration
     */
    public void registrationFailed(User u) {
        if (_users.containsValue(u)) {
            //user already exists
        } else if (!_users.containsValue(u)) {
            //user input an unacceptable password
        }
    }

    public User findUserByUsername(String username) {
        return _users.get(username);
    }

    public boolean userExists(String username) {
        return _users.containsKey(username);
    }

    public User getCurrentUser() { return currentUser; }
    public void setCurrentUser(User user) { currentUser = user; }

    public void addReport(Report report) {
        reports.add(report);
    }
    public void addPurityReport(PurityReport report) { purityReports.add(report); }

    public ArrayList<Report> getReports() {
        return reports;
    }
    public ArrayList<PurityReport> getPurityReports() { return purityReports; }

}
