package it.unibo.deathnote.impl;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.unibo.deathnote.api.DeathNote;

public class DeathNoteImpl implements DeathNote {
    private static long MAX_TIME_CAUSE=40L;   

    private class Entry {
        private String cause="Heart attack";
        private String details="";
        private long time;

        public Entry (){
            this.time=System.currentTimeMillis();
        }

        public void setCause(String cause){
            this.cause=cause;
        }

        public void setDetails(String details){
            this.details=details;
        }

        public String getCause(){
            return this.cause;
        }

        public long getTime(){
            return this.time;
        }

        public String getDetails(){
            return this.details;
        }
    }

    private Map<String,Entry> deathnote = new HashMap<>();
    private Entry thisEntry;

    @Override
    public String getRule(final int ruleNumber) {
        if (ruleNumber < 1 || ruleNumber > RULES.size()){
            throw new IllegalArgumentException("Invalid rule number");
        }
        else{
            return RULES.get(ruleNumber-1);
        }
    }
    
    @Override
    public void writeName(final String name) {
        if (name == null){
            throw new NullPointerException("Name is null");
        }
        else {
            thisEntry = new Entry();
            deathnote.put(name, thisEntry);
        }
    }

    @Override
    public boolean writeDeathCause(final String cause) {
        if (deathnote.isEmpty() || cause==null){
            throw new IllegalStateException("Cause null or deathnote is empty");
        }
        else {
            if (System.currentTimeMillis() - thisEntry.getTime() <= MAX_TIME_CAUSE){
                thisEntry.setCause(cause);
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public boolean writeDetails(final String details) {
        if (deathnote.isEmpty() || details==null){
            throw new IllegalStateException("Details null or deathnote is empty");
        }
        else {
            if (System.currentTimeMillis() - thisEntry.getTime() <= MAX_TIME_CAUSE*10){
                thisEntry.setDetails(details);
                return true;
            }
            else {
                return false;
            }
        }
    }

    @Override
    public String getDeathCause(String name) {
        if (!isNameWritten(name)){
            throw new IllegalArgumentException("Name not in deathnote");
        }
        else {
            return deathnote.get(name).getCause();
        }
    }

    @Override
    public String getDeathDetails(String name) {
        if (!isNameWritten(name)){
            throw new IllegalArgumentException("Name not in deathnote");
        }
        else {
            return deathnote.get(name).getDetails();
        }
    }

    @Override
    public boolean isNameWritten(String name) {
        if (deathnote.containsKey(name)){
            return true;
        }
        else {
            return false;
        }
    }
}
