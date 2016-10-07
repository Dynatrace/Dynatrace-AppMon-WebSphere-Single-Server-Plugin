package com.dynatrace.diagnostics.plugins.jmx.record;

import java.util.logging.Logger;

import com.dynatrace.diagnostics.plugins.jmx.variableholder.TransactionStats;
import com.dynatrace.diagnostics.plugins.jmx.WebSphereConstants;
import com.dynatrace.diagnostics.pdk.MonitorEnvironment;

import java.util.ArrayList;

public class RecordTransactionMeasure implements WebSphereConstants {

	private static final Logger log = Logger.getLogger(RecordTransactionMeasure.class.getName());
	
	public void WriteToDT (MonitorEnvironment envs, ArrayList<TransactionStats> ad)  throws Exception {
		
        try {
        	DynamicMeasure testing = new DynamicMeasure();
            ArrayList<TransactionStats> list = ad;
            for (TransactionStats fromStatic : list) {
                String WLServerName = fromStatic.getserverName();
                String Enviro = fromStatic.getEnviro();
                Double ActiveTransactionsCount = Double.parseDouble(fromStatic.getActiveTransactionsCount());
                Double TransactionRolledBackCount = Double.parseDouble(fromStatic.getTransactionRolledBackCount());
                Double TransactionCommittedCount = Double.parseDouble(fromStatic.getTransactionCommittedCount());
                
                String measureName = WLServerName+"|Transaction|ActiveTransactionsTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_TRANSACTION_GROUP, WEBSPHERE_ACTIVETRANS_ACTION_METRIC, measureName, ActiveTransactionsCount);
                measureName = WLServerName+"|Transaction|TransactionAbandonedTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_TRANSACTION_GROUP, WEBSPHERE_TRANSACTION_ROLLEDBACK_METRIC, measureName, TransactionRolledBackCount);
                measureName = WLServerName+"|Transaction|TransactionCommittedTotal|"+Enviro;
                testing.populateDynamicMeasure(envs, WEBSPHERE_TRANSACTION_GROUP, WEBSPHERE_TRANSACTION_COMMITTED_METRIC, measureName, TransactionCommittedCount);
            }
        } catch (Exception e) {
            log.warning("Record Transaction data: " + e);
        } 
	}
}
