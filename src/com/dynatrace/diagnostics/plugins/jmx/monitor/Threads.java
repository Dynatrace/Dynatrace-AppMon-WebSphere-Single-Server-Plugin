package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;

import javax.management.ObjectName;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadTimeHolder;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;

import com.ibm.websphere.management.AdminClient;

public class Threads implements Callable<ArrayList<ThreadStats>>, WebSphereConstants {
	
	private ArrayList<ThreadStats> test;
	private ObjectName server;
	
	public Threads(ArrayList<ThreadStats> test){
        this.test = test;
	}	
	
	private static final Logger log = Logger.getLogger(Threads.class.getName());
	private ArrayList<ThreadStats> statsList = new ArrayList<ThreadStats>();
    
    ThreadTimeHolder ad = new ThreadTimeHolder();
    AdminClient connections;
    ObjectName destRT;
    MonitorEnvironment envs;
    String environments;
    String name;
    String [] dataMonitor;

    public Threads(AdminClient connection, MonitorEnvironment env, String environment, String serverName,String[] monitorData) {
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
    		log.warning("Issue parsing data (Threads): " + e.toString());
    		return valueInt;
    	}
    	return valueInt;
    }
    
    
    
    @Override
    public ArrayList<ThreadStats> call() throws Exception {
        try {
        	
            try {
                ObjectName queryName = new ObjectName(String.format("*:type=ThreadPool,name=%s,process=" + name + ",*", "*"));
                Set<?> s = connections.queryNames(queryName, null);
                if (!s.isEmpty()) {
                    Iterator<?> iter = s.iterator();
                    while (iter.hasNext()) {
                        server = (ObjectName)iter.next();
                        Object ThreadName = connections.invoke((ObjectName)server,
                            	"getName", null, null);
                       
                		int size = dataMonitor.length;
                		int monitor=0;
                		for(int i=0; i < size; i++){
                			if(ThreadName.toString().equalsIgnoreCase(dataMonitor[i].toString())){
                				monitor=1;
                				break;
                			}
                		}
                		if(monitor==0){
                			ThreadStats stats = new ThreadStats();
                			
                			stats.setThreadName(ThreadName.toString());
                			Object threadContents = connections.invoke((ObjectName)server,
                            	"getStats", null, null);
                			stats.setserverName(name);
                			stats.setEnviro(environments);
                			String phrase = threadContents.toString();
                			String value = parseData(phrase, 3, 7);
                        
                			//Active Count - current
                			String activeCountCurrent = value;
                			String highWaterMark = parseData(phrase, 3, 6);
                			String lowWaterMark = parseData(phrase, 3, 5);
                			stats.setThreadActiveCount(activeCountCurrent);
                			stats.setThreadActiveCountHigh(highWaterMark);
                			stats.setThreadActiveCountLow(lowWaterMark);
                        
                			//Pool Size - current
                			String value2 = parseData(phrase, 5, 7);
                			String poolSizeCurrent = value2;
                			String highWaterMark2 = parseData(phrase, 5, 6);
                			String lowWaterMark2 = parseData(phrase, 5, 5);
                			String lowerBound = parseData(phrase, 5, 9);
                			String upperBound = parseData(phrase, 5, 10);
                			stats.setThreadPoolSizeCurrent(poolSizeCurrent);
                			stats.setThreadPoolSizeHigh(highWaterMark2);
                			stats.setThreadPoolSizeLow(lowWaterMark2);
                			stats.setThreadPoolSizeLower(lowerBound);
                			stats.setThreadPoolSizeUpper(upperBound);
                        
                			statsList.add(stats);
                			ad.addServerStatsList(statsList);
                			statsList.clear();
                		} 
                    }
                } else {
                    log.warning("Thread MBean was not found");
                }
            } catch (Exception e) {
                System.out.println(e);
                if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error Thread data: " + e);
            	}
            }        	
//            statsList.add(stats);
//            ad.addServerStatsList(statsList);
//            statsList.clear();
        } catch (Exception e) {
        	log.warning("Threads Exception: " + e);
        }
        test = ad.getArrayList();
		return test;
    }
}
