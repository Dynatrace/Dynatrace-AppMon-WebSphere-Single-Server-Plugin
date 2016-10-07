package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadStats;


public class RecordThreadMeasure implements WebSphereConstants {

	private static final Logger log = Logger.getLogger(RecordThreadMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<ThreadStats> ad)  throws Exception {
		DynamicMeasure testing = new DynamicMeasure();
		try {
            ArrayList<ThreadStats> list = ad;
            for (ThreadStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String Enviro = fromStatic.getEnviro();
                Double ThreadActiveCount = Double.parseDouble(fromStatic.getThreadActiveCount());
                Double ThreadActiveCountLow = Double.parseDouble(fromStatic.getThreadActiveCountLow());
                Double ThreadActiveCountHigh = Double.parseDouble(fromStatic.getThreadActiveCountHigh());
                Double ThreadPoolSizeCurrent = Double.parseDouble(fromStatic.getThreadPoolSizeCurrent());
                Double ThreadPoolSizeHigh = Double.parseDouble(fromStatic.getThreadPoolSizeHigh());
                Double ThreadPoolSizeLow = Double.parseDouble(fromStatic.getThreadPoolSizeLow());
                Double ThreadPoolSizeLower = Double.parseDouble(fromStatic.getThreadPoolSizeLower());
                Double ThreadPoolSizeUpper = Double.parseDouble(fromStatic.getThreadPoolSizeUpper());                
                String ThreadName = fromStatic.getThreadName();
                
                measureName = WLServerName+"|"+ThreadName+"|ThreadActiveCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_ACTIVE_COUNT_METRIC, measureName, ThreadActiveCount);
                measureName = WLServerName+"|"+ThreadName+"|ThreadActiveCountLow|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_ACTIVE_COUNT_HIGH_METRIC, measureName, ThreadActiveCountLow);
                measureName = WLServerName+"|"+ThreadName+"|ThreadActiveCountHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_ACTIVE_COUNT_lOW_METRIC, measureName, ThreadActiveCountHigh);
                measureName = WLServerName+"|"+ThreadName+"|ThreadPoolSizeCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_POOL_SIZE_CURRENT_METRIC, measureName, ThreadPoolSizeCurrent);
                measureName = WLServerName+"|"+ThreadName+"|ThreadPoolSizeHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_POOL_SIZE_HIGH_METRIC, measureName, ThreadPoolSizeHigh);
                measureName = WLServerName+"|"+ThreadName+"|ThreadPoolSizeLow|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_POOL_SIZE_LOW_METRIC, measureName, ThreadPoolSizeLow);
                measureName = WLServerName+"|"+ThreadName+"|ThreadPoolSizeLower|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_POOL_SIZE_LOWER_METRIC, measureName, ThreadPoolSizeLower);
                measureName = WLServerName+"|"+ThreadName+"|ThreadPoolSizeUpper|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_THREAD_GROUP, WEBSPHERE_THREAD_POOL_SIZE_UPPER_METRIC, measureName, ThreadPoolSizeUpper);
            }
		} catch (Exception e) {
            log.warning("Record Thread data: " + e);
        }
	}
}
