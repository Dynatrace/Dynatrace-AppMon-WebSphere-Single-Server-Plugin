package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class WLServerStats {

    public WLServerStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getNodeStatus() {
        return NodeStatus;
    }

    public void setNodeStatus(String NodeStatus) {
        this.NodeStatus = NodeStatus;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }

    String serverName = null;
    String NodeStatus;
    String Enviro;
}
