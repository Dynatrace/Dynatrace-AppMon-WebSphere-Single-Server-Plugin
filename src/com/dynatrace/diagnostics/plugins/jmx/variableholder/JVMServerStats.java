package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class JVMServerStats {

    public String gettimeDateConverted() {
        return timeDateConverted;
    }

    public void settimeDateConverted(String timeDateConverted) {
        this.timeDateConverted = timeDateConverted;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }


    public String getJVMCPUUsage() {
        return JVMCPUUsage;
    }

    public void setJVMCPUUsage(String JVMCPUUsage) {
        this.JVMCPUUsage = JVMCPUUsage;
    }

    public String getJVMCurrent() {
        return JVMCurrent;
    }

    public void setJVMCurrent(String JVMCurrent) {
        this.JVMCurrent = JVMCurrent;
    }

    public String getJVMHigh() {
        return JVMHigh;
    }

    public void setJVMHigh(String JVMHigh) {
        this.JVMHigh = JVMHigh;
    }

    public String getJVMIntegral() {
        return JVMIntegral;
    }

    public void setJVMIntegral(String JVMIntegral) {
        this.JVMIntegral = JVMIntegral;
    }

    public String getJVMLow() {
        return JVMLow;
    }

    public void setJVMLow(String JVMLow) {
        this.JVMLow = JVMLow;
    }

    public String getJVMLowerBound() {
        return JVMLowerBound;
    }

    public void setJVMLowerBound(String JVMLowerBound) {
        this.JVMLowerBound = JVMLowerBound;
    }

    public String getJVMUpperBound() {
        return JVMUpperBound;
    }

    public void setJVMUpperBound(String JVMUpperBound) {
        this.JVMUpperBound = JVMUpperBound;
    }

    public String getJVMUptime() {
        return JVMUptime;
    }

    public void setJVMUptime(String JVMUptime) {
        this.JVMUptime = JVMUptime;
    }

    public String getJVMUsedMemory() {
        return JVMUsedMemory;
    }

    public void setJVMUsedMemory(String JVMUsedMemory) {
        this.JVMUsedMemory = JVMUsedMemory;
    }

    public String getWaitTimeTotal() {
        return WaitTimeTotal;
    }

    public void setWaitTimeTotal(String WaitTimeTotal) {
        this.WaitTimeTotal = WaitTimeTotal;
    }


    
    
    

    String timeDateConverted;
    String serverName = null;
    String hostname;
    String Enviro;

    String JVMCPUUsage;
    String JVMCurrent;
    String JVMHigh;
    String JVMIntegral;
    String JVMLow;
    String JVMLowerBound;
    String JVMUpperBound;
    String JVMUptime;
    String JVMUsedMemory;
    String WaitTimeTotal;    
    
    
	
}
