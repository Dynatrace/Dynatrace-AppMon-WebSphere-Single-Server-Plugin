package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ApplicationDataStats {

    public ApplicationDataStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getappName() {
        return appName;
    }

    public void setappName(String appName) {
        this.appName = appName;
    }

    public String getHighWaterMark() {
        return HighWaterMark;
    }

    public void setHighWaterMark(String HighWaterMark) {
        this.HighWaterMark = HighWaterMark;
    }

    public String getIntegral() {
        return Integral;
    }

    public void setIntegral(String Integral) {
        this.Integral = Integral;
    }

    public String getLowWaterMark() {
        return LowWaterMark;
    }

    public void setLowWaterMark(String LowWaterMark) {
        this.LowWaterMark = LowWaterMark;
    }
    
    public String getSessionsCurrent() {
        return SessionsCurrent;
    }

    public void setSessionsCurrent(String SessionsCurrent) {
        this.SessionsCurrent = SessionsCurrent;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
   
    
    String serverName = null;
    String appName;
    String HighWaterMark;
    String Integral;
    String LowWaterMark;
    String Enviro;
    String SessionsCurrent;
}
