package com.dynatrace.diagnostics.plugins.jmx.monitor;

import javax.management.ObjectName;

import java.util.ArrayList;

import com.dynatrace.diagnostics.pdk.MonitorEnvironment;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionTimeHolder;

import java.util.concurrent.Callable;
import java.util.Set;
import java.util.Iterator;

import com.ibm.websphere.management.AdminClient;

public class Transaction implements Callable<ArrayList<TransactionStats>>, WebSphereConstants {

	private ArrayList<TransactionStats> test;
	private ObjectName server;
	
	public Transaction(ArrayList<TransactionStats> test){
        this.test = test;
	}
	
	private static final Logger log = Logger.getLogger(Transaction.class.getName());
	private ArrayList<TransactionStats> statsList = new ArrayList<TransactionStats>();
	
	TransactionTimeHolder ad = new TransactionTimeHolder();
    AdminClient connections;
    String name;
    MonitorEnvironment envs;
    String environments;
    
    public Transaction(AdminClient connection, MonitorEnvironment env, String environment, String serverName) {
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
    public ArrayList<TransactionStats> call() throws Exception {
        try {
            ObjectName queryName = new ObjectName(String.format("*:type=TransactionService,name=%s,process=" + name + ",*", "*"));
            Set<?> s = connections.queryNames(queryName, null);
            if (!s.isEmpty()) {
            	Iterator<?> iter = s.iterator();
            	while (iter.hasNext()) {
            		server = (ObjectName)iter.next();
            		TransactionStats stats = new TransactionStats();
            		stats.setserverName(name);
            		stats.setEnviro(environments);
            		Object sessionContents = connections.invoke((ObjectName)server,
                    	"getStats", null, null);
            		String phrase = sessionContents.toString();
            		String activeCount = parseData(phrase, 3, 5);
            		String committedCount = parseData(phrase, 5, 5);        
            		String rolledBackCount = parseData(phrase, 7, 5);
            		stats.setActiveTransactionsCount(activeCount);
            		stats.setTransactionCommittedCount(committedCount);
            		stats.setTransactionRolledBackCount(rolledBackCount);
                
            		statsList.add(stats);
            		ad.addServerStatsList(statsList);
            		statsList.clear();  
            	}
            } else {
            	log.warning("Transaction MBean was not found");
            
            }        	
        } catch (Exception e) {
        	if (log.isLoggable(Level.WARNING)) {
        		log.warning("Transaction error: " + e);
        	}
        }
        test = ad.getArrayList();
		return test;
    }
}
