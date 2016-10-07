package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.DSStats;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RecordDSMeasure implements WebSphereConstants {

	private static final Logger log = Logger.getLogger(RecordDSMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<DSStats> ad)  throws Exception {
		
        try {
        	DynamicMeasure testing = new DynamicMeasure();
        	ArrayList<DSStats> list = ad;
            for (DSStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String JDBCName = fromStatic.getJDBCName();
                Long Status = Long.parseLong(fromStatic.getStatus());
                String Enviro = fromStatic.getEnviro();
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|Status|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_DATASOURCE_GROUP, WEBSPHERE_STATUS_METRIC, measureName, (double)Status);
            }
            
         } catch (Exception e) {
             log.warning("Record DataSource data: " + e);
         } 
	}
	
}
