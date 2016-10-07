package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataStats;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;


public class RecordApplicationDataMeasure implements WebSphereConstants{
	private static final Logger log = Logger.getLogger(RecordApplicationDataMeasure.class.getName());
	String measureName;
	MonitorEnvironment env;
	

	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ApplicationDataStats> ad)  throws Exception {
		env=envs;
		try {
			DynamicMeasure testing = new DynamicMeasure();
			
            for (ApplicationDataStats fromStatic : ad) {
                Double HighWaterMark = Double.parseDouble(fromStatic.getHighWaterMark());
                Double Integral = Double.parseDouble(fromStatic.getIntegral());
                String Enviro = fromStatic.getEnviro();
                Double LowWaterMark = Double.parseDouble(fromStatic.getLowWaterMark());
                Double SessionsCurrent = Double.parseDouble(fromStatic.getSessionsCurrent());
                String WLServerName = fromStatic.getserverName();
                String appName = fromStatic.getappName();
                
                measureName = WLServerName+"|ApplicationData|" + appName + "|HighWaterMark|"+Enviro;
                testing.populateDynamicMeasure(env, WEBSPHERE_APPLICATIONDATA_GROUP, WEBSPHERE_HIGH_WATER_MARK_METRIC, measureName, HighWaterMark);
                measureName = WLServerName+"|ApplicationData|" + appName + "|Integral|"+Enviro;
                testing.populateDynamicMeasure(env, WEBSPHERE_APPLICATIONDATA_GROUP, WEBSPHERE_INTEGRAL_METRIC, measureName, Integral);
                measureName = WLServerName+"|ApplicationData|" + appName + "|LowWaterMark|"+Enviro;
                testing.populateDynamicMeasure(env, WEBSPHERE_APPLICATIONDATA_GROUP, WEBSPHERE_LOW_WATER_MARK_METRIC, measureName, LowWaterMark);
                measureName = WLServerName+"|ApplicationData|" + appName + "|SessionsCurrent|"+Enviro;
                testing.populateDynamicMeasure(env, WEBSPHERE_APPLICATIONDATA_GROUP, WEBSPHERE_SESSIONS_CURRENT_METRIC, measureName, SessionsCurrent);
            }
            
		} catch (Exception e) {
            log.warning("Record Application Data: " + e);
        }
	}
}
