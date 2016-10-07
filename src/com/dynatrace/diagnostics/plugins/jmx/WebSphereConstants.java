package com.dynatrace.diagnostics.plugins.jmx;

public interface WebSphereConstants {
	// Plugin's configuration parameter's constants
	public static final String CONFIG_DT_SERVER = "dtServer";
	public static final String CONFIG_JMX_PORT = "jmxPort";
	public static final String CONFIG_WEBSPHERE_PASSWORD = "JmxPassword";
	public static final String CONFIG_WEBSPHERE_USERNAME = "JmxUserName";
	public static final String CONFIG_WEBSPHERE_ENVIRONMENT = "Environment";
	public static final String CONFIG_WEBSPHERE_THREADS = "Threads";
	public static final String CONFIG_WEBSPHERE_JVM = "JVM";
	public static final String CONFIG_WEBSPHERE_WLSERVER = "WLServer";
	public static final String CONFIG_WEBSPHERE_DATASOURCE = "DataSource";
	public static final String CONFIG_WEBSPHERE_TRANSACTION = "Transaction";
	public static final String CONFIG_WEBSPHERE_SERVLET = "Servlet";
	public static final String CONFIG_WEBSPHERE_JMS = "JMS";
	public static final String CONFIG_WEBSPHERE_APPLICATIONDATA = "ApplicationData";
	public static final String CONFIG_WEBSPHERE_EJBDATA = "EJBData";
	public static final String CONFIG_WEBSPHERE_JDBC = "JDBC";
	public static final String CONFIG_THREADCOUNT_MONITOR = "threadCount";
	public static final String CONFIG_WEBSPHERE_APPDATAMONITOR = "AppDataMonitor";
	public static final String CONFIG_WEBSPHERE_IGNORETHREADPOOL = "ignoreThread";
	public static final String CONFIG_WEBSPHERE_IGNORESERVLET = "ignoreServlet";
	public static final String CONFIG_WEBSPHERE_IGNOREJDBC = "ignoreJDBC";
	public static final String CONFIG_WEBSPHERE_IGNOREDS = "ignoreDS";
	public static final String CONFIG_TIMEOUT_MONITOR = "metricTimeOut";
	
	public static final String CONFIG_SECURITY_MONITOR = "securityEnabled";
	
	public static final String CONFIG_KEYSTORE_MONITOR = "keyStoreFile";
	public static final String CONFIG_KEYPASSWORD_MONITOR = "keyPassword";
	public static final String CONFIG_TRUSTSTORE_MONITOR = "trustStoreFile";
	public static final String CONFIG_TRUSTPASSWORD_MONITOR = "trustPassword";
	
	
	
	
	// dynaTrace based metric groups
	public static final String JMX_MEASURE_SPLIT_NAME = "Measure Name";

	// WebSphere Connection Group
	public static final String WEBSPHERE_CONNECTION_GROUP = "WebSphere Connection Group";
	public static final String WEBSPHERE_CONNECTION_METRIC = "Connection Status";
	public static final String WEBSPHERE_MONITORTIME_METRIC = "WebSphere Monitor Time";
	
	// WebSphere Thread Group
	public static final String WEBSPHERE_THREAD_GROUP = "WebSphere Thread Group";
	public static final String WEBSPHERE_THREAD_ACTIVE_COUNT_METRIC = "Thread Active Count";
	public static final String WEBSPHERE_THREAD_ACTIVE_COUNT_HIGH_METRIC = "Thread Active Count High";
	public static final String WEBSPHERE_THREAD_ACTIVE_COUNT_lOW_METRIC = "Thread Active Count Low";
	public static final String WEBSPHERE_THREAD_POOL_SIZE_CURRENT_METRIC = "Thread Pool Size Current";
	public static final String WEBSPHERE_THREAD_POOL_SIZE_HIGH_METRIC = "Thread Pool Size High";
	public static final String WEBSPHERE_THREAD_POOL_SIZE_LOW_METRIC = "Thread Pool Size Low";
	public static final String WEBSPHERE_THREAD_POOL_SIZE_LOWER_METRIC = "Thread Pool Size Lower";
	public static final String WEBSPHERE_THREAD_POOL_SIZE_UPPER_METRIC = "Thread Pool Size Upper";
	
