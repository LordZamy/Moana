package moanainc.com.moana.model.user;

/**
 * Created by darrion on 2/28/17.
 * << information holder >>
 */

public class Manager extends Worker {

    public Manager(String username, String name) {
        super(username, "", name, AccountType.MANAGER);
    }

}
