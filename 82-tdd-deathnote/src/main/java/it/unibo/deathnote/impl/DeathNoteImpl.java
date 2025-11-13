package it.unibo.deathnote.impl;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

/**
 * Deathnote implementations.
 * The base entry has a heart attack and no details.
 * 
 * <p>
 * Each parameter is used and for different purposes:
 * cause used for the cause of the death,
 * details used to describe the details,
 * name is the name of the person to kill,
 * ruleNumber used for checking if rule is valid.
 * </p>
 * Decided to use a Map String,Entry as the structure.
 */
public final class DeathNoteImpl implements DeathNote {
    private static final long MAX_TIME_CAUSE = 40L;

    private final Map<String, Entry> deathnote = new HashMap<>();
    private Entry thisEntry;

    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()) {
            throw new IllegalArgumentException("Invalid rule number");
        } else {
            return RULES.get(ruleNumber - 1);
        }
    }

    @Override
    public void writeName(final String name) {
        if (name == null) {
            throw new NullPointerException("Name is null"); // NOPMD Specified by the assignment
        } else {
            thisEntry = new Entry();
            deathnote.put(name, thisEntry);
        }
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if (deathnote.isEmpty() || cause == null || thisEntry == null) {
            throw new IllegalStateException("Cause null or deathnote is empty or entry null");
        } else {
            if (System.currentTimeMillis() - thisEntry.getTime() <= MAX_TIME_CAUSE) {
                thisEntry.setCause(cause);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean writeDetails(final String details) {
        if (deathnote.isEmpty() || details == null || thisEntry == null) {
            throw new IllegalStateException("Details null or deathnote is empty or entry null");
        } else {
            if (System.currentTimeMillis() - thisEntry.getTime() <= MAX_TIME_CAUSE * 10) {
                thisEntry.setDetails(details);
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public String getDeathCause(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("Name not in deathnote");
        } else {
            return deathnote.get(name).getCause();
        }
    }

    @Override
    public String getDeathDetails(final String name) {
        if (!isNameWritten(name)) {
            throw new IllegalArgumentException("Name not in deathnote");
        } else {
            return deathnote.get(name).getDetails();
        }
    }

    @Override
    public boolean isNameWritten(final String name) {
        return deathnote.containsKey(name);
    }

    private class Entry {
        private String cause = "Heart attack";
        private String details = "";
        private final long time;

        Entry() {
            this.time = System.currentTimeMillis();
        }

        public void setCause(final String cause) {
            this.cause = cause;
        }

        public void setDetails(final String details) {
            this.details = details;
        }

        public String getCause() {
            return this.cause;
        }

        public long getTime() {
            return this.time;
        }

        public String getDetails() {
            return this.details;
        }
    }
}