	// WebSphere Servlet Group
	public static final String WEBSPHERE_SERVLET_GROUP = "WebSphere Servlet Group";
	public static final String WEBSPHERE_AVERAGE_STATISTIC_AVERAGE_METRIC = "Average Statistic Avg Count";
	public static final String WEBSPHERE_AVERAGE_STATISTIC_MAX_METRIC = "Average Statistic Avg Max";
	public static final String WEBSPHERE_AVERAGE_STATISTIC_MIN_METRIC = "Average Statistic Avg Min";
	public static final String WEBSPHERE_AVERAGE_STATISTIC_AVG_METRIC = "Average Statistic Avg Statistic";
	public static final String WEBSPHERE_AVERAGE_STATISTIC_SUMSQ_METRIC = "Average Statistic Avg Sum Sq";
	public static final String WEBSPHERE_AVERAGE_STATISTIC_TOTAL_METRIC = "Average Statistic Avg Total";
	public static final String WEBSPHERE_TIME_STATISTIC_COUNT_METRIC = "Time Statistic Avg Count";
	public static final String WEBSPHERE_TIME_STATISTIC_MAX_METRIC = "Time Statistic Avg Max";
	public static final String WEBSPHERE_TIME_STATISTIC_MIN_METRIC = "Time Statistic Avg Min";
	public static final String WEBSPHERE_TIME_STATISTIC_AVG_METRIC = "Time Statistic Avg Statistic";
	public static final String WEBSPHERE_TIME_STATISTIC_SUMSQ_METRIC = "Time Statistic Avg Sum Sq";
	public static final String WEBSPHERE_TIME_STATISTIC_TOTAL_METRIC = "Time Statistic Avg Total";
	public static final String WEBSPHERE_TOTAL_REQUEST_COUNT_METRIC = "Total Request Count";
	
	// WebSphere Server Group
	public static final String WEBSPHERE_SERVER_GROUP = "WebSphere Server Group";	
	public static final String WEBSPHERE_NODE_STATUS_METRIC = "Node Status";
	
	// WebSphere JVM Group
	public static final String WEBSPHERE_JVM_GROUP = "WebSphere JVM Group";	
	public static final String WEBSPHERE_JVM_CPU_USAGE_METRIC = "JVM CPU Usage";
	public static final String WEBSPHERE_JVM_CURRENT_METRIC = "JVM Current";
	public static final String WEBSPHERE_JVM_HIGH_METRIC = "JVM High";
	public static final String WEBSPHERE_JVM_INTEGRAL_METRIC = "JVM Integral";
	public static final String WEBSPHERE_JVM_LOW_METRIC = "JVM Low";
	public static final String WEBSPHERE_JVM_LOWER_BOUND_METRIC = "JVM Lower Bound";
	public static final String WEBSPHERE_JVM_UPPER_BOUND_METRIC = "JVM Upper Bound";
	public static final String WEBSPHERE_JVM_UPTIME_METRIC = "JVM Uptime";
	public static final String WEBSPHERE_JVM_USED_MEMORY_METRIC = "JVM Used Memory";
	
