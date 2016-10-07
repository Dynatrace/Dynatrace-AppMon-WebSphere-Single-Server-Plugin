package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class TransactionStats {

    public TransactionStats() {
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
    
    public String getActiveTransactionsCount() {
        return ActiveTransactionsCount;
    }

    public void setActiveTransactionsCount(String ActiveTransactionsCount) {
        this.ActiveTransactionsCount = ActiveTransactionsCount;
    }

    public String getTransactionRolledBackCount() {
        return TransactionRolledBackCount;
    }

    public void setTransactionRolledBackCount(String TransactionRolledBackCount) {
        this.TransactionRolledBackCount = TransactionRolledBackCount;
    }

    public String getTransactionCommittedCount() {
        return TransactionCommittedCount;
    }

    public void setTransactionCommittedCount(String TransactionCommittedCount) {
        this.TransactionCommittedCount = TransactionCommittedCount;
    }    
    
    
    
    
    String timeDateConverted;
    String serverName = null;
    String Enviro;
    String ActiveTransactionsCount;
    String TransactionRolledBackCount;
    String TransactionCommittedCount;
    
}

