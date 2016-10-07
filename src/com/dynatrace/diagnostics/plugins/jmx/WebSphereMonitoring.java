package com.dynatrace.diagnostics.plugins.jmx;

import com.dynatrace.diagnostics.pdk.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.ObjectName;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.dynatrace.diagnostics.plugins.jmx.connection.WebSphereConnection;
import com.dynatrace.diagnostics.plugins.jmx.convertdata.DateTime;
import com.dynatrace.diagnostics.plugins.jmx.monitor.ApplicationData;
import com.dynatrace.diagnostics.plugins.jmx.monitor.Servlet;
import com.dynatrace.diagnostics.plugins.jmx.monitor.JDBC;
import com.dynatrace.diagnostics.plugins.jmx.monitor.DataSource;
import com.dynatrace.diagnostics.plugins.jmx.monitor.JVM;
import com.dynatrace.diagnostics.plugins.jmx.monitor.Threads;
import com.dynatrace.diagnostics.plugins.jmx.monitor.Transaction;
import com.dynatrace.diagnostics.plugins.jmx.monitor.WLServer;
import com.dynatrace.diagnostics.plugins.jmx.record.DynamicMeasure;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ApplicationDataStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ServletStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JDBCStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.DSStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.JVMServerStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.ThreadStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionStats;
import com.dynatrace.diagnostics.plugins.jmx.variableholder.WLServerStats;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.ibm.websphere.management.AdminClient;


public class WebSphereMonitoring implements Monitor, WebSphereConstants {

	private static final Logger log = Logger.getLogger(WebSphereMonitoring.class.getName());
	
	private WebSphereConnection wc = null;
	//private MBeanServerConnection connection=null;
	private AdminClient connection=null;
	String environment;
	String measureName;
	Boolean threads;
	Boolean jvm;
	Boolean wlserver;
	Boolean transaction;
	Boolean sessionData;
	Boolean servlet;
	Boolean datasource;
	Boolean jdbc;
	String[] dataMonitor;
	String[] threadIgnore;
	String[] jdbcIgnore;
	String[] servletIgnore;
	String[] dsIgnore;
	String host;
	String port;
	String username;
	String password;
	int threadPoolCount;
	int timeOut;
	String security;
	String keyStore;
	String keyPassword;
	String trustStore;
	String trustPassword;
	
	ExecutorService serviceThread;
	
	public static boolean isEmptyOrBlank(String str) {
		return str == null || str.trim().isEmpty();
		}

	
	private WebSphereProperties properties;

