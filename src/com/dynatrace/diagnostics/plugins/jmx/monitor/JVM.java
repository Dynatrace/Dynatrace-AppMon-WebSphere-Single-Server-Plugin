package com.dynatrace.diagnostics.plugins.jmx.monitor;

import java.text.DecimalFormat;

import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMTimeHolder;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMServerStats;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;
import java.util.Set;
import java.util.Iterator;

import com.ibm.websphere.management.AdminClient;

public class JVM implements Callable<ArrayList<JVMServerStats>>, WebSphereConstants {

	private ArrayList<JVMServerStats> test;
	private ObjectName server;
	
	public JVM(ArrayList<JVMServerStats> test){
        this.test = test;
	}	
	
	private static final Logger log = Logger.getLogger(JVM.class.getName());
	private ArrayList<JVMServerStats> statsList = new ArrayList<JVMServerStats>();
	JVMTimeHolder ad = new JVMTimeHolder();
	
    AdminClient connections;
    String name;
    MonitorEnvironment envs;
    String environments;

    static public String customFormat(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }

    public JVM(AdminClient connection, MonitorEnvironment env, String environment, String serverName) {
    	envs = env;
        name = serverName;
        connections = connection;
        environments=environment;
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
    		log.warning("Issue parsing data (JVM): " + e.toString());
    		return valueInt;
    	}
    	return valueInt;
    }   
    
    
    
    @Override
    public ArrayList<JVMServerStats> call() throws Exception {

        try {
            ObjectName queryName = new ObjectName(String.format("*:type=JVM,name=%s,process=" + name + ",*", "*"));
            
            Set<?> s = connections.queryNames(queryName, null);
        if (!s.isEmpty()) {
            Iterator<?> iter = s.iterator();
            while (iter.hasNext()) {
                server = (ObjectName)iter.next();
            	JVMServerStats stats = new JVMServerStats();
                stats.setServerName(name);
                stats.setEnviro(environments);
                Object jvmContents = connections.invoke((ObjectName)server,
                    	"getStats", null, null);
                String phrase = jvmContents.toString();

                //Heap Size - BoundedRangeStatistic
                String lowWaterMark = parseData(phrase, 3, 5);
                String highWaterMark = parseData(phrase, 3, 6);        
                String current = parseData(phrase, 3, 7);
                String integral = parseData(phrase, 3, 8);
                String lowerBound = parseData(phrase, 3, 9);       
                String upperBound = parseData(phrase, 3, 10);
                stats.setJVMLow(lowWaterMark);
                stats.setJVMHigh(highWaterMark);
                stats.setJVMCurrent(current);
                stats.setJVMIntegral(integral);
                stats.setJVMLowerBound(lowerBound);
                stats.setJVMUpperBound(upperBound);
                
                //JVM Used Memory
                String usedMem = parseData(phrase, 5, 5);
                stats.setJVMUsedMemory(usedMem);
                
                //JVM UpTime
                String upTime = parseData(phrase, 7, 5);
                stats.setJVMUptime(upTime);
                
                //JVM Process CPU Usage
                String processCPUUsage = parseData(phrase, 9, 5);
                stats.setJVMCPUUsage(processCPUUsage);
                
              statsList.add(stats);
              ad.addServerStatsList(statsList);
              statsList.clear(); 
            }
        } else {
            log.warning("JVM MBean was not found");
            
        }        	
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Error JVM data: " + e);
        	}
        }
        test = ad.getArrayList();
		return test;
    }
}

