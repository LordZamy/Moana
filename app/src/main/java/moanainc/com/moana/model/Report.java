package moanainc.com.moana.model;

import java.util.Date;

import moanainc.com.moana.model.user.User;

/**
 * Created by darrion on 2/28/17.
 */

public interface Report {

    String getName();
    Date getDate();
    User getCreator();
    Report createReport();
    double getLat();
    double getLng();
}
