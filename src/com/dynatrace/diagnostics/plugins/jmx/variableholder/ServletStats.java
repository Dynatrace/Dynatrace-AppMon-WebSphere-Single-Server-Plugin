package com.dynatrace.diagnostics.plugins.jmx.variableholder;

public class ServletStats {

    public ServletStats() {
        super();
    }

    public String getserverName() {
        return serverName;
    }

    public void setserverName(String serverName) {
        this.serverName = serverName;
    }

    public String getAverageAvgStatistic() {
        return AverageAvgStatistic;
    }
    public void setAverageAvgStatistic(String AverageAvgStatistic) {
        this.AverageAvgStatistic = AverageAvgStatistic;
    }
    public String getAverageAvgMin() {
        return AverageAvgMin;
    }
    public void setAverageAvgMin(String AverageAvgMin) {
        this.AverageAvgMin = AverageAvgMin;
	}

    public String getAverageAvgMax() {
        return AverageAvgMax;
    }
    public void setAverageAvgMax(String AverageAvgMax) {
        this.AverageAvgMax = AverageAvgMax;
    }

    public String getAverageAvgTotal() {
        return AverageAvgTotal;
    }
    public void setAverageAvgTotal(String AverageAvgTotal) {
        this.AverageAvgTotal = AverageAvgTotal;
    }

    public String getAverageAvgCount() {
        return AverageAvgCount;
    }
    public void setAverageAvgCount(String AverageAvgCount) {
        this.AverageAvgCount = AverageAvgCount;
    }

    public String getAverageAvgSumSq() {
        return AverageAvgSumSq;
    }
    public void setAverageAvgSumSq(String AverageAvgSumSq) {
        this.AverageAvgSumSq = AverageAvgSumSq;
    }
    public String getTimeAvgStatistic() {
        return TimeAvgStatistic;
    }
    public void setTimeAvgStatistic(String TimeAvgStatistic) {
        this.TimeAvgStatistic = TimeAvgStatistic;
    }
    public String getTimeAvgMin() {
        return TimeAvgMin;
    }
    public void setTimeAvgMin(String TimeAvgMin) {
        this.TimeAvgMin = TimeAvgMin;
	}

    public String getTimeAvgMax() {
        return TimeAvgMax;
    }
    public void setTimeAvgMax(String TimeAvgMax) {
        this.TimeAvgMax = TimeAvgMax;
    }

    public String getTimeAvgTotal() {
        return TimeAvgTotal;
    }
    public void setTimeAvgTotal(String TimeAvgTotal) {
        this.TimeAvgTotal = TimeAvgTotal;
    }

    public String getTimeAvgCount() {
        return TimeAvgCount;
    }
    public void setTimeAvgCount(String TimeAvgCount) {
        this.TimeAvgCount = TimeAvgCount;
    }

    public String getTimeAvgSumSq() {
        return TimeAvgSumSq;
    }
    public void setTimeAvgSumSq(String TimeAvgSumSq) {
        this.TimeAvgSumSq = TimeAvgSumSq;
    }
    public String getservletName() {
        return servletName;
    }
    public void setservletName(String servletName) {
        this.servletName = servletName;
    }

    public String getEnviro() {
        return Enviro;
    }

    public void setEnviro(String Enviro) {
        this.Enviro = Enviro;
    }
    public String getTotalRequest() {
        return TotalRequest;
    }

    public void setTotalRequest(String TotalRequest) {
        this.TotalRequest = TotalRequest;
    }    
    String serverName = null;
    String Enviro;
    String AverageAvgStatistic;
    String AverageAvgMin;
    String AverageAvgMax;
    String AverageAvgTotal;
    String AverageAvgCount;
    String AverageAvgSumSq;
    String TimeAvgStatistic;
    String TimeAvgMin;
    String TimeAvgMax;
    String TimeAvgTotal;
    String TimeAvgCount;
    String TimeAvgSumSq;
    String TotalRequest;
    String servletName;
    
    
}
