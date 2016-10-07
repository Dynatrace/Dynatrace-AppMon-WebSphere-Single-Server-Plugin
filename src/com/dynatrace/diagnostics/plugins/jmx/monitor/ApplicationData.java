package com.dynatrace.diagnostics.plugins.jmx.monitor;


import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataTimeHolder;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;
import java.util.Iterator;

import com.ibm.websphere.management.AdminClient;

public class ApplicationData implements Callable<ArrayList<ApplicationDataStats>>, WebSphereConstants {
	private ArrayList<ApplicationDataStats> test;
	private ObjectName server;
	
    public ApplicationData(ArrayList<ApplicationDataStats> test){
	        this.test = test;
    }

	
	private static final Logger log = Logger.getLogger(ApplicationData.class.getName());
    ApplicationDataTimeHolder ad = new ApplicationDataTimeHolder();
    private ArrayList<ApplicationDataStats> statsList = new ArrayList<ApplicationDataStats>();
	
    AdminClient connections;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;
    String name;

    public ApplicationData(AdminClient connection, MonitorEnvironment env, String environment, String[] monitorData, String serverName) { 
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
    		log.warning("Issue parsing data (Application Data): " + e.toString());
    		return valueInt;
    	}
    	return valueInt;
    }    
    
    
    
    @Override
    public ArrayList<ApplicationDataStats> call() throws Exception {

        try {

            ObjectName queryName = new ObjectName(String.format("*:type=SessionManager,name=%s,process=" + name + ",*", "*"));
            Set<?> s = connections.queryNames(queryName, null);
            if (!s.isEmpty()) {
            	Iterator<?> iter = s.iterator();
            	
            	while (iter.hasNext()) {
            		server = (ObjectName)iter.next();
            		Object sessionContents = connections.invoke((ObjectName)server,
            				"getStats", null, null);
                
            		String phrase = sessionContents.toString();
                
            		//Session Manager - live sessions.
                
            		String sessionName = parseData(phrase, 1, 0);
            		
            		//dataMonitor - list of unmonitored items.....
            		int size = dataMonitor.length;
            		int monitor=0;
            		for(int i=0; i < size; i++){
            			if(sessionName.equalsIgnoreCase(dataMonitor[i].toString())){
            				monitor=1;
            				break;
            			}
            		}
            		if(monitor==0){
            			ApplicationDataStats stats = new ApplicationDataStats();
            			stats.setappName(sessionName);
            			stats.setEnviro(environments);
            			stats.setserverName(name);
            			String lowWaterMark = parseData(phrase, 3, 5);
            			stats.setLowWaterMark(lowWaterMark);
            			String highWaterMark = parseData(phrase, 3, 6); 
            			stats.setHighWaterMark(highWaterMark);
            			String current = parseData(phrase, 3, 7);
            			stats.setSessionsCurrent(current);
            			String integral = parseData(phrase, 3, 8);
            			stats.setIntegral(integral);
                
            			statsList.add(stats);
            			ad.addServerStatsList(statsList);
            			statsList.clear();
            		}
            	}
            } else {
            	log.warning("Server MBean was not found for Application Data.");
            
            }
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Error getting Application Data: " + e);
        	}
        } 
        test = ad.getArrayList();
    	return test;
    }
}