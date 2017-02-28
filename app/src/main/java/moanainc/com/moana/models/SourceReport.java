package moanainc.com.moana.models;

/**
 * Created by darrion on 2/28/17.
 */

public class SourceReport implements Report {
    @Override
    public Report createReport() {
        return new SourceReport();
    }
}
