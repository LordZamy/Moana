package moanainc.com.moana.model.report;

import java.io.Serializable;

public enum PurityCondition implements Serializable {
    SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

    /**
     * Created by reecedantin on 3/16/17.
     */
    private final String text;

    PurityCondition(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}