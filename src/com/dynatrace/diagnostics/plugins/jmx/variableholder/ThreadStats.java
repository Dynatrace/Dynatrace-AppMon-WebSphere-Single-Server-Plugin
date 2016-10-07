package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ThreadStats {

    public ThreadStats() {
        super();
    }

    public String gettimeDateConverted() {
        return timeDateConverted;
    }

    public void settimeDateConverted(String timeDateConverted) {
        this.timeDateConverted = timeDateConverted;
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    public String getThreadActiveCount() {
        return ThreadActiveCount;
    }

    public void setThreadActiveCount(String ThreadActiveCount) {
        this.ThreadActiveCount = ThreadActiveCount;
    }

    public String getThreadActiveCountLow() {
        return ThreadActiveCountLow;
    }

    public void setThreadActiveCountLow(String ThreadActiveCountLow) {
        this.ThreadActiveCountLow = ThreadActiveCountLow;
    }

    public String getThreadActiveCountHigh() {
        return ThreadActiveCountHigh;
    }

    public void setThreadActiveCountHigh(String ThreadActiveCountHigh) {
        this.ThreadActiveCountHigh = ThreadActiveCountHigh;
    }

    public String getThreadPoolSizeCurrent() {
        return ThreadPoolSizeCurrent;
    }

    public void setThreadPoolSizeCurrent(String ThreadPoolSizeCurrent) {
        this.ThreadPoolSizeCurrent = ThreadPoolSizeCurrent;
    }

    public String getThreadPoolSizeHigh() {
        return ThreadPoolSizeHigh;
    }

    public void setThreadPoolSizeHigh(String ThreadPoolSizeHigh) {
        this.ThreadPoolSizeHigh = ThreadPoolSizeHigh;
    }

    public String getThreadPoolSizeLow() {
        return ThreadPoolSizeLow;
    }

    public void setThreadPoolSizeLow(String ThreadPoolSizeLow) {
        this.ThreadPoolSizeLow = ThreadPoolSizeLow;
    }

    public String getThreadPoolSizeLower() {
        return ThreadPoolSizeLower;
    }

    public void setThreadPoolSizeLower(String ThreadPoolSizeLower) {
        this.ThreadPoolSizeLower = ThreadPoolSizeLower;
    }

    public String getThreadPoolSizeUpper() {
        return ThreadPoolSizeUpper;
    }

    public void setThreadPoolSizeUpper(String ThreadPoolSizeUpper) {
        this.ThreadPoolSizeUpper = ThreadPoolSizeUpper;
    }
    public String getThreadName() {
        return ThreadName;
    }

    public void setThreadName(String ThreadName) {
        this.ThreadName = ThreadName;
    }
    
    String ThreadName;
    String timeDateConverted;
    String serverName = null;
    String Enviro;
    String ThreadActiveCount;
    String ThreadActiveCountLow;
    String ThreadActiveCountHigh;
    String ThreadPoolSizeCurrent;
    String ThreadPoolSizeHigh;
    String ThreadPoolSizeLow;
    String ThreadPoolSizeLower;
    String ThreadPoolSizeUpper;    
    
    
}

