package com.dynatrace.diagnostics.plugins.jmx.connection;

import java.io.IOException;
import java.net.MalformedURLException;

import java.util.Properties;
import com.ibm.websphere.management.AdminClient; 
import com.ibm.websphere.management.AdminClientFactory; 
import com.ibm.websphere.management.exception.ConnectorException;
import java.util.logging.Logger;


public class WebSphereConnection {

	private static final Logger log = Logger.getLogger(WebSphereConnection.class.getName());
	private String keyStoreFile; 
	private String keyPassword; 
	private String trustStoreFile;
	private String trustPassword;
    private AdminClient adminClient;
    
    public AdminClient initConnection(String hostname, String portString, String username, String password, String security, String keyStore, 
    		String keyPass, String trustStore, String trustPass) throws IOException, MalformedURLException {
    	
    	Properties clientProps = new Properties();
    	
    	keyStoreFile=keyStore;
    	keyPassword=keyPass;
    	trustStoreFile=trustStore;
    	trustPassword=trustPass;
    	
        clientProps.setProperty( AdminClient.CONNECTOR_TYPE, AdminClient.CONNECTOR_TYPE_SOAP); 
        clientProps.setProperty(AdminClient.CONNECTOR_HOST, hostname); 
        clientProps.setProperty(AdminClient.CONNECTOR_PORT, portString);
        
        if(security.equalsIgnoreCase("true")) {
        	clientProps.setProperty(AdminClient.CONNECTOR_SECURITY_ENABLED, security); 
        	clientProps.setProperty(AdminClient.USERNAME, username); 
        	clientProps.setProperty(AdminClient.PASSWORD, password); 
        	clientProps.setProperty("com.ibm.ssl.keyStoreFileBased", "true"); 
        	clientProps.setProperty("com.ibm.ssl.trustStoreFileBased", "true"); 
        	clientProps.setProperty("com.ibm.ssl.keyStore", keyStoreFile); 
        	clientProps.setProperty("javax.net.ssl.keyStore", keyStoreFile); 
        	clientProps.setProperty("com.ibm.ssl.keyStorePassword", keyPassword); 
        	clientProps.setProperty("javax.net.ssl.keyStorePassword", keyPassword);    
 
    
        	if (keyStoreFile.endsWith(".p12")){
        		clientProps.setProperty("com.ibm.ssl.keyStoreType", "PKCS12"); 
        		clientProps.setProperty("javax.net.ssl.keyStoreType", "PKCS12"); 
        	} else { 
        		clientProps.setProperty("com.ibm.ssl.keyStoreType", "JKS");
        		clientProps.setProperty("javax.net.ssl.keyStoreType", "JKS"); 
        	} 
        	clientProps.setProperty("com.ibm.ssl.trustStore", trustStoreFile);
        	clientProps.setProperty("javax.net.ssl.trustStore", trustStoreFile); 
        	clientProps.setProperty("com.ibm.ssl.trustStorePassword", trustPassword); 
        	clientProps.setProperty("javax.net.ssl.trustStorePassword", trustPassword); 
        	if (trustStoreFile.endsWith(".p12")){
        		clientProps.setProperty("com.ibm.ssl.trustStoreType", "PKCS12"); 
        		clientProps.setProperty("javax.net.ssl.trustStoreType", "PKCS12"); 
        	} else { 
        		clientProps.setProperty("com.ibm.ssl.trustStoreType", "JKS");
        		clientProps.setProperty("javax.net.ssl.trustStoreType", "JKS"); 
        	}   
        }
	
	
        try { 
        	adminClient = AdminClientFactory.createAdminClient(clientProps);
        } catch (ConnectorException e) { 
        	log.warning("Exception creating Admin Client Connection: " + e); 
        	return null;
        } 
	log.info("Connected to Application Server");    	
    return 	adminClient;
    }
}