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

    public Report createAvidReport() {
        return new AvidReport();
    }

    // TODO: a method to pull a report submitted by this user from the report manager.
}
