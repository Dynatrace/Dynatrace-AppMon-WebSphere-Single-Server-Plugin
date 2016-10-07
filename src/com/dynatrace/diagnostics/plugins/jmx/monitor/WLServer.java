package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.ObjectName;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerTimeHolder;

import java.util.ArrayList;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Callable;
import java.util.Set;

import com.ibm.websphere.management.AdminClient;

public class WLServer implements Callable<ArrayList<WLServerStats>>, WebSphereConstants {

	
	private ArrayList<WLServerStats> test;
	private ObjectName server;
	
	public WLServer(ArrayList<WLServerStats> test){
        this.test = test;
	}

	
	private static final Logger log = Logger.getLogger(WLServer.class.getName());
	private ArrayList<WLServerStats> statsList = new ArrayList<WLServerStats>();
    
	
	WLServerTimeHolder ad = new WLServerTimeHolder();
    AdminClient connections;
    String name;
    MonitorEnvironment envs;
    String environments;

    public WLServer(AdminClient connection, MonitorEnvironment env, String environment, String serverName) {
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
    		log.warning("Issue parsing data (Threads): " + e.toString());
    		return valueInt;
    	}
    	return valueInt;
    }     
    
    @Override
    public ArrayList<WLServerStats> call() throws Exception {
        try {
            ObjectName queryName = new ObjectName(String.format("*:type=Server,name=%s,process=" + name + ",*", "*"));
            Set<?> s = connections.queryNames(queryName, null);
            if (!s.isEmpty()) {
            	WLServerStats stats = new WLServerStats();
                server = (ObjectName)s.iterator().next();
                Object nodeName = connections.invoke((ObjectName)server,
                        	"getName", null, null);
                
                stats.setEnviro(environments);
                stats.setserverName(nodeName.toString());    
                Object nodeState = connections.invoke((ObjectName)server,
                        	"getState", null, null);
                stats.setNodeStatus(nodeState.toString());
                 
                statsList.add(stats);
                ad.addServerStatsList(statsList);     
                statsList.clear();    
                
            } else {
                log.warning("Server MBean was not found");
                
            }        	
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Server error: " + e);
        	}
        }
        test = ad.getArrayList();
		return test;
    }
}