	@Override
	public Status setup(MonitorEnvironment env) throws Exception {
		
		properties = new WebSphereProperties();
		
		// set plugin's configuration parameters
		properties.setDtServer(env.getConfigString(CONFIG_DT_SERVER).trim());
		properties.setJmxPort(env.getConfigString(CONFIG_JMX_PORT).trim());
		properties.setJmxUserName(env.getConfigString(CONFIG_WEBSPHERE_USERNAME).trim());
		properties.setJmxPassword(env.getConfigPassword(CONFIG_WEBSPHERE_PASSWORD).trim());
		properties.setEnvironment(env.getConfigString(CONFIG_WEBSPHERE_ENVIRONMENT).trim());
		properties.setThreads(env.getConfigBoolean(CONFIG_WEBSPHERE_THREADS));
		properties.setJVM(env.getConfigBoolean(CONFIG_WEBSPHERE_JVM));
		properties.setWLServer(env.getConfigBoolean(CONFIG_WEBSPHERE_WLSERVER));
		properties.setdatasource(env.getConfigBoolean(CONFIG_WEBSPHERE_DATASOURCE));
		properties.setTransaction(env.getConfigBoolean(CONFIG_WEBSPHERE_TRANSACTION));
		properties.setServlet(env.getConfigBoolean(CONFIG_WEBSPHERE_SERVLET));
		properties.setJMS(env.getConfigBoolean(CONFIG_WEBSPHERE_JMS));
		properties.setAppData(env.getConfigBoolean(CONFIG_WEBSPHERE_APPLICATIONDATA));
		properties.setJDBC(env.getConfigBoolean(CONFIG_WEBSPHERE_JDBC));
		properties.setAppDataMonitor(env.getConfigString(CONFIG_WEBSPHERE_APPDATAMONITOR).trim());
		properties.setthreadCount(env.getConfigString(CONFIG_THREADCOUNT_MONITOR).trim());
		properties.settimeOut(env.getConfigString(CONFIG_TIMEOUT_MONITOR).trim());
		properties.setIgnoreThreads(env.getConfigString(CONFIG_WEBSPHERE_IGNORETHREADPOOL).trim());
		properties.setIgnoreServlet(env.getConfigString(CONFIG_WEBSPHERE_IGNORESERVLET).trim());
		properties.setIgnoreJDBC(env.getConfigString(CONFIG_WEBSPHERE_IGNOREJDBC).trim());
		properties.setIgnoreDS(env.getConfigString(CONFIG_WEBSPHERE_IGNOREDS).trim());
		properties.setSecurity(env.getConfigBoolean(CONFIG_SECURITY_MONITOR));
		properties.setKeyStoreFile(env.getConfigString(CONFIG_KEYSTORE_MONITOR).trim());
		properties.setKeyPassword(env.getConfigPassword(CONFIG_KEYPASSWORD_MONITOR).trim());
		properties.setTrustStoreFile(env.getConfigString(CONFIG_TRUSTSTORE_MONITOR).trim());
		properties.setTrustPassword(env.getConfigPassword(CONFIG_TRUSTPASSWORD_MONITOR).trim());

		
		//add to properties so user can adjust.
		String tCount=properties.getthreadCount(); 
		threadPoolCount=Integer.valueOf(tCount);
		String tout=properties.gettimeOut();
		
		//How long we are willing to wait for a response.
		timeOut=Integer.valueOf(tout);
		
		
		//Security
		Boolean securityCheck = properties.getSecurity();
		keyStore = properties.getKeyStoreFile();
		keyPassword = properties.getKeyPassword();
		trustStore = properties.geTtrustStoreFile();
		trustPassword = properties.getTrustPassword();
		
		if(securityCheck){
			security="true";
		}else {
			security="false";
		}
		
		if (threadPoolCount < 1) {
            threadPoolCount = 1;
        } 
		if (threadPoolCount > 10) {
            threadPoolCount = 10;
        }
		
		
		log.info("Monitoring Thread count: " + threadPoolCount);
		
		//Need to add timeout....
		serviceThread =  Executors.newFixedThreadPool(threadPoolCount);
		
		host = properties.getDtServer();
		port =properties.getJmxPort();
		username=properties.getJmxUserName();
		password=properties.getJmxPassword();
		environment=properties.getEnvironment();
		
		if (isEmptyOrBlank(host)){
			host="nothingWasEntered";
		}
		if (isEmptyOrBlank(port)){
			port="nothingWasEntered";
		}
		if (isEmptyOrBlank(environment)){
			environment="nothingWasEntered";
		}

		
		String monitorAppData=properties.getAppDataMonitor();
		String ignoreThreads=properties.getIgnoreThreads();
		String ignoreServlet=properties.getIgnoreServlet();
		String ignoreJDBC=properties.getIgnoreJDBC();
		String ignoreDS=properties.getIgnoreDS();
		
		threads=properties.getThreads();
		jvm=properties.getJVM();
		wlserver=properties.getWLServer();
		transaction=properties.getTransaction();
		sessionData=properties.getAppData();
		servlet=properties.getServlet();
		datasource=properties.getdatasource();
		jdbc=properties.getJDBC();
		
		if (isEmptyOrBlank(monitorAppData)){
			monitorAppData="nothingWasEntered";
		}
		if (isEmptyOrBlank(ignoreThreads)){
			ignoreThreads="nothingWasEntered";
		}
		if (isEmptyOrBlank(ignoreServlet)){
			ignoreServlet="nothingWasEntered";
		}
		if (isEmptyOrBlank(ignoreJDBC)){
			ignoreJDBC="nothingWasEntered";
		}
		if (isEmptyOrBlank(ignoreDS)){
			ignoreDS="nothingWasEntered";
		}
		
		//APP DATA IGNORE STUFF
		dataMonitor = monitorAppData.split("\\s+");
		
		threadIgnore=ignoreThreads.split("\\s+");
		jdbcIgnore=ignoreJDBC.split("\\s+");
		servletIgnore=ignoreServlet.split("\\s+");
		dsIgnore=ignoreDS.split("\\s+");
		

		wc = new WebSphereConnection();
		connection = wc.initConnection(host, port, username, password, security, keyStore, keyPassword, trustStore, trustPassword);
		
		if(connection==null){
			DynamicMeasure testing = new DynamicMeasure();
			log.warning("Connection is null. We will try again. Server connection information = " + host + " " + port + " " + username);
			measureName = "AdminConnection|"+environment;
			Float f = (float) 0;
			testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
			return new Status(Status.StatusCode.PartialSuccess, "Connection Failure. Weblogic Admin Server: " + host + ":" + port 
					+ " with user: " + username);
		} else {
			return new Status(Status.StatusCode.Success);
		}
	}


