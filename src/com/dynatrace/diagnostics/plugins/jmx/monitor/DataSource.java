package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.ObjectName;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;

import java.util.ArrayList;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.DSStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.DSTimeHolder;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;
import java.util.Set;
import java.util.Iterator;

import com.ibm.websphere.management.AdminClient;

public class DataSource implements Callable<ArrayList<DSStats>>, WebSphereConstants {

	private ArrayList<DSStats> test;
	private ObjectName server;
	
	public DataSource(ArrayList<DSStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(DataSource.class.getName());
	private ArrayList<DSStats> statsList = new ArrayList<DSStats>();
    
    DSTimeHolder ad = new DSTimeHolder();
    
    AdminClient connections;
    String name;
    MonitorEnvironment envs;
    String environments;
    String [] dataMonitor;

    public DataSource(AdminClient connection, MonitorEnvironment env, String environment, String serverName,String[] monitorData) {
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
    		log.warning("Issue parsing data (DataSource): " + e.toString());
    		return valueInt;
    	}
    	return valueInt;
    }    
    
    
    
    @Override
    public ArrayList<DSStats> call() throws Exception {
 
            try {
                ObjectName queryName = new ObjectName(String.format("*:type=DataSource,name=%s,process=" + name + ",*", "*"));
                Set<?> s = connections.queryNames(queryName, null);
                if (!s.isEmpty()) {
                	Iterator<?> iter = s.iterator();
                	
                	while (iter.hasNext()) {
                		
                		
                		server = (ObjectName)iter.next();
                		Object dsName = connections.invoke((ObjectName)server,
                        	"getName", null, null);
                		
                		int size = dataMonitor.length;
                		int monitor=0;
                		for(int i=0; i < size; i++){
                			if(dsName.toString().equalsIgnoreCase(dataMonitor[i].toString())){
                				monitor=1;
                				break;
                			}
                		}
                		if(monitor==0){
                			DSStats stats = new DSStats();
                			Object jvmContents = connections.invoke((ObjectName)server,
                					"getStatus", null, null);
                    	//Lifecycle status:  0=Status request failed; 1=Active; 2=Paused; 3=Stopped; 4=Paused - Mixed: some of the 
                    	//ConnectionFactories, DataSources, and ActivationSpecs are Paused while others are active; 99=Failed
                			stats.setserverName(name.toString());
                			stats.setEnviro(environments);
                			stats.setJDBCName(dsName.toString());
                    
                			stats.setstate(jvmContents.toString());
                			statsList.add(stats);
                			ad.addServerStatsList(statsList);
                			statsList.clear();
                		}
                	}
                } else {
                	log.warning("DataSource MBean was not found");
                
                }           	
            } catch (Exception e) {
            	if (log.isLoggable(Level.WARNING)) {
            		log.warning("Error DataSource data: " + e);
            	}
            }
        test = ad.getArrayList();
		return test;
    }
}
