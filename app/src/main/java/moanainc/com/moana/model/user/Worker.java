package moanainc.com.moana.model.user;

import java.util.Date;

import moanainc.com.moana.firebase.FirebaseInterface;
import moanainc.com.moana.model.report.PurityCondition;
import moanainc.com.moana.model.report.PurityReport;

/**
 * Created by darrion on 2/28/17.
 * << information holder >>
 */

public class Worker extends User {

    public Worker(String username, String name, AccountType accountType) {
        super(username, "", name, accountType);
    }

    public void createPurityReport(String reportName, Date dateCreated, double lat, double lng, PurityCondition condition, int virusPPM, int contaminationPPM) {
        FirebaseInterface.addPurityReport(new PurityReport(reportName, dateCreated, this, lat, lng, condition, virusPPM, contaminationPPM));
    }

    public void createHistoryReport(String reportName, Date dateCreated, double lat, double lng, PurityCondition condition, int virusPPM, int contaminationPPM) {
        FirebaseInterface.addHistoricalReport(new PurityReport(reportName, dateCreated, this, lat, lng, condition, virusPPM, contaminationPPM));
    }
}
