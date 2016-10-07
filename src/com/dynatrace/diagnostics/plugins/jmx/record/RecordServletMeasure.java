package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ServletStats;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordServletMeasure implements WebSphereConstants {
	private static final Logger log = Logger.getLogger(RecordServletMeasure.class.getName());
	String measureName;
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ServletStats> ad)  throws Exception {
		
		try {
			DynamicMeasure testing = new DynamicMeasure();
            ArrayList<ServletStats> list = ad;
            for (ServletStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String Enviro = fromStatic.getEnviro();
                String servletName = fromStatic.getservletName();
                Double TimeAvgStatistic = Double.parseDouble(fromStatic.getTimeAvgStatistic());
                Double TimeAvgMin = Double.parseDouble(fromStatic.getTimeAvgMin());
                Double TimeAvgMax = Double.parseDouble(fromStatic.getTimeAvgMax());
                Double TimeAvgTotal = Double.parseDouble(fromStatic.getTimeAvgTotal());
                Double TimeAvgCount = Double.parseDouble(fromStatic.getTimeAvgCount());
                Double TimeAvgSumSq = Double.parseDouble(fromStatic.getTimeAvgSumSq());                
                Double AverageAvgStatistic = Double.parseDouble(fromStatic.getAverageAvgStatistic());
                Double AverageAvgMin = Double.parseDouble(fromStatic.getAverageAvgMin());
                Double AverageAvgMax = Double.parseDouble(fromStatic.getAverageAvgMax());
                Double AverageAvgTotal = Double.parseDouble(fromStatic.getAverageAvgTotal());
                Double AverageAvgCount = Double.parseDouble(fromStatic.getAverageAvgCount());
                Double AverageAvgSumSq = Double.parseDouble(fromStatic.getAverageAvgSumSq());
                Double TotalRequests = Double.parseDouble(fromStatic.getTotalRequest());
                
                measureName = WLServerName+"|Servlet|" + servletName +"|AverageAvgMax|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_AVERAGE_STATISTIC_MAX_METRIC, measureName, AverageAvgMax);
                measureName = WLServerName+"|Servlet|" + servletName +"|AverageAvgMin|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_AVERAGE_STATISTIC_MIN_METRIC, measureName, AverageAvgMin);
                measureName = WLServerName+"|Servlet|" + servletName +"|AverageAvgStatistic|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_AVERAGE_STATISTIC_AVG_METRIC, measureName, AverageAvgStatistic);
                measureName = WLServerName+"|Servlet|" + servletName +"|AverageAvgSumSq|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_AVERAGE_STATISTIC_SUMSQ_METRIC, measureName, AverageAvgSumSq);
                measureName = WLServerName+"|Servlet|" + servletName +"|AverageAvgTotal|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_AVERAGE_STATISTIC_TOTAL_METRIC, measureName, AverageAvgTotal);
                measureName = WLServerName+"|Servlet|" + servletName +"|AverageAvgCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_AVERAGE_STATISTIC_AVERAGE_METRIC, measureName, AverageAvgCount);
                measureName = WLServerName+"|Servlet|" + servletName +"|TimeAvgMax|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_TIME_STATISTIC_MAX_METRIC, measureName, TimeAvgMax);
                measureName = WLServerName+"|Servlet|" + servletName +"|TimeAvgMin|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_TIME_STATISTIC_MIN_METRIC, measureName, TimeAvgMin);
                measureName = WLServerName+"|Servlet|" + servletName +"|TimeAvgStatistic|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_TIME_STATISTIC_AVG_METRIC, measureName, TimeAvgStatistic);
                measureName = WLServerName+"|Servlet|" + servletName +"|TimeAvgSumSq|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_TIME_STATISTIC_SUMSQ_METRIC, measureName, TimeAvgSumSq);
                measureName = WLServerName+"|Servlet|" + servletName +"|TimeAvgTotal|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_TIME_STATISTIC_TOTAL_METRIC, measureName, TimeAvgTotal);
                measureName = WLServerName+"|Servlet|" + servletName +"|TimeAvgCount|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_TIME_STATISTIC_COUNT_METRIC, measureName, TimeAvgCount);
                measureName = WLServerName+"|Servlet|" + servletName +"|TotalRequests|" + Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVLET_GROUP, WEBSPHERE_TOTAL_REQUEST_COUNT_METRIC, measureName, TotalRequests);
            }
		
		} catch (Exception e) {
            log.info("Record Servlet Data: " + e);
        } 
	}
}