	@Override
	public Status execute(MonitorEnvironment env) throws Exception {
		
		try{
			connection.isAlive(); //Test the connection..
		}catch (Exception con){
			log.warning("Error with connection: " + con);
			connection=null;
		}
		
		
		DynamicMeasure testing = new DynamicMeasure();
		if(connection==null){
			
			connection = wc.initConnection(host, port, username, password, security, keyStore, keyPassword, trustStore, trustPassword);
			
			if(connection==null){
				measureName = "AdminConnection|"+environment;
				Float f = (float) 0;
				testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
				return new Status(Status.StatusCode.PartialSuccess, "Unable to connect to the Admin Server: " + properties.getDtServer() + ":" + properties.getJmxPort());
			}
		}
		
        String environment=properties.getEnvironment();
        long webMonitorStartTime = DateTime.getDateTime();
        String server_name ="FixMe";
        
        
            try {
            	ObjectName server;
            	Set<?> s = null;
            	try {
            		ObjectName queryName = new ObjectName(String.format("*:type=Server,name=%s,*", "*"));
                    s = connection.queryNames(queryName, null);
            	} catch (Exception e){
            		if(connection != null){
            			try{
            				connection.isAlive(); //Test the connection..
            			}catch (Exception con){
            				log.warning("Error with connection: " + con);
            				connection=null;
            			}
            			
                		connection = wc.initConnection(host, port, username, password, security, keyStore, keyPassword, trustStore, trustPassword);
                		
                		if(connection == null){
                			measureName = "AdminConnection|"+environment;
                			Float f = (float) 0;
                			testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
                			log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBSPHERE_CONNECTION_GROUP + " " +  WEBSPHERE_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
                			return new Status(Status.StatusCode.PartialSuccess, "Connection lost. Unable to connect to ServerRuntime " + host + ":" + port + ":" + username); //stops the execute method...
                		} 
                	} else {
                		try{
            				connection.isAlive(); //Test the connection..
            			}catch (Exception con){
            				log.warning("Error with connection: " + con);
            				connection=null;
            			}
                			
                			measureName = "AdminConnection|"+environment;
                			Float f = (float) 0;
                			testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
                			log.warning("Not able to collect the server name. We will not complete this monitoring cycle. Will try again on next cycle.");
                			return new Status(Status.StatusCode.PartialSuccess, "Not able to collect the server name. We will not complete this monitoring cycle. Will try again on next cycle. " + host + ":" + port + ":" + username);
                		}
            	}
            	
            	
                if (!s.isEmpty()) {
                	try {
                		server = (ObjectName)s.iterator().next();
                		Object nodeName = connection.invoke((ObjectName)server,
                          	"getName", null, null);
                		server_name =nodeName.toString();
                	} catch (Exception e){
                    	if(connection != null){
                    		try{
                				connection.isAlive(); //Test the connection..
                			}catch (Exception con){
                				log.warning("Error with connection: " + con);
                				connection=null;
                			}
                    		connection = wc.initConnection(host, port, username, password, security, keyStore, keyPassword, trustStore, trustPassword);
                    		if(connection == null){
                    			measureName = "AdminConnection|"+environment;
                    			Float f = (float) 0;
                    			testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
                    			log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBSPHERE_CONNECTION_GROUP + " " +  WEBSPHERE_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
                    			return new Status(Status.StatusCode.PartialSuccess, "Connection lost. Unable to connect to ServerRuntime " + host + ":" + port + ":" + username); //stops the execute method...
                    		} 
                    	} else {
                    		try{
                				connection.isAlive(); //Test the connection..
                			}catch (Exception con){
                				log.warning("Error with connection: " + con);
                				connection=null;
                			}
                    			
                    			measureName = "AdminConnection|"+environment;
                    			Float f = (float) 0;
                    			testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
                    			log.warning("Not able to collect the server name. We will not complete this monitoring cycle. Will try again on next cycle.");
                    			return new Status(Status.StatusCode.PartialSuccess, "Not able to collect the server name. We will not complete this monitoring cycle. Will try again on next cycle. " + host + ":" + port + ":" + username);
                    		}
                	}
                
                	log.info("Sending Environment: " + environment);

                	Future<ArrayList<ThreadStats>> thd = null;
                	if(threads){
                		log.info("Entering Thread Monitoring ");
                		Threads sumTask = new Threads(connection, env, environment, server_name, threadIgnore);
                		thd = serviceThread.submit(sumTask);
                    	log.info("Thread - Job was submitted");
                	}
                	
                	Future<ArrayList<ServletStats>> fsd = null;
                	if(servlet){ 
                		log.info("Entering Servelet Monitoring ");
                		Servlet sumTask = new Servlet(connection, env, environment,server_name,servletIgnore);
                		fsd = serviceThread.submit(sumTask);
                    	log.info("Servlet - Job was submitted");
                	}
 
                	Future<ArrayList<WLServerStats>> wld = null;
                	if(wlserver){
                		log.info("Entering Server Monitoring ");
                		WLServer sumTask = new WLServer(connection, env, environment,server_name);
                		wld = serviceThread.submit(sumTask);
                    	log.info("Server - Job was submitted");
                	}                	

                	Future<ArrayList<JVMServerStats>> jvmd = null;
                	if(jvm){
                		log.info("Entering JVM Monitoring ");
                		JVM sumTask = new JVM(connection, env, environment,server_name);
                		jvmd = serviceThread.submit(sumTask);
                    	log.info("JVM - Job was submitted");
                	}                	
                	
                	Future<ArrayList<JDBCStats>> dbd = null;
                	if(jdbc){
                		log.info("Entering JDBC Monitoring ");
                		JDBC sumTask = new JDBC(connection, env, environment,server_name,jdbcIgnore);
                		dbd = serviceThread.submit(sumTask);
                    	log.info("JDBC - Job was submitted");
                	}                	

                	Future<ArrayList<DSStats>> dbs = null;
                	if(datasource){ 
                		log.info("Entering DataSource Monitoring ");
                		DataSource sumTask = new DataSource(connection, env, environment,server_name,dsIgnore);
                		dbs = serviceThread.submit(sumTask);
                    	log.info("DataSource - Job was submitted");
                	}                	
                	
                	Future<ArrayList<ApplicationDataStats>> ads = null;
                	if(sessionData){
                		log.info("In Application Data collection");
                		ApplicationData sumTask = new ApplicationData(connection, env, environment, dataMonitor,server_name);
                		ads = serviceThread.submit(sumTask);
                		log.info("Session Data - Job was submitted");
                	}                	

                	Future<ArrayList<TransactionStats>> trd = null;
                	if(transaction){
                		log.info("Entering Transaction Monitoring ");
                		Transaction sumTask = new Transaction(connection, env, environment,server_name);
                		trd = serviceThread.submit(sumTask);
                		log.info("Transaction - Job was submitted");
                	}                  

              	// populate gathered dynamic measures in PWH
                	
            		if(wlserver){
            			try{
            				ArrayList<WLServerStats> results = wld.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("WLServer - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordWLServerMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordWLServerMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("WLServer - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        wld.cancel(true);
                		}
                	}

            		if(jvm){
            			try{
            				ArrayList<JVMServerStats> results = jvmd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("JVM - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordJVMMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordJVMMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("JVM - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        jvmd.cancel(true);
                		}
            		}

            		if(threads){
                		try{
                			ArrayList<ThreadStats> result = thd.get(timeOut, TimeUnit.MILLISECONDS);
                			log.info("Threads - Result size: " + result.size());
                			if (result.size() > 0) {
                            	com.dynatrace.diagnostics.plugins.jmx.record.RecordThreadMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordThreadMeasure();
                            	DTAS.WriteToDT(env, result);
                			}
                		}catch(TimeoutException e){
                	        log.warning("Threads - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        thd.cancel(true);
                		}

                	}
            		if(transaction){
            			try{
            				ArrayList<TransactionStats> results = trd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Transaction - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordTransactionMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordTransactionMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("Transaction - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        trd.cancel(true);
                		}
                	}

            		if(jdbc){
            			try{
            				ArrayList<JDBCStats> results = dbd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("JDBC - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordJDBCMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordJDBCMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("JDBC - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        dbd.cancel(true);
                		}
                	}
            		
            		
            		if(datasource){
            			try{
            				ArrayList<DSStats> results = dbs.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("JDBC - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordDSMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordDSMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("DataSource - No response after " + timeOut + " milliseconds. Canceling the task.");
            				dbs.cancel(true);
                		}
                	}
            		

            		if(sessionData){
            			try{
            				ArrayList<ApplicationDataStats> result = ads.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Application Data - Result size: " + result.size());
            				if (result.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordApplicationDataMeasure DTAD = new com.dynatrace.diagnostics.plugins.jmx.record.RecordApplicationDataMeasure();
            					DTAD.WriteToDT(env, result);
            					log.info("AppData was sent to PWH");
            				}
            			}catch(TimeoutException e){
            				log.warning("Application Data - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        ads.cancel(true);
                		}
            		}

            		if(servlet){
            			try{
            				ArrayList<ServletStats> results = fsd.get(timeOut, TimeUnit.MILLISECONDS);
            				log.info("Servlet - Result size: " + results.size());
            				if (results.size() > 0) {
            					com.dynatrace.diagnostics.plugins.jmx.record.RecordServletMeasure DTAS = new com.dynatrace.diagnostics.plugins.jmx.record.RecordServletMeasure();
            					DTAS.WriteToDT(env, results);
            				}
            			}catch(TimeoutException e){
            				log.warning("Servlet - No response after " + timeOut + " milliseconds. Canceling the task.");
                	        fsd.cancel(true);
                		}
            		}
            		
            		
            		 }
            } catch (Exception e) {
            	log.info("Exception in Monitoring " + environment + ": " + e);
            	if(connection != null){
            		try{
        				connection.isAlive(); //Test the connection..
        			}catch (Exception con){
        				log.warning("Error with connection: " + con);
        				connection=null;
        			}
            		
            		connection = wc.initConnection(host, port, username, password, security, keyStore, keyPassword, trustStore, trustPassword);
            		if(connection == null){
            			measureName = "AdminConnection|"+environment;
            			Float f = (float) 0;
            			testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
            			log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBSPHERE_CONNECTION_GROUP + " " +  WEBSPHERE_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
            			return new Status(Status.StatusCode.PartialSuccess, "Connection lost. Unable to connect to ServerRuntime: " + host + ":" + port + ":" + username + e);
            		} else{
            			return new Status(Status.StatusCode.PartialSuccess, "Connection reestablished: " + host + ":" + port + ":" + username);
            		}
            	}
            }  

            //How long it took to monitor the domain.
        long webPerfEndTime = DateTime.getDateTime();
		long WebtotalPerfTime = (webPerfEndTime - webMonitorStartTime); 
		measureName = "MonitorDomainTime|"+environment;
		testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_MONITORTIME_METRIC, measureName, (double)WebtotalPerfTime);
		
	    measureName = "AdminConnection|"+environment;
		Float f = (float) 1;
		testing.populateDynamicMeasure(env, WEBSPHERE_CONNECTION_GROUP, WEBSPHERE_CONNECTION_METRIC, measureName, (double)f);
		log.info("Info sent to populateDynamicMeasure = " + " " + env + " " +   WEBSPHERE_CONNECTION_GROUP + " " +  WEBSPHERE_CONNECTION_METRIC + " " +   measureName + " " +  (double)f);
		return new Status(Status.StatusCode.Success);
	}

	@Override
	 public void teardown(MonitorEnvironment env) throws Exception {
		if (log.isLoggable(Level.INFO)) {
			log.info("Entering teardown method");
		}
		
		//On plugin failure we want to ensure the old threads are shutdown.
		try {
			serviceThread.shutdown(); // Disable new tasks from being submitted
			   try {
			     // Wait a while for existing tasks to terminate
			     if (!serviceThread.awaitTermination(5, TimeUnit.SECONDS)) {
			    	 serviceThread.shutdownNow(); // Cancel currently executing tasks
			       // Wait a while for tasks to respond to being cancelled
			       if (!serviceThread.awaitTermination(30, TimeUnit.SECONDS))
			    	   log.warning("Pool did not terminate");
			     }
			   } catch (InterruptedException ie) {
			     // (Re-)Cancel if current thread also interrupted
				   serviceThread.shutdownNow();
			     // Preserve interrupt status
			     Thread.currentThread().interrupt();
			   }

		} catch (Exception e){
			log.warning("Exception with shutting down threads: " + e);
		}
	}
}