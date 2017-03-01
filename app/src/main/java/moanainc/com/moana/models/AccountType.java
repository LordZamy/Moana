package moanainc.com.moana.models;

import java.io.Serializable;

/**
 * Created by josh baldwin on 2/21/2017.
 */

public enum AccountType implements Serializable {
    USER("User"), WORKER("Worker"), MANAGER("Manager"), ADMIN("Admin");

    private final String text;

    AccountType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}