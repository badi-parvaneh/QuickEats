package com.example.a160final;

import java.util.Date;

/**
 * Created by badiparvaneh on 5/1/18.
 */

public class Status {
    private Date statusSet;
    private String message;

    public Status(Date statusSet, String message) {
        this.statusSet = statusSet;
        this.message = message;
    }

    public Date getStatusSet() {
        return statusSet;
    }

    public String getMessage() {
        return message;
    }

    public void setStatusSet(Date statusSet) {
        this.statusSet = statusSet;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
