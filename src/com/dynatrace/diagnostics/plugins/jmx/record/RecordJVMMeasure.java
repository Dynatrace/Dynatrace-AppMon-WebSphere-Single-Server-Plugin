package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMServerStats;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RecordJVMMeasure implements WebSphereConstants {
	
	private static final Logger log = Logger.getLogger(RecordJVMMeasure.class.getName());
	
	public void WriteToDT (MonitorEnvironment envs,ArrayList<JVMServerStats> ad)  throws Exception {
		try {
			DynamicMeasure testing = new DynamicMeasure();
            ArrayList<JVMServerStats> list = ad;
            for (JVMServerStats fromStatic : list) {
                String WLServerName = fromStatic.getServerName();
                String Enviro = fromStatic.getEnviro();
                Double JVMCPUUsage = Double.parseDouble(fromStatic.getJVMCPUUsage());
                Double JVMCurrent = Double.parseDouble(fromStatic.getJVMCurrent());
                Double JVMHigh = Double.parseDouble(fromStatic.getJVMHigh());
                Double JVMIntegral = Double.parseDouble(fromStatic.getJVMIntegral());
                Double JVMLow = Double.parseDouble(fromStatic.getJVMLow());
                Double JVMLowerBound = Double.parseDouble(fromStatic.getJVMLowerBound());
                Double JVMUpperBound = Double.parseDouble(fromStatic.getJVMUpperBound());
                Double JVMUptime = Double.parseDouble(fromStatic.getJVMUptime());
                Double JVMUsedMemory = Double.parseDouble(fromStatic.getJVMUsedMemory());
                
                String measureName = WLServerName+"|JVM|JVMCPUUsage|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_CPU_USAGE_METRIC, measureName, JVMCPUUsage);
                measureName = WLServerName+"|JVM|JVMCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_CURRENT_METRIC, measureName, JVMCurrent);
                measureName = WLServerName+"|JVM|JVMHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_HIGH_METRIC, measureName, JVMHigh);
                measureName = WLServerName+"|JVM|JVMIntegral|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_INTEGRAL_METRIC, measureName, JVMIntegral);
                measureName = WLServerName+"|JVM|JVMLow|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_LOW_METRIC, measureName, JVMLow);
                measureName = WLServerName+"|JVM|JVMLowerBound|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_LOWER_BOUND_METRIC, measureName, JVMLowerBound);
                measureName = WLServerName+"|JVM|JVMUpperBound|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_UPPER_BOUND_METRIC, measureName, JVMUpperBound);
                measureName = WLServerName+"|JVM|JVMUptime|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_UPTIME_METRIC, measureName, JVMUptime);
                measureName = WLServerName+"|JVM|JVMUsedMemory|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JVM_GROUP, WEBSPHERE_JVM_USED_MEMORY_METRIC, measureName, JVMUsedMemory);
            }
		} catch (Exception e) {
            log.warning("Record JVM data: " + e);
        } 
	}
}
