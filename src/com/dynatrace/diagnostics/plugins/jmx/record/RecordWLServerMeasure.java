package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerStats;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;

public class RecordWLServerMeasure implements WebSphereConstants {
	
	private static final Logger log = Logger.getLogger(RecordWLServerMeasure.class.getName());
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<WLServerStats> ad)  throws Exception {
		try {
			DynamicMeasure testing = new DynamicMeasure();
			ArrayList<WLServerStats> list = ad;
            for (WLServerStats fromStatic : list) {
            	String Enviro = fromStatic.getEnviro();
                String WLServerName = fromStatic.getserverName();
                String NodeStatus = fromStatic.getNodeStatus();
                Double s = 0.0;
                if(NodeStatus.equalsIgnoreCase("STARTED")){
                	s = 1.0;
                }
                
                String measureName = WLServerName+"|NodeStatus|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_SERVER_GROUP, WEBSPHERE_NODE_STATUS_METRIC, measureName, s);
            }
		} catch (Exception e) {
            log.warning("Record Server data: " + e);
        } 
	}
}
