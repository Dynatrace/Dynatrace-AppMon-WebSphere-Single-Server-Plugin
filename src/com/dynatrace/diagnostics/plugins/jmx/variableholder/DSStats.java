package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class DSStats {

    public DSStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getJDBCName() {
        return JDBCName;
    }

    public void setJDBCName(String JDBCName) {
        this.JDBCName = JDBCName;
    }

    public String getStatus() {
        return Status;
    }

    public void setstate(String Status) {
        this.Status = Status;
    }


    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    String timeDateConverted;

    String serverName = null;
    String JDBCName;

    String Status;

    String Enviro;
}
