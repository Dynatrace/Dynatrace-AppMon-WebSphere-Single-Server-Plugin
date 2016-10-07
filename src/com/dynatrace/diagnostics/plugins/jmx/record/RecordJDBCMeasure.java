package com.dynatrace.diagnostics.plugins.jmx.record;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCStats;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RecordJDBCMeasure implements WebSphereConstants {

	private static final Logger log = Logger.getLogger(RecordJDBCMeasure.class.getName());
	String measureName;
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<JDBCStats> ad)  throws Exception {
		
        try {
        	DynamicMeasure testing = new DynamicMeasure();
        	ArrayList<JDBCStats> list = ad;
            for (JDBCStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String JDBCName = fromStatic.getJDBCName();
                String Enviro = fromStatic.getEnviro();
                
                Double FreePoolCurrent = Double.parseDouble(fromStatic.getFreePoolCurrent());
                Double FreePoolHigh = Double.parseDouble(fromStatic.getFreePoolHigh());
                Double FreePoolIntegral = Double.parseDouble(fromStatic.getFreePoolIntegral());
                Double FreePoolLower = Double.parseDouble(fromStatic.getFreePoolLower());
                Double FreePoolSizeLow = Double.parseDouble(fromStatic.getFreePoolSizeLow());
                Double FreePoolUpper = Double.parseDouble(fromStatic.getFreePoolUpper());
                Double PercentUsedCurrent = Double.parseDouble(fromStatic.getPercentUsedCurrent());
                Double PercentUsedHigh = Double.parseDouble(fromStatic.getPercentUsedHigh());
                Double PercentUsedIntegral = Double.parseDouble(fromStatic.getPercentUsedIntegral());
                Double PercentUsedLow = Double.parseDouble(fromStatic.getPercentUsedLow());
                
                Double PoolSizeCurrent = Double.parseDouble(fromStatic.getPoolSizeCurrent());
                Double PoolSizeHigh = Double.parseDouble(fromStatic.getPoolSizeHigh());
                Double PoolSizeIntegral = Double.parseDouble(fromStatic.getPoolSizeIntegral());
                Double PoolSizeLow = Double.parseDouble(fromStatic.getPoolSizeLow());
                Double PoolSizeLower = Double.parseDouble(fromStatic.getPoolSizeLower());
                Double PoolSizeUpper = Double.parseDouble(fromStatic.getPoolSizeUpper());
                Double UseTime = Double.parseDouble(fromStatic.getUseTime());
                Double UseTimeAverage = Double.parseDouble(fromStatic.getUseTimeAverage());
                Double UseTimeAverageCount = Double.parseDouble(fromStatic.getUseTimeAverageCount());
                Double UseTimeAverageMax = Double.parseDouble(fromStatic.getUseTimeAverageMax());
                Double UseTimeAverageMin = Double.parseDouble(fromStatic.getUseTimeAverageMin());
                Double UseTimeAverageSumSq = Double.parseDouble(fromStatic.getUseTimeAverageSumSq());  
                
                Double UseTimeAverageTotal = Double.parseDouble(fromStatic.getUseTimeAverageTotal());
                Double UseTimeCount = Double.parseDouble(fromStatic.getUseTimeCount());
                Double UseTimeMax = Double.parseDouble(fromStatic.getUseTimeMax());
                Double UseTimeMin = Double.parseDouble(fromStatic.getUseTimeMin());
                Double UseTimeSumSq = Double.parseDouble(fromStatic.getUseTimeSumSq());
                Double UseTimeTotal = Double.parseDouble(fromStatic.getUseTimeTotal());
                Double WaitingThreadCurrent = Double.parseDouble(fromStatic.getWaitingThreadCurrent());
                Double WaitingThreadHigh = Double.parseDouble(fromStatic.getWaitingThreadHigh());
                Double WaitingThreadIntegral = Double.parseDouble(fromStatic.getWaitingThreadIntegral());
                Double WaitingThreadLow = Double.parseDouble(fromStatic.getWaitingThreadLow());
                Double WaitTime = Double.parseDouble(fromStatic.getWaitTime());
                Double WaitTimeAverage = Double.parseDouble(fromStatic.getWaitTimeAverage());            	
                
                Double WaitTimeAverageCount = Double.parseDouble(fromStatic.getWaitTimeAverageCount());
                Double WaitTimeAverageMax = Double.parseDouble(fromStatic.getWaitTimeAverageMax());
                Double WaitTimeAverageMin = Double.parseDouble(fromStatic.getWaitTimeAverageMin());
                Double WaitTimeAverageSumSq = Double.parseDouble(fromStatic.getWaitTimeAverageSumSq());
                Double WaitTimeAverageTotal = Double.parseDouble(fromStatic.getWaitTimeAverageTotal());
                Double WaitTimeCount = Double.parseDouble(fromStatic.getWaitTimeCount());
                Double WaitTimeMax = Double.parseDouble(fromStatic.getWaitTimeMax());
                Double WaitTimeMin = Double.parseDouble(fromStatic.getWaitTimeMin());
                Double WaitTimeSumSq = Double.parseDouble(fromStatic.getWaitTimeSumSq());
                Double WaitTimeTotal = Double.parseDouble(fromStatic.getWaitTimeTotal());
                Double createCount = Double.parseDouble(fromStatic.getcreateCount());
                Double closeCount = Double.parseDouble(fromStatic.getcloseCount());
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|createCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_CREATE_COUNT_METRIC, measureName, createCount);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|closeCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_CLOSE_COUNT_METRIC, measureName, closeCount);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FreePoolCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_FREE_POOL_CURRENT_METRIC, measureName, FreePoolCurrent);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FreePoolHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_FREE_POOL_HIGH_METRIC, measureName, FreePoolHigh);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FreePoolIntegral|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_FREE_POOL_INTEGRAL_METRIC, measureName, FreePoolIntegral);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FreePoolLower|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_FREE_POOL_LOWER_METRIC, measureName, FreePoolLower);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FreePoolSizeLow|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_FREE_POOL_LOW_METRIC, measureName, FreePoolSizeLow);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|FreePoolUpper|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_FREE_POOL_UPPER_METRIC, measureName, FreePoolUpper);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PercentUsedCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_PERCENT_USED_CURRENT_METRIC, measureName, PercentUsedCurrent);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PercentUsedHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_PERCENT_USED_HIGH_METRIC, measureName, PercentUsedHigh);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PercentUsedIntegral|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_PERCENT_USED_INTEGRAL_METRIC, measureName, PercentUsedIntegral);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PercentUsedLow|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_PERCENT_USED_LOW_METRIC, measureName, PercentUsedLow);            	
            	
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PoolSizeCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_POOL_SIZE_CURRENT_METRIC, measureName, PoolSizeCurrent);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PoolSizeHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_POOL_SIZE_HIGH_METRIC, measureName, PoolSizeHigh);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PoolSizeIntegral|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_POOL_SIZE_INTEGRAL_METRIC, measureName, PoolSizeIntegral);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PoolSizeLow|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_POOL_SIZE_LOW_METRIC, measureName, PoolSizeLow);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PoolSizeLower|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_POOL_SIZE_LOWER_METRIC, measureName, PoolSizeLower);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|PoolSizeUpper|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_POOL_SIZE_UPPER_METRIC, measureName, PoolSizeUpper);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTime|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_METRIC, measureName, UseTime);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeAverage|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_AVERAGE_METRIC, measureName, UseTimeAverage);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeAverageCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_AVERAGE_COUNT_METRIC, measureName, UseTimeAverageCount);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeAverageMax|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_AVERAGE_MAX_METRIC, measureName, UseTimeAverageMax);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeAverageMin|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_AVERAGE_MIN_METRIC, measureName, UseTimeAverageMin);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeAverageSumSq|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_AVERAGE_SUMSQ_METRIC, measureName, UseTimeAverageSumSq);             	
            	
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeAverageTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_AVERAGE_TOTAL_METRIC, measureName, UseTimeAverageTotal);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_COUNT_METRIC, measureName, UseTimeCount);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeMax|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_MAX_METRIC, measureName, UseTimeMax);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeMin|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_MIN_METRIC, measureName, UseTimeMin);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeSumSq|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_SUMSQ_METRIC, measureName, UseTimeSumSq);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|UseTimeTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_USE_TIME_TOTAL_METRIC, measureName, UseTimeTotal);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitingThreadCurrent|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAITING_THREAD_CURRENT_METRIC, measureName, WaitingThreadCurrent);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitingThreadHigh|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAITING_THREAD_HIGH_METRIC, measureName, WaitingThreadHigh);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitingThreadIntegral|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAITING_THREAD_INTEGRAL_METRIC, measureName, WaitingThreadIntegral);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitingThreadLow|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAITING_THREAD_LOW_METRIC, measureName, WaitingThreadLow);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTime|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_METRIC, measureName, WaitTime);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeAverage|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_AVERAGE_METRIC, measureName, WaitTimeAverage);            	
                
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeAverageCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_AVERAGE_COUNT_METRIC, measureName, WaitTimeAverageCount);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeAverageMax|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_AVERAGE_MAX_METRIC, measureName, WaitTimeAverageMax);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeAverageMin|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_AVERAGE_MIN_METRIC, measureName, WaitTimeAverageMin);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeAverageSumSq|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_AVERAGE_SUMSQ_METRIC, measureName, WaitTimeAverageSumSq);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeAverageTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_AVERAGE_TOTAL_METRIC, measureName, WaitTimeAverageTotal);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeCount|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_COUNT_METRIC, measureName, WaitTimeCount);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeMax|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_MAX_METRIC, measureName, WaitTimeMax);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeMin|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_MIN_METRIC, measureName, WaitTimeMin);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeSumSq|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_SUMSQ_METRIC, measureName, WaitTimeSumSq);
                measureName = WLServerName+"|JDBC|" + JDBCName + "|WaitTimeTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_JDBC_GROUP, WEBSPHERE_WAIT_TIME_TOTAL_METRIC, measureName, WaitTimeTotal);
            }
            
          } catch (Exception e) {
              log.warning("Record JDBC data: " + e);
          } 
	}
	
}
