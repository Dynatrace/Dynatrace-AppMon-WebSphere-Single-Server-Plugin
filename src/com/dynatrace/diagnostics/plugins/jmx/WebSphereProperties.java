package com.dynatrace.diagnostics.plugins.jmx;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXServiceURL;

public class WebSphereProperties {
	private String environment;
	private String dtServer;
	private String jmxPort;
	private String JmxUserName;
	private String JmxPassword;
	private String[] metrics;
	private JMXServiceURL jmxServiceUrl;
	private JMXConnector jmxConnector;
	private MBeanServerConnection mbsConnection;
	private String objectNamePrefix;
	private Boolean threads;
	private Boolean appData;
	private Boolean jms;
	private Boolean saf;
	private Boolean jvm;
	private Boolean transaction;
	private Boolean Servlet;
	private Boolean datasource;
	private Boolean wlServer;
	private Boolean jdbc;
	private String appDataMonitor;
	private String threadCount;
	private String timeOut;
	private String ignoreThreads;
	private String ignoreServlet;
	private String ignoreJDBC;
	private String ignoreDS;
	private Boolean security;
	
	private String keyStoreFile;
	private String keyPassword;
	private String trustStoreFile;
	private String trustPassword;
	
	
	
	public String getKeyStoreFile() {
		return keyStoreFile;
	}
	public void setKeyStoreFile(String keyStoreFile) {
		this.keyStoreFile = keyStoreFile;
	}
	public String getKeyPassword() {
		return keyPassword;
	}
	public void setKeyPassword(String keyPassword) {
		this.keyPassword = keyPassword;
	}
	public String geTtrustStoreFile() {
		return trustStoreFile;
	}
	public void setTrustStoreFile(String trustStoreFile) {
		this.trustStoreFile = trustStoreFile;
	}
	public String getTrustPassword() {
		return trustPassword;
	}
	public void setTrustPassword(String trustPassword) {
		this.trustPassword = trustPassword;
	}
	
	
	
	
	

	public Boolean getSecurity() {
		return security;
	}
	public void setSecurity(Boolean security) {
		this.security = security;
	}
	public String getIgnoreThreads() {
		return ignoreThreads;
	}
	public void setIgnoreThreads(String ignoreThreads) {
		this.ignoreThreads = ignoreThreads;
	}
	public String getIgnoreServlet() {
		return ignoreServlet;
	}
	public void setIgnoreServlet(String ignoreServlet) {
		this.ignoreServlet = ignoreServlet;
	}
	public String getIgnoreJDBC() {
		return ignoreJDBC;
	}
	public void setIgnoreJDBC(String ignoreJDBC) {
		this.ignoreJDBC = ignoreJDBC;
	}
	public String getIgnoreDS() {
		return ignoreDS;
	}
	public void setIgnoreDS(String ignoreDS) {
		this.ignoreDS = ignoreDS;
	}
	public String getDtServer() {
		return dtServer;
	}
	public void setDtServer(String dtServer) {
		this.dtServer = dtServer;
	}
	public String getJmxPort() {
		return jmxPort;
	}
	public void setJmxPort(String jmxPort) {
		this.jmxPort = jmxPort;
	}
	public String getthreadCount() {
		return threadCount;
	}
	public void setthreadCount(String threadCount) {
		this.threadCount = threadCount;
	}
	public String gettimeOut() {
		return timeOut;
	}
	public void settimeOut(String timeOut) {
		this.timeOut = timeOut;
	}
	public String[] getMetrics() {
		return metrics;
	}
	public void setMetrics(String[] metrics) {
		this.metrics = metrics;
	}
	public JMXServiceURL getJmxServiceUrl() {
		return jmxServiceUrl;
	}
	public void setJmxServiceUrl(JMXServiceURL jmxServiceUrl) {
		this.jmxServiceUrl = jmxServiceUrl;
	}
	public JMXConnector getJmxConnector() {
		return jmxConnector;
	}
	public void setJmxConnector(JMXConnector jmxConnector) {
		this.jmxConnector = jmxConnector;
	}
	public MBeanServerConnection getMbsConnection() {
		return mbsConnection;
	}
	public void setMbsConnection(MBeanServerConnection mbsConnection) {
		this.mbsConnection = mbsConnection;
	}
	public String getObjectNamePrefix() {
		return objectNamePrefix;
	}
	public void setObjectNamePrefix(String objectNamePrefix) {
		this.objectNamePrefix = objectNamePrefix;
	}
	public String getJmxUserName() {		
		return JmxUserName;
	}
	public void setJmxUserName(String JmxUserName) {
		this.JmxUserName = JmxUserName;
	}
	public String getJmxPassword() {		
		return JmxPassword;
	}
	public void setJmxPassword(String JmxPassword) {
		this.JmxPassword = JmxPassword;
	}
	public String getEnvironment() {
		return environment;
	}
	public void setEnvironment(String Environment) {
		this.environment = Environment;
	}
	public Boolean getThreads() {
		return threads;
	}
	public void setThreads(Boolean Threads) {
		this.threads = Threads;
	}
	public Boolean getJVM() {
		return jvm;
	}
	public void setJVM(Boolean JVM) {
		this.jvm = JVM;
	}
	public Boolean getWLServer() {
		return wlServer;
	}
	public void setWLServer(Boolean WLServer) {
		this.wlServer = WLServer;
	}
	public Boolean getdatasource() {
		return datasource;
	}
	public void setdatasource(Boolean datasource) {
		this.datasource = datasource;
	}
	public Boolean getTransaction() {
		return transaction;
	}
	public void setTransaction(Boolean Transaction) {
		this.transaction = Transaction;
	}
	public Boolean getServlet() {
		return Servlet;
	}
	public void setServlet(Boolean Servlet) {
		this.Servlet = Servlet;
	}
	public Boolean getJMS() {
		return jms;
	}
	public void setJMS(Boolean JMS) {
		this.jms = JMS;
	}
	public Boolean getSAF() {
		return saf;
	}
	public void setSAF(Boolean SAF) {
		this.saf = SAF;
	}
	public Boolean getAppData() {
		return appData;
	}
	public void setAppData(Boolean AppData) {
		this.appData = AppData;
	}
	public Boolean getJDBC() {
		return jdbc;
	}
	public void setJDBC(Boolean JDBC) {
		this.jdbc = JDBC;
	}
	public String getAppDataMonitor() {
		return appDataMonitor;
	}
	public void setAppDataMonitor(String AppDataMonitor) {
		this.appDataMonitor = AppDataMonitor;
	}
}
