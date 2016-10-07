package com.dynatrace.diagnostics.plugins.jmx.monitor;


import javax.management.ObjectName;

import java.util.Iterator;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;

import java.util.ArrayList;
import java.util.Set;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ServletStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ServletTimeHolder;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;

import com.ibm.websphere.management.AdminClient;

public class Servlet implements Callable<ArrayList<ServletStats>>, WebSphereConstants {
	
	private ArrayList<ServletStats> test;
	private ObjectName server;
	
	public Servlet(ArrayList<ServletStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(Servlet.class.getName());
	private ArrayList<ServletStats> statsList = new ArrayList<ServletStats>();
	
	ServletTimeHolder ad = new ServletTimeHolder();
	
    AdminClient connections;
    String [] dataMonitor;
    MonitorEnvironment envs;
    String environments;
    String name;
    
    public Servlet(AdminClient connection, MonitorEnvironment env, String environment, String serverName,String[] monitorData) {
    	envs = env;
        name=serverName;
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
    		log.warning("Issue parsing data (Servlet): " + e.toString());
    		return valueInt;
    	}
    	return valueInt;
    }    
    

    @Override
    public ArrayList<ServletStats> call() throws Exception {
        try {
            ObjectName queryName = new ObjectName(String.format("*:type=Servlet,name=%s,process=" + name + ",*", "*"));
            Set<?> s = connections.queryNames(queryName, null);
            if (!s.isEmpty()) {
            	Iterator<?> iter = s.iterator();
            	
            	while (iter.hasNext()) {
            		server = (ObjectName)iter.next();
            		Object servletName = connections.invoke((ObjectName)server,
                    	"getName", null, null);
            		
            		int size = dataMonitor.length;
            		int monitor=0;
            		for(int i=0; i < size; i++){
            			if(servletName.toString().equalsIgnoreCase(dataMonitor[i].toString())){
            				monitor=1;
            				break;
            			}
            		}
            		if(monitor==0){
            			ServletStats stats = new ServletStats();
            			stats.setserverName(name);
                		stats.setEnviro(environments);
            			stats.setservletName(servletName.toString());
            			Object servletContents = connections.invoke((ObjectName)server,
                    	"getStats", null, null);
            			String phrase = servletContents.toString();

            			//totalRequests
            			String totalRequestCount = parseData(phrase, 3, 5);
            			stats.setTotalRequest(totalRequestCount);

            			//responseTime AverageStatistic
            			String avgStatistic = parseData(phrase, 5, 5);
            			String avgMin = parseData(phrase, 5, 6);
            			String avgMax = parseData(phrase, 5, 7);
            			String avgTotal = parseData(phrase, 5, 8);
            			String avgCount = parseData(phrase, 5, 9);
            			String avgSumSq = parseData(phrase, 5, 10);
            			stats.setAverageAvgStatistic(avgStatistic);
            			stats.setAverageAvgMin(avgMin);
            			stats.setAverageAvgMax(avgMax);
            			stats.setAverageAvgTotal(avgTotal);
            			stats.setAverageAvgCount(avgCount);
            			stats.setAverageAvgSumSq(avgSumSq);
                
            			// --TimeStatistic
            			String avgStatistic1 = parseData(phrase, 5, 12);
            			String avgMin2 = parseData(phrase, 5, 13);
            			String avgMax3 = parseData(phrase, 5, 14);
            			String avgTotal4 = parseData(phrase, 5, 15);
            			String avgCount5 = parseData(phrase, 5, 16);
            			String avgSumSq6 = parseData(phrase, 5, 17);
            			stats.setTimeAvgStatistic(avgStatistic1);
            			stats.setTimeAvgMin(avgMin2);
            			stats.setTimeAvgMax(avgMax3);
            			stats.setTimeAvgTotal(avgTotal4);
            			stats.setTimeAvgCount(avgCount5);
            			stats.setTimeAvgSumSq(avgSumSq6);
                
            			statsList.add(stats);
            			ad.addServerStatsList(statsList);
            			statsList.clear();
            		}
            	}
            } else {
            	System.out.println("Server MBean was not found");
            
            }        	
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Error File Store data: " + e);
        	}
        }
        test = ad.getArrayList();
		return test;
    }
}