package edu.iis.mto.time;

import org.joda.time.DateTime;

public class FakeSystemClock {
    private DateTime dateTime;

    public FakeSystemClock() {
        this.dateTime = new DateTime();
    }

    public FakeSystemClock(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
