package de.schdef.slash.coding.support.time;

public class StandardTimeService
    implements TimeService {

    @Override
    public long currentTimeInMillis() {
        return System.currentTimeMillis();
    }

}