	// WebSphere JDBC Group
	public static final String WEBSPHERE_JDBC_GROUP = "WebSphere JDBC Group";
	public static final String WEBSPHERE_CREATE_COUNT_METRIC = "JDBC Create Count";
	public static final String WEBSPHERE_CLOSE_COUNT_METRIC = "JDBC Close Count";
	public static final String WEBSPHERE_FREE_POOL_CURRENT_METRIC = "Free Pool Current";
	public static final String WEBSPHERE_FREE_POOL_HIGH_METRIC = "Free Pool High";
	public static final String WEBSPHERE_FREE_POOL_INTEGRAL_METRIC = "Free Pool Integral";
	public static final String WEBSPHERE_FREE_POOL_LOWER_METRIC = "Free Pool Lower";
	public static final String WEBSPHERE_FREE_POOL_LOW_METRIC = "Free Pool Size Low";
	public static final String WEBSPHERE_FREE_POOL_UPPER_METRIC = "Free Pool Upper";
	public static final String WEBSPHERE_PERCENT_USED_CURRENT_METRIC = "Percent Used Current";
	public static final String WEBSPHERE_PERCENT_USED_HIGH_METRIC = "Percent Used High";
	public static final String WEBSPHERE_PERCENT_USED_INTEGRAL_METRIC = "Percent Used Integral";
	public static final String WEBSPHERE_PERCENT_USED_LOW_METRIC = "Percent Used Low";
	public static final String WEBSPHERE_POOL_SIZE_CURRENT_METRIC = "Pool Size Current";
	public static final String WEBSPHERE_POOL_SIZE_HIGH_METRIC = "Pool Size High";
	public static final String WEBSPHERE_POOL_SIZE_INTEGRAL_METRIC = "Pool Size Integral";
	public static final String WEBSPHERE_POOL_SIZE_LOW_METRIC = "Pool Size Low";
	public static final String WEBSPHERE_POOL_SIZE_LOWER_METRIC = "Pool Size Lower";
	public static final String WEBSPHERE_POOL_SIZE_UPPER_METRIC = "Pool Size Upper";
	public static final String WEBSPHERE_USE_TIME_METRIC = "Use Time";
	public static final String WEBSPHERE_USE_TIME_AVERAGE_METRIC = "Use Time Average";
	public static final String WEBSPHERE_USE_TIME_AVERAGE_COUNT_METRIC = "Use Time Average Count";
	public static final String WEBSPHERE_USE_TIME_AVERAGE_MAX_METRIC = "Use Time Average Max";
	public static final String WEBSPHERE_USE_TIME_AVERAGE_MIN_METRIC = "Use Time Average Min";
	public static final String WEBSPHERE_USE_TIME_AVERAGE_SUMSQ_METRIC = "Use Time Average SumSq";
	public static final String WEBSPHERE_USE_TIME_AVERAGE_TOTAL_METRIC = "Use Time Average Total";
	public static final String WEBSPHERE_USE_TIME_COUNT_METRIC = "Use Time Count";
	public static final String WEBSPHERE_USE_TIME_MAX_METRIC = "Use Time Max";
	public static final String WEBSPHERE_USE_TIME_MIN_METRIC = "Use Time Min";
	public static final String WEBSPHERE_USE_TIME_SUMSQ_METRIC = "Use Time SumSq";
	public static final String WEBSPHERE_USE_TIME_TOTAL_METRIC = "Use Time Total";
	public static final String WEBSPHERE_WAITING_THREAD_CURRENT_METRIC = "Waiting Thread Current";
	public static final String WEBSPHERE_WAITING_THREAD_HIGH_METRIC = "Waiting Thread High";
	public static final String WEBSPHERE_WAITING_THREAD_INTEGRAL_METRIC = "Waiting Thread Integral";
	public static final String WEBSPHERE_WAITING_THREAD_LOW_METRIC = "Waiting Thread Low";
	public static final String WEBSPHERE_WAIT_TIME_METRIC = "Wait Time";
	public static final String WEBSPHERE_WAIT_TIME_AVERAGE_METRIC = "Wait Time Average";
	public static final String WEBSPHERE_WAIT_TIME_AVERAGE_COUNT_METRIC = "Wait Time Average Count";
	public static final String WEBSPHERE_WAIT_TIME_AVERAGE_MAX_METRIC = "Wait Time Average Max";
	public static final String WEBSPHERE_WAIT_TIME_AVERAGE_MIN_METRIC = "Wait Time Average Min";
	public static final String WEBSPHERE_WAIT_TIME_AVERAGE_SUMSQ_METRIC = "Wait Time Average SumSq";
	public static final String WEBSPHERE_WAIT_TIME_AVERAGE_TOTAL_METRIC = "Wait Time Average Total";
	public static final String WEBSPHERE_WAIT_TIME_COUNT_METRIC = "Wait Time Count";
	public static final String WEBSPHERE_WAIT_TIME_MAX_METRIC = "Wait Time Max";
	public static final String WEBSPHERE_WAIT_TIME_MIN_METRIC = "Wait Time Min";
	public static final String WEBSPHERE_WAIT_TIME_SUMSQ_METRIC = "Wait Time SumSq";
	public static final String WEBSPHERE_WAIT_TIME_TOTAL_METRIC = "Wait Time Total";
	
	// WebSphere DataSource Group
	public static final String WEBSPHERE_DATASOURCE_GROUP = "WebSphere DataSource Group";
	public static final String WEBSPHERE_STATUS_METRIC = "DataSource Status";	
	
	// WebSphere Application Data Group
	public static final String WEBSPHERE_APPLICATIONDATA_GROUP = "WebSphere Application Data Group";	
	public static final String WEBSPHERE_HIGH_WATER_MARK_METRIC = "High Water Mark";
	public static final String WEBSPHERE_INTEGRAL_METRIC = "Integral";
	public static final String WEBSPHERE_LOW_WATER_MARK_METRIC = "Low Water Mark";	
	public static final String WEBSPHERE_SESSIONS_CURRENT_METRIC = "Sessions Current";	
	
	// WebSphere Transaction Group
	public static final String WEBSPHERE_TRANSACTION_GROUP = "WebSphere Transaction Group";	
	public static final String WEBSPHERE_ACTIVETRANS_ACTION_METRIC = "Active Transactions Count";
	public static final String WEBSPHERE_TRANSACTION_ROLLEDBACK_METRIC = "Transactions RolledBack Count";
	public static final String WEBSPHERE_TRANSACTION_COMMITTED_METRIC = "Transactions Committed Count";
	

}
