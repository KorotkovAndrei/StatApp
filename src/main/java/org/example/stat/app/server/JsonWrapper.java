package org.example.stat.app.server;

public class JsonWrapper {

    private String mostSpeeches;
    private String mostSecurity;
    private String leastWordy;
    private String exception;

    public JsonWrapper(String mostSpeeches, String mostSecurity, String leastWordy) {
        this.mostSpeeches = mostSpeeches;
        this.mostSecurity = mostSecurity;
        this.leastWordy = leastWordy;
    }

    public JsonWrapper(String exception) {
        this.exception = exception;
    }

    public String getMostSpeeches() {
        return mostSpeeches;
    }

    public void setMostSpeeches(String mostSpeeches) {
        this.mostSpeeches = mostSpeeches;
    }

    public String getMostSecurity() {
        return mostSecurity;
    }

    public void setMostSecurity(String mostSecurity) {
        this.mostSecurity = mostSecurity;
    }

    public String getLeastWordy() {
        return leastWordy;
    }

    public void setLeastWordy(String leastWordy) {
        this.leastWordy = leastWordy;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
