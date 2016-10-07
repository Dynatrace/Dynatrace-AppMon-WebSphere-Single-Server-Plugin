package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCTimeHolder;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;
import java.util.Set;
import java.util.Iterator;

import com.ibm.websphere.management.AdminClient;

public class JDBC implements Callable<ArrayList<JDBCStats>>, WebSphereConstants {

	private ArrayList<JDBCStats> test;
	private ObjectName server;
	
	public JDBC(ArrayList<JDBCStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(JDBC.class.getName());
	private ArrayList<JDBCStats> statsList = new ArrayList<JDBCStats>();
    
    JDBCTimeHolder ad = new JDBCTimeHolder();
    AdminClient connections;
    String name;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;
    

    public JDBC(AdminClient connection, MonitorEnvironment env, String environment, String serverName,String[] monitorData) {
    	envs = env;
        name = serverName;
        connections = connection;
        environments=environment;
        dataMonitor=monitorData;
    }

    
    public String parseData(String phrase, int lineNum, int valNum){
    	String valueInt = "0";
    	try {  
    	  String lines[] = phrase.split("\\r?\\n");
    	  String useMe = lines[lineNum];
    	  String delims = "[,]+";
    	  String[] tokens = useMe.split(delims);
    	  String value = tokens[valNum];
    	  delims = "[=]+";
    	  tokens = value.split(delims);
    	  valueInt = tokens[1];
     	   
    	} catch(Exception e){
    		log.warning("Issue parsing data (JDBC): " + e.toString());
    		return valueInt;
    	}
    	return valueInt;
    }    
    
    
    
    @Override
    public ArrayList<JDBCStats> call() throws Exception {
 
    	try {
                ObjectName queryName = new ObjectName(String.format("*:type=JDBCProvider,name=%s,process=" + name + ",*", "*"));
                Set<?> s = connections.queryNames(queryName, null);
            if (!s.isEmpty()) {
                // iterate through the Set
                Iterator<?> iter = s.iterator();
                
                while (iter.hasNext()) {
                    server = (ObjectName)iter.next();
                    Object jvmContents = connections.invoke((ObjectName)server,
                        	"getStats", null, null);
                    String phrase = jvmContents.toString();
                    String nameJDBC = parseData(phrase, 1, 0);
                    
                    
            		int size = dataMonitor.length;
            		int monitor=0;
            		for(int i=0; i < size; i++){
            			if(nameJDBC.equalsIgnoreCase(dataMonitor[i].toString())){
            				monitor=1;
            				break;
            			}
            		}
            		if(monitor==0){
            			JDBCStats stats = new JDBCStats();
            			stats.setserverName(name);
                    	stats.setEnviro(environments);
            			stats.setJDBCName(nameJDBC);
            			String createCount = parseData(phrase, 5, 5);
            			stats.setcreateCount(createCount);
            			String closeCount = parseData(phrase, 7, 5);
            			stats.setcloseCount(closeCount);
            			//Pool Size
            			String lowWaterMark = parseData(phrase, 9, 5);
            			String highWaterMark = parseData(phrase, 9, 6);        
            			String current = parseData(phrase, 9, 7);
            			String integral = parseData(phrase, 9, 8);
            			String lowerBound = parseData(phrase, 9, 9);       
            			String upperBound = parseData(phrase, 9, 10);
            			stats.setPoolSizeLow(lowWaterMark);
            			stats.setPoolSizeHigh(highWaterMark);
            			stats.setPoolSizeCurrent(current);
            			stats.setPoolSizeIntegral(integral);
            			stats.setPoolSizeLower(lowerBound);
            			stats.setPoolSizeUpper(upperBound);
                    
            			//FreePoolSize
            			String lowWaterMark2 = parseData(phrase, 11, 5);
            			String highWaterMark2 = parseData(phrase, 11, 6);        
            			String current2 = parseData(phrase, 11, 7);
            			String integral2 = parseData(phrase, 11, 8);
            			String lowerBound2 = parseData(phrase, 11, 9);       
            			String upperBound2 = parseData(phrase, 11, 10);        
            			stats.setFreePoolSizeLow(lowWaterMark2);
            			stats.setFreePoolHigh(highWaterMark2);
            			stats.setFreePoolCurrent(current2);
            			stats.setFreePoolIntegral(integral2);
            			stats.setFreePoolLower(lowerBound2);
            			stats.setFreePoolUpper(upperBound2);
                   
            			//WaitingThreadCount   
            			String lowWaterMark3 = parseData(phrase, 13, 5);
            			String highWaterMark3 = parseData(phrase, 13, 6);        
            			String current3 = parseData(phrase, 13, 7);
            			String integral3 = parseData(phrase, 13, 8);
            			stats.setWaitingThreadLow(lowWaterMark3);
            			stats.setWaitingThreadHigh(highWaterMark3);
            			stats.setWaitingThreadCurrent(current3);
            			stats.setWaitingThreadIntegral(integral3);
                    
            			//PercentUsed  -- had to change
            			String lowWaterMark4 = parseData(phrase, 15, 6);
            			String highWaterMark4 = parseData(phrase, 15, 7);        
            			String current4 = parseData(phrase, 15, 8);
            			String integral4 = parseData(phrase, 15, 9);
            			stats.setPercentUsedLow(lowWaterMark4);
            			stats.setPercentUsedHigh(highWaterMark4);
            			stats.setPercentUsedCurrent(current4);
            			stats.setPercentUsedIntegral(integral4);
                    
            			//UseTime Average
            			String avgAvg = parseData(phrase, 17, 5);
            			String minAvg = parseData(phrase, 17, 6);        
            			String maxAvg = parseData(phrase, 17, 7);
            			String totalAvg = parseData(phrase, 17, 8);
            			String countAvg = parseData(phrase, 17, 9);       
            			String sumSqAvg = parseData(phrase, 17, 10);
            			stats.setUseTimeAverage(avgAvg);
            			stats.setUseTimeAverageMin(minAvg);
            			stats.setUseTimeAverageMax(maxAvg);
            			stats.setUseTimeAverageTotal(totalAvg);
            			stats.setUseTimeAverageCount(countAvg);
            			stats.setUseTimeAverageSumSq(sumSqAvg);
                    
            			//UseTime Time
            			String avgTime = parseData(phrase, 17, 12);
            			String minTime = parseData(phrase, 17, 13);        
            			String maxTime = parseData(phrase, 17, 14);
            			String totalTime = parseData(phrase, 17, 15);
            			String countTime = parseData(phrase, 17, 16);       
            			String sumSqTime = parseData(phrase, 17, 17);
            			stats.setUseTime(avgTime);
            			stats.setUseTimeMin(minTime);
            			stats.setUseTimeMax(maxTime);
            			stats.setUseTimeTotal(totalTime);
            			stats.setUseTimeCount(countTime);
            			stats.setUseTimeSumSq(sumSqTime);

            			//WaitTime - Average 
            			String avgAvg1 = parseData(phrase, 19, 5);
            			String minAvg1 = parseData(phrase, 19, 6);        
            			String maxAvg1 = parseData(phrase, 19, 7);
            			String totalAvg1 = parseData(phrase, 19, 8);
            			String countAvg1 = parseData(phrase, 19, 9);       
            			String sumSqAvg1 = parseData(phrase, 19, 10);
            			stats.setWaitTimeAverage(avgAvg1);
            			stats.setWaitTimeAverageMin(minAvg1);
            			stats.setWaitTimeAverageMax(maxAvg1);
            			stats.setWaitTimeAverageTotal(totalAvg1);
            			stats.setWaitTimeAverageCount(countAvg1);
            			stats.setWaitTimeAverageSumSq(sumSqAvg1);

            			//WaitTime - Time
            			String avgAvg2 = parseData(phrase, 19, 12);
            			String minAvg2 = parseData(phrase, 19, 13);        
            			String maxAvg2 = parseData(phrase, 19, 14);
            			String totalAvg2 = parseData(phrase, 19, 15);
            			String countAvg2 = parseData(phrase, 19, 16);       
            			String sumSqAvg2 = parseData(phrase, 19, 17);
            			stats.setWaitTime(avgAvg2);
            			stats.setWaitTimeMin(minAvg2);
            			stats.setWaitTimeMax(maxAvg2);
            			stats.setWaitTimeTotal(totalAvg2);
            			stats.setWaitTimeCount(countAvg2);
            			stats.setWaitTimeSumSq(sumSqAvg2);

            			statsList.add(stats);
            			ad.addServerStatsList(statsList);
            			statsList.clear();
            		}
                    
                }
            } else {
            	log.warning("JDBC MBean was not found");
                
            }            	
                
    	} catch (Exception e) {
    		log.warning("Error JDBC data: " + e);
    		if (log.isLoggable(Level.WARNING)) {
    			log.warning("Error JDBC data: " + e);
    		}
            	
    	}
        
        test = ad.getArrayList();
		return test;
    }
}
