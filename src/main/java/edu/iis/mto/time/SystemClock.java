package edu.iis.mto.time;

import org.joda.time.DateTime;

public class SystemClock {
    private DateTime dateTime;

    public SystemClock() {
        this.dateTime = new DateTime();
    }

    public SystemClock(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
